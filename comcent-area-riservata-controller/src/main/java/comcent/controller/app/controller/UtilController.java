package comcent.controller.app.controller;

import comcent.controller.base.AbstractController;
import comcent.service.dto.base.ConcreteDTO;
import comcent.service.exceptions.BaseException;
import comcent.service.services.utils.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by simon.calabrese on 14/12/2017.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/util")
public class UtilController extends AbstractController {

    @Autowired
    private UtilService utilService;

    @RequestMapping(value = "/getDocs", method = RequestMethod.GET)
    public List<Map> getDocumentLinks() throws BaseException {
        return utilService.getDocumentLinks();
    }

    @RequestMapping(value = "/addDoc", method = RequestMethod.POST)
    public ConcreteDTO addDoc(@RequestBody Map<String,String> param) throws BaseException {
        return utilService.addDoc(param);
    }

    @RequestMapping(value = "/delDoc", method = RequestMethod.GET)
    public ConcreteDTO delDoc(@RequestParam(value = "name") final String name) throws BaseException {
        return utilService.delDoc(name);
    }
}
