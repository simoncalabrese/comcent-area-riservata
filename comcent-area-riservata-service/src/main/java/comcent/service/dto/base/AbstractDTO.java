package comcent.service.dto.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
public abstract class AbstractDTO implements Serializable {

    public enum HttpStatus {
        OK("200"),KO("KO");

        private String des;
        HttpStatus(final String des) {
            this.des=des;
        }

        public String getDes() {
            return des;
        }
    }

    public AbstractDTO(List<MessageDTO> messages) {
        this.messages = messages;
    }

    public AbstractDTO() {
    }

    private static final long serialVersionUID = -6336261999566551037L;
    private List<MessageDTO> messages;
    private HttpStatus status = HttpStatus.OK;

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }

    public String getStatus() {
        return status.getDes();
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
