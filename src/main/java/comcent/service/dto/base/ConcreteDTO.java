package comcent.service.dto.base;

import java.util.List;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
public class ConcreteDTO extends AbstractDTO {
    private static final long serialVersionUID = 7197564941732747832L;

    public ConcreteDTO(List<MessageDTO> messages) {
        super(messages);
    }

    public ConcreteDTO() {
        super();
    }
}
