package comcent.controller.app.controller;

import comcent.controller.base.AbstractController;
import comcent.service.dto.activation.ActivationDTO;
import comcent.service.dto.plafont.GetPlafontDTO;
import comcent.service.exceptions.BaseException;
import comcent.service.services.activation.ActivationSerice;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activations")
public class ActivationController extends AbstractController{

    @Autowired
    private ActivationSerice activationSerice;

    @RequestMapping(value = "/getActivations", method = RequestMethod.POST)
    public Map<Integer, List<Pair<Integer, List<ActivationDTO>>>> getActivation(@RequestBody final GetPlafontDTO getPlafontDTO) throws BaseException {
        return activationSerice.getActivations(getPlafontDTO);
    }
}
