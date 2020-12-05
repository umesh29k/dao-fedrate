package com.jrstep.dao.fedrate.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "orgnsm")
@Data
public class Orgnsm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "orgnsm_seq")
    @SequenceGenerator(name = "orgnsm_seq", sequenceName = "ORGNSM_SEQ", allocationSize
            = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ORGNSM_ID")
    private String orgnsmId;

    @Column(name = "OR_VRSN")
    private Integer orVrsn;

    @Column(name = "VLDTY_DATE")
    private Date vldtyDate;

    @Column(name = "STS_CD")
    private String stsCd;

    @Column(name = "CONTRCT_ID")
    private String contrctId;

    @Column(name = "CUSTMR_ID")
    private String custmrId;

    @Column(name = "CMPNY_NAME")
    private String cmpnyName;

    @Column(name = "STREET")
    private String STREET;

    @Column(name = "POST_CD")
    private String postCd;

    @Column(name = "CITY")
    private String CITY;

    @Column(name = "LANG_ID")
    private String langId;

    @Column(name = "TEL_NO")
    private String telNo;

    @Column(name = "NOTICE_NO")
    private String noticeNo;

    @Column(name = "NOTICE_DATE")
    private Date noticeDate;

    @Column(name = "REG_NO")
    private String regNo;

    @Column(name = "BANK_ACCT_NO")
    private String bankAcctNo;

    @Column(name = "VAT_NO")
    private String vatNo;

    @Column(name = "ORGNSM_DVSN")
    private String orgnsmDvsn;

    @Column(name = "ORGNSM_BLDNG")
    private String orgnsmBldng;

    @Column(name = "ORGNSM_DB_ALLWD_YESNO")
    private String orgnsmDbAllwdYesno;

    @Column(name = "ORGNSM_PRICE_CFFCNT")
    private Double orgnsmPriceCffcnt;

    @Column(name = "OPER_BADGE_ID")
    private String operBadgeId;

    @Column(name = "OPER_ID")
    private String operId;

    @Column(name = "TIME_STAMP")
    private Date timeStamp;

    @Column(name = "TYP_ENC_ID")
    private String typEncId;

    @Column(name = "ELEM_ENC_ID")
    private String elemEncId;

    @Column(name = "PRSNL_RGSTRTN_FLG")
    private String prsnlRgstrtnFlg;

    @Column(name = "PC_RDCTN_CFFCNT")
    private Double pcRdctnCffcnt;

    @Column(name = "VALDTN_RDCTN_CFFCNT")
    private Double valdtnRdctnCffcnt;

    @Column(name = "ORGNSM_SLS_START_DATE")
    private Date orgnsmSlsStartDate;

    @Column(name = "ORGNSM_SLS_STOP_DATE")
    private Date orgnsmSlsStopDate;

    @Column(name = "INVOICE_TYPE_ID")
    private String invoiceTypeId;

    @Column(name = "ORGNSM_TYPE_ID")
    private String orgnsmTypeId;

    @Column(name = "PAYMENT_NATR_ID")
    private String paymentNatrId;

    @Column(name = "FIXED_VCHR_AMT_FLG")
    private String fixedVchrAmtFlg;

    @Column(name = "PRODUCT_NATR_ID")
    private String productNatrId;

    @Column(name = "PRODUCT_TYPE_ID")
    private String productTypeId;

    @Column(name = "STKT_RDCTN_CFFCNT")
    private Double stktRdctnCffcnt;

    @Column(name = "FID_AWD_FLG")
    private String fidAwdFlg;

    @Column(name = "FACT_OFFICE_ID")
    private String factOfficeId;

    @Column(name = "FIXED_VCHR_AMT_MANY_FLG")
    private String fixedVchrAmtManyFlg;

    @Column(name = "FID_FRML_ID")
    private String fidFrmlId;

    @Column(name = "FID_FRML_ID_ORGNSM")
    private String fidFrmlIdOrgnsm;

}
