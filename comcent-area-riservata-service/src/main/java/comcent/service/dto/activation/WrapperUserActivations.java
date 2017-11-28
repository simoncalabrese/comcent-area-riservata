package comcent.service.dto.activation;

import comcent.service.dto.base.AbstractDTO;
import comcent.service.dto.user.UserDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by simon.calabrese on 28/11/2017.
 */
public class WrapperUserActivations extends AbstractDTO {

    private UserDTO user;
    private List<ActivationDTO> activations;
    private Map plafont;
    private List<WrapperUserActivations> wrapper;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<ActivationDTO> getActivations() {
        return activations;
    }

    public void setActivations(List<ActivationDTO> activations) {
        this.activations = activations;
    }

    public List<WrapperUserActivations> getWrapper() {
        return wrapper;
    }

    public void setWrapper(List<WrapperUserActivations> wrapper) {
        this.wrapper = wrapper;
    }

    public Map getPlafont() {
        return plafont;
    }

    public void setPlafont(Map plafont) {
        this.plafont = plafont;
    }
}
