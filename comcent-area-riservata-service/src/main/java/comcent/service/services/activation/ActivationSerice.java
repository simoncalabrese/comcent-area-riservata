package comcent.service.services.activation;

import comcent.common.builders.QueryParamsBuilder;
import comcent.service.dbmappings.HierarchyMappings;
import comcent.service.dbmappings.UserMapping;
import comcent.service.dbmappings.functions.ConvertionFunction;
import comcent.service.dto.activation.ActivationDTO;
import comcent.service.dto.activation.WrapperUserActivations;
import comcent.service.dto.base.ConcreteDTO;
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
        final Function<Pair<UserDTO, List<ActivationDTO>>, WrapperUserActivations> funcFinal = e -> {
            final WrapperUserActivations wrapperUserActivations = new WrapperUserActivations();
            wrapperUserActivations.setUser(e.getKey());
            wrapperUserActivations.setActivations(e.getValue()
                    .stream()
                    .peek(pair -> pair.setUserInsertDetail(Optional.ofNullable(pair.getUserInsert())
                            .map(u -> plafontService.getUser(u))
                            .orElse(null)))
                    .collect(Collectors.toList()));
            return wrapperUserActivations;
        };

        final Function<Pair<UserDTO, List<Pair<UserDTO, List<ActivationDTO>>>>, WrapperUserActivations> funcIntermediate = e -> {
            final WrapperUserActivations wrapperUserActivations = new WrapperUserActivations();
            wrapperUserActivations.setUser(e.getKey());
            wrapperUserActivations.setWrapper(e.getValue().stream().map(funcFinal.andThen(elem -> {
                elem.setPlafont(convertObjectCloned(getPlafontDTO, s -> {
                    try {
                        s.setUserId(elem.getUser().getId());
                        return plafontService.getPlafont(s);
                    } catch (BaseException e1) {
                        return null;
                    }
                }));
                return elem;
            })).collect(Collectors.toList()));
            return wrapperUserActivations;
        };
        final WrapperUserActivations w = new WrapperUserActivations();
        //se abbiamo uno user di bottom, la nostra hierarchy sarà vuota in quando non c'è nessuno al di sotto e quindi
        //possiamo eeguire la query delle attivazioni altrimenti...
        if (CollectionUtils.isEmpty(hierarchyMappings)) {
            final Map<Integer, List<Pair<UserDTO, List<ActivationDTO>>>> mapRet = Stream.of(getPlafontDTO).collect(Collectors.groupingBy(GetPlafontDTO::getUserId,
                    Collectors.mapping(e -> {
                        try {
                            return Pair.of(plafontService.getUser(e.getUserId()), getActivation(e));
                        } catch (BaseException ex) {
                            return null;
                        }
                    }, Collectors.toList())));
            final List<WrapperUserActivations> collect = mapRet.entrySet()
                    .stream()
                    .map(e -> {
                        final WrapperUserActivations wrapperUserActivations = new WrapperUserActivations();
                        wrapperUserActivations.setUser(plafontService.getUser(e.getKey()));
                        wrapperUserActivations.setWrapper(e.getValue().stream().map(funcFinal.andThen(elem -> {
                            convertObjectCloned(getPlafontDTO, s -> {
                                try {
                                    s.setUserId(elem.getUser().getId());
                                    return plafontService.getPlafont(s);
                                } catch (BaseException e1) {
                                    return null;
                                }
                            });
                            return elem;
                        })).collect(Collectors.toList()));
                        return wrapperUserActivations;
                    }).collect(Collectors.toList());
            w.setWrapper(collect);
        } else {
            final Map<Integer, List<Integer>> hierarchiMap = hierarchyMappings
                    .stream()
                    .filter(e -> e.getTop() != null)
                    .map(e -> Pair.of(e.getCenter(),
                            e.getBottom()))
                    .collect(Collectors.groupingBy(Pair::getKey, Collectors.mapping(Pair::getValue, Collectors.toList())));
            final List<WrapperUserActivations> collect = hierarchiMap.entrySet().stream().map(e ->
                    Pair.of(plafontService.getUser(e.getKey()), e.getValue().stream().map(i -> {
                        getPlafontDTO.setUserId(i);
                        try {
                            return Pair.of(plafontService.getUser(i), getActivation(getPlafontDTO));
                        } catch (BaseException e1) {
                            return null;
                        }
                    }).collect(Collectors.toList())))
                    .map(funcIntermediate).collect(Collectors.toList());
            collect.forEach(user ->
                    user.setPlafont(user.getWrapper().stream()
                            .map(WrapperUserActivations::getPlafont)
                            .filter(Objects::nonNull)
                            .flatMap(e -> e.entrySet().stream())
                            .collect(Collectors.groupingBy(Map.Entry::getKey,
                                    Collectors.summingDouble(e -> e.getValue() != null ? Double.valueOf(e.getValue()) : 0D)))
                            .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toString()))));
            w.setWrapper(collect);
        }
        return w;
    }

    private List<ActivationDTO> getActivation(final GetPlafontDTO getPlafontDTO) throws BaseException {
        return doPostCallList(ActivationDTO.class, ApiEnum.GET_ACTIVATIONS, getPlafontDTO);
    }

    public ConcreteDTO delActivation(final Integer id, final Integer amount) throws BaseException {
        return ((Function<Integer, ConcreteDTO>) e -> {
            try {
                return doGetCall(String.class,
                        ApiEnum.DEL_ACTIVATION,
                        QueryParamsBuilder.getBuilder().appendParams("id", id),
                        s -> new ConcreteDTO());
            } catch (BaseException e1) {
                throw new RuntimeException(e1);
            }
        }).andThen(response -> {
            try {
                return plafontService.addPlafont(Suppliers.Utils.toAddPlafonStorno.apply(amount));
            } catch (BaseException e) {
                throw new RuntimeException(e);
            }
        }).apply(null);
    }

}
