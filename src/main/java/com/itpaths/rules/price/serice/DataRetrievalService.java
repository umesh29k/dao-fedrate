package com.itpaths.rules.price.serice;

import com.itpaths.rules.price.dao.model.PcFtktPrice;
import com.itpaths.rules.price.dao.model.PcLimit;
import com.itpaths.rules.price.dao.model.PcVoygrClass;
import com.itpaths.rules.price.dao.model.TktPrmtr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataRetrievalService {
    @Autowired
    NamedParameterJdbcTemplate namedPJT;
    @Autowired
    JdbcTemplate jt;
    private static final String pc_voygr_class = "SELECT * FROM `pc_voygr_class` where price_cd=:price_cd, voygr_id=:id, pc_vrsn=:pc_vrsn, class_id=:class_id";
    private static final String tkt_prmtr = "SELECT * FROM `tkt_prmtr` where VLDTY_DATE in (SELECT MAX( VLDTY_DATE ) from tkt_prmtr)";
    private static final String pc_limit = "SELECT * FROM `pc_limit` where price_cd=:price_cd, pc_vrsn=:pc_vrsn, " +
            "voygr_id=:voygr_id, pc_dstnc=:pc_dstnc, pc_no_in_grp=:pc_no_in_grp";

    /*public TktPrmtr getTicketParams() {
        TktPrmtr tktPrmtr = new TktPrmtr();
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        List<Map<String, Object>> rows = jt.queryForList(tkt_prmtr, namedParameters, Map.class);
        for (Map<String, Object> row : rows) {
            tktPrmtr.setTpNormCffcntClass1((Double) row.get("TP_NORM_CFFCNT_CLASS_1"));
            tktPrmtr.setTpNormMinPriceClass1Eur((Double) row.get("TP_NORM_MIN_PRICE_CLASS_1_EUR"));
            tktPrmtr.setTpNormCffcntClass2((Double) row.get("TP_NORM_CFFCNT_CLASS_2"));
            tktPrmtr.setTpNormMinPriceClass2Eur((Double) row.get("TP_NORM_MIN_PRICE_CLASS_2_EUR"));
            tktPrmtr.setTpNormFxdChargeEur((Double) row.get("TP_NORM_MIN_PRICE_CLASS_2_EUR"));
        }
        return tktPrmtr;
    }

    public List<PcVoygrClass> getPcVoygerClass(String id, String price_cd, String class_id, Integer pc_vrsn) {
        List<PcVoygrClass> pcVoygrClasses = new ArrayList<>();
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("price_cd", price_cd)
                .addValue("class_id", class_id.toString())
                .addValue("pc_vrsn", pc_vrsn);
        List<Map<String, Object>> rows = jt.queryForList(pc_voygr_class, namedParameters, Map.class);
        for (Map<String, Object> row : rows) {
            PcVoygrClass pcVoygrClass = new PcVoygrClass();
            pcVoygrClass.setClassId((String) row.get("PC_SUPLMNT_AMT_EUR"));
            pcVoygrClass.setPcMaxAmtEur((Double) row.get("pc_max_amt_eur"));
            pcVoygrClasses.add(pcVoygrClass);
        }
        return pcVoygrClasses;
    }
*/
    /*public PcLimit getPcLimit(String price_cd, Integer pcVrsn, String voygr_id, double distance, double qty) {
        PcLimit pcLimit = new PcLimit();
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("price_cd", price_cd)
                .addValue("pc_vrsn", pcVrsn)
                .addValue("voygr_id", voygr_id)
                .addValue("pc_dstnc", distance)
                .addValue("pc_no_in_grp", qty);
        List<Map<String, Object>> rows = jt.queryForList(pc_limit, namedParameters, Map.class);
        for (Map<String, Object> row : rows) {
            pcLimit.setPcLimitAmtEur((Double) row.get("PC_LIMIT_AMT_EUR"));
            pcLimit.setPcLimitAmt((Integer) row.get("PC_LIMIT_AMT"));
        }
        return pcLimit;
    }*/

    /*public PcFtktPrice getPcFtktPrice(String price_cd, Integer pcVrsn, String class_id) {
        final String pc_limit = "SELECT * FROM `pc_ftkt_price` where price_cd=:price_cd, pc_vrsn=:pc_vrsn, " +
                "class_id=:class_id";
        PcFtktPrice pcFtktPrice = new PcFtktPrice();
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("price_cd", price_cd)
                .addValue("pc_vrsn", pcVrsn)
                .addValue("class_id", class_id);
        List<Map<String, Object>> rows = jt.queryForList(pc_limit, namedParameters, Map.class);
        for (Map<String, Object> row : rows) {
            pcFtktPrice.setFtktPriceEur((Double) row.get("FTKT_PRICE_EUR"));
            pcFtktPrice.setFtktPrice((Integer) row.get("FTKT_PRICE"));
        }
        return pcFtktPrice;
    }*/

}
