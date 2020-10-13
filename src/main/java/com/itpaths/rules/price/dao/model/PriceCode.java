package com.itpaths.rules.price.dao.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name = "price_code")
public class PriceCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "PRICE_CD")
    private String priceCd;

    @Column(name = "PC_VRSN")
    private Integer pcVrsn;

    @Column(name = "VLDTY_DATE")
    private Date vldtyDate;

    @Column(name = "STS_CD")
    private String stsCd;

    @Column(name = "DSCRPN_D")
    private String dscrpnD;

    @Column(name = "DSCRPN_E")
    private String dscrpnE;

    @Column(name = "DSCRPN_F")
    private String dscrpnF;

    @Column(name = "DSCRPN_G")
    private String dscrpnG;

    @Column(name = "PC_ABBR_D")
    private String pcAbbrD;

    @Column(name = "PC_ABBR_E")
    private String pcAbbrE;

    @Column(name = "PC_ABBR_F")
    private String pcAbbrF;

    @Column(name = "PC_ABBR_G")
    private String pcAbbrG;

    @Column(name = "PC_CLERK_MSG_D")
    private String pcClerkMsgD;

    @Column(name = "PC_CLERK_MSG_E")
    private String pcClerkMsgE;

    @Column(name = "PC_CLERK_MSG_F")
    private String pcClerkMsgF;

    @Column(name = "PC_CLERK_MSG_G")
    private String pcClerkMsgG;

    @Column(name = "BNFCRY_ID")
    private String bnfcryId;

    @Column(name = "CLSD_TSTATN_WRNG_ID")
    private String clsdTstatnWrngId;

    @Column(name = "DPRTR_TSTATN_AUTHZN")
    private String dprtrTstatnAuthzn;

    @Column(name = "DSTNTN_TSTATN_AUTHZN")
    private String dstntnTstatnAuthzn;

    @Column(name = "EMSSN_TSTATN_AUTHZN")
    private String emssnTstatnAuthzn;

    @Column(name = "PRICE_NATR_ID")
    private String priceNatrId;

    @Column(name = "RETRN_DSCRPN_ID")
    private String retrnDscrpnId;

    @Column(name = "TKT_TYPE_ID")
    private String tktTypeId;

    @Column(name = "PC_APPLY_CNFCTN_TAX")
    private String pcApplyCnfctnTax;

    @Column(name = "PC_APPLY_RDCTN_FULL")
    private String pcApplyRdctnFull;

    @Column(name = "PC_CNVT_DPRTR_TSTATN")
    private String pcCnvtDprtrTstatn;

    @Column(name = "PC_CNVT_DSTNTN_TSTATN")
    private String pcCnvtDstntnTstatn;

    @Column(name = "PC_CNVT_VIA_TSTATN")
    private String pcCnvtViaTstatn;

    @Column(name = "PC_DFLT_AMT_RMBRSMNT")
    private Integer pcDfltAmtRmbrsmnt;

    @Column(name = "PC_ITNRY_CLASS")
    private String pcItnryClass;

    @Column(name = "PC_MAX_DSTNC_JRNEY")
    private Integer pcMaxDstncJrney;

    @Column(name = "PC_MAX_DSTNC_TAXBL")
    private Integer pcMaxDstncTaxbl;

    @Column(name = "PC_MIN_DSTNC_JRNEY")
    private Integer pcMinDstncJrney;

    @Column(name = "PC_MIN_DSTNC_TAXBL")
    private Integer pcMinDstncTaxbl;

    @Column(name = "PC_PRESLS_MAX_DAYS")
    private Integer pcPreslsMaxDays;

    @Column(name = "PC_PRESLS_MIN_DAYS")
    private Integer pcPreslsMinDays;

    @Column(name = "PC_SLS_START_DATE")
    private Date pcSlsStartDate;

    @Column(name = "PC_SLS_STOP_DATE")
    private Date pcSlsStopDate;

    @Column(name = "PC_TKT_CITY_NET_ALLWD")
    private String pcTktCityNetAllwd;

    @Column(name = "PC_TKT_MTRIP_ALLWD")
    private String pcTktMtripAllwd;

    @Column(name = "MTRIP_AGLMRTN_ID")
    private String mtripAglmrtnId;

    @Column(name = "PC_TSTATN_ALTRNV_ALLWD")
    private String pcTstatnAltrnvAllwd;

    @Column(name = "PC_TSTATN_VIA_ALLWD")
    private String pcTstatnViaAllwd;

    @Column(name = "PC_UPLFT_CFFCNT")
    private Double pcUplftCffcnt;

    @Column(name = "FPT_DUPLCT_ALLWD_YESNO")
    private String fptDuplctAllwdYesno;

    @Column(name = "FPT_PRSNLZD_YESNO")
    private String fptPrsnlzdYesno;

    @Column(name = "PC_MULTI_TARIFF_MAX_NO")
    private Integer pcMultiTariffMaxNo;

    @Column(name = "PC_MULTI_TARIFF_MIN_NO")
    private Integer pcMultiTariffMinNo;

    @Column(name = "PC_ALLWD_FOR_AUTMT")
    private String pcAllwdForAutmt;

    @Column(name = "PC_ALLWD_FOR_PORTBL")
    private String pcAllwdForPortbl;

    @Column(name = "PC_GRP_TARIFF_MIN_NO")
    private Integer pcGrpTariffMinNo;

    @Column(name = "PC_NO_PRICE_DSTNCS")
    private Integer pcNoPriceDstncs;

    @Column(name = "PC_PRSTTN_ALLWD_WTHT_SNCB")
    private String pcPrsttnAllwdWthtSncb;

    @Column(name = "PC_PRSTTN_VALD_DAYS_PTTRN")
    private String pcPrsttnValdDaysPttrn;

    @Column(name = "MTT_NO_OF_TRIPS")
    private Integer mttNoOfTrips;

    @Column(name = "PC_REFUND_DAYS")
    private Integer pcRefundDays;

    @Column(name = "WEEKEND_FLAG")
    private String weekendFlag;

    @Column(name = "FUTURE1_FLAG")
    private String future1Flag;

    @Column(name = "FUTURE2_FLAG")
    private String future2Flag;

    @Column(name = "FUTURE1_VALUE")
    private Integer future1Value;

    @Column(name = "FUTURE2_VALUE")
    private Integer future2Value;

    @Column(name = "FUTURE1_CFFCNT")
    private Double future1Cffcnt;

    @Column(name = "OPER_BADGE_ID")
    private String operBadgeId;

    @Column(name = "OPER_ID")
    private String operId;

    @Column(name = "TIME_STAMP")
    private Date timeStamp;

    @Column(name = "IVT_MANY_VGRS_FLAG")
    private String ivtManyVgrsFlag;

    @Column(name = "IVT_FXD_AMT_FLAG")
    private String ivtFxdAmtFlag;

    @Column(name = "IVT_ACCESS_PASS_FLAG")
    private String ivtAccessPassFlag;

    @Column(name = "LUGGAGE_FLG")
    private String luggageFlg;

    @Column(name = "CRUB_ID")
    private String crubId;

    @Column(name = "CMRCL_MSG_FLG")
    private String cmrclMsgFlg;

    @Column(name = "SLS_ITEM_ID")
    private String slsItemId;

    @Column(name = "SLS_ITEM_ID_MTRIP")
    private String slsItemIdMtrip;

    @Column(name = "SLS_ITEM_ID_EVENT_ONLY")
    private String slsItemIdEventOnly;

    @Column(name = "PC_DFLT_AMT_RMBRSMNT_EUR")
    private Double pcDfltAmtRmbrsmntEur;

    @Column(name = "FDLTY_CARD_FLG")
    private String fdltyCardFlg;

    @Column(name = "PRICE_FRML_ID")
    private String priceFrmlId;

    @Column(name = "TRF_PP_FRML_ID")
    private String trfPpFrmlId;

    @Column(name = "TRF_PP_ORGNSM_ID")
    private String trfPpOrgnsmId;

    @Column(name = "PRODUCT_NATR_ID")
    private String productNatrId;

    @Column(name = "PRODUCT_TYPE_ID")
    private String productTypeId;

    @Column(name = "FID_PORTFOLIO_AWD_FLG")
    private String fidPortfolioAwdFlg;

    @Column(name = "IBIS_MAX_ADULT")
    private Integer ibisMaxAdult;

    @Column(name = "IBIS_MAX_CHILD")
    private Integer ibisMaxChild;

    @Column(name = "PC_ALLWD_TT")
    private String pcAllwdTt;

    @Column(name = "PC_ALLWD_FOR_WEB")
    private String pcAllwdForWeb;

    @Column(name = "PC_REFUND_AWD_FLG")
    private String pcRefundAwdFlg;

    @Column(name = "PC_REFUND_COSTS_FLG")
    private String pcRefundCostsFlg;

    @Column(name = "PC_REFUND_COSTS_EUR")
    private Double pcRefundCostsEur;

    @Column(name = "PC_MTARIFF_SINGLE_TKT_FLG")
    private String pcMtariffSingleTktFlg;

    @Column(name = "IBIS_COSTS")
    private Double ibisCosts;

}
