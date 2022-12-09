package comcent.common.components;

import java.util.Map;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
public abstract class AbstractPropertiesConfig {
    public abstract Map<String,String> getApi();
    public abstract String getBasePath();
}
