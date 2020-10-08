package com.itpaths.rules.price.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Formula {
    private boolean apply;
    private String tkt_type_id;
    private String price_natr_id;
    private String trf_pp_frml_id;
    private String orgnsm_id;
    private String method;
    private String status;

    public Formula() {
        this.apply = true;
        this.tkt_type_id = "";
        this.price_natr_id = "";
        this.trf_pp_frml_id = "";
        this.orgnsm_id = "";
        this.method = "";
        this.status = "";
    }

    public boolean isApply() {
        return apply;
    }

    public void setApply(boolean apply) {
        this.apply = apply;
    }

    public String getTkt_type_id() {
        return tkt_type_id;
    }

    public void setTkt_type_id(String tkt_type_id) {
        this.tkt_type_id = tkt_type_id;
    }

    public String getPrice_natr_id() {
        return price_natr_id;
    }

    public void setPrice_natr_id(String price_natr_id) {
        this.price_natr_id = price_natr_id;
    }

    public String getTrf_pp_frml_id() {
        return trf_pp_frml_id;
    }

    public void setTrf_pp_frml_id(String trf_pp_frml_id) {
        this.trf_pp_frml_id = trf_pp_frml_id;
    }

    public String getOrgnsm_id() {
        return orgnsm_id;
    }

    public void setOrgnsm_id(String orgnsm_id) {
        this.orgnsm_id = orgnsm_id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        System.out.println("Method Name: " + method);
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        System.out.println("Status: " + status);
        this.status = status;
    }
}
