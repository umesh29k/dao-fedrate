package com.itpaths.rules.price.serice;

import com.itpaths.rules.price.api.Rules;
import com.itpaths.rules.price.dao.model.*;
import com.itpaths.rules.price.dao.repository.*;
import com.itpaths.rules.price.model.dto.*;
import com.itpaths.rules.price.exception.ApiException;
import com.itpaths.rules.price.model.PriceRequest;
import com.itpaths.rules.price.model.PriceResult;
import com.itpaths.rules.price.util.Price_Natr_Id;
import com.itpaths.rules.price.util.econst.Constants;
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
    @Autowired
    private TktPrmtrRepository tktPrmtrRepo;
    @Autowired
    private PcVoygrRepository pcVoygrRepo;
    @Autowired
    private PcVoygrClassRepository pcVoygrClassRepo;
    @Autowired
    private CityNetSupplmntRepository cityNetSupplmntRepo;
    @Autowired
    private OrgnsmRepository orgnsmRepo;
    @Autowired
    private CalndrRepository calndrRepo;
    @Autowired
    private PcLimitRepository pcLimitRepo;
    @Autowired
    private PcFtktPriceRepository pcFtktPriceRepo;

    private Double tkt_price_eur = 0d;
    private Double[] params = new Double[37];
    private Double[] E1 = new Double[2];
    private Double[] DS = new Double[2];
    private Double trf_pp_price_eur = 0.0;
    private String frml_id = null;
    private PriceCode priceCode;
    private TktPrmtr tktPrmtr;

    public PriceService() {
        for (int i = 0; i < params.length; i++) {
            params[i] = 0d;
        }
        for (int i = 0; i < E1.length; i++) {
            E1[i] = 0d;
        }
        for (int i = 0; i < DS.length; i++) {
            DS[i] = 0d;
        }
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
                    int pNatrId = Integer.parseInt(priceCode.getPriceNatrId());
                    formula.setPrice_natr_id(Constants.price_cd.get(pNatrId));
                    //Invoke Rules
                    formula = rules.getMethod(formula);
                    System.out.println("INFO:: Formula Invoked: " + formula.toString());

                    if (formula.getMethod() != null) {
                        if (!formula.getMethod().isEmpty()) {
                            //invoke methods retrieved from the METHOD decision table
                            try {
                                priceResult = getTktPriceEur(priceRequest, formula, priceResult);
                            }
                            catch(Exception e){
                                priceResult.setStatus(e.getMessage());
                            }
                        } else if (formula.getStatus() == null)
                            formula.setStatus("SAS_PRICE_ERR");
                    } else if (formula.getStatus() == null)
                        priceResult.setStatus("SAS_PRICE_ERR");

                    //******* DROOLS : Start Rule 14 "set trf_pp_frml_id if orgnsm_id is filled "*************
                    if (priceCode.getTrfPpFrmlId() == null && !priceRequest.getOrgnsm_id().isEmpty())
                        priceCode.setTrfPpFrmlId("RT_CLASSIC_DEBET");
                    else if (priceCode.getTrfPpFrmlId().isEmpty() && !priceRequest.getOrgnsm_id().isEmpty()) {
                        priceCode.setTrfPpFrmlId("RT_CLASSIC_DEBET");
                    }
                    //******* DROOLS : end Rule 14 "price_natr_id unknown"*************
                    if (!priceCode.getTrfPpFrmlId().isEmpty()) {
                        List<TtFormula> ttFormula = ttFormulaRepo.findByFrmlId(priceCode.getTrfPpFrmlId());
                        if (ttFormula.size() == 0)
                            priceResult.setStatus(priceResult.getStatus() + "; " + "SAS_DBF_TT_FORMULA");
                        else
                            frml_id = ttFormula.get(0).getFrmlId();
                    }

                    if (priceRequest.getClass_id() != null) {
                        if (!priceRequest.getClass_id().isEmpty()) {
                            if (Constants.class_id.get(Integer.parseInt(priceRequest.getClass_id())) != null)
                                if (Constants.class_id.get(Integer.parseInt(priceRequest.getClass_id()))
                                        .equalsIgnoreCase("RTP_T_CL_UPGRADE")) {
                                    //calculate price 1st class
                                    tktPrmtr = tktPrmtrRepo.find();

                                    //Invoke Formula
                                    double res1st = 0;
                                    params[11] = tktPrmtr.getTpNormCffcntClass1();
                                    params[13] = tktPrmtr.getTpNormMinPriceClass1Eur();
                                    params[15] = 0.0;
                                    //example RT_CLASSIC
                                    //What is the formula, need to be invoked?
                                    //if error, then throw SAS_TT_FORMULA_ERROR
                                    try {
                                        res1st = invokeForumla(frml_id, E1, DS, params);
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
                                        res2nd = invokeForumla(frml_id, E1, DS, params);
                                    } catch (InvocationTargetException e) {
                                        priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
                                    }

                                    //calculate price upgrade
                                    params[25] = res1st;
                                    params[26] = res2nd;
                                    //ex RT_CLASSIC_UPGRADE
                                    try {
                                        trf_pp_price_eur = invokeForumla(frml_id + "_UPGRADE", E1, DS, params);
                                        priceResult.setTotal_pp_price_eur(trf_pp_price_eur);
                                    } catch (InvocationTargetException e) {
                                        priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
                                    }
                                    //if error, then throw SAS_TT_FORMULA_ERROR
                                } else {
                                    try {
                                        trf_pp_price_eur = invokeForumla(frml_id, E1, DS, params);
                                        priceResult.setTotal_pp_price_eur(trf_pp_price_eur);
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
    private PriceResult getTktPriceEur(PriceRequest priceRequest, Formula formula, PriceResult priceResult) throws ApiException {
        double result = 0;
        PriceResult pr = new PriceResult();
        Method sumInstanceMethod;
        try {
            sumInstanceMethod = Price_Natr_Id.class.getMethod(formula.getMethod(), PriceRequest.class);
            Price_Natr_Id price_natr_id = new Price_Natr_Id(priceCodeRepo, tktPrmtrRepo,
                    pcVoygrRepo, pcVoygrClassRepo, cityNetSupplmntRepo, orgnsmRepo, calndrRepo, pcLimitRepo, pcFtktPriceRepo, ttFormulaRepo,
                    E1, DS, params, priceCode, formula);
            result
                    = (Double) sumInstanceMethod.invoke(price_natr_id, priceRequest);
            if (formula.getMethod().equalsIgnoreCase("do_classic")) {
                pr = price_natr_id.do_classic(priceRequest);
            } else if (formula.getMethod().equalsIgnoreCase("do_classic_max")) {
                pr = price_natr_id.do_classic_max(priceRequest);
            } else if (formula.getMethod().equalsIgnoreCase("do_classic_plus")) {
                pr = price_natr_id.do_classic_plus(priceRequest);
            } else if (formula.getMethod().equalsIgnoreCase("do_fixed")) {
                pr = price_natr_id.do_fixed(priceRequest);
            } else if (formula.getMethod().equalsIgnoreCase("do_fixed_plus")) {
                pr = price_natr_id.do_fixed_plus(priceRequest);
            } else if (formula.getMethod().equalsIgnoreCase("do_zone")) {
                pr = price_natr_id.do_zone(priceRequest);
            } else if (formula.getMethod().equalsIgnoreCase("do_zone_plus")) {
                pr = price_natr_id.do_zone_plus(priceRequest);
            } else if (formula.getMethod().equalsIgnoreCase("do_classic")) {
                pr = price_natr_id.do_classic(priceRequest);
            } else if (formula.getMethod().equalsIgnoreCase("do_mtariff_plus")) {
                pr = price_natr_id.do_mtariff_plus(priceRequest);
            } else if (formula.getMethod().equalsIgnoreCase("do_classic")) {
                pr = price_natr_id.do_classic(priceRequest);
            }
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return pr;
    }
}
