package comcent.service.services.plafont;

import comcent.common.builders.QueryParamsBuilder;
import comcent.service.dbmappings.IdMapping;
import comcent.service.dto.plafont.AddPlafontDTO;
import comcent.service.exceptions.BaseException;
import comcent.service.services.AbstractService;
import comcent.service.services.ApiEnum;
import org.springframework.stereotype.Service;

@Service
public class PlafontService extends AbstractService {
    public void addPlafont(final AddPlafontDTO addPlafontDTO) throws BaseException {
        final Integer lastMovementId = getLastMovementId(addPlafontDTO.getUserId());
        addPlafontDTO.setId(lastMovementId);

    }

    private Integer getLastMovementId(final Integer userId) throws BaseException {
        return doGetCall(IdMapping.class, ApiEnum.GET_ID, QueryParamsBuilder.getBuilder().appendParams("userId", userId), IdMapping::getId);
    }
}
