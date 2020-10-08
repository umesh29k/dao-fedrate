package com.itpaths.rules.price.model.dto;

import lombok.*;
import java.time.OffsetDateTime;
import java.time.OffsetDateTime;

/**
 * 
 * @author umesh
 * Create at 2020-10-09 03:54
 */
@Data
public class TktType {

    /**
     * 
     */
    public String tktTypeId;

    /**
     * 
     */
    public OffsetDateTime vldtyDate;

    /**
     * 
     */
    public String stsCd;

    /**
     * 
     */
    public String dscrpnD;

    /**
     * 
     */
    public String dscrpnE;

    /**
     * 
     */
    public String dscrpnF;

    /**
     * 
     */
    public String dscrpnG;

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
    public OffsetDateTime timeStamp;
}