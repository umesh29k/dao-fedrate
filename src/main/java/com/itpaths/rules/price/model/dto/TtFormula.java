package com.itpaths.rules.price.model.dto;


import lombok.Data;

import java.sql.Date;

/**
 * @author umesh
 * Create at 2020-10-07 02:41
 */
@Data
public class TtFormula {
    public String frmlId;
    public String stsCd;
    public Date vldtyDate;
    public String dscrpnD;
    public String dscrpnE;
    public String dscrpnF;
    public String dscrpnG;
    public String prmtrNo;
    public String frmlStrng;
    public String operId;
    public String operBadgeId;
    public Date timeStamp;
    public String ibisAwdFlg;
}