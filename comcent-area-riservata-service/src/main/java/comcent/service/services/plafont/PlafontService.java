package comcent.service.services.plafont;

import comcent.common.builders.QueryParamsBuilder;
import comcent.service.dbmappings.IdMapping;
import comcent.service.dbmappings.functions.ConvertionFunction;
import comcent.service.dto.activation.ActivationDTO;
import comcent.service.dto.base.ConcreteDTO;
import comcent.service.dto.plafont.AddPlafontDTO;
import comcent.service.dto.plafont.GetPlafontDTO;
import comcent.service.exceptions.BaseException;
import comcent.service.exceptions.Suppliers;
import comcent.service.services.AbstractService;
import comcent.service.services.ApiEnum;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class PlafontService extends AbstractService {
    public ConcreteDTO addPlafont(final AddPlafontDTO addPlafontDTO) throws BaseException {
        final Integer lastMovementId = getLastMovementId(addPlafontDTO.getUserId(), Boolean.TRUE);
        addPlafontDTO.setDateString(Optional.ofNullable(addPlafontDTO.getDataMov())
                .map(ConvertionFunction.dateToString)
                .orElseGet(() -> ConvertionFunction.dateToString.apply(new Date())));
        addPlafontDTO.setId(lastMovementId);
        final Boolean result = doPostCall(String.class, ApiEnum.INSERT_MOVEMENT, addPlafontDTO,
                e -> e.equals("0"));
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

    public ConcreteDTO insertActivation(final ActivationDTO activationDTO) throws BaseException {
        final Integer lastMovementId = getLastMovementId(activationDTO.getUser(), Boolean.FALSE);
        activationDTO.setId(lastMovementId);
        final Boolean result = doPostCall(String.class, ApiEnum.INSERT_ACTIVATION, activationDTO,
                e -> e.equals("0"));
        if (result) {
            return addPlafont(activationDtoToAddPlafontDto(activationDTO));
        } else {
            throw new BaseException(Suppliers.INSERT_AMOUNT_RESULT);
        }
    }

    private AddPlafontDTO activationDtoToAddPlafontDto(final ActivationDTO activationDTO) throws BaseException {
        final AddPlafontDTO dto = new AddPlafontDTO();
        dto.setUserId(activationDTO.getUser());
        dto.setDataMov(new Date());
        dto.setAmount(0 - activationDTO.getAmntPlafont());
        return dto;
    }

    private Integer getLastMovementId(final Integer userId, final Boolean isTransaction) throws BaseException {
        return Optional.ofNullable(doGetCall(IdMapping.class,
                isTransaction ? ApiEnum.GET_ID : ApiEnum.GET_ID_ACTIVATION,
                QueryParamsBuilder.getBuilder().appendParams("userId", userId), IdMapping::getId))
                .map(e -> ++e).orElse(null);
    }
}
