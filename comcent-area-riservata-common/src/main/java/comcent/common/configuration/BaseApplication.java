package comcent.common.configuration;


import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"comcent.service", "comcent.common", "comcent.controller" })
public abstract class BaseApplication {

    @Autowired
    private ObjectMapper jacksonMapper;

    @PostConstruct
    public void initApp() {
        jacksonMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }
}
