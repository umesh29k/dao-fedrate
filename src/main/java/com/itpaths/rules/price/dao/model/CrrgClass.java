package com.itpaths.rules.price.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name = "crrg_class")
public class CrrgClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "CLASS_ID")
    private String classId;

    @Column(name = "VLDTY_DATE")
    private Date vldtyDate;

    @Column(name = "STS_CD")
    private String stsCd;

    @Column(name = "CLASS_STKT_CFFCNT_K")
    private Double classStktCffcntK;

    @Column(name = "CLASS_STKT_CFFCNT_K2")
    private Double classStktCffcntK2;

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
    private Date timeStamp;

}
