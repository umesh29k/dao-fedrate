package com.itpaths.rules.price.util.econst;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static Map<Integer, String> class_id;
    public static Map<Integer, String> price_cd;
    public static Map<String, String> tktTypeID;
    public static Map<String, String> voygr_id;

    static {
        class_id = new HashMap();
        class_id.put(1, "RTP_T_CL_FIRST");
        class_id.put(2, "RTP_T_CL_SECOND");
        class_id.put(3, "RTP_T_CL_BOTH");
        class_id.put(4, "RTP_T_CL_IRRELEVANT");
        class_id.put(5, "RTP_T_CL_UPGRADE");

        price_cd = new HashMap();
        price_cd.put(1, "RTP_T_PN_CLASSIC");
        price_cd.put(2, "RTP_T_PN_ZONE");
        price_cd.put(3, "RTP_T_PN_FIXED");
        price_cd.put(4, "RTP_T_PN_CLASSIC_PLUS");
        price_cd.put(5, "RTP_T_PN_ZONE_PLUS");
        price_cd.put(6, "RTP_T_PN_FIXED_PLUS");
        price_cd.put(7, "RTP_T_PN_CLASSIC_MAX");
        price_cd.put(8, "RTP_T_PN_GEOG");
        price_cd.put(9, "RTP_T_PN_GEOG_PLUS");
        price_cd.put(10, "RTP_T_PN_OTHER");
        price_cd.put(11, "RTP_T_PN_MTARIFF");
        price_cd.put(12, "RTP_T_PN_MTARIFF_PLUS");
        price_cd.put(13, "RTP_T_PN_MTARIFF_MAX");

        tktTypeID = new HashMap();
        tktTypeID.put("0", "RTP_T_TT_SORRY_PASS"); //Sorry pass
        tktTypeID.put("1", "RTP_T_TT_SIMPLE"); //Simple ticket
        tktTypeID.put("2", "RTP_T_TT_COMBINED"); //Combined ticket
        tktTypeID.put("3", "RTP_T_TT_AGLOCARD"); //Multiple trip within agglom.
        tktTypeID.put("4", "RTP_T_TT_FIXED"); //Fixed price ticket
        tktTypeID.put("5", "RTP_T_TT_FIXED_BP"); //Fixed price bonus pass
        tktTypeID.put("6", "RTP_T_TT_WEEKEND"); //Weekend ticket
        tktTypeID.put("7", "RTP_T_TT_MINER"); //Mine worker ticket
        tktTypeID.put("8", "RTP_T_TT_CNTNGNT"); //Contingent event ticket
        tktTypeID.put("9", "RTP_T_TT_CNTNGNT_COMBINED"); //Combined contingent event ticket
        tktTypeID.put("A", "RTP_T_TT_FIXED_HPPNG"); //Happening fixed price ticket
        tktTypeID.put("V", "RTP_T_TT_CNTNGNT_HOTSPOT"); //Hotspot card

        voygr_id = new HashMap();
        voygr_id.put("1", "RTP_T_VY_ADULT");
        voygr_id.put("2", "RTP_T_VY_CHILD");
        voygr_id.put("3", "RTP_T_VY_BENE1");
        voygr_id.put("4", "RTP_T_VY_BENE2");
        voygr_id.put("5", "RTP_T_VY_BENE3");
        voygr_id.put("6", "RTP_T_VY_ADTNL_ADULT");
    }
}
