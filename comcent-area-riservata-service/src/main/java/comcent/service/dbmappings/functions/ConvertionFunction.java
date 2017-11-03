package comcent.service.dbmappings.functions;

import comcent.common.components.Converter;
import comcent.service.dbmappings.UserMapping;
import comcent.service.dto.user.UserDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;

public class ConvertionFunction {
    public static final Function<String, Boolean> toBooleanConvertion = string -> StringUtils.equalsIgnoreCase(string, "S");

    public static final Converter<UserMapping, UserDTO> toUserDto = userMapping -> {
        if (userMapping == null)
            return null;
        final UserDTO user = new UserDTO();
        user.setId(userMapping.getID());
        user.setCodFisc(userMapping.getCOD_FISC());
        user.setEmail(userMapping.getEMAIL());
        user.setName(userMapping.getNAME());
        user.setPartIva(userMapping.getP_IVA());
        user.setPhone(userMapping.getPHONE());
        user.setReadPermission(toBooleanConvertion.apply(userMapping.getREAD_PERMISSION()));
        user.setSurname(userMapping.getSURNAME());
        user.setWritePermission(toBooleanConvertion.apply(userMapping.getWRITE_PERMISSION()));
        user.setReference(ConvertionFunction.toUserDto.apply(userMapping.getREFERENCE()));
        return user;
    };
}
