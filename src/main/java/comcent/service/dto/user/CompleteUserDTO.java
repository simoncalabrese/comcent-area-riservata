package comcent.service.dto.user;

/**
 * Created by simon.calabrese on 04/12/2017.
 */
public class CompleteUserDTO extends UserDTO {


    private static final long serialVersionUID = 4173594399888428616L;
    private String psw;
    private Integer referenceId;
    private String readPermissionString;
    private String writePermissionString;

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public Integer getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Integer referenceId) {
        this.referenceId = referenceId;
    }

    public String getReadPermissionString() {
        return readPermissionString;
    }

    public void setReadPermissionString(String readPermissionString) {
        this.readPermissionString = readPermissionString;
    }

    public String getWritePermissionString() {
        return writePermissionString;
    }

    public void setWritePermissionString(String writePermissionString) {
        this.writePermissionString = writePermissionString;
    }
}
