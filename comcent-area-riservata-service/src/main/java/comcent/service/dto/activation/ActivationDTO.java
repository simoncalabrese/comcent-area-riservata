package comcent.service.dto.activation;

import comcent.service.dto.base.AbstractDTO;

import java.util.Date;

public class ActivationDTO extends AbstractDTO {
    private static final long serialVersionUID = -5691038387390912270L;
    private Integer id;
    private Integer user;
    private String desActivation;
    private Integer amntPlafont;
    private Date dateAtt;
    private String dateString;
    private Integer userInsert;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getDesActivation() {
        return desActivation;
    }

    public void setDesActivation(String desActivation) {
        this.desActivation = desActivation;
    }

    public Integer getAmntPlafont() {
        return amntPlafont;
    }

    public void setAmntPlafont(Integer amntPlafont) {
        this.amntPlafont = amntPlafont;
    }

    public Date getDateAtt() {
        return dateAtt;
    }

    public void setDateAtt(Date dateAtt) {
        this.dateAtt = dateAtt;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public Integer getUserInsert() {
        return userInsert;
    }

    public void setUserInsert(Integer userInsert) {
        this.userInsert = userInsert;
    }

    @Override
    public String toString() {
        return "ActivationDTO{" +
                "id=" + id +
                ", user=" + user +
                ", desActivation='" + desActivation + '\'' +
                ", amntPlafont=" + amntPlafont +
                ", dateAtt=" + dateAtt +
                ", dateString='" + dateString + '\'' +
                ", userInsert=" + userInsert +
                '}';
    }
}
