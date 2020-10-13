package com.itpaths.rules.price.dao.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name = "city_net_supplmnt")
public class CityNetSupplmnt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "CITY_NET_ID")
    private String cityNetId;

    @Column(name = "DURTN_ID")
    private String durtnId;

    @Column(name = "AGE_CTGRY_ID")
    private String ageCtgryId;

    @Column(name = "VLDTY_DATE")
    private Date vldtyDate;

    @Column(name = "STS_CD")
    private String stsCd;

    @Column(name = "CND_STKT_FXD_CHARGE_S")
    private Integer cndStktFxdChargeS;

    @Column(name = "CNS_PP_CFFCNT")
    private Double cnsPpCffcnt;

    @Column(name = "RAILWAYS_AMT_1")
    private Integer railwaysAmt1;

    @Column(name = "RAILWAYS_AMT_2")
    private Integer railwaysAmt2;

    @Column(name = "CND_STKT_FXD_CHARGE_S_EUR")
    private Double cndStktFxdChargeSEur;

    @Column(name = "RAILWAYS_AMT_1_EUR")
    private Double railwaysAmt1Eur;

    @Column(name = "RAILWAYS_AMT_2_EUR")
    private Double railwaysAmt2Eur;

    @Column(name = "CNS_VRF_SUPPLMNT_AMT_EUR")
    private Double cnsVrfSupplmntAmtEur;

}
