package comcent.service.services;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
public enum ApiEnum {
    LOGIN("login"),
    GET_ID("getId"),
    INSERT_MOVEMENT("insertMovement"),
    INSERT_ACTIVATION("insertActivation"),
    GET_PLAFONT("getPlafont"), GET_ID_ACTIVATION("getIdActivation"),
    GET_USERS("getUsers"),
    GET_ACTIVATIONS("getActivations"),
    GET_USER_DATA("getUserData"),
    SIGNUP("signUp");

    private String apiMapKey;

    ApiEnum(String apiMapKey) {
        this.apiMapKey = apiMapKey;
    }

    public String getApiMapKey() {
        return apiMapKey;
    }
}
