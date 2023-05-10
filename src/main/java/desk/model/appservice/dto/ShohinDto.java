package main.java.desk.model.appservice.dto;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;

/** JacksonライブラリーによるJsonデータマッピング
 * @author none **/
public class ShohinDto {
    @JsonProperty("uniqueId")
    private String uniqueId;
    @JsonProperty("shohinCode")
    private Short shohinCode;
    @JsonProperty("shohinName")
    private String shohinName;
    @JsonProperty("editDate")
    private BigDecimal editDate;
    @JsonProperty("editTime")
    private BigDecimal editTime;
    @JsonProperty("remarks")
    private String remarks;

    public String getUniqueId() {
        return this.uniqueId;
    }
    public void setUniqueId(String value) {
        this.uniqueId = value;
    }

    public Short getShohinCode() {
        return this.shohinCode;
    }
    public void setShohinCode(Short value) {
        this.shohinCode = value;
    }

    public String getShohinName() {
        return this.shohinName;
    }
    public void setShohinName(String value) {
        this.shohinName = value;
    }

    public BigDecimal getEditDate() {
        return this.editDate;
    }
    public void setEditDate(BigDecimal value) {
        this.editDate = value;
    }

    public BigDecimal getEditTime() {
        return this.editTime;
    }
    public void setEditTime(BigDecimal value) {
        this.editTime = value;
    }

    public String getRemarks() {
        return this.remarks;
    }
    public void setRemarks(String value) {
        this.remarks = value;
    }
}