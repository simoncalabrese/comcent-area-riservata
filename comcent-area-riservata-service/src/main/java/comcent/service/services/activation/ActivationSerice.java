package comcent.service.services.activation;

import comcent.common.builders.QueryParamsBuilder;
import comcent.service.dbmappings.HierarchyMappings;
import comcent.service.dbmappings.UserMapping;
import comcent.service.dbmappings.functions.ConvertionFunction;
import comcent.service.dto.activation.ActivationDTO;
import comcent.service.dto.activation.WrapperUserActivations;
import comcent.service.dto.base.ConcreteDTO;
import comcent.service.dto.plafont.AddPlafontDTO;
import comcent.service.dto.plafont.GetPlafontDTO;
import comcent.service.dto.user.UserDTO;
import comcent.service.exceptions.BaseException;
import comcent.service.exceptions.Suppliers;
import comcent.service.services.AbstractService;
import comcent.service.services.ApiEnum;
import comcent.service.services.plafont.PlafontService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ActivationSerice extends AbstractService {

    @Autowired
    private PlafontService plafontService;

    public WrapperUserActivations getActivations(final GetPlafontDTO getPlafontDTO) throws BaseException {
        final String dateStart = Optional.ofNullable(getPlafontDTO.getDateStart()).orElseGet(ConvertionFunction.getFirstOfMonthAsString);
        final String dateEnd = Optional.ofNullable(getPlafontDTO.getDateEnd()).orElseGet(ConvertionFunction.getTodayAsString);
        getPlafontDTO.setDateStart(dateStart);
        getPlafontDTO.setDateEnd(dateEnd);
        final List<HierarchyMappings> hierarchyMappings = plafontService.getUsersDependency(getPlafontDTO.getUserId());
        final Function<WrapperUserActivations, Map<String, String>> concatPlafont = e -> Optional.ofNullable(e.getWrapper())
                .map(wr -> wr.stream()
                        .filter(wrap -> wrap.getPlafont() != null)
                        .flatMap(wrap -> wrap.getPlafont().entrySet().stream())
                        .collect(Collectors.groupingBy(Map.Entry::getKey,
                                Collectors.summingDouble(wrap -> wrap.getValue() != null ? Double.valueOf(wrap.getValue()) : 0D)))
                        .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, set -> set.getValue().toString()))
                ).orElse(null);
        final Function<Integer, WrapperUserActivations> userToWrapper = e -> {
            try {
                final WrapperUserActivations wrapperUserActivations = new WrapperUserActivations();
                final UserDTO user = plafontService.getUser(e);
                getPlafontDTO.setUserId(e);
                wrapperUserActivations.setUser(user);
                wrapperUserActivations.setActivations(getActivation(getPlafontDTO)
                        .stream()
                        .peek(att -> att.setUserInsertDetail(plafontService.getUser(att.getUserInsert())))
                        .collect(Collectors.toList()));
                wrapperUserActivations.setPlafont(plafontService.getPlafont(getPlafontDTO));
                return wrapperUserActivations;
            } catch (BaseException ex) {
                throw new RuntimeException(ex);
            }
        };
        final WrapperUserActivations w = new WrapperUserActivations();
        //se abbiamo uno user di bottom, la nostra hierarchy sarà vuota in quando non c'è nessuno al di sotto e quindi
        //possiamo eeguire la query delle attivazioni altrimenti...
        if (CollectionUtils.isEmpty(hierarchyMappings)) {
            final Supplier<List<WrapperUserActivations>> wSup = () -> {
                final WrapperUserActivations apply = userToWrapper.apply(getPlafontDTO.getUserId());
                apply.setWrapper(Collections.singletonList(cloneObject(apply)));
                return Collections.singletonList(apply);
            };
            w.setWrapper(wSup.get());
        } else {
            final List<WrapperUserActivations> allWrappers = hierarchyMappings
                    .stream()
                    .map(HierarchyMappings::getBottom)
                    .filter(e -> !e.equals(getPlafontDTO.getUserId()))
                    .map(userToWrapper).collect(Collectors.toList());
            final List<WrapperUserActivations> middle = hierarchyMappings.stream().filter(e -> e.getTop() == null).map(HierarchyMappings::getBottom)
                    .flatMap(e -> allWrappers.stream().filter(u -> u.getUser().getId().equals(e)))
                    .collect(Collectors.toList());
            //Prendo quelli che stanno al centro e li metto nel wrapper
            if (middle != null && !middle.isEmpty()) {
                w.setWrapper(middle);
                //tutti quelli che non c'erano sopra;
                final List<WrapperUserActivations> nonMiddle = allWrappers.stream()
                        .filter(e -> !w.getWrapper()
                                .stream()
                                .map(WrapperUserActivations::getUser)
                                .collect(Collectors.toList())
                                .contains(e.getUser()))
                        .collect(Collectors.toList());
                final Map<Integer, List<WrapperUserActivations>> collect1 = hierarchyMappings
                        .stream()
                        .filter(e -> e.getTop() != null)
                        .map(e -> Pair.of(e.getBottom(), e.getCenter()))
                        .collect(Collectors.groupingBy(Pair::getValue, Collectors.mapping(
                                e -> nonMiddle.
                                        stream()
                                        .filter(u -> u.getUser().getId().equals(e.getKey()))
                                        .findFirst()
                                        .orElse(null),
                                Collectors.toList())));
                w.setWrapper(w.getWrapper().stream().peek(e -> {
                    e.setWrapper(collect1.get(e.getUser().getId()));
                    e.setPlafont(concatPlafont.apply(e));
                }).collect(Collectors.toList()));
            } else {
                w.setWrapper(allWrappers);
                w.setPlafont(concatPlafont.apply(w));
            }
        }
        return Optional.ofNullable(w).map(this::countAct).orElse(null);
    }

    private WrapperUserActivations countAct(final WrapperUserActivations wr) {
        if (wr.getWrapper() == null || wr.getWrapper().isEmpty()) {
            wr.setActivationsCount(Optional.ofNullable(wr.getActivations()).map(e -> e.stream().count()).orElse(0L));
            return wr;
        } else {
            wr.setWrapper(wr.getWrapper().stream().map(this::countAct).collect(Collectors.toList()));
            wr.setActivationsCount(wr.getWrapper().stream().mapToLong(WrapperUserActivations::getActivationsCount).sum());
            return wr;
        }
    }

    private List<ActivationDTO> getActivation(final GetPlafontDTO getPlafontDTO) throws BaseException {
        return doPostCallList(ActivationDTO.class, ApiEnum.GET_ACTIVATIONS, getPlafontDTO);
    }

    public ConcreteDTO delActivation(final Integer id, final Double amount, final Integer user, final Integer userInsert) throws BaseException {
        return ((Function<Integer, ConcreteDTO>) e -> {
            try {
                final Boolean res = doGetCall(String.class,
                        ApiEnum.DEL_ACTIVATION,
                        QueryParamsBuilder.getBuilder().appendParams("id", id),
                        s -> s.equals("1"));
                if (res) return new ConcreteDTO();
                else throw new RuntimeException();
            } catch (BaseException e1) {
                throw new RuntimeException(e1);
            }
        }).andThen(response -> {
            try {
                final AddPlafontDTO apply = Suppliers.Utils.toAddPlafonStorno.apply(amount, user);
                apply.setUserInsert(userInsert);
                return plafontService.addPlafont(apply);
            } catch (BaseException e) {
                throw new RuntimeException(e);
            }
        }).apply(null);
    }

}
