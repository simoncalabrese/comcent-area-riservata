package comcent.service.services;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
public enum ApiEnum {
    LOGIN("login"),
    GET_ID("getId");

    private String apiMapKey;

    ApiEnum(String apiMapKey) {
        this.apiMapKey = apiMapKey;
    }

    public String getApiMapKey() {
        return apiMapKey;
    }
}
