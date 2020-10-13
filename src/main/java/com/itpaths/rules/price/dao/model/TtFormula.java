package com.itpaths.rules.price.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "tt_formula")
@Data
public class TtFormula implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "FRML_ID")
    private String frmlId;

    @Column(name = "STS_CD")
    private String stsCd;

    @Column(name = "VLDTY_DATE")
    private Date vldtyDate;

    @Column(name = "DSCRPN_D")
    private String dscrpnD;

    @Column(name = "DSCRPN_E")
    private String dscrpnE;

    @Column(name = "DSCRPN_F")
    private String dscrpnF;

    @Column(name = "DSCRPN_G")
    private String dscrpnG;

    @Column(name = "PRMTR_NO")
    private Integer prmtrNo;

    @Column(name = "FRML_STRNG")
    private byte[] frmlStrng;

    @Column(name = "OPER_ID")
    private String operId;

    @Column(name = "OPER_BADGE_ID")
    private String operBadgeId;

    @Column(name = "TIME_STAMP")
    private Date timeStamp;

    @Column(name = "IBIS_AWD_FLG")
    private String ibisAwdFlg;

}
