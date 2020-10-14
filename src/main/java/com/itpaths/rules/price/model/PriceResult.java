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
    @ApiModelProperty(notes = "Status", example = "SAS_PRICE_ERR")
    private String status;

    @Override
    public String toString() {
        return "PriceResult{" +
                "total_price_eur='" + total_price_eur + '\'' +
                ", total_pp_price_eur='" + total_pp_price_eur + '\'' +
                ", mobib_price_eur='" + mobib_price_eur + '\'' +
                ", mobib_tarif_code='" + mobib_tarif_code + '\'' +
                ", diabolo_amt_eur='" + diabolo_amt_eur + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
