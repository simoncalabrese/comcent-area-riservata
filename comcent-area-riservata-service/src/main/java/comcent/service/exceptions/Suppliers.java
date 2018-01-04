package comcent.service.exceptions;


import comcent.service.dto.base.AbstractDTO;
import comcent.service.dto.base.ConcreteDTO;
import comcent.service.dto.base.MessageDTO;
import comcent.service.dto.plafont.AddPlafontDTO;

import java.util.Collections;
import java.util.function.Function;
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

    public static Supplier<AbstractDTO> INSERT_AMOUNT_RESULT = () -> {
        final MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCodErrore("003");
        messageDTO.setDesErrore("Errore durante l' inserimento del versamento. Contattare Assistenza");
        return new ConcreteDTO(Collections.singletonList(messageDTO));
    };

    public static Supplier<AbstractDTO> INSERT_USER = () -> {
        final MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCodErrore("004");
        messageDTO.setDesErrore("Errore durante la registrazione");
        return new ConcreteDTO(Collections.singletonList(messageDTO));
    };

    public static Supplier<AbstractDTO> INSERT_DOC = () -> {
        final MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCodErrore("004");
        messageDTO.setDesErrore("Errore durante l' inserimento del link");
        return new ConcreteDTO(Collections.singletonList(messageDTO));
    };

    public static Supplier<AbstractDTO> DEL_DOC = () -> {
        final MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCodErrore("004");
        messageDTO.setDesErrore("Errore durante l' eliminazione del link");
        return new ConcreteDTO(Collections.singletonList(messageDTO));
    };


    public static class Utils {
        public static Function<Integer,AddPlafontDTO> toAddPlafonStorno = i -> {
            final AddPlafontDTO dto = new AddPlafontDTO();
            dto.setAmount(i);
            dto.setUserId(0);
            return dto;
        };
    }
}
