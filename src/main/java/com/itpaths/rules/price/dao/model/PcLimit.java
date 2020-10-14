package com.itpaths.rules.price.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "pc_limit")
public class PcLimit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "pc_limit_seq")
    @SequenceGenerator(name = "pc_limit_seq", sequenceName = "PC_LIMIT_SEQ", allocationSize
            = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

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
