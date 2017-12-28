package comcent.service.services.login;


import comcent.service.dbmappings.UserMapping;
import comcent.service.dbmappings.functions.ConvertionFunction;
import comcent.service.dto.base.ConcreteDTO;
import comcent.service.dto.user.CompleteUserDTO;
import comcent.service.dto.user.LoginDTO;
import comcent.service.dto.user.UserDTO;
import comcent.service.exceptions.BaseException;
import comcent.service.exceptions.Suppliers;
import comcent.service.services.AbstractService;
import comcent.service.services.ApiEnum;
import comcent.service.services.plafont.PlafontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
@Service
public class LoginService extends AbstractService {

    @Autowired
    private PlafontService plafontService;

    public UserDTO login(final LoginDTO loginDTO) throws BaseException {
        return doPostCall(UserMapping.class, ApiEnum.LOGIN, loginDTO, ConvertionFunction.toUserDto);
    }

    public ConcreteDTO signUp(final CompleteUserDTO userDTO) throws BaseException {
        userDTO.setId(plafontService.getUsersDependencyPublic(0)
                .stream()
                .max(Comparator.comparing(UserDTO::getId))
                .map(e -> e.getId() + 1)
                .orElse(0));
        userDTO.setReferenceId(Optional.ofNullable(userDTO.getReferenceId()).orElse(0));
        userDTO.setWritePermissionString(ConvertionFunction.toStringFlag.apply(userDTO.getWritePermission()));
        userDTO.setReadPermissionString(ConvertionFunction.toStringFlag.apply(userDTO.getReadPermission()));
        final Boolean res = doPostCall(String.class, ApiEnum.SIGNUP, userDTO, e -> e.equals("0"));
        if (res) {
            return new ConcreteDTO();
        } else {
            throw new BaseException(Suppliers.INSERT_USER);
        }
    }
}
