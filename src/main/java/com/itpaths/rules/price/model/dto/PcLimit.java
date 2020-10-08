package com.itpaths.rules.price.model.dto;

import lombok.Data;

/**
 * 
 * @author umesh
 * Create at 2020-10-09 03:54
 */
@Data
public class PcLimit {
    public String priceCd;
    public Integer pcVrsn;
    public String voygrId;
    public Integer pcDstnc;
    public Integer pcNoInGrp;
    public Integer pcLimitAmt;
    public Double pcLimitAmtEur;
}