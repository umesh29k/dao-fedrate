package com.itpaths.rules.price.serice;

import com.itpaths.rules.price.api.Rules;
import com.itpaths.rules.price.model.dto.*;
import com.itpaths.rules.price.exception.ApiException;
import com.itpaths.rules.price.model.PriceRequest;
import com.itpaths.rules.price.model.PriceResult;
import com.itpaths.rules.price.util.FormulaUtil;
import com.itpaths.rules.price.util.Price_Natr_Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceService {
    @Autowired
    private Rules rules;
    @Autowired
    private DataRetrievalService datRetService;
    Map<Integer, String> class_id = new HashMap();
    Map<Integer, String> price_cd = new HashMap<>();
    Map<String, String> tktTypeID = new HashMap<>();
    Map<String, String> voygr_id = new HashMap<>();
    private double tkt_price_eur = 0;
    private Double[] params = new Double[37];
    private Double trf_pp_price_eur = 0.0;
    private String frmlId = "";
    private Integer diabolo_amt_single = 0;

    public PriceService() {
        class_id.put(1, "RTP_T_CL_FIRST");
        class_id.put(2, "RTP_T_CL_SECOND");
        class_id.put(3, "RTP_T_CL_BOTH");
        class_id.put(4, "RTP_T_CL_IRRELEVANT");
        class_id.put(5, "RTP_T_CL_UPGRADE");

        price_cd.put(1, "RTP_T_PN_CLASSIC");
        price_cd.put(2, "RTP_T_PN_ZONE");
        price_cd.put(3, "RTP_T_PN_FIXED");
        price_cd.put(4, "RTP_T_PN_CLASSIC_PLUS");
        price_cd.put(5, "RTP_T_PN_ZONE_PLUS");
        price_cd.put(6, "RTP_T_PN_FIXED_PLUS");
        price_cd.put(7, "RTP_T_PN_CLASSIC_MAX");
        price_cd.put(8, "RTP_T_PN_GEOG");
        price_cd.put(9, "RTP_T_PN_GEOG_PLUS");
        price_cd.put(10, "RTP_T_PN_OTHER");
        price_cd.put(11, "RTP_T_PN_MTARIFF");
        price_cd.put(12, "RTP_T_PN_MTARIFF_PLUS");
        price_cd.put(13, "RTP_T_PN_MTARIFF_MAX");

        tktTypeID.put("0", "RTP_T_TT_SORRY_PASS"); //Sorry pass
        tktTypeID.put("1", "RTP_T_TT_SIMPLE"); //Simple ticket
        tktTypeID.put("2", "RTP_T_TT_COMBINED"); //Combined ticket
        tktTypeID.put("3", "RTP_T_TT_AGLOCARD"); //Multiple trip within agglom.
        tktTypeID.put("4", "RTP_T_TT_FIXED"); //Fixed price ticket
        tktTypeID.put("5", "RTP_T_TT_FIXED_BP"); //Fixed price bonus pass
        tktTypeID.put("6", "RTP_T_TT_WEEKEND"); //Weekend ticket
        tktTypeID.put("7", "RTP_T_TT_MINER"); //Mine worker ticket
        tktTypeID.put("8", "RTP_T_TT_CNTNGNT"); //Contingent event ticket
        tktTypeID.put("9", "RTP_T_TT_CNTNGNT_COMBINED"); //Combined contingent event ticket
        tktTypeID.put("A", "RTP_T_TT_FIXED_HPPNG"); //Happening fixed price ticket
        tktTypeID.put("V", "RTP_T_TT_CNTNGNT_HOTSPOT"); //Hotspot card

        voygr_id.put("1", "RTP_T_VY_ADULT");
        voygr_id.put("2", "RTP_T_VY_CHILD");
        voygr_id.put("3", "RTP_T_VY_BENE1");
        voygr_id.put("4", "RTP_T_VY_BENE2");
        voygr_id.put("5", "RTP_T_VY_BENE3");
        voygr_id.put("6", "RTP_T_VY_ADTNL_ADULT");
    }

    public PriceResult calculate(PriceRequest priceRequest) {
        /**
         * invoke rule
         */
        Formula formula = new Formula();
        PriceResult priceResult = new PriceResult();
        if (priceRequest.getPrice_cd() != null)
            if (!priceRequest.getPrice_cd().isEmpty()) {
                //get data from price_code for the given id
                List<PriceCode> priceCodes = datRetService.getPriceData(priceRequest.getPrice_cd());
                for (PriceCode priceCode : priceCodes) {
                    formula.setPrice_natr_id(priceCode.getPriceNatrId());
                    formula.setTkt_type_id(tktTypeID.get(priceCode.getTktTypeId()));

                    //Invoke Rules
                    formula = rules.getMethod(formula);

                    if (formula.getMethod() != null) {
                        if (!formula.getMethod().isEmpty())
                            tkt_price_eur = getTkt_price_eur(priceRequest, formula);
                        else if (formula.getStatus() == null)
                            formula.setStatus("SAS_PRICE_ERR");
                    } else if (formula.getStatus() == null)
                        formula.setStatus("SAS_PRICE_ERR");

                    if (priceCode.getTrfPpFrmlId().isEmpty() && !priceRequest.getOrgnsm_id().isEmpty()) {
                        priceCode.setTrfPpFrmlId("RT_CLASSIC_DEBET");
                    }
                    if (priceRequest.getClass_id() != null) {
                        if (!priceRequest.getClass_id().isEmpty()) {
                            if (class_id.get(Integer.parseInt(priceRequest.getClass_id()))
                                    .equalsIgnoreCase("RTP_T_CL_UPGRADE")) {
                                //calculate price 1st class
                                TktPrmtr tktPrmtr = datRetService.getTicketParams();

                                //Invoke Formula
                                double res1st = 0;
                                params[11] = tktPrmtr.getTpNormCffcntClass1();
                                params[13] = tktPrmtr.getTpNormMinPriceClass1Eur();
                                params[15] = 0.0;
                                //example RT_CLASSIC
                                //What is the formula, need to be invoked?
                                //if error, then throw SAS_TT_FORMULA_ERROR
                                try {
                                    trf_pp_price_eur = invokeForumla(priceCode);
                                } catch (InvocationTargetException e) {
                                    priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
                                }

                                //calculate price 2nd class
                                double res2nd = 0;
                                params[11] = tktPrmtr.getTpNormCffcntClass2();
                                params[13] = tktPrmtr.getTpNormMinPriceClass2Eur();
                                //Invoke Formula
                                //What is the formula, need to be invoked?
                                //if error, then throw SAS_TT_FORMULA_ERROR
                                //ex invoke RT_CLASSIC
                                try {
                                    trf_pp_price_eur = invokeForumla(priceCode);
                                } catch (InvocationTargetException e) {
                                    priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
                                }

                                //calculate price upgrade
                                params[25] = res1st;
                                params[26] = res2nd;
                                //ex RT_CLASSIC_UPGRADE
                                try {
                                    trf_pp_price_eur = invokeForumla(priceCode);
                                } catch (InvocationTargetException e) {
                                    priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
                                }
                                //if error, then throw SAS_TT_FORMULA_ERROR
                            } else {
                                //What is the formula, need to be invoked?
                                //if error, then throw SAS_TT_FORMULA_ERROR
                            }
                        }
                    }
                }
            }

        System.out.println(formula.toString());
        return priceResult;
    }

    public void doClassic(PriceCode priceCode, PriceRequest priceRequest) {
        get_distances();
        get_params();

        if (priceCode.getPriceFrmlId().isEmpty())
            priceCode.setPriceFrmlId("RT_CLASSIC");
        try {
            trf_pp_price_eur = invokeForumla(priceCode);
        } catch (InvocationTargetException e) {
            priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
        }

        if (price_cd.get(priceCode.getPriceNatrId()).equalsIgnoreCase("RTP_T_PN_MTARIFF")
                || price_cd.get(priceCode.getPriceNatrId()).equalsIgnoreCase("RTP_T_PN_MTARIFF_PLUS")
                || price_cd.get(priceCode.getPriceNatrId()).equalsIgnoreCase("RTP_T_PN_MTARIFF_MAX")) {
            if (voygr_id.get(priceRequest.getVoygr_id()).equalsIgnoreCase("RTP_T_VY_ADULT")) {
                PcVoygr pcVoygr = datRetService.getPcVoyger(priceRequest.getVoygr_id());

                params[6] = Double.parseDouble(pcVoygr.getPcRdctnCffcnt().toString());
                if (price_cd.get(priceCode.getPriceNatrId()).equalsIgnoreCase("RTP_T_PN_MTARIFF_PLUS")) {
                    params[21] = pcVoygr.getPcSuplmntAmtEur();
                    params[23] = Double.parseDouble(pcVoygr.getPcUplftAmtEur().toString());
                }
                params[20] = Double.parseDouble("99999");
                if (price_cd.get(priceCode.getPriceNatrId()).equalsIgnoreCase("RTP_T_PN_MTARIFF_MAX")) {
                    try {
                        PcVoygrClass pcVoygrClass = get_pc_voygr_class(priceRequest.getVoygr_id(),
                                priceCode.getPriceCd(), 4,
                                priceCode.getPcVrsn());
                        params[20] = Double.valueOf(pcVoygrClass.getPcMaxAmtEur() + diabolo_amt_single);
                    } catch (ApiException e) {
                        e.printStackTrace();
                    }
                    params[27] = 0.0;
                    tkt_price_eur = get_price_classic();
                    double qty = priceRequest.getQty();
                    qty = qty - 1;
                    if (qty > 0) {
                        params[20] = (double) 99999;
                        if (price_cd.get(priceCode.getPriceNatrId()).equalsIgnoreCase("RTP_T_PN_MTARIFF_MAX")) {
                            String voygrId = null;
                            for (String key : voygr_id.keySet()) {
                                if (voygr_id.get(key).equalsIgnoreCase("RTP_T_VY_ADTNL_ADULT")) {
                                    voygrId = key;
                                }
                            }
                            try {
                                PcVoygrClass pcVoygrClass = get_pc_voygr_class(voygrId, priceCode.getPriceCd(), 4,
                                        priceCode.getPcVrsn());
                                params[20] = Double.valueOf(pcVoygrClass.getPcMaxAmtEur() + diabolo_amt_single);
                            } catch (ApiException e) {
                                e.printStackTrace();
                            }
                            params[6] = Double.valueOf(datRetService.getPcVoyger(voygrId).getPcRdctnCffcnt());
                        } else if (price_cd.get(priceCode.getPriceNatrId()).equalsIgnoreCase("RTP_T_PN_MTARIFF_PLUS")) {
                            params[21] = pcVoygr.getPcSuplmntAmtEur();
                            params[23] = (double) pcVoygr.getPcUplftAmtEur();
                        }
                        tkt_price_eur = get_price_classic();
                    }
                }
            }
        } else {
            params[6] = Double.valueOf(datRetService.getPcVoyger(priceRequest.getVoygr_id()).getPcRdctnCffcnt());
            tkt_price_eur = get_price_classic();
        }
    }

    private double get_price_classic() {
        /**
         * Add diabolo_amt_total to parameter 21 of parameter array
         * IF class_id = RTP_T_CL_UPGRADE THEN
         *     /* Calculate price 1st class
         *     Set parameter 20 of parameter array = 99999
         *     IF price_natr_id of price_code = RTP_T_PN_CLASSIC_MAX or RTP_T_PN_MTARIFF_MAX THEN
         *         Set class_id = RTP_T_CL_FIRST
         *         CALL local subroutine get_pc_voygr_class
         *         Set parameter 20 of parameter array = pc_max_amt_eur of pc_voygr_class + diabolo_amt_single
         *     ENDIF
         *     Set parameter 11 of parameter array = tp_norm_cffcnt_class_1 of table tkt_prmtr
         *     Set parameter 13 of parameter array = tp_norm_min_price_class_1_eur of table tkt_prmtr
         *     Set parameter 15 = 0
         *     Execute formula using formula parser (parameter array as input) => giving result
         *     IF error in formula then return error SAS_TT_FORMULA_ERROR
         *     Set result 1st = result
         *
         *     /* Calculate price 2nd class
         *     Set parameter 20 of parameter array = 99999
         *     IF price_natr_id of price_code = RTP_T_PN_CLASSIC_MAX or RTP_T_PN_MTARIFF_MAX THEN
         *         Set class_id = RTP_T_CL_SECOND
         *         CALL local subroutine get_pc_voygr_class
         *         Set parameter 20 of parameter array = pc_max_amt_eur of pc_voygr_class + diabolo_amt_single
         *     ENDIF
         *     Set parameter 11 of parameter array = tp_norm_cffcnt_class_2 of table tkt_prmtr
         *     Set parameter 13 of parameter array = tp_norm_min_price_class_2_eur of table tkt_prmtr
         *     Execute formula using formula parser (parameter array as input) => giving result
         *     IF error in formula then return error SAS_TT_FORMULA_ERROR
         *     Set parameter 3 of parameter array = result
         *     Set parameter 4 of parameter array = result 1st
         *
         *     /* Calculate price upgrade
         *     Set parameter 13 of parameter array = tp_norm_min_price_upclass_eur of table tkt_prmtr
         *     Set parameter 20 of parameter array = 99999
         *     IF price_natr_id of price_code = RTP_T_PN_CLASSIC_MAX or RTP_T_PN_MTARIFF_MAX THEN
         *         Set class_id = class_id of incoming request
         *         CALL local subroutine get_pc_voygr_class
         *         Set parameter 20 of parameter array = pc_max_amt_eur of pc_voygr_class + diabolo_amt_single
         *     ENDIF
         *     Append _UPGRADE to original frml_id of tt_formula
         *     Get this formula from table tt_formula (field frml_strng)
         *     IF not found then return error SAS_DBF_TT_FORMULA
         *     Execute formula using formula parser (parameter array as input) => giving result
         * ELSE
         *     Execute formula (from frml_strng of table tt_formula) using formula parser (parameter array as input) => giving result
         * ENDIF
         * IF error in formula then return error SAS_TT_FORMULA_ERROR
         */
        return 0;
    }

    private PcVoygrClass get_pc_voygr_class(String voygr_id, String priceCd, int i, Integer pcVrsn) throws ApiException {
        PcVoygrClass pcVoygrClass = datRetService.getPcVoygerClass(voygr_id, priceCd, i, pcVrsn);
        if (pcVoygrClass.getClassId() == null || pcVoygrClass.getPcMaxAmtEur() == null)
            throw new ApiException("SAS_VOYGR_CLASS_INV");
        return pcVoygrClass;
    }

    private void get_params() {
        /**
         * Set all parameters to 0
         * Set parameter 1 of parameter array = tp_norm_fxd_charge_eur of tkt_prmtr
         * Set parameter 2 of parameter array = taxable distance returned by subroutine sdf_cal_tkt_dstnc
         * Set parameter 5 of parameter array = tp_norm_unit_price_eur of tkt_prmtr
         * Set parameter 7 of parameter array = tp_nrdctn_fxd_charge_eur of tkt_prmtr
         * IF pc_apply_rdctn_full of price_code = Y THEN
         *     Set parameter 8 of parameter array = 0
         * ELSE
         *     Set parameter 8 of parameter array = 1
         * ENDIF
         * Set parameter 9 of parameter array = tp_nrdctn_dstnc of tkt_prmtr
         * Set parameter 10 of parameter array = tp_adtnl_fxd_charge_eur of tkt_prmtr
         * Set parameter 12 of parameter array = tp_adtnl_cffcnt of tkt_prmtr
         * IF class_id = RTP_T_CL_FIRST or RTP_T_CL_UPGRADE THEN
         *     Set parameter 11 of parameter array = tp_norm_cffcnt_class_1 of tkt_prmtr
         *     Set parameter 13 of parameter array = tp_norm_min_price_class_1_eur of tkt_prmtr
         * ELSE
         *     Set parameter 11 of parameter array = tp_norm_cffcnt_class_2 of tkt_prmtr
         *     Set parameter 13 of parameter array = tp_norm_min_price_class_2_eur of tkt_prmtr
         * ENDIF
         * Set parameter 14 of parameter array = via_kav returned by subroutine sdf_cal_tkt_dstnc
         * IF via_kav = 0 THEN
         *     Set parameter 19 of parameter array = 0
         * ELSE
         *     Set parameter 19 of parameter array = 2
         * ENDIF
         * IF mtrip_flag of incoming request = Y THEN
         *     Set parameter 16 of parameter array = mtt_no_of_trips of price_code
         *     Set parameter 17 of parameter array = tp_mtrip_cffcnt of tkt_prmtr
         *     Set parameter 18 of parameter array = 1
         * ELSE
         *     Set parameter 16 of parameter array = 1
         *     Set parameter 17 of parameter array = 1
         *     IF itnry_id = 1 THEN
         *         Set parameter 18 of parameter array = tp_norm_cffcnt_one_way of tkt_prmtr
         *     ELSE
         *         Set parameter 18 of parameter array = tp_norm_cffcnt_both_way of tkt_prmtr
         *     ENDIF
         * ENDIF
         * IF city_net_flag of incoming request = Y THEN
         *     Set parameter 1 of parameter array = cnd_stkt_fxd_charge_s_eur of city_net_supplmnt
         * ENDIF
         * IF parameter 20 of parameter array = 0 THEN
         *     Set parameter 20 of parameter array = 99999
         * ENDIF
         * IF pc_uplft_cffcnt of price_code = 0 THEN
         *     Set parameter 22 of parameter array = 1
         * ELSE
         *     Set parameter 22 of parameter array = pc_uplft_cffcnt of price_code
         * ENDIF
         * Set parameter 23 of parameter array = pc_uplft_amt_eur of pc_voygr
         * IF orgnsm_id of incoming request filled THEN
         *     Get record from table orgnsm with orgnsm_id
         *     IF found THEN
         *         Set parameter 24 of parameter array = pc_rdctn_cffcnt of orgnsm
         *     ENDIF
         * ENDIF
         * Set parameter 27 of parameter array = qty
         * Get record from table calndr for traveldate
         * Set parameter 28 of parameter array = calndr_day_in_wk of calndr
         * IF calndr_holiday of calndr = Y THEN
         *     Set parameter 29 of parameter array = 1
         * ENDIF
         * Set parameter 30 of parameter array = departure-destination distance (set in sdf_cal_tkt_dstnc)
         * Set parameter 31 of parameter array = departure-via distance (set in sdf_cal_tkt_dstnc)
         * Set parameter 32 of parameter array = via-destination distance (set in sdf_cal_tkt_dstnc)
         * Set parameter 33 of parameter array = alternative-departure distance (set in sdf_cal_tkt_dstnc)
         * Set parameter 35 of parameter array = class_id
         * Set parameter 36 of parameter array = diabolo_amt_single
         */
    }

    private void get_distances() {
    }

    private void do_zone() {
        /**
         * CALL local subroutine get_distances
         * // start implementation wido-304
         * Read record from table pc_limit with price_cd,pc_vrsn,voygr_id,departure-destination distance,qty
         *            SELECT * INTO :pc_limit_rec FROM ssp_db_2.pc_limit t
         *               WHERE t.price_cd         =  :pc_limit_rec.price_cd
         *               AND   t.pc_vrsn          =  :pc_limit_rec.pc_vrsn
         *               AND   t.voygr_id         =  :pc_limit_rec.voygr_id
         *               AND   t.pc_dstnc         >= :pc_limit_rec.pc_dstnc
         *               AND   t.pc_no_in_grp     <= :pc_limit_rec.pc_no_in_grp
         *            ORDER BY t.pc_dstnc ASC,
         *                     t.pc_no_in_grp DESC
         *            LIMIT TO 1 ROWS;
         * // end implementation wido-304
         * CALL local subroutine get_params
         * Add diabolo_amt_total to parameter 21 of parameter array
         * Set parameter 20 of parameter array = pc_limit_amt_eur of table pc_limit
         * IF price_frml_id of price_code is empty or RT_CLASSIC THEN // implemented in WIDO-264
         *     Set frml_id = RT_ZONE
         * ELSE
         *     Set frml_id = price_frml_id of price_code
         * ENDIF
         * Get formula frml_id from table tt_formula (field frml_strng)
         * IF not found then return error SAS_DBF_TT_FORMULA
         * IF class_id = RTP_T_CL_FIRST THEN
         *     Execute formula using formula parser (parameter array as input) => giving result
         *     IF error in formula then return error SAS_TT_FORMULA_ERROR
         * ELSE IF class_id = RTP_T_CL_SECOND THEN
         *     Set parameter 11 of parameter array = 1
         *     Set parameter 22 of parameter array = 1
         *     Set parameter 23 of parameter array = 0
         *     Execute formula using formula parser (parameter array as input) => giving result
         *     IF error in formula then return error SAS_TT_FORMULA_ERROR
         * ELSE IF class_id = RTP_T_CL_UPGRADE THEN
         *     /* Calculate price 1st class
         *     Set parameter 11 of parameter array = tp_norm_cffcnt_class_1 of table tkt_prmtr
         *     Execute formula using formula parser (parameter array as input) => giving result
         *     IF error in formula then return error SAS_TT_FORMULA_ERROR
         *     Set result 1st = result
         *
         *     /* Calculate price 2nd class
         *     Set parameter 11 of parameter array = tp_norm_cffcnt_class_2 of table tkt_prmtr
         *     Execute formula using formula parser (parameter array as input) => giving result
         *     IF error in formula then return error SAS_TT_FORMULA_ERROR
         *     Set parameter 3 of parameter array = result
         *     Set parameter 4 of parameter array = result 1st
         *
         *     /* Calculate price upgrade
         *     Append _UPGRADE to original frml_id of tt_formula
         *     Get this formula from table tt_formula (field frml_strng)
         *     IF not found then return error SAS_DBF_TT_FORMULA
         *     Execute formula using formula parser (parameter array as input) => giving result
         *     IF error in formula then return error SAS_TT_FORMULA_ERROR
         * ENDIF
         * Set tkt_price_eur = result
         */
    }

    private void do_zone_plus() {
        /**
         * IF event_only_flag of incoming request = Y THEN
         *     Set tkt_price_eur = pc_suplmnt_amt_eur of pc_voygr
         * ELSE
         *     Set parameter 21 of parameter array = pc_suplmnt_amt_eur of pc_voygr
         *     Call local subroutine do_zone
         * ENDIF
         */
    }

    private void do_mtariff_plus() {
        /**
         * IF event_only_flag of incoming request = Y THEN
         *     Set tkt_price_eur = pc_suplmnt_amt_eur of pc_voygr * qty
         * ELSE
         *     CALL local subroutine do_classic, returns tkt_price_eur
         * ENDIF
         */
    }

    private double invokeForumla(PriceCode priceCode) throws InvocationTargetException {
        double result = 0;
        Method sumInstanceMethod;
        try {
            Double param[] = new Double[35];
            Double[] E1 = new Double[2];
            Double[] DS = new Double[2];
            sumInstanceMethod = FormulaUtil.class.getMethod(priceCode.getPriceFrmlId());
            FormulaUtil operationsInstance = new FormulaUtil(param, E1, DS);
            result = (Double) sumInstanceMethod.invoke(operationsInstance);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }

    private double getTkt_price_eur(PriceRequest priceRequest, Formula formula) {
        double result = 0;
        Method sumInstanceMethod;
        try {
            sumInstanceMethod = Price_Natr_Id.class.getMethod(formula.getMethod(), PriceRequest.class, Formula.class);
            Price_Natr_Id operationsInstance = new Price_Natr_Id();
            result
                    = (Double) sumInstanceMethod.invoke(operationsInstance, priceRequest, formula);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }
}
