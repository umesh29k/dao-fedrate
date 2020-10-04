package com.itpaths.rules.price.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Price {
    @ApiModelProperty(notes = "Price Code", example = "XSDF")
    private String price_cd;
    @ApiModelProperty(notes = "Class ID", example = "12")
    private String class_id;
    @ApiModelProperty(notes = "Itinerary ID", example = "2342")
    private String itnry_id;
    @ApiModelProperty(notes = "Voyager ID", example = "1212")
    private String voygr_id;
    @ApiModelProperty(notes = "Event only requested", example = "")
    private String event_only_flag;
    @ApiModelProperty(notes = "Multi - Trip flag", example = "")
    private String mtrip_flag;
    @ApiModelProperty(notes = "City net required with ticket", example = "")
    private String city_net_flag;
    @ApiModelProperty(notes = "Number of voyager", example = "")
    private double qty;
    @ApiModelProperty(notes = "Multi tariff number of adult", example = "")
    private double qtyad;
    @ApiModelProperty(notes = "Multi tariff number of children", example = "")
    private double qtych;
    @ApiModelProperty(notes = "Multi tariff for beneficiary 1", example = "")
    private double qtyb1;
    @ApiModelProperty(notes = "Multi tariff for beneficiary 2", example = "")
    private double qtyb2;
    @ApiModelProperty(notes = "Multi tariff for beneficiary 3", example = "")
    private double qtyb3;
    @ApiModelProperty(notes = "Departure train-station", example = "")
    private String dprtr_tstatn;
    @ApiModelProperty(notes = "Destination train-station", example = "")
    private String dstntn_tstatn;
    @ApiModelProperty(notes = "Via train-station", example = "")
    private String via_tstatn;
    @ApiModelProperty(notes = "Alternative return train-station", example = "")
    private String altrnv_tstatn;
    @ApiModelProperty(notes = "Birthdate", example = "")
    private Date birthdate;
    @ApiModelProperty(notes = "Duration", example = "")
    private String durtn_id;
    @ApiModelProperty(notes = "Contingent", example = "")
    private String cntngnt_id;
    @ApiModelProperty(notes = "Organism", example = "")
    private String orgnsm_id;

    private String tkt_type_id;
    private double diabolo_amt_single;
    private double diabolo_amt_total;
    private String price_natr_id;

    private String status;

    public String getPrice_cd() {
        return price_cd;
    }

    public void setPrice_cd(String price_cd) {
        this.price_cd = price_cd;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getItnry_id() {
        return itnry_id;
    }

    public void setItnry_id(String itnry_id) {
        this.itnry_id = itnry_id;
    }

    public String getVoygr_id() {
        return voygr_id;
    }

    public void setVoygr_id(String voygr_id) {
        this.voygr_id = voygr_id;
    }

    public String getEvent_only_flag() {
        return event_only_flag;
    }

    public void setEvent_only_flag(String event_only_flag) {
        this.event_only_flag = event_only_flag;
    }

    public String getMtrip_flag() {
        return mtrip_flag;
    }

    public void setMtrip_flag(String mtrip_flag) {
        this.mtrip_flag = mtrip_flag;
    }

    public String getCity_net_flag() {
        return city_net_flag;
    }

    public void setCity_net_flag(String city_net_flag) {
        this.city_net_flag = city_net_flag;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getQtyad() {
        return qtyad;
    }

    public void setQtyad(double qtyad) {
        this.qtyad = qtyad;
    }

    public double getQtych() {
        return qtych;
    }

    public void setQtych(double qtych) {
        this.qtych = qtych;
    }

    public double getQtyb1() {
        return qtyb1;
    }

    public void setQtyb1(double qtyb1) {
        this.qtyb1 = qtyb1;
    }

    public double getQtyb2() {
        return qtyb2;
    }

    public void setQtyb2(double qtyb2) {
        this.qtyb2 = qtyb2;
    }

    public double getQtyb3() {
        return qtyb3;
    }

    public void setQtyb3(double qtyb3) {
        this.qtyb3 = qtyb3;
    }

    public String getDprtr_tstatn() {
        return dprtr_tstatn;
    }

    public void setDprtr_tstatn(String dprtr_tstatn) {
        this.dprtr_tstatn = dprtr_tstatn;
    }

    public String getDstntn_tstatn() {
        return dstntn_tstatn;
    }

    public void setDstntn_tstatn(String dstntn_tstatn) {
        this.dstntn_tstatn = dstntn_tstatn;
    }

    public String getVia_tstatn() {
        return via_tstatn;
    }

    public void setVia_tstatn(String via_tstatn) {
        this.via_tstatn = via_tstatn;
    }

    public String getAltrnv_tstatn() {
        return altrnv_tstatn;
    }

    public void setAltrnv_tstatn(String altrnv_tstatn) {
        this.altrnv_tstatn = altrnv_tstatn;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getDurtn_id() {
        return durtn_id;
    }

    public void setDurtn_id(String durtn_id) {
        this.durtn_id = durtn_id;
    }

    public String getCntngnt_id() {
        return cntngnt_id;
    }

    public void setCntngnt_id(String cntngnt_id) {
        this.cntngnt_id = cntngnt_id;
    }

    public String getOrgnsm_id() {
        return orgnsm_id;
    }

    public void setOrgnsm_id(String orgnsm_id) {
        this.orgnsm_id = orgnsm_id;
    }

    public String getTkt_type_id() {
        return tkt_type_id;
    }

    public void setTkt_type_id(String tkt_type_id) {
        this.tkt_type_id = tkt_type_id;
    }

    public double getDiabolo_amt_single() {
        return diabolo_amt_single;
    }

    public void setDiabolo_amt_single(double diabolo_amt_single) {
        this.diabolo_amt_single = diabolo_amt_single;
    }

    public double getDiabolo_amt_total() {
        return diabolo_amt_total;
    }

    public void setDiabolo_amt_total(double diabolo_amt_total) {
        this.diabolo_amt_total = diabolo_amt_total;
    }

    public String getPrice_natr_id() {
        return price_natr_id;
    }

    public void setPrice_natr_id(String price_natr_id) {
        this.price_natr_id = price_natr_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
