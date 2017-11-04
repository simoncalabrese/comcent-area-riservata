package comcent.controller.app.controller;

import comcent.controller.base.AbstractController;
import comcent.service.dto.activation.ActivationDTO;
import comcent.service.dto.base.ConcreteDTO;
import comcent.service.dto.plafont.AddPlafontDTO;
import comcent.service.dto.plafont.GetPlafontDTO;
import comcent.service.exceptions.BaseException;
import comcent.service.services.plafont.PlafontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/plafont")
public class PlafontController extends AbstractController {

    @Autowired
    private PlafontService plafontService;

    @RequestMapping("/addPlafont")
    public ConcreteDTO addPlafont(@RequestBody final AddPlafontDTO addPlafontDTO) throws BaseException {
        return plafontService.addPlafont(addPlafontDTO);
    }

    @RequestMapping("/getPlafont")
    public Map getPlafont(@RequestBody final GetPlafontDTO getPlafontDTO) throws BaseException {
        return plafontService.getPlafont(getPlafontDTO);
    }

    @RequestMapping("/insertActivation")
    public ConcreteDTO insertApplication(@RequestBody final ActivationDTO activationDTO) throws BaseException {
        return plafontService.insertActivation(activationDTO);
    }
}
