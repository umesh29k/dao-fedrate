package com.itpaths.rules.price.util.econst;

public enum PRICE_CD {
    RTP_T_PN_CLASSIC("RTP_T_PN_CLASSIC", 01),
    RTP_T_PN_ZONE("RTP_T_PN_ZONE", 02),
    RTP_T_PN_FIXED("RTP_T_PN_FIXED", 03),
    RTP_T_PN_CLASSIC_PLUS("RTP_T_PN_CLASSIC_PLUS", 04),
    RTP_T_PN_ZONE_PLUS("RTP_T_PN_ZONE_PLUS", 05),
    RTP_T_PN_FIXED_PLUS("RTP_T_PN_FIXED_PLUS", 06),
    RTP_T_PN_CLASSIC_MAX("RTP_T_PN_CLASSIC_MAX", 07),
    //RTP_T_PN_GEOG("RTP_T_PN_GEOG", 08),
    //RTP_T_PN_GEOG_PLUS("RTP_T_PN_GEOG_PLUS", 09),
    RTP_T_PN_OTHER("RTP_T_PN_OTHER", 10),
    RTP_T_PN_MTARIFF("RTP_T_PN_MTARIFF", 11),
    RTP_T_PN_MTARIFF_PLUS("RTP_T_PN_MTARIFF_PLUS", 12),
    RTP_T_PN_MTARIFF_MAX("RTP_T_PN_MTARIFF_MAX", 13);

    private final String key;
    private final Integer value;

    PRICE_CD(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
    public Integer getValue() {
        return value;
    }
}
