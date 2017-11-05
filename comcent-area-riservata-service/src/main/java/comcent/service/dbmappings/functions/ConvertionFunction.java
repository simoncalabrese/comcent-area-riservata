package comcent.service.dbmappings.functions;

import comcent.common.components.Converter;
import comcent.service.dbmappings.UserMapping;
import comcent.service.dto.activation.ActivationDTO;
import comcent.service.dto.plafont.AddPlafontDTO;
import comcent.service.dto.user.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConvertionFunction {
    public static final Function<String, Boolean> toBooleanConvertion = string -> StringUtils.equalsIgnoreCase(string, "S");
    public static final Function<Date, String> dateToString = date -> {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(date);
    };

    public static final Supplier<String> getTodayAsString = () -> Optional.of(new Date()).map(dateToString).orElse(null);

    public static final Supplier<String> getFirstOfMonthAsString = () -> {
        final Calendar c = Calendar.getInstance();   // this takes current date
        c.set(Calendar.DAY_OF_MONTH, 1);
        return Optional.of(c.getTime()).map(dateToString).orElse(null);
    };

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

    public static <T> Stream<T> notIntersect(final List<T> first, final List<T> second) {
        return first.stream().filter(e -> !second.stream().anyMatch(m -> m.equals(e)));
    }
}
