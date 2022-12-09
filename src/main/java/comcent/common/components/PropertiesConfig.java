package comcent.common.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
@Component(value = "PropertiesConfig")
@ConfigurationProperties
public class PropertiesConfig extends AbstractPropertiesConfig {
    @Value("${app.basePath}")
    private String basePath;

    @Autowired
    private Api api;

    @Override
    public Map<String, String> getApi() {
        return this.api.getApi();
    }

    @Override
    public String getBasePath() {
        return this.basePath;
    }
}
