package com.itpaths.rules.price.dto;

import lombok.Data;

import java.sql.Date;

/**
 * @author umesh
 * Create at 2020-10-07 03:33
 */
@Data
public class Calndr {

    /**
     *
     */
    public Date calndrDate;

    /**
     *
     */
    public String stsCd;

    /**
     *
     */
    public String calndrDayInWk;

    /**
     *
     */
    public String calndrDayInYr;

    /**
     *
     */
    public String calndrHoliday;

    /**
     *
     */
    public String calndrWkNo;

    /**
     *
     */
    public String calndrHighSeasn;

    /**
     *
     */
    public String operBadgeId;

    /**
     *
     */
    public String operId;

    /**
     *
     */
    public Date timeStamp;
}