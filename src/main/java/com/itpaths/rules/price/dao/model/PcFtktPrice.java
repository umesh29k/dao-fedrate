package com.itpaths.rules.price.dao.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "pc_ftkt_price")
@Data
public class PcFtktPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "PRICE_CD")
    private String priceCd;

    @Column(name = "PC_VRSN")
    private Integer pcVrsn;

    @Column(name = "AGE_CTGRY_ID")
    private String ageCtgryId;

    @Column(name = "CLASS_ID")
    private String classId;

    @Column(name = "DURTN_ID")
    private String durtnId;

    @Column(name = "FTKT_PRICE")
    private Integer ftktPrice;

    @Column(name = "FTKT_PRICE_EUR")
    private Double ftktPriceEur;

}
