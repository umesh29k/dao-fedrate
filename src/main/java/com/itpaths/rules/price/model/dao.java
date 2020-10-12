package com.itpaths.rules.price.model;

import lombok.Data;

import java.sql.Date;
import java.time.OffsetDateTime;

public class dao {
    /**
     * @author umesh
     * Create at 2020-10-07 02:41
     */
    @Data
    public static class TtFormula {
        public String frmlId;
        public String stsCd;
        public Date vldtyDate;
        public String dscrpnD;
        public String dscrpnE;
        public String dscrpnF;
        public String dscrpnG;
        public String prmtrNo;
        public String frmlStrng;
        public String operId;
        public String operBadgeId;
        public Date timeStamp;
        public String ibisAwdFlg;
    }

    /**
     *
     * @author umesh
     * Create at 2020-10-09 03:54
     */
    @Data
    public static class TktType {

        /**
         *
         */
        public String tktTypeId;

        /**
         *
         */
        public OffsetDateTime vldtyDate;

        /**
         *
         */
        public String stsCd;

        /**
         *
         */
        public String dscrpnD;

        /**
         *
         */
        public String dscrpnE;

        /**
         *
         */
        public String dscrpnF;

        /**
         *
         */
        public String dscrpnG;

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
        public OffsetDateTime timeStamp;
    }

    /**
     * @author umesh
     * Create at 2020-10-07 03:33
     */
    @Data
    public static class TktPrmtr {
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

    /**
     * @author umesh
     */

    @Data
    public static class PriceCode {
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

    /**
     *
     * @author umesh
     * Create at 2020-10-07 05:12
     */
    @Data
    public static class PcVoygrClass {

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
        public String classId;

        /**
         *
         */
        public Integer pcDfltAmt;

        /**
         *
         */
        public Integer pcMaxAmt;

        /**
         *
         */
        public Integer pcDfltAmtEur;

        /**
         *
         */
        public Integer pcMaxAmtEur;
    }

    /**
     *
     * @author umesh
     * Create at 2020-10-07 04:52
     */
    @Data
    public static class PcVoygr {

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

    /**
     *
     * @author umesh
     * Create at 2020-10-09 03:54
     */
    @Data
    public static class PcLimit {
        public String priceCd;
        public Integer pcVrsn;
        public String voygrId;
        public Integer pcDstnc;
        public Integer pcNoInGrp;
        public Integer pcLimitAmt;
        public Double pcLimitAmtEur;
    }

    /**
     *
     * @author umesh
     * Create at 2020-10-09 04:37
     */
    @Data
    public static class PcFtktPrice {

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

    /**
     *
     * @author umesh
     * Create at 2020-10-07 03:33
     */
    @Data
    public static class Orgnsm {

        /**
         *
         */
        public String orgnsmId;

        /**
         *
         */
        public Integer orVrsn;

        /**
         *
         */
        public Date vldtyDate;

        /**
         *
         */
        public String stsCd;

        /**
         *
         */
        public String contrctId;

        /**
         *
         */
        public String custmrId;

        /**
         *
         */
        public String cmpnyName;

        /**
         *
         */
        public String street;

        /**
         *
         */
        public String postCd;

        /**
         *
         */
        public String city;

        /**
         *
         */
        public String langId;

        /**
         *
         */
        public String telNo;

        /**
         *
         */
        public String noticeNo;

        /**
         *
         */
        public Date noticeDate;

        /**
         *
         */
        public String regNo;

        /**
         *
         */
        public String bankAcctNo;

        /**
         *
         */
        public String vatNo;

        /**
         *
         */
        public String orgnsmDvsn;

        /**
         *
         */
        public String orgnsmBldng;

        /**
         *
         */
        public String orgnsmDbAllwdYesno;

        /**
         *
         */
        public Integer orgnsmPriceCffcnt;

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
        public String typEncId;

        /**
         *
         */
        public String elemEncId;

        /**
         *
         */
        public String prsnlRgstrtnFlg;

        /**
         *
         */
        public Integer pcRdctnCffcnt;

        /**
         *
         */
        public Integer valdtnRdctnCffcnt;

        /**
         *
         */
        public Date orgnsmSlsStartDate;

        /**
         *
         */
        public Date orgnsmSlsStopDate;

        /**
         *
         */
        public String invoiceTypeId;

        /**
         *
         */
        public String orgnsmTypeId;

        /**
         *
         */
        public String paymentNatrId;

        /**
         *
         */
        public String fixedVchrAmtFlg;

        /**
         *
         */
        public String productNatrId;

        /**
         *
         */
        public String productTypeId;

        /**
         *
         */
        public Integer stktRdctnCffcnt;

        /**
         *
         */
        public String fidAwdFlg;

        /**
         *
         */
        public String factOfficeId;

        /**
         *
         */
        public String fixedVchrAmtManyFlg;

        /**
         *
         */
        public String fidFrmlId;

        /**
         *
         */
        public String fidFrmlIdOrgnsm;
    }

    /**
     *
     * @author umesh
     * Create at 2020-10-09 01:56
     */
    @Data
    public static class CityNetSupplmnt {
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

    /**
     * @author umesh
     * Create at 2020-10-07 03:33
     */
    @Data
    public static class Calndr {

        /**
         *
         */
        public Date calndrDate;

        /**
         *
         */
        public String stsCd;

        /**
         *
         */
        public String calndrDayInWk;

        /**
         *
         */
        public String calndrDayInYr;

        /**
         *
         */
        public String calndrHoliday;

        /**
         *
         */
        public String calndrWkNo;

        /**
         *
         */
        public String calndrHighSeasn;

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
    }
}
