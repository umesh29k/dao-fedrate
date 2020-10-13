package com.itpaths.rules.price.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pc_voygr")
@Data
public class PcVoygr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "PRICE_CD")
    private String priceCd;

    @Column(name = "PC_VRSN")
    private Integer pcVrsn;

    @Column(name = "VOYGR_ID")
    private String voygrId;

    @Column(name = "PC_RDCTN_CFFCNT")
    private Double pcRdctnCffcnt;

    @Column(name = "PC_SUPLMNT_AMT")
    private Integer pcSuplmntAmt;

    @Column(name = "PC_UPLFT_AMT")
    private Integer pcUplftAmt;

    @Column(name = "PC_SUPLMNT_AMT_EUR")
    private Double pcSuplmntAmtEur;

    @Column(name = "PC_UPLFT_AMT_EUR")
    private Double pcUplftAmtEur;

    @Column(name = "PC_VOYGR_CLASS_MSK")
    private String pcVoygrClassMsk;

}
