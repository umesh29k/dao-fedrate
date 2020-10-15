package com.itpaths.rules.price.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itpaths.rules.price.model.PriceRequest;
import com.itpaths.rules.price.model.PriceResult;
import com.itpaths.rules.price.util.Price_Natr_Id;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Formula {
    private boolean apply;
    private String tkt_type_id;
    private String price_natr_id;
    private String trf_pp_frml_id;
    private String orgnsm_id;
    private String method;
    private String status;
    private PriceRequest priceRequest;
    private PriceResult priceResult;
    private Price_Natr_Id priceNatrId;
    private Double tkt_price_eur;

    public Formula() {
        this.apply = true;
        this.tkt_type_id = "";
        this.price_natr_id = "";
        this.trf_pp_frml_id = "";
        this.orgnsm_id = "";
        this.method = "";
        this.status = "";
    }
    
    public void setStatus(String status) {
        System.out.println("Status: " + status);
        this.status = status;
    }
}
