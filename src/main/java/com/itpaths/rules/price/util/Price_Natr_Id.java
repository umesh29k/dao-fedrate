package com.itpaths.rules.price.util;

import com.itpaths.rules.price.dao.model.*;
import com.itpaths.rules.price.dao.repository.*;
import com.itpaths.rules.price.exception.ApiException;
import com.itpaths.rules.price.model.PriceRequest;
import com.itpaths.rules.price.model.PriceResult;
import com.itpaths.rules.price.model.dto.Formula;
import com.itpaths.rules.price.service.DataRetrievalService;
import com.itpaths.rules.price.util.econst.CLASS_CD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.itpaths.rules.price.util.econst.Constants.*;

public class Price_Natr_Id {
    private DataRetrievalService dataRetrievalService;
    private PriceCodeRepository priceCodeRepo;
    private TktPrmtrRepository tktPrmtrRepo;
    private PcVoygrRepository pcVoygrRepo;
    private PcVoygrClassRepository pcVoygrClassRepo;
    private CityNetSupplmntRepository cityNetSupplmntRepo;
    private OrgnsmRepository orgnsmRepo;
    private CalndrRepository calndrRepo;
    private PcLimitRepository pcLimitRepo;
    private PcFtktPriceRepository pcFtktPriceRepo;
    private TtFormulaRepository ttFormulaRepo;
    private StationsDistancesRepository stationsDistancesRepo;
    private Double qty;
    private String frml_id = null;
    private double classId;

    private Double diabolo_amt_single = 0d;
    private Double diabolo_amt_total = 0d;

    private Double tkt_price_eur = 0d;

    private Double trf_pp_price_eur = 0.0;
    private PriceCode priceCode;
    private PcVoygr pcVoygr;
    private TktPrmtr tktPrmtr;
    private PcVoygrClass pcVoygrClass;
    private Formula formula;
    private Double[] E1;
    private Double[] DS;
    private Double[] params;
    //private PriceResult priceResult;

    public Price_Natr_Id(PriceCodeRepository priceCodeRepo, TktPrmtrRepository tktPrmtrRepo,
                         PcVoygrRepository pcVoygrRepo, PcVoygrClassRepository pcVoygrClassRepo, CityNetSupplmntRepository cityNetSupplmntRepo, OrgnsmRepository orgnsmRepo, CalndrRepository calndrRepo, PcLimitRepository pcLimitRepo, PcFtktPriceRepository pcFtktPriceRepo, TtFormulaRepository ttFormulaRepo,
                         Double[] E1, Double[] DS, Double[] params, PriceCode priceCode, Formula formula, StationsDistancesRepository stationsDistancesRepo
    ) {
        this.priceCodeRepo = priceCodeRepo;
        this.tktPrmtrRepo = tktPrmtrRepo;
        this.pcVoygrRepo = pcVoygrRepo;
        this.pcVoygrClassRepo = pcVoygrClassRepo;
        this.cityNetSupplmntRepo = cityNetSupplmntRepo;
        this.orgnsmRepo = orgnsmRepo;
        this.calndrRepo = calndrRepo;
        this.pcLimitRepo = pcLimitRepo;
        this.pcFtktPriceRepo = pcFtktPriceRepo;
        this.ttFormulaRepo = ttFormulaRepo;
        this.E1 = E1;
        this.DS = DS;
        this.params = params;
        this.priceCode = priceCode;
        this.formula = formula;
        this.stationsDistancesRepo = stationsDistancesRepo;
    }

