package comcent.service.dto.plafont;

import comcent.service.dto.base.AbstractDTO;
import comcent.service.dto.user.UserDTO;

import java.util.Date;

public class AddPlafontDTO extends AbstractDTO {
    private static final long serialVersionUID = -1950028834748908028L;
    private Integer id;
    private Integer userId;
    private Date dataMov;
    private String dateString;
    private Double amount;
    private Integer userInsert;
    private UserDTO userInsertDetail;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDataMov() {
        return dataMov;
    }

    public void setDataMov(Date dataMov) {
        this.dataMov = dataMov;
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

    public UserDTO getUserInsertDetail() {
        return userInsertDetail;
    }

    public void setUserInsertDetail(UserDTO userInsertDetail) {
        this.userInsertDetail = userInsertDetail;
    }
}
