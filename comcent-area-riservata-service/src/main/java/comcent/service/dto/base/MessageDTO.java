package comcent.service.dto.base;

import java.io.Serializable;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
public class MessageDTO implements Serializable {

    private static final long serialVersionUID = -8180777639339029687L;

    private String codErrore;
    private String desErrore;

    public String getCodErrore() {
        return codErrore;
    }

    public void setCodErrore(String codErrore) {
        this.codErrore = codErrore;
    }

    public String getDesErrore() {
        return desErrore;
    }

    public void setDesErrore(String desErrore) {
        this.desErrore = desErrore;
    }
}
