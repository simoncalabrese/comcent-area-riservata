package comcent.service.services.login;


import comcent.service.dbmappings.UserMapping;
import comcent.service.dbmappings.functions.ConvertionFunction;
import comcent.service.dto.user.LoginDTO;
import comcent.service.dto.user.UserDTO;
import comcent.service.exceptions.BaseException;
import comcent.service.services.AbstractService;
import comcent.service.services.ApiEnum;
import org.springframework.stereotype.Service;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
@Service
public class LoginService extends AbstractService {
    public UserDTO login(final LoginDTO loginDTO) throws BaseException {
        return doPostCall(UserMapping.class, ApiEnum.LOGIN, loginDTO, ConvertionFunction.toUserDto);
    }
}
