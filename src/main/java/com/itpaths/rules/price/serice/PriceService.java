package com.itpaths.rules.price.serice;

import com.itpaths.rules.price.api.Rules;
import com.itpaths.rules.price.dto.Formula;
import com.itpaths.rules.price.model.PriceRequest;
import com.itpaths.rules.price.model.PriceResult;
import com.itpaths.rules.price.util.Price_Natr_Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Service
public class PriceService {
    @Autowired
    private Rules rules;
    @Autowired
    private DataRetrievalService datRetService;
    private String price_frml_id;
    private String frml_id;
    private String price_natr_id;
    private String[] param = new String[30];
    private String voygr_id = "";
    private double tkt_price_eur = 0;
    private double diabolo_amt_total = 0;
    private double diabolo_amt_single = 0;

    public PriceResult calculate(PriceRequest priceRequest) {
        /**
         * invoke rule
         */
        Formula formula = new Formula();
        PriceResult priceResult = new PriceResult();

        //Invoke Rules
        formula = rules.ticketPrice(priceRequest, formula);

        if (formula.getMethod() != null)
            tkt_price_eur = getTkt_price_eur(formula);
        else if (formula.getStatus() == null)
            formula.setStatus("SAS_PRICE_ERR");

        if (formula.getTrf_pp_frml_id().isEmpty() && !formula.getOrgnsm_id().isEmpty()) {
            formula.setTrf_pp_frml_id("RT_CLASSIC_DEBET");
        } else if (!formula.getTrf_pp_frml_id().isEmpty()) {
            // Parameter array was already filled by price calculation
            List<String> ids = datRetService.isData(formula.getTrf_pp_frml_id());
            if (ids.size() == 0) {
                //Error: SAS_DBF_TT_FORMULA
                formula.setTrf_pp_frml_id("SAS_DBF_TT_FORMULA");
            }
        }
        getFormula(priceRequest);
        System.out.println(formula.toString());
        return priceResult;
    }

    private double getTkt_price_eur(Formula formula) {
        double result = 0;
        Method sumInstanceMethod;
        try {
            sumInstanceMethod = Price_Natr_Id.class.getMethod(formula.getMethod(), int.class);
            Price_Natr_Id operationsInstance = new Price_Natr_Id();
            result
                    = (Double) sumInstanceMethod.invoke(operationsInstance, 1);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void getFormula(PriceRequest priceRequest) {

        if (priceRequest.getClass_id().equals("RTP_T_CL_UPGRADE")) {
            /**
             * /* Calculate price 1st class
             *         Set parameter 11 of parameter array = tp_norm_cffcnt_class_1 of table tkt_prmtr
             *         Set parameter 13 of parameter array = tp_norm_min_price_class_1_eur of table tkt_prmtr
             *         Set parameter 15 = 0
             *         Execute formula using formula parser (parameter array as input) => giving result
             *         IF error in formula then return error SAS_TT_FORMULA_ERROR
             *         Set result 1st = result
             *
             *         /* Calculate price 2nd class
             *         *> Set parameter 11 of parameter array = tp_norm_cffcnt_class_2 of table tkt_prmtr
             *         Set parameter 13 of parameter array = tp_norm_min_price_class_2_eur of table tkt_prmtr
             *         Execute formula using formula parser (parameter array as input) => giving result
             *         IF error in formula then return error SAS_TT_FORMULA_ERROR
             *         Set parameter 25 of parameter array = result
             *         Set parameter 26 of parameter array = result 1st
             *
             *         /* Calculate price upgrade
             *         Append _UPGRADE to original frml_id of tt_formula
             *         Get this formula from table tt_formula (field frml_strng)
             *         IF not found then return error SAS_DBF_TT_FORMULA
             *         Execute formula using formula parser (parameter array as input) => giving result
             *         IF error in formula then return error SAS_TT_FORMULA_ERROR
             */
        } else {
            /**
             * Execute formula using formula parser (parameter array as input) => giving result
             * IF error in formula then return error SAS_TT_FORMULA_ERROR
             */

        }

    }

    public void do_classic(PriceRequest priceRequest, Formula formula) {
        double result = 0;
        double tkt_price_eur = result;
        int quantity = 2;

        get_distances();
        get_params();
        if (price_frml_id.isEmpty())
            frml_id = "RT_CLASSIC";
        else
            frml_id = price_frml_id;

        //Get records from tt_formula where frml_id
        //if not found then throw "SAS_DBF_TT_FORMULA"
        if (price_natr_id.equals("RTP_T_PN_MTARIFF")
                || price_natr_id.equals("RTP_T_PN_MTARIFF_PLUS")
                || price_natr_id.equals("RTP_T_PN_MTARIFF_MAX")) {

            //* Multi-tariff with a coefficient for 1 voyager and another for 2nd to nth voyager
            //Query database table pc_voygr with voygr_id = RTP_T_VY_ADULT

            getPC_voyar("RTP_T_VY_ADULT");
            param[6] = "pc_rdctn_cffcnt";
            if (price_natr_id.equals("RTP_T_PN_MTARIFF_PLUS")) {
                param[21] = "pc_suplmnt_amt_eur";
                param[23] = "pc_uplft_amt_eur";
            }
            param[20] = "99999";
            if (price_natr_id.equals("RTP_T_PN_MTARIFF_MAX")) {
                voygr_id = "RTP_T_VY_ADULT";
                //Set parameter 20 of parameter array = pc_max_amt_eur of pc_voygr_class + diabolo_amt_single
            }
            get_pc_voygr_class();
            param[27] = "1";
            get_price_classic();

            quantity--;
            if (quantity > 0) {
                param[20] = "99999";
                if (price_natr_id.equalsIgnoreCase("RTP_T_PN_MTARIFF_MAX")) {
                    voygr_id = "RTP_T_VY_ADTNL_ADULT";
                    get_pc_voygr_class();
                    //Set parameter 20 of parameter array = pc_max_amt_eur of pc_voygr_class + diabolo_amt_single
                }
                param[6] = "pc_rdctn_cffcnt"; //where voyar_id = "RTP_T_VY_ADTNL_ADULT"
                if (price_natr_id.equalsIgnoreCase("RTP_T_PN_MTARIFF_PLUS")) {
                    param[21] = "pc_suplmnt_amt_eur";
                    param[23] = "pc_uplft_amt_eur";
                }
                get_price_classic();
                tkt_price_eur = tkt_price_eur + (result * quantity);
            }
        } else {
            param[6] = "pc_rdctn_cffcnt";
            get_price_classic();
            double tkt_price_eau = result;
        }
    }

    private void get_price_classic() {
    }

    private void get_pc_voygr_class() {
    }

    private void getPC_voyar(String voyar_id) {
    }

    public void get_distances() {

    }

    public void get_params() {

    }
}
