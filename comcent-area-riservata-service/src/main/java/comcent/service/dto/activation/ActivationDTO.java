package comcent.service.dto.activation;

import comcent.service.dto.base.AbstractDTO;

public class ActivationDTO extends AbstractDTO {
    private static final long serialVersionUID = -5691038387390912270L;
    private Integer id;
    private Integer user;
    private String desActivation;
    private Integer amntPlafont;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getDesActivation() {
        return desActivation;
    }

    public void setDesActivation(String desActivation) {
        this.desActivation = desActivation;
    }

    public Integer getAmntPlafont() {
        return amntPlafont;
    }

    public void setAmntPlafont(Integer amntPlafont) {
        this.amntPlafont = amntPlafont;
    }
}
