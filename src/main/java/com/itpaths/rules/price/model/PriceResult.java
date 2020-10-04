package com.itpaths.rules.price.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceResult {
    @ApiModelProperty(notes = "total price", example = "0.0 EUR")
    private String total_price_eur;
    @ApiModelProperty(notes = "total PP price", example = "0.0 EUR")
    private String total_pp_price_eur;
    @ApiModelProperty(notes = "MOBIB price", example = "0.0 EUR")
    private String mobib_price_eur;
    @ApiModelProperty(notes = "Tarrif Code", example = "ADCD")
    private String mobib_tarif_code;
    @ApiModelProperty(notes = "DIABOLO amount", example = "0.0 EUR")
    private String diabolo_amt_eur;

    public String getTotal_price_eur() {
        return total_price_eur;
    }

    public void setTotal_price_eur(String total_price_eur) {
        this.total_price_eur = total_price_eur;
    }

    public String getTotal_pp_price_eur() {
        return total_pp_price_eur;
    }

    public void setTotal_pp_price_eur(String total_pp_price_eur) {
        this.total_pp_price_eur = total_pp_price_eur;
    }

    public String getMobib_price_eur() {
        return mobib_price_eur;
    }

    public void setMobib_price_eur(String mobib_price_eur) {
        this.mobib_price_eur = mobib_price_eur;
    }

    public String getMobib_tarif_code() {
        return mobib_tarif_code;
    }

    public void setMobib_tarif_code(String mobib_tarif_code) {
        this.mobib_tarif_code = mobib_tarif_code;
    }

    public String getDiabolo_amt_eur() {
        return diabolo_amt_eur;
    }

    public void setDiabolo_amt_eur(String diabolo_amt_eur) {
        this.diabolo_amt_eur = diabolo_amt_eur;
    }

    @Override
    public String toString() {
        return "PriceResult{" +
                "total_price_eur='" + total_price_eur + '\'' +
                ", total_pp_price_eur='" + total_pp_price_eur + '\'' +
                ", mobib_price_eur='" + mobib_price_eur + '\'' +
                ", mobib_tarif_code='" + mobib_tarif_code + '\'' +
                ", diabolo_amt_eur='" + diabolo_amt_eur + '\'' +
                '}';
    }
}
