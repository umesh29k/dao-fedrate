package com.itpaths.rules.price.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "rndg_frml_export")
@Data
public class RndgFrmlExport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer ID;

    @Column(name = "RNDG_ID")
    private String rndgId;

    @Column(name = "VLDTY_DATE")
    private Timestamp vldtyDate;

    @Column(name = "STS_CD")
    private String stsCd;

    @Column(name = "RNDG_LIMIT")
    private Integer rndgLimit;

    @Column(name = "RNDG_FRML_ID")
    private String rndgFrmlId;

    @Column(name = "DSCRPN_D")
    private String dscrpnD;

    @Column(name = "DSCRPN_E")
    private String dscrpnE;

    @Column(name = "DSCRPN_F")
    private String dscrpnF;

    @Column(name = "DSCRPN_G")
    private String dscrpnG;

    @Column(name = "OPER_BADGE_ID")
    private String operBadgeId;

    @Column(name = "OPER_ID")
    private String operId;

    @Column(name = "TIME_STAMP")
    private Timestamp timeStamp;

}
