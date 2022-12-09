package comcent.service.dto.plafont;

import comcent.service.dto.base.AbstractDTO;

public class GetPlafontDTO extends AbstractDTO {
    private static final long serialVersionUID = -727139150601805905L;
    private Integer userId;
    private String dateStart;
    private String dateEnd;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
