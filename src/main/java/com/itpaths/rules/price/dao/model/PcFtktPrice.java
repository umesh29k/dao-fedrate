package com.itpaths.rules.price.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pc_ftkt_price")
@Data
public class PcFtktPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "pc_ftkt_price_seq")
    @SequenceGenerator(name = "pc_ftkt_price_seq", sequenceName = "PC_FTKT_PRICE_SEQ", allocationSize
            = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

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
