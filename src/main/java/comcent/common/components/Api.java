package comcent.common.components;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
@Component
@ConfigurationProperties(prefix = "app.restUrl")
public class Api {
    private Map<String, String> api;

    public Map<String, String> getApi() {
        return api;
    }

    public void setApi(Map<String, String> api) {
        this.api = api;
    }
}
