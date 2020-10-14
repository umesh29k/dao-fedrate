package com.itpaths.rules.price.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name = "calndr")
public class Calndr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "calndr_seq")
    @SequenceGenerator(name = "calndr_seq", sequenceName = "CALNDR_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "CALNDR_DATE", nullable = false)
    private Date calndrDate;

    @Column(name = "STS_CD")
    private String stsCd;

    @Column(name = "CALNDR_DAY_IN_WK")
    private Integer calndrDayInWk;

    @Column(name = "CALNDR_DAY_IN_YR")
    private Integer calndrDayInYr;

    @Column(name = "CALNDR_HOLIDAY")
    private String calndrHoliday;

    @Column(name = "CALNDR_WK_NO")
    private Integer calndrWkNo;

    @Column(name = "CALNDR_HIGH_SEASN")
    private String calndrHighSeasn;

    @Column(name = "OPER_BADGE_ID")
    private String operBadgeId;

    @Column(name = "OPER_ID")
    private String operId;

    @Column(name = "TIME_STAMP")
    private Date timeStamp;

}
