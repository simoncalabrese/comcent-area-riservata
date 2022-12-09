package comcent.controller.app.controller;

import comcent.controller.base.AbstractController;
import comcent.service.dto.activation.ActivationDTO;
import comcent.service.dto.activation.WrapperUserActivations;
import comcent.service.dto.base.ConcreteDTO;
import comcent.service.dto.plafont.GetPlafontDTO;
import comcent.service.dto.user.UserDTO;
import comcent.service.exceptions.BaseException;
import comcent.service.services.activation.ActivationSerice;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/activations")
public class ActivationController extends AbstractController {

    @Autowired
    private ActivationSerice activationSerice;

    @RequestMapping(value = "/getActivations", method = RequestMethod.POST)
    public WrapperUserActivations getActivation(@RequestBody final GetPlafontDTO getPlafontDTO) throws BaseException {
        return activationSerice.getActivations(getPlafontDTO);
    }

    @RequestMapping(value = "/delActivation", method = RequestMethod.GET)
    public ConcreteDTO delActivation(@RequestParam(value = "id") final Integer id,
                                     @RequestParam(value = "amount") final Double amount,
                                     @RequestParam(value = "user") final Integer user,
                                     @RequestParam(value = "userInsert") final Integer userInsert) throws BaseException {
        return activationSerice.delActivation(id,amount,user,userInsert);
    }
}
