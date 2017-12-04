package comcent.controller.app.controller;

import comcent.controller.base.AbstractController;
import comcent.service.dto.base.ConcreteDTO;
import comcent.service.dto.user.CompleteUserDTO;
import comcent.service.dto.user.LoginDTO;
import comcent.service.dto.user.UserDTO;
import comcent.service.exceptions.BaseException;
import comcent.service.services.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/index")
public class LoginController extends AbstractController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserDTO login(@RequestBody final LoginDTO loginDTO) throws BaseException {
        return loginService.login(loginDTO);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ConcreteDTO signUp(@RequestBody final CompleteUserDTO userDTO) throws BaseException {
        return loginService.signUp(userDTO);
    }
}
