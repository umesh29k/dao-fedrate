package com.itpaths.rules.price.serice;

import com.itpaths.rules.price.api.Rules;
import com.itpaths.rules.price.model.dto.*;
import com.itpaths.rules.price.exception.ApiException;
import com.itpaths.rules.price.model.PriceRequest;
import com.itpaths.rules.price.model.PriceResult;
import com.itpaths.rules.price.util.CLASS_CD;
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
    private Double diabolo_amt_single = 0d;
    private Double diabolo_amt_total = 0d;
    private double qty;
    private String frml_id = null;
    private double classId;

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

    private PriceCode priceCode;
    private PcVoygr pcVoygr;
    private TktPrmtr tktPrmtr;
    private PcVoygrClass pcVoygrClass;

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
                for (PriceCode pc : priceCodes) {
                    priceCode = pc;
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
                                tktPrmtr = datRetService.getTicketParams();

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
        /**
         *
         // start implementation wido-310
         IF tkt_price_eur (calculated by srf__get_tkt_price) < 0 THEN return error SAS_PRICE_ERR
         // end implementation wido-310
         IF trf_pp_price_eur (calculated by srf__get_tkt_price) < 0 THEN return error SAS_PRICE_ERR
         */
        if (tkt_price_eur<0)
            try {
                throw new ApiException("SAS_PRICE_ERR");
            } catch (ApiException e) {
                e.printStackTrace();
            }
        else if (trf_pp_price_eur<0)
            try {
                throw new ApiException("SAS_PRICE_ERR");
            } catch (ApiException e) {
                e.printStackTrace();
            }

        System.out.println(formula.toString());
        return priceResult;
    }

    public double doClassic(PriceRequest priceRequest) {
        try {
            get_distances(priceRequest);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        get_params(priceRequest);
        if (priceCode.getPriceFrmlId().isEmpty())
            priceCode.setPriceFrmlId("RT_CLASSIC");
        try {
            trf_pp_price_eur = invokeForumla(frml_id);
        } catch (InvocationTargetException e) {
            priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
        }

        if (price_cd.get(priceCode.getPriceNatrId()).equalsIgnoreCase("RTP_T_PN_MTARIFF")
                || price_cd.get(priceCode.getPriceNatrId()).equalsIgnoreCase("RTP_T_PN_MTARIFF_PLUS")
                || price_cd.get(priceCode.getPriceNatrId()).equalsIgnoreCase("RTP_T_PN_MTARIFF_MAX")) {
            if (voygr_id.get(priceRequest.getVoygr_id()).equalsIgnoreCase("RTP_T_VY_ADULT")) {
                pcVoygr = datRetService.getPcVoyger(priceRequest.getVoygr_id());

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
                    tkt_price_eur = get_price_classic(priceRequest);
                    qty = priceRequest.getQty();
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
                        tkt_price_eur = get_price_classic(priceRequest);
                    }
                }
            }
        } else {
            params[6] = Double.valueOf(datRetService.getPcVoyger(priceRequest.getVoygr_id()).getPcRdctnCffcnt());
            tkt_price_eur = get_price_classic(priceRequest);
        }
        return tkt_price_eur;
    }

    private double get_price_classic(PriceRequest priceRequest) {
        double classId = 0;
        params[21] += diabolo_amt_total; // Do we add this to or need to be assigned??
        if (class_id.get(Double.parseDouble(priceRequest.getClass_id())).equalsIgnoreCase("RTP_T_CL_UPGRADE")) {
            params[20] = 99999d;
            if (priceCode.getPriceNatrId().equalsIgnoreCase("RTP_T_PN_CLASSIC_MAX")
                    || priceCode.getPriceNatrId().equalsIgnoreCase("RTP_T_PN_MTARIFF_MAX")) {
                classId = CLASS_CD.RTP_T_CL_FIRST.getKey();
                try {
                    PcVoygrClass pcVoygrClass = get_pc_voygr_class(priceRequest.getVoygr_id(),
                            priceRequest.getPrice_cd(), 4,
                            priceCode.getPcVrsn());
                    params[20] = pcVoygrClass.getPcMaxAmtEur() + diabolo_amt_single;
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
            params[11] = tktPrmtr.getTpNormCffcntClass1();
            params[13] = tktPrmtr.getTpNormMinPriceClass1Eur();
            params[15] = 0d;
            //execute formula
            double result1 = 0, result2 = 0;
            try {
                result1 = invokeForumla(frml_id);
                //on error SAS_TT_FORMULA_ERROR
            } catch (InvocationTargetException e) {
                priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
            }

            params[20] = 99999d;
            if (priceCode.getPriceNatrId().equalsIgnoreCase("RTP_T_PN_CLASSIC_MAX")
                    || priceCode.getPriceNatrId().equalsIgnoreCase("RTP_T_PN_MTARIFF_MAX")) {
                classId = CLASS_CD.RTP_T_CL_SECOND.getKey();
                try {
                    PcVoygrClass pcVoygrClass = get_pc_voygr_class(priceRequest.getVoygr_id(),
                            priceRequest.getPrice_cd(), 4,
                            priceCode.getPcVrsn());
                    params[20] = pcVoygrClass.getPcMaxAmtEur() + diabolo_amt_single;
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
            params[11] = (double) tktPrmtr.getTpNormMinPriceClass2();
            params[13] = tktPrmtr.getTpNormMinPriceClass2Eur();
            try {
                result2 = invokeForumla(frml_id);
                //on error SAS_TT_FORMULA_ERROR
            } catch (InvocationTargetException e) {
                priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
            }
            params[3] = result1;
            params[4] = result2;
            params[13] = tktPrmtr.getTpNormMinPriceUpclassEur();
            params[20] = 99999d;
            if (priceCode.getPriceNatrId().equalsIgnoreCase("RTP_T_PN_CLASSIC_MAX")
                    || priceCode.getPriceNatrId().equalsIgnoreCase("RTP_T_PN_MTARIFF_MAX")) {
                priceRequest.setClass_id(Integer.toString(CLASS_CD.RTP_T_CL_SECOND.getKey()));
                classId = Double.parseDouble(priceRequest.getClass_id());
                try {
                    PcVoygrClass pcVoygrClass = get_pc_voygr_class(priceRequest.getVoygr_id(),
                            priceRequest.getPrice_cd(), 4,
                            priceCode.getPcVrsn());
                    params[20] = pcVoygrClass.getPcMaxAmtEur() + diabolo_amt_single;
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
            try {
                //ex RT_CLASSIC_UPGRADE [ invoke upgrade forumla of retrieved on ]
                trf_pp_price_eur = invokeForumla(frml_id + "_UPGRADE");
                //on error SAS_TT_FORMULA_ERROR
            } catch (InvocationTargetException e) {
                priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
            }
        } else {
            try {
                //ex RT_CLASSIC_UPGRADE [ invoke upgrade forumla of retrieved on ]
                trf_pp_price_eur = invokeForumla(frml_id);
                //on error SAS_TT_FORMULA_ERROR
            } catch (InvocationTargetException e) {
                priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
            }
        }
        return 0;
    }

    private PcVoygrClass get_pc_voygr_class(String voygr_id, String priceCd, int i, Integer pcVrsn) throws ApiException {
        PcVoygrClass pcVoygrClass = datRetService.getPcVoygerClass(voygr_id, priceCd, i, pcVrsn);
        if (pcVoygrClass.getClassId() == null || pcVoygrClass.getPcMaxAmtEur() == null)
            throw new ApiException("SAS_VOYGR_CLASS_INV");
        return pcVoygrClass;
    }

    private void get_params(PriceRequest priceRequest) {
        tktPrmtr = datRetService.getTicketParams();
        params[1] = tktPrmtr.getTpNormFxdChargeEur();
        params[2] = sdf_cal_tkt_dstnc().get("taxable_distance"); //return taxable_distance
        params[5] = tktPrmtr.getTpNormUnitPriceEur();
        params[7] = tktPrmtr.getTpNrdctnFxdChargeEur();

        if (priceCode.getPcApplyRdctnFull().equalsIgnoreCase("Y"))
            params[8] = 0.0;
        else
            params[8] = 1.0;
        params[9] = (double) tktPrmtr.getTpNrdctnDstnc();
        params[10] = tktPrmtr.getTpAdtnlFxdChargeEur();
        params[12] = tktPrmtr.getTpAdtnlCffcnt();

        if (priceRequest.getClass_id() == "RTP_T_CL_FIRST" || priceRequest.getClass_id() == "RTP_T_CL_UPGRADE") {
            params[11] = tktPrmtr.getTpNormCffcntClass1();
            params[13] = tktPrmtr.getTpNormMinPriceClass1Eur();
        } else {
            params[11] = tktPrmtr.getTpNormCffcntClass2();
            params[13] = tktPrmtr.getTpNormMinPriceClass2Eur();
        }
        double via_kav = sdf_cal_tkt_dstnc().get("via_kav"); //returns via_kav
        params[14] = via_kav;
        if (via_kav == 0)
            params[19] = 0.0;
        else
            params[19] = 2.0;

        if (priceRequest.getMtrip_flag().equalsIgnoreCase("Y")) {
            params[16] = Double.parseDouble(priceCode.getMttNoOfTrips());
            params[17] = tktPrmtr.getTpMtripCffcnt();
            params[18] = 1.0;
        } else {
            params[16] = 1.0;
            params[17] = 1.0;
            if (priceRequest.getItnry_id().equalsIgnoreCase("1")) {
                params[18] = tktPrmtr.getTpNormCffcntOneWay();
            } else {
                params[18] = tktPrmtr.getTpNormCffcntBothWays();
            }
        }
        CityNetSupplmnt cityNetSupplmnt = datRetService.getCity_net_supplmnt();
        if (priceRequest.getCity_net_flag().equalsIgnoreCase("Y")) {
            params[1] = cityNetSupplmnt.getCndStktFxdChargeSEur();
        }
        if (params[20] == 0.0) {
            params[20] = (double) 99999;
        }
        if (priceCode.getPcUplftCffcnt() == 0.0)
            params[22] = 1.0;
        else
            params[22] = (double) priceCode.getPcUplftCffcnt();
        params[23] = (double) pcVoygr.getPcUplftAmtEur();
        if (!priceRequest.getOrgnsm_id().isEmpty()) {
            Orgnsm orgnsm = datRetService.getOrgnsm();
            if (orgnsm != null)
                params[24] = (double) orgnsm.getPcRdctnCffcnt();
        }
        params[27] = qty;
        Calndr calndr = datRetService.getCalndr();
        params[28] = Double.parseDouble(calndr.getCalndrDayInWk());
        if (calndr.getCalndrHoliday().equalsIgnoreCase("Y"))
            params[29] = 1.0;
        params[30] = sdf_cal_tkt_dstnc().get("departure-destination distance");
        params[31] = sdf_cal_tkt_dstnc().get("departure-via distance");
        params[32] = sdf_cal_tkt_dstnc().get("via-destination distance");
        params[33] = sdf_cal_tkt_dstnc().get("alternative-departure distance");
        params[35] = Double.parseDouble(priceRequest.getClass_id());
        params[36] = (double) diabolo_amt_single;
    }

    private Map<String, Double> sdf_cal_tkt_dstnc() {
        Map<String, Double> map = new HashMap<>();
        return map;
    }

    private double get_distances(PriceRequest priceRequest) throws ApiException {
        /**
         * What does it mean to:
         * taxable distance returned
         * and
         * taxable distance
         */
        double distance = 0;
        if (!priceRequest.getDprtr_tstatn().isEmpty() && !priceRequest.getDstntn_tstatn().isEmpty()) {
            sdf_cal_tkt_dstnc();
            if (distance > priceCode.getPcMaxDstncJrney()
                    || distance < priceCode.getPcMinDstncJrney())
                throw new ApiException("SAS_PC_DSTNC_INV");
        }
        return distance;
    }

    private void do_zone(PriceRequest priceRequest) throws ApiException {
        double distance = get_distances(priceRequest);
        //Does this sub-routine get_distance need params??
        PcLimit pcLimit = datRetService.getPcLimit(priceRequest.getPrice_cd(), priceCode.getPcVrsn(),
                priceRequest.getVoygr_id(),
                distance,
                qty
        );
        get_params(priceRequest);
        params[21] = diabolo_amt_total;
        params[20] = pcLimit.getPcLimitAmtEur();
        if (priceCode.getPriceFrmlId().isEmpty() || priceCode.getPriceFrmlId().equalsIgnoreCase("RT_CLASSIC")) {
            frml_id = "RT_ZONE";
        } else
            frml_id = priceCode.getPriceFrmlId();
        if (class_id.get(priceRequest.getClass_id()).equalsIgnoreCase("RTP_T_CL_FIRST")) {
            try {
                tkt_price_eur = invokeForumla(frml_id);
            } catch (InvocationTargetException e) {
                priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
            }
        } else if (class_id.get(priceRequest.getClass_id()).equalsIgnoreCase("RTP_T_CL_SECOND")) {
            params[11] = 1d;
            params[22] = 1d;
            params[23] = 0d;
            try {
                tkt_price_eur = invokeForumla(frml_id);
            } catch (InvocationTargetException e) {
                priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
            }
        } else if (class_id.get(priceRequest.getClass_id()).equalsIgnoreCase("RTP_T_CL_UPGRADE")) {
            double result1 = 0, result2 = 0;
            params[11] = tktPrmtr.getTpNormCffcntClass1();
            try {
                result1 = trf_pp_price_eur = invokeForumla(frml_id);
            } catch (InvocationTargetException e) {
                priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
            }
            params[11] = tktPrmtr.getTpNormCffcntClass2();
            try {
                result2 = trf_pp_price_eur = invokeForumla(frml_id);
            } catch (InvocationTargetException e) {
                priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
            }
            params[3] = result1;
            params[4] = result2;
            try {
                tkt_price_eur = trf_pp_price_eur = invokeForumla(frml_id + "_UPGRADE");
            } catch (InvocationTargetException e) {
                priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
            }
        }
    }

    private void do_zone_plus(PriceRequest priceRequest) {
        if (priceRequest.getEvent_only_flag().equalsIgnoreCase("Y"))
            tkt_price_eur = pcVoygr.getPcSuplmntAmtEur();
        else
            params[21] = pcVoygr.getPcSuplmntAmtEur();
        try {
            do_zone(priceRequest);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void do_mtariff_plus(PriceRequest priceRequest) {
        if (priceRequest.getEvent_only_flag().equalsIgnoreCase("Y"))
            tkt_price_eur = pcVoygr.getPcSuplmntAmtEur() * qty;
        else
            tkt_price_eur = (double) doClassic(priceRequest);
    }

    private double invokeForumla(String frml_id) throws InvocationTargetException {
        double result = 0;
        Method sumInstanceMethod;
        try {
            Double param[] = new Double[35];
            Double[] E1 = new Double[2];
            Double[] DS = new Double[2];
            sumInstanceMethod = FormulaUtil.class.getMethod(frml_id);
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

    public void do_fixed(PriceRequest priceRequest) {
        double distance;
        try {
            get_distances(priceRequest);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        get_params(priceRequest);
        params[2] = 0d;
        PcFtktPrice pcFtktPrice = datRetService.getPcFtktPrice(priceRequest.getPrice_cd(), priceCode.getPcVrsn(),
                priceRequest.getClass_id());
        if (tktTypeID.get(priceRequest.getTkt_type_id()).equalsIgnoreCase("RTP_T_TT_FIXED")
                || tktTypeID.get(priceRequest.getTkt_type_id()).equalsIgnoreCase("RTP_T_TT_AGLOCARD")) {
            params[20] = pcFtktPrice.getFtktPriceEur() + diabolo_amt_single;
        } else {
            double voyarId = Double.parseDouble(priceRequest.getVoygr_id());
            try {
                get_pc_voygr_class(priceRequest.getVoygr_id(), priceRequest.getPrice_cd(), 4, priceCode.getPcVrsn());
            } catch (ApiException e) {
                e.printStackTrace();
            }
            PcVoygrClass pcVoygrClass = datRetService.getPcVoygerClass(priceRequest.getVoygr_id(), priceRequest.getPrice_cd(),
                    Integer.parseInt(priceRequest.getClass_id()), priceCode.getPcVrsn());
            params[20] = pcVoygrClass.getPcDfltAmtEur() + diabolo_amt_single;
        }

        if (priceCode.getPriceFrmlId().isEmpty() || priceCode.getPriceFrmlId().equalsIgnoreCase("RT_CLASSIC")) {
            frmlId = "RT_FIXED";
        } else
            frmlId = priceCode.getPriceFrmlId();

        try {
            trf_pp_price_eur = invokeForumla(frml_id);
        } catch (InvocationTargetException e) {
            priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
        }
    }

    public void do_fixed_plus(PriceRequest priceRequest) {
        if (priceRequest.getEvent_only_flag().equalsIgnoreCase("Y")) {
            tkt_price_eur = pcVoygr.getPcSuplmntAmtEur();
        } else {
            params[21] = pcVoygr.getPcSuplmntAmtEur();
            do_fixed(priceRequest);
        }
        do_fixed_plus(priceRequest);
    }

    public void do_classic_plus(PriceRequest priceRequest) {
        if (priceRequest.getEvent_only_flag().equalsIgnoreCase("Y")) {
            if (priceRequest.getMtrip_flag().equalsIgnoreCase("Y"))
                tkt_price_eur = pcVoygr.getPcSuplmntAmtEur() * Double.parseDouble(priceCode.getMttNoOfTrips());
            else
                tkt_price_eur = pcVoygr.getPcSuplmntAmtEur();
        } else
            doClassic(priceRequest);
        do_classic_plus(priceRequest);
    }

    public void do_classic_max(PriceRequest priceRequest) {
        classId = Double.parseDouble(priceRequest.getClass_id());
        try {
            PcVoygrClass pcVoygrClass = get_pc_voygr_class(priceRequest.getVoygr_id(),
                    priceCode.getPriceCd(), 4,
                    priceCode.getPcVrsn());
            params[20]= pcVoygrClass.getPcMaxAmtEur()+diabolo_amt_single;
            doClassic(priceRequest);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
