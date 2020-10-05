package com.itpaths.rules.price.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Formula {
    private boolean apply;
    private String trf_pp_frml_id;
    private String orgnsm_id;
    private String method;
    private String status;
}
