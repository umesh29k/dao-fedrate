package com.itpaths.rules.price.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "STATIONS_DISTANCES")
@Data
public class StationsDistances implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer ID;
    @Column(name = "DSTNC_FROM_TSTATN_ID")
    private String dstncFromTstatnId;
    @Column(name = "DSTNC_TO_TSTATN_ID")
    private String dstncToTstatnId;
    @Column(name = "VLDTY_DATE")
    private String vldtyDate;
    @Column(name = "STS_CD")
    private String stsCd;
    @Column(name = "TSTATN_INTER_DSTNC")
    private String tstatnInterDstnc;
    @Column(name = "OPER_BADGE_ID")
    private String operBadgeId;
    @Column(name = "OPER_ID")
    private String operId;
    @Column(name = "TIME_STAMP")
    private String timeStamp;
}
