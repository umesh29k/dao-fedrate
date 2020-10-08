package com.itpaths.rules.price.model.dto;

import lombok.Data;

/**
 * 
 * @author umesh
 * Create at 2020-10-09 04:37
 */
@Data
public class PcFtktPrice {

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
    public String ageCtgryId;

    /**
     * 
     */
    public String classId;

    /**
     * 
     */
    public String durtnId;

    /**
     * 
     */
    public Integer ftktPrice;

    /**
     * 
     */
    public Double ftktPriceEur;
}