package com.itpaths.rules.price.util;

import com.itpaths.rules.price.model.dto.Formula;
import com.itpaths.rules.price.model.PriceRequest;

public class Price_Natr_Id {
    private String[] param = new String[30];
    private String voygr_id = "";
    private double diabolo_amt_total = 0;
    private double diabolo_amt_single = 0;
    private String price_frml_id = "";
    private String frml_id = "";
    private String price_natr_id = "";

    public double do_classic(PriceRequest priceRequest, Formula formula) {
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

            getPC_voyar(priceRequest, formula);
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
            get_pc_voygr_class(priceRequest, formula);
            param[27] = "1";
            get_price_classic(priceRequest, formula);

            quantity--;
            if (quantity > 0) {
                param[20] = "99999";
                if (price_natr_id.equalsIgnoreCase("RTP_T_PN_MTARIFF_MAX")) {
                    voygr_id = "RTP_T_VY_ADTNL_ADULT";
                    get_pc_voygr_class(priceRequest, formula);
                    //Set parameter 20 of parameter array = pc_max_amt_eur of pc_voygr_class + diabolo_amt_single
                }
                param[6] = "pc_rdctn_cffcnt"; //where voyar_id = "RTP_T_VY_ADTNL_ADULT"
                if (price_natr_id.equalsIgnoreCase("RTP_T_PN_MTARIFF_PLUS")) {
                    param[21] = "pc_suplmnt_amt_eur";
                    param[23] = "pc_uplft_amt_eur";
                }
                get_price_classic(priceRequest, formula);
                tkt_price_eur = tkt_price_eur + (result * quantity);
            }
        } else {
            param[6] = "pc_rdctn_cffcnt";
            get_price_classic(priceRequest, formula);
            double tkt_price_eau = result;
        }
        return Double.parseDouble(priceRequest.getPrice_cd());
    }

    public double getPC_voyar(PriceRequest priceRequest, Formula formula) {
        return Double.parseDouble(priceRequest.getPrice_cd());
    }

    public double do_classic_max(PriceRequest priceRequest, Formula formula) {
        return Double.parseDouble(priceRequest.getPrice_cd());
    }

    public double do_classic_plus(PriceRequest priceRequest, Formula formula) {
        return Double.parseDouble(priceRequest.getPrice_cd());
    }

    public double do_fixed(PriceRequest priceRequest, Formula formula) {
        return Double.parseDouble(priceRequest.getPrice_cd());
    }

    public double do_fixed_plus(PriceRequest priceRequest, Formula formula) {
        return Double.parseDouble(priceRequest.getPrice_cd());
    }

    public double do_zone(PriceRequest priceRequest, Formula formula) {
        return Double.parseDouble(priceRequest.getPrice_cd());
    }

    public double do_mtariff_plus(PriceRequest priceRequest, Formula formula) {
        return Double.parseDouble(priceRequest.getPrice_cd());
    }

    public double get_price_classic(PriceRequest priceRequest, Formula formula) {
        return Double.parseDouble(priceRequest.getPrice_cd());
    }

    public double get_pc_voygr_class(PriceRequest priceRequest, Formula formula) {
        return Double.parseDouble(priceRequest.getPrice_cd());
    }

    public void get_distances() {

    }

    public void get_params() {

    }
}











