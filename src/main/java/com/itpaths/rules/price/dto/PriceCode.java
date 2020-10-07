package com.itpaths.rules.price.dto;

import lombok.*;

import java.sql.Date;

/**
 * @author umesh
 */

@Data
public class PriceCode {
    public String priceCd;
    public Integer pcVrsn;
    public Date vldtyDate;
    public String stsCd;
    public String dscrpnD;
    public String dscrpnE;
    public String dscrpnF;
    public String dscrpnG;
    public String pcAbbrD;
    public String pcAbbrE;
    public String pcAbbrF;
    public String pcAbbrG;
    public String pcClerkMsgD;
    public String pcClerkMsgE;
    public String pcClerkMsgF;
    public String pcClerkMsgG;
    public String bnfcryId;
    public String clsdTstatnWrngId;
    public String dprtrTstatnAuthzn;
    public String dstntnTstatnAuthzn;
    public String emssnTstatnAuthzn;
    public String priceNatrId;
    public String retrnDscrpnId;
    public String tktTypeId;
    public String pcApplyCnfctnTax;
    public String pcApplyRdctnFull;
    public String pcCnvtDprtrTstatn;
    public String pcCnvtDstntnTstatn;
    public String pcCnvtViaTstatn;
    public String pcDfltAmtRmbrsmnt;
    public String pcItnryClass;
    public Integer pcMaxDstncJrney;
    public Integer pcMaxDstncTaxbl;
    public Integer pcMinDstncJrney;
    public Integer pcMinDstncTaxbl;
    public String pcPreslsMaxDays;
    public String pcPreslsMinDays;
    public Date pcSlsStartDate;
    public Date pcSlsStopDate;
    public String pcTktCityNetAllwd;
    public String pcTktMtripAllwd;
    public String mtripAglmrtnId;
    public String pcTstatnAltrnvAllwd;
    public String pcTstatnViaAllwd;
    public Integer pcUplftCffcnt;
    public String fptDuplctAllwdYesno;
    public String fptPrsnlzdYesno;
    public String pcMultiTariffMaxNo;
    public String pcMultiTariffMinNo;
    public String pcAllwdForAutmt;
    public String pcAllwdForPortbl;
    public String pcGrpTariffMinNo;
    public String pcNoPriceDstncs;
    public String pcPrsttnAllwdWthtSncb;
    public String pcPrsttnValdDaysPttrn;
    public String mttNoOfTrips;
    public String pcRefundDays;
    public String weekendFlag;
    public String future1Flag;
    public String future2Flag;
    public Integer future1Value;
    public Integer future2Value;
    public Double future1Cffcnt;
    public String operBadgeId;
    public String operId;
    public Date timeStamp;
    public String ivtManyVgrsFlag;
    public String ivtFxdAmtFlag;
    public String ivtAccessPassFlag;
    public String luggageFlg;
    public String crubId;
    public String cmrclMsgFlg;
    public String slsItemId;
    public String slsItemIdMtrip;
    public String slsItemIdEventOnly;
    public Double pcDfltAmtRmbrsmntEur;
    public String fdltyCardFlg;
    public String priceFrmlId;
    public String trfPpFrmlId;
    public String trfPpOrgnsmId;
    public String productNatrId;
    public String productTypeId;
    public String fidPortfolioAwdFlg;
    public String ibisMaxAdult;
    public String ibisMaxChild;
    public String pcAllwdTt;
    public String pcAllwdForWeb;
    public String pcRefundAwdFlg;
    public String pcRefundCostsFlg;
    public Double pcRefundCostsEur;
    public String pcMtariffSingleTktFlg;
    public Double ibisCosts;
}