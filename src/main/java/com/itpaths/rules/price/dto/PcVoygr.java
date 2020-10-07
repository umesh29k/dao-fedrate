package com.itpaths.rules.price.dto;

import lombok.Data;

/**
 * 
 * @author umesh
 * Create at 2020-10-07 04:52
 */
@Data
public class PcVoygr {

    /**
     * 
     */
    public String priceCd;

    /**
     * 
     */
    public Integer pcVrsn;

    /**
     * 
     */
    public String voygrId;

    /**
     * 
     */
    public Integer pcRdctnCffcnt;

    /**
     * 
     */
    public Integer pcSuplmntAmt;

    /**
     * 
     */
    public Integer pcUplftAmt;

    /**
     * 
     */
    public Double pcSuplmntAmtEur;

    /**
     * 
     */
    public Integer pcUplftAmtEur;

    /**
     * 
     */
    public String pcVoygrClassMsk;
}