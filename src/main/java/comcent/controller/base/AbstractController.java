package comcent.controller.base;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
public abstract class AbstractController extends GlobalExceptionHandler {

    protected final Logger log = LoggerFactory.getLogger(AbstractController.class);
}
