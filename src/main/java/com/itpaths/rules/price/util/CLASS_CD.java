package com.itpaths.rules.price.util;


public enum CLASS_CD {
    RTP_T_CL_FIRST(01, "RTP_T_CL_FIRST"),
    RTP_T_CL_SECOND(02, "RTP_T_CL_SECOND"),
    RTP_T_CL_BOTH(03, "RTP_T_CL_BOTH"),
    RTP_T_CL_IRRELEVANT(04, "RTP_T_CL_IRRELEVANT"),
    RTP_T_CL_UPGRADE(05, "RTP_T_CL_UPGRADE");
    private Integer key;
    private String value;

    CLASS_CD(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
