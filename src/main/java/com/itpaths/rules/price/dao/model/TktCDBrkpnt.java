package com.itpaths.rules.price.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tkt_c_d_brkpnt")
public class TktCDBrkpnt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "TKT_CDB_SEQ_NO")
    private Integer tktCdbSeqNo;

    @Column(name = "TKT_CDB_DSTNC")
    private Integer tktCdbDstnc;

    @Column(name = "TKT_CDB_FRML_ID")
    private String tktCdbFrmlId;

    @Column(name = "TP_VRSN")
    private Integer tpVrsn;

}
