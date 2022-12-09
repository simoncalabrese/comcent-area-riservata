package comcent.service.dbmappings;

import java.io.Serializable;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
public class UserMapping implements Serializable {
    private Integer ID;
    private String NAME;
    private String SURNAME;
    private String COD_FISC;
    private String P_IVA;
    private Integer REFERENCE;
    private String READ_PERMISSION;
    private String WRITE_PERMISSION;
    private String EMAIL;
    private String PHONE;
    private String PSW;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getSURNAME() {
        return SURNAME;
    }

    public void setSURNAME(String SURNAME) {
        this.SURNAME = SURNAME;
    }

    public String getCOD_FISC() {
        return COD_FISC;
    }

    public void setCOD_FISC(String COD_FISC) {
        this.COD_FISC = COD_FISC;
    }

    public String getP_IVA() {
        return P_IVA;
    }

    public void setP_IVA(String p_IVA) {
        P_IVA = p_IVA;
    }

    public Integer getREFERENCE() {
        return REFERENCE;
    }

    public void setREFERENCE(Integer REFERENCE) {
        this.REFERENCE = REFERENCE;
    }

    public String getREAD_PERMISSION() {
        return READ_PERMISSION;
    }

    public void setREAD_PERMISSION(String READ_PERMISSION) {
        this.READ_PERMISSION = READ_PERMISSION;
    }

    public String getWRITE_PERMISSION() {
        return WRITE_PERMISSION;
    }

    public void setWRITE_PERMISSION(String WRITE_PERMISSION) {
        this.WRITE_PERMISSION = WRITE_PERMISSION;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getPSW() {
        return PSW;
    }

    public void setPSW(String PSW) {
        this.PSW = PSW;
    }
}
