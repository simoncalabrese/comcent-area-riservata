package comcent.service.dto.user;


import comcent.service.dto.base.AbstractDTO;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
public class LoginDTO extends AbstractDTO {
    private static final long serialVersionUID = 4244225523708953428L;
    private String username;
    private String password;

    public String getUsername() {
        return username.toUpperCase();
    }

    public String getPassword() {
        return password.toUpperCase();
    }
}
