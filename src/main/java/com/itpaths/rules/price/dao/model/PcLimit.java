package com.itpaths.rules.price.dao.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "pc_limit")
public class PcLimit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "PRICE_CD")
    private String priceCd;

    @Column(name = "PC_VRSN")
    private Integer pcVrsn;

    @Column(name = "VOYGR_ID")
    private String voygrId;

    @Column(name = "PC_DSTNC")
    private Integer pcDstnc;

    @Column(name = "PC_NO_IN_GRP")
    private Integer pcNoInGrp;

    @Column(name = "PC_LIMIT_AMT")
    private Integer pcLimitAmt;

    @Column(name = "PC_LIMIT_AMT_EUR")
    private Double pcLimitAmtEur;

}
