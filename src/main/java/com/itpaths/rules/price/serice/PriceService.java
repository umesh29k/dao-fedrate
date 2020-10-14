package com.itpaths.rules.price.serice;

import com.itpaths.rules.price.api.Rules;
import com.itpaths.rules.price.dao.model.*;
import com.itpaths.rules.price.dao.repository.*;
import com.itpaths.rules.price.model.dto.*;
import com.itpaths.rules.price.exception.ApiException;
import com.itpaths.rules.price.model.PriceRequest;
import com.itpaths.rules.price.model.PriceResult;
import com.itpaths.rules.price.util.Price_Natr_Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.itpaths.rules.price.util.Common.invokeForumla;

@Service
public class PriceService {
    @Autowired
    private Rules rules;
    @Autowired
    private DataRetrievalService dataRetrievalService;
    @Autowired
    private PriceCodeRepository priceCodeRepo;
    @Autowired
            private TtFormulaRepository ttFormulaRepo;

    Map<Integer, String> class_id = new HashMap();
    Map<Integer, String> price_cd = new HashMap<>();
    Map<String, String> tktTypeID = new HashMap<>();
    Map<String, String> voygr_id = new HashMap<>();

    private Double tkt_price_eur = 0d;
    private Double[] params = new Double[37];
    private Double trf_pp_price_eur = 0.0;
    private String frml_id = null;
    private PriceCode priceCode;
    private TktPrmtr tktPrmtr;

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

    public List<PriceResult> calculate(PriceRequest priceRequest) throws ApiException {
        /**
         * invoke rule
         */
        Formula formula = new Formula();
        List<PriceResult> priceResults = new ArrayList<>();

        /**
         * IF tkt_type_id of table price_code = RTP_T_TT_CNTNGNT_HOTSPOT THEN
         *     tkt_price_eur was already set in routine do_validate_cntngnt_20
         */
        if (priceRequest.getTkt_type_id().equalsIgnoreCase("14")) { //RTP_T_TT_CNTNGNT_HOTSPOT
            PriceResult priceResult = new PriceResult();
            priceResult.setStatus("tkt_price_eur was already set in " +
                    "routine " +
                    "do_validate_cntngnt_20");
            priceResults.add(priceResult);
        } else if (priceRequest.getPrice_cd() != null) {
            if (!priceRequest.getPrice_cd().isEmpty()) {
                //get data from price_code for the given id
                List<PriceCode> priceCodes = priceCodeRepo.findByPriceCd(priceRequest.getPrice_cd());
                for (PriceCode pc : priceCodes) {
                    PriceResult priceResult = new PriceResult();
                    priceCode = pc;

                    formula.setPrice_natr_id(priceCode.getPriceNatrId());
                    //Invoke Rules
                    formula = rules.getMethod(formula);
                    System.out.println("INFO:: Formula Invoked: " + formula.toString());

                    if (formula.getMethod() != null) {
                        if (!formula.getMethod().isEmpty()) {
                            //invoke methods retrieved from the METHOD decision table
                            tkt_price_eur = getTkt_price_eur(priceRequest, formula, priceResult);
                            priceResult.setStatus(formula.getStatus());
                            priceResult.setTotal_price_eur(tkt_price_eur.toString());
                        } else if (formula.getStatus() == null)
                            formula.setStatus("SAS_PRICE_ERR");
                    } else if (formula.getStatus() == null)
                        priceResult.setStatus("SAS_PRICE_ERR");

                    //******* DROOLS : Start Rule 14 "set trf_pp_frml_id if orgnsm_id is filled "*************
                    if (priceCode.getTrfPpFrmlId().isEmpty() && !priceRequest.getOrgnsm_id().isEmpty()) {
                        priceCode.setTrfPpFrmlId("RT_CLASSIC_DEBET");
                    }
                    //******* DROOLS : end Rule 14 "price_natr_id unknown"*************
                    if (!priceCode.getTrfPpFrmlId().isEmpty()) {
                        List<TtFormula> ttFormula = ttFormulaRepo.findByFrmlId(priceCode.getTrfPpFrmlId());
                        if(ttFormula.size() == 0)
                            priceResult.setStatus(priceResult.getStatus() + "; " + "SAS_DBF_TT_FORMULA");
                        else
                            frml_id = ttFormula.get(0).getFrmlId();
                    }

                    if (priceRequest.getClass_id() != null) {
                        if (!priceRequest.getClass_id().isEmpty()) {
                            if (class_id.get(Integer.parseInt(priceRequest.getClass_id()))
                                    .equalsIgnoreCase("RTP_T_CL_UPGRADE")) {
                                //calculate price 1st class
                                tktPrmtr = dataRetrievalService.getTicketParams();

                                //Invoke Formula
                                double res1st = 0;
                                params[11] = tktPrmtr.getTpNormCffcntClass1();
                                params[13] = tktPrmtr.getTpNormMinPriceClass1Eur();
                                params[15] = 0.0;
                                //example RT_CLASSIC
                                //What is the formula, need to be invoked?
                                //if error, then throw SAS_TT_FORMULA_ERROR
                                try {
                                    res1st = invokeForumla(frml_id);
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
                                    res2nd = invokeForumla(frml_id);
                                } catch (InvocationTargetException e) {
                                    priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
                                }

                                //calculate price upgrade
                                params[25] = res1st;
                                params[26] = res2nd;
                                //ex RT_CLASSIC_UPGRADE
                                try {
                                    trf_pp_price_eur = invokeForumla(frml_id + "_UPGRADE");
                                    priceResult.setTotal_pp_price_eur(trf_pp_price_eur.toString());
                                } catch (InvocationTargetException e) {
                                    priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
                                }
                                //if error, then throw SAS_TT_FORMULA_ERROR
                            } else {
                                try {
                                    trf_pp_price_eur = invokeForumla(frml_id);
                                    priceResult.setTotal_pp_price_eur(trf_pp_price_eur.toString());
                                } catch (InvocationTargetException e) {
                                    priceCode.setTrfPpFrmlId("SAS_TT_FORMULA_ERROR");
                                }
                                //What is the formula, need to be invoked?
                                //if error, then throw SAS_TT_FORMULA_ERROR
                            }
                        }
                    }
                    /**
                     *
                     // start implementation wido-310
                     IF tkt_price_eur (calculated by srf__get_tkt_price) < 0 THEN return error SAS_PRICE_ERR
                     // end implementation wido-310
                     IF trf_pp_price_eur (calculated by srf__get_tkt_price) < 0 THEN return error SAS_PRICE_ERR
                     */
                    if (tkt_price_eur < 0)
                        priceResult.setStatus(priceResult.getStatus() + "; tkt_price_eur_status: SAS_PRICE_ERR");
                    else if (trf_pp_price_eur < 0)
                        priceResult.setStatus(priceResult.getStatus() + "; trf_pp_price_eur_status: SAS_PRICE_ERR");
                    priceResults.add(priceResult);
                }
            }
        }
        return priceResults;
    }

    //invoke methods retrieved from the METHOD decision table
    private double getTkt_price_eur(PriceRequest priceRequest, Formula formula, PriceResult priceResult) {
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
