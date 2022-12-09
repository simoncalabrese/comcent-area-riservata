package comcent.service.dto.user;


import comcent.service.dto.base.AbstractDTO;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
public class UserDTO extends AbstractDTO {
    private static final long serialVersionUID = -814487417621307500L;
    private Integer id;
    private String name;
    private String surname;
    private String codFisc;
    private String partIva;
    private UserDTO reference;
    private Boolean readPermission;
    private Boolean writePermission;
    private String email;
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCodFisc() {
        return codFisc;
    }

    public void setCodFisc(String codFisc) {
        this.codFisc = codFisc;
    }

    public String getPartIva() {
        return partIva;
    }

    public void setPartIva(String partIva) {
        this.partIva = partIva;
    }

    public UserDTO getReference() {
        return reference;
    }

    public void setReference(UserDTO reference) {
        this.reference = reference;
    }

    public Boolean getReadPermission() {
        return readPermission;
    }

    public void setReadPermission(Boolean readPermission) {
        this.readPermission = readPermission;
    }

    public Boolean getWritePermission() {
        return writePermission;
    }

    public void setWritePermission(Boolean writePermission) {
        this.writePermission = writePermission;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
