package comcent.controller.app.controller;

import comcent.controller.base.AbstractController;
import comcent.service.dto.activation.ActivationDTO;
import comcent.service.dto.base.ConcreteDTO;
import comcent.service.dto.plafont.AddPlafontDTO;
import comcent.service.dto.plafont.GetPlafontDTO;
import comcent.service.exceptions.BaseException;
import comcent.service.services.plafont.PlafontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/plafont")
public class PlafontController extends AbstractController {

    @Autowired
    private PlafontService plafontService;

    @RequestMapping(value = "/addPlafont", method = RequestMethod.POST)
    public ConcreteDTO addPlafont(@RequestBody final AddPlafontDTO addPlafontDTO) throws BaseException {
        return plafontService.addPlafont(addPlafontDTO);
    }

    @RequestMapping(value = "/getPlafont", method = RequestMethod.POST)
    public Map getPlafont(@RequestBody final GetPlafontDTO getPlafontDTO) throws BaseException {
        return plafontService.getPlafont(getPlafontDTO);
    }

    @RequestMapping(value = "/insertActivation", method = RequestMethod.POST)
    public ConcreteDTO insertApplication(@RequestBody final ActivationDTO activationDTO) throws BaseException {
        return plafontService.insertActivation(activationDTO);
    }
}
