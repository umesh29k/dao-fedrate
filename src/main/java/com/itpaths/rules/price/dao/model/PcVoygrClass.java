package com.itpaths.rules.price.dao.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "pc_voygr_class")
@Entity
@Data
public class PcVoygrClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "PRICE_CD")
    private String priceCd;

    @Column(name = "PC_VRSN")
    private Integer pcVrsn;

    @Column(name = "VOYGR_ID")
    private String voygrId;

    @Column(name = "CLASS_ID")
    private String classId;

    @Column(name = "PC_DFLT_AMT")
    private Integer pcDfltAmt;

    @Column(name = "PC_MAX_AMT")
    private Integer pcMaxAmt;

    @Column(name = "PC_DFLT_AMT_EUR")
    private Double pcDfltAmtEur;

    @Column(name = "PC_MAX_AMT_EUR")
    private Double pcMaxAmtEur;

}
