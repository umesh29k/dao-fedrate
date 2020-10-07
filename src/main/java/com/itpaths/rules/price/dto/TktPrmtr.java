package com.itpaths.rules.price.dto;

import lombok.Data;

import java.sql.Date;

/**
 * @author umesh
 * Create at 2020-10-07 03:33
 */
@Data
public class TktPrmtr {
    public Integer tpVrsn;
    public Date vldtyDate;
    public String stsCd;
    public Integer tpMtripRndg;
    public Integer tpNormRndg;
    public Double tpAdtnlCffcnt;
    public Integer tpAdtnlFxdCharge;
    public Double tpMtripCffcnt;
    public Double tpNormCffcntBothWays;
    public Double tpNormCffcntClass1;
    public Double tpNormCffcntClass2;
    public Double tpNormCffcntOneWay;
    public Integer tpNormFxdCharge;
    public Integer tpNormMinPriceClass1;
    public Integer tpNormMinPriceClass2;
    public Double tpNormUnitPrice;
    public Integer tpNrdctnDstnc;

    /**
     *
     */
    public Integer tpNrdctnFxdCharge;

    /**
     *
     */
    public Double tpZoneCffcntBothWays;

    /**
     *
     */
    public Double tpZoneCffcntClass1;

    /**
     *
     */
    public Double tpZoneCffcntClass2;

    /**
     *
     */
    public Double tpZoneCffcntOneWay;

    /**
     *
     */
    public Integer tpZoneFxdCharge;

    /**
     *
     */
    public Integer tpZoneMinPriceClass1;

    /**
     *
     */
    public Integer tpZoneMinPriceClass2;

    /**
     *
     */
    public Double tpZoneUnitPrice;

    /**
     *
     */
    public String operBadgeId;

    /**
     *
     */
    public String operId;

    /**
     *
     */
    public Date timeStamp;

    /**
     *
     */
    public Integer tpNormMinPriceUpclass;

    /**
     *
     */
    public String tpMtripDurtn;

    /**
     *
     */
    public Double tpAdtnlFxdChargeEur;

    /**
     *
     */
    public Double tpNormFxdChargeEur;

    /**
     *
     */
    public Double tpNormMinPriceClass1Eur;

    /**
     *
     */
    public Double tpNormMinPriceClass2Eur;

    /**
     *
     */
    public Double tpNormUnitPriceEur;

    /**
     *
     */
    public Double tpNrdctnFxdChargeEur;

    /**
     *
     */
    public Double tpZoneFxdChargeEur;

    /**
     *
     */
    public Double tpZoneMinPriceClass1Eur;

    /**
     *
     */
    public Double tpZoneMinPriceClass2Eur;

    /**
     *
     */
    public Double tpZoneUnitPriceEur;

    /**
     *
     */
    public Double tpNormMinPriceUpclassEur;

    /**
     *
     */
    public String tpRndgEur;

    /**
     *
     */
    public Integer pcRefundCostsEur;
}