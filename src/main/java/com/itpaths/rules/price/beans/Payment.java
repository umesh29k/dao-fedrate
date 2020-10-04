package com.itpaths.rules.price.beans;

import lombok.Data;

@Data
public class Payment {
    private String total_price_eur;
    private String total_pp_price_eur;
    private String mobib_price_eur;
    private String mobib_tarif_code;
    private String diabolo_amt_eur;
}
