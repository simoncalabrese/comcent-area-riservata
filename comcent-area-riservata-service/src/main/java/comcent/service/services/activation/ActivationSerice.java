package comcent.service.services.activation;

import comcent.common.builders.QueryParamsBuilder;
import comcent.service.dbmappings.HierarchyMappings;
import comcent.service.dbmappings.functions.ConvertionFunction;
import comcent.service.dto.activation.ActivationDTO;
import comcent.service.dto.plafont.GetPlafontDTO;
import comcent.service.exceptions.BaseException;
import comcent.service.services.AbstractService;
import comcent.service.services.ApiEnum;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ActivationSerice extends AbstractService {

    public Map<Integer, List<Pair<Integer, List<ActivationDTO>>>> getActivations(final GetPlafontDTO getPlafontDTO) throws BaseException {
        final String dateStart = Optional.ofNullable(getPlafontDTO.getDateStart()).orElseGet(ConvertionFunction.getFirstOfMonthAsString);
        final String dateEnd = Optional.ofNullable(getPlafontDTO.getDateEnd()).orElseGet(ConvertionFunction.getTodayAsString);
        getPlafontDTO.setDateStart(dateStart);
        getPlafontDTO.setDateEnd(dateEnd);
        final List<HierarchyMappings> hierarchyMappings = doGetCallList(HierarchyMappings.class,
                ApiEnum.GET_USERS,
                QueryParamsBuilder.getBuilder().appendParams("userId", getPlafontDTO.getUserId()));
        //se abbiamo uno user di bottom, la nostra hierarchy sarà vuota in quando non c'è nessuno al di sotto e quindi
        //possiamo eeguire la query delle attivazioni altrimenti...
        if (CollectionUtils.isEmpty(hierarchyMappings)) {
            return Stream.of(getPlafontDTO).collect(Collectors.groupingBy(GetPlafontDTO::getUserId,
                    Collectors.mapping(e -> {
                        try {
                            return Pair.of(e.getUserId(), getActivation(e));
                        } catch (BaseException ex) {
                            return null;
                        }
                    }, Collectors.toList())));
        } else {
            final Map<Integer, List<Integer>> hierarchiMap = hierarchyMappings
                    .stream()
                    .filter(e -> e.getTop() != null)
                    .map(e -> Pair.of(e.getCenter(),
                            e.getBottom()))
                    .collect(Collectors.groupingBy(Pair::getKey, Collectors.mapping(Pair::getValue, Collectors.toList())));
            return hierarchiMap.entrySet().stream().map(e ->
                    Pair.of(e.getKey(), e.getValue().stream().map(i -> {
                        getPlafontDTO.setUserId(i);
                        try {
                            return Pair.of(i, getActivation(getPlafontDTO));
                        } catch (BaseException e1) {
                            return null;
                        }
                    }).collect(Collectors.toList()))).collect(Collectors.toMap(Pair::getKey, Pair::getValue));

        }
    }

    public List<ActivationDTO> getActivation(final GetPlafontDTO getPlafontDTO) throws BaseException {
        return doPostCallList(ActivationDTO.class, ApiEnum.GET_ACTIVATIONS, getPlafontDTO);
    }
}
