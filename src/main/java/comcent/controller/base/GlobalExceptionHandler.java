package comcent.controller.base;

import comcent.service.dto.base.AbstractDTO;
import comcent.service.dto.base.ConcreteDTO;
import comcent.service.dto.base.MessageDTO;
import comcent.service.exceptions.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
public abstract class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final Function<BaseException, AbstractDTO> mapException = ex -> {
        final AbstractDTO exceptionDto = ex.getExceptionDto();
        exceptionDto.setStatus(AbstractDTO.HttpStatus.KO);
        return exceptionDto;
    };

    public Supplier<AbstractDTO> getGenericError = () -> {
        final AbstractDTO dto = new ConcreteDTO();
        dto.setStatus(AbstractDTO.HttpStatus.KO);
        final MessageDTO mess = new MessageDTO();
        mess.setCodErrore("000");
        mess.setDesErrore("Errore interno. Contattare assistenza");
        dto.setMessages(Collections.singletonList(mess));
        return dto;
    };

    @ResponseBody
    @ExceptionHandler(value = BaseException.class)
    public AbstractDTO generalException(final HttpServletResponse response, final HttpServletResponse request, final BaseException e) {
        log.error("ECCEZIONE SOLLEVATA", e);
        return mapException.apply(e);
    }

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public AbstractDTO runtimeException(final HttpServletResponse response, final HttpServletResponse request, final RuntimeException e) {
        log.error("ECCEZIONE SOLLEVATA", e);
        return getGenericError.get();
    }
}
