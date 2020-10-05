package com.itpaths.rules.price.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceRequest {
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
    @ApiModelProperty(notes = "Ticket Type ID", example = "")
    private String tkt_type_id;

    @Override
    public String toString() {
        return "PriceRequest{" +
                "price_cd='" + price_cd + '\'' +
                ", class_id='" + class_id + '\'' +
                ", itnry_id='" + itnry_id + '\'' +
                ", voygr_id='" + voygr_id + '\'' +
                ", event_only_flag='" + event_only_flag + '\'' +
                ", mtrip_flag='" + mtrip_flag + '\'' +
                ", city_net_flag='" + city_net_flag + '\'' +
                ", qty=" + qty +
                ", qtyad=" + qtyad +
                ", qtych=" + qtych +
                ", qtyb1=" + qtyb1 +
                ", qtyb2=" + qtyb2 +
                ", qtyb3=" + qtyb3 +
                ", dprtr_tstatn='" + dprtr_tstatn + '\'' +
                ", dstntn_tstatn='" + dstntn_tstatn + '\'' +
                ", via_tstatn='" + via_tstatn + '\'' +
                ", altrnv_tstatn='" + altrnv_tstatn + '\'' +
                ", birthdate=" + birthdate +
                ", durtn_id='" + durtn_id + '\'' +
                ", cntngnt_id='" + cntngnt_id + '\'' +
                ", orgnsm_id='" + orgnsm_id + '\'' +
                ", tkt_type_id='" + tkt_type_id + '\'' +
                '}';
    }
}
