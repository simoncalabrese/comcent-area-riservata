package comcent.service.exceptions;


import comcent.service.dto.base.AbstractDTO;
import comcent.service.dto.base.ConcreteDTO;
import comcent.service.dto.base.MessageDTO;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
public class Suppliers {
    public static Supplier<AbstractDTO> CONNECTION_ERROR = () -> {
        final MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCodErrore("001");
        messageDTO.setDesErrore("Errore comunicazione Server. Contattare Assistenza");
        return new ConcreteDTO(Collections.singletonList(messageDTO));
    };

    public static Supplier<AbstractDTO> CONNECTION_RESULT_ERROR = () -> {
        final MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCodErrore("002");
        messageDTO.setDesErrore("Errore ritorno dal server. Contattare Assistenza");
        return new ConcreteDTO(Collections.singletonList(messageDTO));
    };
}
