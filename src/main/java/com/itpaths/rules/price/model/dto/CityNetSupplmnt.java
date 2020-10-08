package com.itpaths.rules.price.model.dto;

import lombok.Data;

import java.time.OffsetDateTime;

/**
 * 
 * @author umesh
 * Create at 2020-10-09 01:56
 */
@Data
public class CityNetSupplmnt {
    public String cityNetId;
    public String durtnId;
    public String ageCtgryId;
    public OffsetDateTime vldtyDate;

    /**
     *
     */
    public String stsCd;

    /**
     * 
     */
    public Integer cndStktFxdChargeS;

    /**
     * 
     */
    public Double cnsPpCffcnt;

    /**
     * 
     */
    public Integer railwaysAmt1;

    /**
     * 
     */
    public Integer railwaysAmt2;

    /**
     * 
     */
    public Double cndStktFxdChargeSEur;

    /**
     * 
     */
    public Double railwaysAmt1Eur;

    /**
     * 
     */
    public Double railwaysAmt2Eur;

    /**
     * 
     */
    public Double cnsVrfSupplmntAmtEur;
}