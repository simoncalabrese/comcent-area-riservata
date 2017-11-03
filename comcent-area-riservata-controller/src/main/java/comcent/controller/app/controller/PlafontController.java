package comcent.controller.app.controller;

import comcent.controller.base.AbstractController;
import comcent.service.dto.plafont.AddPlafontDTO;
import comcent.service.exceptions.BaseException;
import comcent.service.services.plafont.PlafontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plafont")
public class PlafontController extends AbstractController {

    @Autowired
    private PlafontService plafontService;

    @RequestMapping("/addPlafont")
    public void addPlafont(@RequestBody final AddPlafontDTO addPlafontDTO) throws BaseException {
        plafontService.addPlafont(addPlafontDTO);
    }
}
