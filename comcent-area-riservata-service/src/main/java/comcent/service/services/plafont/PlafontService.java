package comcent.service.services.plafont;

import comcent.common.builders.QueryParamsBuilder;
import comcent.service.dbmappings.IdMapping;
import comcent.service.dbmappings.functions.ConvertionFunction;
import comcent.service.dto.base.ConcreteDTO;
import comcent.service.dto.plafont.AddPlafontDTO;
import comcent.service.dto.plafont.GetPlafontDTO;
import comcent.service.exceptions.BaseException;
import comcent.service.exceptions.Suppliers;
import comcent.service.services.AbstractService;
import comcent.service.services.ApiEnum;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlafontService extends AbstractService {
    public ConcreteDTO addPlafont(final AddPlafontDTO addPlafontDTO) throws BaseException {
        final Integer lastMovementId = getLastMovementId(addPlafontDTO.getUserId());
        addPlafontDTO.setDateString(Optional.ofNullable(addPlafontDTO.getDataMov())
                .map(ConvertionFunction.dateToString)
                .orElseGet(() -> ConvertionFunction.dateToString.apply(new Date())));
        addPlafontDTO.setId(lastMovementId);
        final Boolean result = doPostCall(String.class, ApiEnum.INSERT_MOVEMENT, addPlafontDTO,
                e -> e.equals("0"));
        //durante l' inserimento dell' attivazione invece verrà fatto il decremento chiamando la stessa funzione con numero negativo.
        if (result) {
            return new ConcreteDTO();
        } else {
            throw new BaseException(Suppliers.INSERT_AMOUNT_RESULT);
        }
    }

    public Map getPlafont(final GetPlafontDTO getPlafontDTO) throws BaseException {
        final String dateStart = Optional.ofNullable(getPlafontDTO.getDateStart()).orElseGet(ConvertionFunction.getFirstOfMonthAsString);
        final String dateEnd = Optional.ofNullable(getPlafontDTO.getDateEnd()).orElseGet(ConvertionFunction.getTodayAsString);

        getPlafontDTO.setDateStart(dateStart);
        getPlafontDTO.setDateEnd(dateEnd);
        return doPostCallList(Map.class, ApiEnum.GET_PLAFONT, getPlafontDTO).stream().reduce((map, map2) -> {
            map.putAll(map2);
            return map;
        }).orElse(null);
    }

    private Integer getLastMovementId(final Integer userId) throws BaseException {
        return Optional.ofNullable(doGetCall(IdMapping.class, ApiEnum.GET_ID, QueryParamsBuilder.getBuilder().appendParams("userId", userId), IdMapping::getId))
                .map(e -> ++e).orElse(null);
    }
}
