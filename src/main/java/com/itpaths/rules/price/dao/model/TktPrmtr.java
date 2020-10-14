package com.itpaths.rules.price.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name = "tkt_prmtr")
public class TktPrmtr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "tkt_prmtr_seq")
    @SequenceGenerator(name = "tkt_prmtr_seq", sequenceName = "TKT_PRMTR_SEQ", allocationSize
            = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "TP_VRSN")
    private Integer tpVrsn;

    @Column(name = "VLDTY_DATE")
    private Date vldtyDate;

    @Column(name = "STS_CD")
    private String stsCd;

    @Column(name = "TP_MTRIP_RNDG")
    private String tpMtripRndg;

    @Column(name = "TP_NORM_RNDG")
    private String tpNormRndg;

    @Column(name = "TP_ADTNL_CFFCNT")
    private Double tpAdtnlCffcnt;

    @Column(name = "TP_ADTNL_FXD_CHARGE")
    private Integer tpAdtnlFxdCharge;

    @Column(name = "TP_MTRIP_CFFCNT")
    private Double tpMtripCffcnt;

    @Column(name = "TP_NORM_CFFCNT_BOTH_WAYS")
    private Double tpNormCffcntBothWays;

    @Column(name = "TP_NORM_CFFCNT_CLASS_1")
    private Double tpNormCffcntClass1;

    @Column(name = "TP_NORM_CFFCNT_CLASS_2")
    private Double tpNormCffcntClass2;

    @Column(name = "TP_NORM_CFFCNT_ONE_WAY")
    private Double tpNormCffcntOneWay;

    @Column(name = "TP_NORM_FXD_CHARGE")
    private Integer tpNormFxdCharge;

    @Column(name = "TP_NORM_MIN_PRICE_CLASS_1")
    private Integer tpNormMinPriceClass1;

    @Column(name = "TP_NORM_MIN_PRICE_CLASS_2")
    private Integer tpNormMinPriceClass2;

    @Column(name = "TP_NORM_UNIT_PRICE")
    private Double tpNormUnitPrice;

    @Column(name = "TP_NRDCTN_DSTNC")
    private Integer tpNrdctnDstnc;

    @Column(name = "TP_NRDCTN_FXD_CHARGE")
    private Integer tpNrdctnFxdCharge;

    @Column(name = "TP_ZONE_CFFCNT_BOTH_WAYS")
    private Double tpZoneCffcntBothWays;

    @Column(name = "TP_ZONE_CFFCNT_CLASS_1")
    private Double tpZoneCffcntClass1;

    @Column(name = "TP_ZONE_CFFCNT_CLASS_2")
    private Double tpZoneCffcntClass2;

    @Column(name = "TP_ZONE_CFFCNT_ONE_WAY")
    private Double tpZoneCffcntOneWay;

    @Column(name = "TP_ZONE_FXD_CHARGE")
    private Integer tpZoneFxdCharge;

    @Column(name = "TP_ZONE_MIN_PRICE_CLASS_1")
    private Integer tpZoneMinPriceClass1;

    @Column(name = "TP_ZONE_MIN_PRICE_CLASS_2")
    private Integer tpZoneMinPriceClass2;

    @Column(name = "TP_ZONE_UNIT_PRICE")
    private Double tpZoneUnitPrice;

    @Column(name = "OPER_BADGE_ID")
    private String operBadgeId;

    @Column(name = "OPER_ID")
    private String operId;

    @Column(name = "TIME_STAMP")
    private Date timeStamp;

    @Column(name = "TP_NORM_MIN_PRICE_UPCLASS")
    private Integer tpNormMinPriceUpclass;

    @Column(name = "TP_MTRIP_DURTN")
    private Integer tpMtripDurtn;

    @Column(name = "TP_ADTNL_FXD_CHARGE_EUR")
    private Double tpAdtnlFxdChargeEur;

    @Column(name = "TP_NORM_FXD_CHARGE_EUR")
    private Double tpNormFxdChargeEur;

    @Column(name = "TP_NORM_MIN_PRICE_CLASS_1_EUR")
    private Double tpNormMinPriceClass1Eur;

    @Column(name = "TP_NORM_MIN_PRICE_CLASS_2_EUR")
    private Double tpNormMinPriceClass2Eur;

    @Column(name = "TP_NORM_UNIT_PRICE_EUR")
    private Double tpNormUnitPriceEur;

    @Column(name = "TP_NRDCTN_FXD_CHARGE_EUR")
    private Double tpNrdctnFxdChargeEur;

    @Column(name = "TP_ZONE_FXD_CHARGE_EUR")
    private Double tpZoneFxdChargeEur;

    @Column(name = "TP_ZONE_MIN_PRICE_CLASS_1_EUR")
    private Double tpZoneMinPriceClass1Eur;

    @Column(name = "TP_ZONE_MIN_PRICE_CLASS_2_EUR")
    private Double tpZoneMinPriceClass2Eur;

    @Column(name = "TP_ZONE_UNIT_PRICE_EUR")
    private Double tpZoneUnitPriceEur;

    @Column(name = "TP_NORM_MIN_PRICE_UPCLASS_EUR")
    private Double tpNormMinPriceUpclassEur;

    @Column(name = "TP_RNDG_EUR")
    private String tpRndgEur;

    @Column(name = "PC_REFUND_COSTS_EUR")
    private Double pcRefundCostsEur;

}
