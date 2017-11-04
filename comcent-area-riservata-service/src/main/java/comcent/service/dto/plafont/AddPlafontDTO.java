package comcent.service.dto.plafont;

import comcent.service.dto.base.AbstractDTO;

import java.math.BigDecimal;
import java.util.Date;

public class AddPlafontDTO extends AbstractDTO {
    private static final long serialVersionUID = -1950028834748908028L;
    private Integer id;
    private Integer userId;
    private Date dataMov;
    private String dateString;
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
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
}
