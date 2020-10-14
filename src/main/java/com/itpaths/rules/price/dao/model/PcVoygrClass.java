package com.itpaths.rules.price.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "pc_voygr_class")
@Entity
@Data
public class PcVoygrClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "pc_voygr_class_seq")
    @SequenceGenerator(name = "pc_voygr_class_seq", sequenceName = "PC_VOYGR_CLASS_SEQ", allocationSize
            = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

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