    public PriceResult do_classic(PriceRequest priceRequest) {
        PriceResult pr = new PriceResult();
        try {
            params[30] = get_distances(priceRequest);
        } catch (ApiException e) {
            formula.setStatus(e.getMsg());
            e.printStackTrace();
        }
        get_params(priceRequest);
        if (priceCode.getPriceFrmlId().isEmpty())
            priceCode.setPriceFrmlId("RT_CLASSIC");
        frml_id = priceCode.getPriceFrmlId();
        try {
            trf_pp_price_eur = Common.invokeForumla(frml_id, E1, DS, params);
            pr.setTotal_pp_price_eur(trf_pp_price_eur);
        } catch (Exception e) {
            priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
        }
        if (priceCode.getPriceNatrId() != null)
            if (price_cd.get(priceCode.getPriceNatrId()) != null)
                if (price_cd.get(priceCode.getPriceNatrId()).equalsIgnoreCase("RTP_T_PN_MTARIFF")
                        || price_cd.get(priceCode.getPriceNatrId()).equalsIgnoreCase("RTP_T_PN_MTARIFF_PLUS")
                        || price_cd.get(priceCode.getPriceNatrId()).equalsIgnoreCase("RTP_T_PN_MTARIFF_MAX")) {
                    if (priceRequest.getVoygr_id() != null)
                        if (voygr_id.get(priceRequest.getVoygr_id()) != null)
                            if (voygr_id.get(priceRequest.getVoygr_id()).equalsIgnoreCase("RTP_T_VY_ADULT")) {
                                pcVoygr = pcVoygrRepo.findByVoygrId(priceRequest.getVoygr_id());
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
                                            PcVoygr pcVoygr = pcVoygrRepo.findByVoygrId(voygrId);
                                            params[6] = Double.valueOf(pcVoygr.getPcRdctnCffcnt());
                                        } else if (price_cd.get(priceCode.getPriceNatrId()).equalsIgnoreCase("RTP_T_PN_MTARIFF_PLUS")) {
                                            params[21] = pcVoygr.getPcSuplmntAmtEur();
                                            params[23] = (double) pcVoygr.getPcUplftAmtEur();
                                        }
                                        tkt_price_eur += get_price_classic(priceRequest);
                                    }
                                }
                            }
                } else {
                    PcVoygr pcVoygr = pcVoygrRepo.findByVoygrId(priceRequest.getVoygr_id());
                    if (pcVoygr != null)
                        params[6] = Double.valueOf(pcVoygr.getPcRdctnCffcnt());
                    tkt_price_eur = get_price_classic(priceRequest);
                }
        pr.setTotal_price_eur(tkt_price_eur);
        return pr;
    }

    private double get_price_classic(PriceRequest priceRequest) {
        double classId = 0;
        params[21] += diabolo_amt_total;
        if (priceRequest.getClass_id() != null)
            if (class_id.get(Integer.parseInt(priceRequest.getClass_id())) != null)// Do we add this to or need to be assigned??
                if (class_id.get(Integer.parseInt(priceRequest.getClass_id())).equalsIgnoreCase("RTP_T_CL_UPGRADE")) {
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
                        result1 = Common.invokeForumla(frml_id, E1, DS, params);
                        //on error SAS_TT_FORMULA_ERROR
                    } catch (Exception e) {
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
                        result2 = Common.invokeForumla(frml_id, E1, DS, params);
                        //on error SAS_TT_FORMULA_ERROR
                    } catch (Exception e) {
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
                        trf_pp_price_eur = Common.invokeForumla(frml_id + "_UPGRADE", E1, DS, params);
                        //on error SAS_TT_FORMULA_ERROR
                    } catch (Exception e) {
                        priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
                    }
                } else {
                    try {
                        //ex RT_CLASSIC_UPGRADE [ invoke upgrade forumla of retrieved on ]
                        trf_pp_price_eur = Common.invokeForumla(frml_id, E1, DS, params);
                        //on error SAS_TT_FORMULA_ERROR
                    } catch (Exception e) {
                        priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
                    }
                }
        return trf_pp_price_eur;
    }

    private PcVoygrClass get_pc_voygr_class(String voygr_id, String priceCd, int classId, Integer pcVrsn) throws ApiException {
        String classIdStr = class_id.get(classId);
        List<PcVoygrClass> pcVoygrClass = pcVoygrClassRepo.findByPriceCdAndVoygrIdAndPcVrsnAndClassId(priceCd, voygr_id, pcVrsn, classIdStr);
        if (pcVoygrClass.size() == 0) {
            pcVoygrClass = pcVoygrClassRepo.findByPriceCdAndVoygrIdAndPcVrsnAndClassId(voygr_id,
                    priceCd, pcVrsn, "RTP_T_CL_IRRELEVANT");
        }
        if (pcVoygrClass.size() == 0)
            throw new ApiException("SAS_VOYGR_CLASS_INV");
        return pcVoygrClass.get(0);
    }

    private void get_params(PriceRequest priceRequest) {
        tktPrmtr = tktPrmtrRepo.find();
        params[1] = tktPrmtr.getTpNormFxdChargeEur();
        if (sdf_cal_tkt_dstnc(priceRequest).get("taxable_distance") != null)
            params[2] = sdf_cal_tkt_dstnc(priceRequest).get("taxable_distance"); //return taxable_distance
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
        double via_kav = 0;
        if (sdf_cal_tkt_dstnc(priceRequest).get("via_kav") != null)
            via_kav = sdf_cal_tkt_dstnc(priceRequest).get("via_kav"); //returns via_kav
        params[14] = via_kav;
        if (via_kav == 0)
            params[19] = 0.0;
        else
            params[19] = 2.0;

        if (priceRequest.getMtrip_flag().equalsIgnoreCase("Y")) {
            params[16] = priceCode.getMttNoOfTrips().doubleValue();
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
        CityNetSupplmnt cityNetSupplmnt = cityNetSupplmntRepo.findById(1).get(0);
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
        if (pcVoygr == null)
            pcVoygr = pcVoygrRepo.findByVoygrId(priceRequest.getVoygr_id());
        if (pcVoygr != null)
            params[23] = (double) pcVoygr.getPcUplftAmtEur();
        if (!priceRequest.getOrgnsm_id().isEmpty()) {
            List<Orgnsm> orgnsms = orgnsmRepo.findByOrgnsmId(priceRequest.getOrgnsm_id());
            if (orgnsms.size() > 0) {
                Orgnsm orgnsm = orgnsms.get(0);
                if (orgnsm != null)
                    params[24] = orgnsm.getPcRdctnCffcnt();
            }
        }
        params[27] = qty;
        //Get today's date
        long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateForm = sdf.format(new java.util.Date(time));
        java.util.Date dt = null;
        try {
            dt = sdf.parse(dateForm);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calndr calndr = null;
        try {
            calndr = calndrRepo.findByCalndrDate(dateForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (calndr != null) {
            params[28] = calndr.getCalndrDayInWk().doubleValue();
            if (calndr.getCalndrHoliday().equalsIgnoreCase("Y"))
                params[29] = 1.0;
        }
        if (sdf_cal_tkt_dstnc(priceRequest).get("departure_destination_distance") != null)
            params[30] = sdf_cal_tkt_dstnc(priceRequest).get("departure_destination_distance");
        /*if (sdf_cal_tkt_dstnc(priceRequest).get("departure-via distance") != null)
            params[31] = sdf_cal_tkt_dstnc(priceRequest).get("departure-via distance");
        if (sdf_cal_tkt_dstnc(priceRequest).get("via-destination distance") != null)
            params[32] = sdf_cal_tkt_dstnc(priceRequest).get("via-destination distance");
        if (sdf_cal_tkt_dstnc(priceRequest).get("alternative-departure distance") != null)
            params[33] = sdf_cal_tkt_dstnc(priceRequest).get("alternative-departure distance");*/
        params[35] = Double.parseDouble(priceRequest.getClass_id());
        params[36] = (double) diabolo_amt_single;
    }

    private Map<String, Double> sdf_cal_tkt_dstnc(PriceRequest priceRequest) {
        Map<String, Double> map = new HashMap<>();
        StationsDistances stationsDistance = null;
        double distance = 0;
        double actual_distance_1 = 0, actual_distance_2 = 0;

        stationsDistance = stationsDistancesRepo.findByDstncFromTstatnIdAndDstncToTstatnId(priceRequest.getDprtr_tstatn(), priceRequest.getDstntn_tstatn());

        if (stationsDistance != null) {
            distance = Integer.parseInt(stationsDistance.getTstatnInterDstnc());
            map.put("departure_destination_distance", distance);
        }
        else{
            map.put("SAS_DSTNC_NOTFOUND", 0d);
        }

        return map;
    }

    public Double sdf_cal_tkt_tax_dstnc(){
        double distance = 0;
        double actual_distance_1 = 0, actual_distance_2 = 0;
        double result = 0;
        if(distance > priceCode.getPcMaxDstncTaxbl())
                distance = priceCode.getPcMaxDstncTaxbl();
        else if(distance<priceCode.getPcMinDstncTaxbl())
            distance = priceCode.getPcMinDstncTaxbl();

        /*Get tkt_cdb_frml_id from table tkt_c_d_brkpnt
        with tp_vrsn of entry of tkt_prmtr based on traveldate
        and tkt_cdb_dstnc >= actual distance
        Get formula tkt_cdb_frml_id of table tkt_c_d_brkpnt from table tt_formula (field frml_strng)
        IF not found then return error SAS_DBF_TT_FORMULA
        Set parameter 1 of parameter array = actual distance
        Execute formula using formula parser (parameter array as input) => giving result
        IF error in formula then return error SAS_TT_FORMULA_ERROR
        taxable distance = result
        */
        return result;
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
            distance = sdf_cal_tkt_dstnc(priceRequest).get("taxable_distance");
            //if (distance > priceCode.getPcMaxDstncJrney()
            //       || distance < priceCode.getPcMinDstncJrney())
            //throw new ApiException("SAS_PC_DSTNC_INV");
        }
        return distance;
    }

    public PriceResult do_zone(PriceRequest priceRequest) throws ApiException {
        PriceResult pr = new PriceResult();
        Double distance = get_distances(priceRequest);
        params[30] = distance;
        //Does this sub-routine get_distance need params??
        PcLimit pcLimit = pcLimitRepo.findByPriceCdAndPcVrsnAndVoygrIdAndPcDstncAndPcNoInGrp(priceRequest.getPrice_cd(),
                priceCode.getPcVrsn(),
                priceRequest.getVoygr_id(),
                distance.intValue(),
                qty.intValue());
        get_params(priceRequest);
        params[21] = diabolo_amt_total;
        params[20] = pcLimit.getPcLimitAmtEur();
        if (priceCode.getPriceFrmlId().isEmpty() || priceCode.getPriceFrmlId().equalsIgnoreCase("RT_CLASSIC")) {
            frml_id = "RT_ZONE";
        } else
            frml_id = priceCode.getPriceFrmlId();
        if (class_id.get(priceRequest.getClass_id()).equalsIgnoreCase("RTP_T_CL_FIRST")) {
            try {
                tkt_price_eur = Common.invokeForumla(frml_id, E1, DS, params);
                pr.setTotal_price_eur(tkt_price_eur);
            } catch (Exception e) {
                priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
            }
        } else if (class_id.get(priceRequest.getClass_id()).equalsIgnoreCase("RTP_T_CL_SECOND")) {
            params[11] = 1d;
            params[22] = 1d;
            params[23] = 0d;
            try {
                tkt_price_eur = Common.invokeForumla(frml_id, E1, DS, params);
                pr.setTotal_price_eur(tkt_price_eur);
            } catch (Exception e) {
                priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
            }
        } else if (class_id.get(priceRequest.getClass_id()).equalsIgnoreCase("RTP_T_CL_UPGRADE")) {
            double result1 = 0, result2 = 0;
            params[11] = tktPrmtr.getTpNormCffcntClass1();
            try {
                result1 = trf_pp_price_eur = Common.invokeForumla(frml_id, E1, DS, params);
                pr.setTotal_pp_price_eur(trf_pp_price_eur);
            } catch (Exception e) {
                priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
            }
            params[11] = tktPrmtr.getTpNormCffcntClass2();
            try {
                result2 = trf_pp_price_eur = Common.invokeForumla(frml_id, E1, DS, params);
            } catch (Exception e) {
                priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
            }
            params[3] = result1;
            params[4] = result2;
            try {
                tkt_price_eur = trf_pp_price_eur = Common.invokeForumla(frml_id + "_UPGRADE", E1, DS, params);
                pr.setTotal_price_eur(tkt_price_eur);
            } catch (Exception e) {
                priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
                pr.setStatus(priceCode.getTrfPpFrmlId());
            }
        }
        return pr;
    }

    public PriceResult do_zone_plus(PriceRequest priceRequest) {
        PriceResult pr = new PriceResult();
        if (priceRequest.getEvent_only_flag().equalsIgnoreCase("Y"))
            tkt_price_eur = pcVoygr.getPcSuplmntAmtEur();
        else
            params[21] = pcVoygr.getPcSuplmntAmtEur();
        try {
            tkt_price_eur += do_zone(priceRequest).getTotal_price_eur();
        } catch (ApiException e) {
            pr.setStatus(e.getMsg());
        }
        pr.setTotal_price_eur(tkt_price_eur);
        return pr;
    }

    public PriceResult do_mtariff_plus(PriceRequest priceRequest) {
        PriceResult pr = new PriceResult();
        if (priceRequest.getEvent_only_flag().equalsIgnoreCase("Y"))
            tkt_price_eur = pcVoygr.getPcSuplmntAmtEur() * qty;
        else
            tkt_price_eur = do_classic(priceRequest).getTotal_price_eur();
        pr.setTotal_price_eur(tkt_price_eur);
        return pr;
    }

    public PriceResult do_fixed(PriceRequest priceRequest) {
        PriceResult pr = new PriceResult();
        double distance;
        try {
            params[30] = get_distances(priceRequest);
        } catch (ApiException e) {
            pr.setStatus(e.getMsg());
        }
        get_params(priceRequest);
        params[2] = 0d;
        PcFtktPrice pcFtktPrice = pcFtktPriceRepo.findByPriceCdAndPcVrsnAndClassId(priceRequest.getPrice_cd(),
                priceCode.getPcVrsn(),
                priceRequest.getClass_id());
        if (tktTypeID.get(priceRequest.getTkt_type_id()).equalsIgnoreCase("RTP_T_TT_FIXED")
                || tktTypeID.get(priceRequest.getTkt_type_id()).equalsIgnoreCase("RTP_T_TT_AGLOCARD")) {
            params[20] = pcFtktPrice.getFtktPriceEur() + diabolo_amt_single;
        } else {
            String voyarId = priceRequest.getVoygr_id();
            try {
                get_pc_voygr_class(voyarId, priceRequest.getPrice_cd(), 4, priceCode.getPcVrsn());
            } catch (ApiException e) {
                e.printStackTrace();
            }
            pcVoygrClassRepo.findByPriceCdAndVoygrIdAndPcVrsnAndClassId(
                    priceRequest.getPrice_cd(),
                    priceRequest.getVoygr_id(), priceCode.getPcVrsn(),
                    priceRequest.getClass_id()).get(0);
            params[20] = pcVoygrClass.getPcDfltAmtEur() + diabolo_amt_single;
        }

        if (priceCode.getPriceFrmlId().isEmpty() || priceCode.getPriceFrmlId().equalsIgnoreCase("RT_CLASSIC")) {
            frml_id = "RT_FIXED";
        } else
            frml_id = priceCode.getPriceFrmlId();

        try {
            trf_pp_price_eur = Common.invokeForumla(frml_id, E1, DS, params);
        } catch (Exception e) {
            priceCode.setTrfPpFrmlId("SAS_DBF_TT_FORMULA");
            pr.setStatus(priceCode.getTrfPpFrmlId());
        }
        pr.setTotal_pp_price_eur(trf_pp_price_eur);
        return pr;
    }

    public PriceResult do_fixed_plus(PriceRequest priceRequest) {
        PriceResult pr = new PriceResult();
        if (priceRequest.getEvent_only_flag().equalsIgnoreCase("Y")) {
            tkt_price_eur = pcVoygr.getPcSuplmntAmtEur();
        } else {
            params[21] = pcVoygr.getPcSuplmntAmtEur();
            tkt_price_eur = do_fixed(priceRequest).getTotal_price_eur();
        }
        //do_fixed_plus(priceRequest);
        pr.setTotal_price_eur(tkt_price_eur);
        return pr;
    }

    public PriceResult do_classic_plus(PriceRequest priceRequest) {
        PriceResult pr = new PriceResult();
        if (priceRequest.getEvent_only_flag().equalsIgnoreCase("Y")) {
            if (priceRequest.getMtrip_flag().equalsIgnoreCase("Y"))
                tkt_price_eur = pcVoygr.getPcSuplmntAmtEur() * priceCode.getMttNoOfTrips().doubleValue();
            else
                tkt_price_eur = pcVoygr.getPcSuplmntAmtEur();
        } else
            tkt_price_eur = do_classic(priceRequest).getTotal_price_eur();
        //tkt_price_eur += do_classic_plus(priceRequest);
        pr.setTotal_price_eur(tkt_price_eur);
        return pr;
    }

    public PriceResult do_classic_max(PriceRequest priceRequest) throws ApiException {
        PriceResult pr = new PriceResult();
        classId = Double.parseDouble(priceRequest.getClass_id());
        try {
            PcVoygrClass pcVoygrClass = get_pc_voygr_class(priceRequest.getVoygr_id(),
                    priceCode.getPriceCd(), 4,
                    priceCode.getPcVrsn());
            params[20] = pcVoygrClass.getPcMaxAmtEur() + diabolo_amt_single;
            tkt_price_eur = do_classic(priceRequest).getTotal_price_eur();
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
        pr.setTotal_price_eur(tkt_price_eur);
        return pr;
    }
}
