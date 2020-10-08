package com.itpaths.rules.price.serice;

import com.itpaths.rules.price.model.PriceRequest;
import com.itpaths.rules.price.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String tt_formula_by_id = "select * from tt_formula where FRML_ID=:id";
    private static final String price_code = "select * from price_code where price_cd=:price_cd";
    private static final String tkt_prmtr = "SELECT * FROM `tkt_prmtr` where VLDTY_DATE in (SELECT MAX( VLDTY_DATE ) from tkt_prmtr)";
    private static final String pc_voygr = "SELECT * FROM `pc_voygr` where voygr_id=:id";
    private static final String pc_voygr_class = "SELECT * FROM `pc_voygr_class` where price_cd=:price_cd, " +
            "voygr_id=:id, pc_vrsn=:pc_vrsn, class_id=:class_id";

    public List<TtFormula> getFormula(String id) {
        List<TtFormula> ttFormulas = new ArrayList();
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        List<Map> rows = jdbcTemplate.queryForList(tt_formula_by_id, namedParameters, Map.class);
        for (Map<String, Object> row : rows) {
            TtFormula ttFormula = new TtFormula();
            ttFormula.setFrmlStrng((String) row.get("FRML_STRNG"));
            ttFormulas.add(ttFormula);
        }
        return ttFormulas;
    }

    public List<PriceCode> getPriceData(String price_cd) {
        List<PriceCode> priceCodes = new ArrayList();
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", price_cd);
        List<Map> rows = jdbcTemplate.queryForList(price_code, namedParameters, Map.class);
        for (Map<String, Object> row : rows) {
            PriceCode priceCode = new PriceCode();
            priceCode.setPriceCd((String) row.get("PRICE_CD"));
            priceCode.setTktTypeId((String) row.get("TKT_TYPE_ID"));
            priceCode.setCrubId((String) row.get("CRUB_ID"));
            priceCode.setPriceNatrId((String) row.get("PRICE_NATR_ID"));
            priceCode.setTrfPpFrmlId((String) row.get("TRF_PP_FRML_ID"));
            priceCode.setPriceFrmlId((String) row.get("PRICE_FRML_ID"));
            priceCode.setPcMaxDstncJrney((int) row.get("PC_MAX_DSTNC_JRNEY"));
            priceCode.setPcMinDstncJrney((int) row.get("PC_MIN_DSTNC_JRNEY"));
            priceCode.setPcApplyRdctnFull((String) row.get("PC_APPLY_RDCTN_FULL"));
            priceCode.setMttNoOfTrips((String) row.get("MTT_NO_OF_TRIPS"));
            priceCode.setPcUplftCffcnt((int) row.get("PC_UPLFT_CFFCNT"));
            priceCodes.add(priceCode);
        }
        return priceCodes;
    }

    public TktPrmtr getTicketParams() {
        TktPrmtr tktPrmtr = new TktPrmtr();
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        List<Map> rows = jdbcTemplate.queryForList(tkt_prmtr, namedParameters, Map.class);
        for (Map<String, Object> row : rows) {
            tktPrmtr.setTpNormCffcntClass1((Double) row.get("TP_NORM_CFFCNT_CLASS_1"));
            tktPrmtr.setTpNormMinPriceClass1Eur((Double) row.get("TP_NORM_MIN_PRICE_CLASS_1_EUR"));
            tktPrmtr.setTpNormCffcntClass2((Double) row.get("TP_NORM_CFFCNT_CLASS_2"));
            tktPrmtr.setTpNormMinPriceClass2Eur((Double) row.get("TP_NORM_MIN_PRICE_CLASS_2_EUR"));
            tktPrmtr.setTpNormFxdChargeEur((Double) row.get("TP_NORM_MIN_PRICE_CLASS_2_EUR"));
        }
        return tktPrmtr;
    }

    public PcVoygr getPcVoyger(String id) {
        PcVoygr pcVoygr = new PcVoygr();
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        List<Map> rows = jdbcTemplate.queryForList(pc_voygr, namedParameters, Map.class);
        for (Map<String, Object> row : rows) {
            pcVoygr.setPcSuplmntAmtEur((Double) row.get("PC_SUPLMNT_AMT_EUR"));
            pcVoygr.setPcUplftAmtEur((Integer) row.get("PC_UPLFT_AMT_EUR"));
            pcVoygr.setPcRdctnCffcnt((Integer) row.get("PC_RDCTN_CFFCNT"));
        }
        return pcVoygr;
    }

    public PcVoygrClass getPcVoygerClass(String id, String price_cd, Integer class_id, Integer pc_vrsn) {
        PcVoygrClass pcVoygrClass = new PcVoygrClass();
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("price_cd", price_cd)
                .addValue("class_id", class_id.toString())
                .addValue("pc_vrsn", pc_vrsn);
        List<Map> rows = jdbcTemplate.queryForList(pc_voygr_class, namedParameters, Map.class);
        for (Map<String, Object> row : rows) {
            pcVoygrClass.setClassId((String) row.get("PC_SUPLMNT_AMT_EUR"));
            pcVoygrClass.setPcMaxAmtEur((Integer) row.get("pc_max_amt_eur"));
        }
        return pcVoygrClass;
    }

    public CityNetSupplmnt getCity_net_supplmnt() {
        final String pc_limit = "SELECT * FROM `city_net_supplmnt`";
        CityNetSupplmnt cityNetSupplmnt = new CityNetSupplmnt();
        SqlParameterSource namedParameters = new MapSqlParameterSource();
                //.addValue("price_cd", price_cd);
        List<Map> rows = jdbcTemplate.queryForList(pc_limit, namedParameters, Map.class);
        for (Map<String, Object> row : rows) {
            cityNetSupplmnt.setCndStktFxdChargeSEur((Double) row.get("CND_STKT_FXD_CHARGE_S_EUR"));
        }
        return cityNetSupplmnt;
    }

    public Orgnsm getOrgnsm() {
        final String pc_limit = "SELECT * FROM `ORGNSM`";
        Orgnsm orgnsm = new Orgnsm();
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        List<Map> rows = jdbcTemplate.queryForList(pc_limit, namedParameters, Map.class);
        for (Map<String, Object> row : rows) {
            orgnsm.setPcRdctnCffcnt((Integer) row.get("PC_RDCTN_CFFCNT"));
        }
        return orgnsm;
    }

    public Calndr getCalndr() {
        final String pc_limit = "SELECT * FROM `CALNDR`";
        Calndr calndr = new Calndr();
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        List<Map> rows = jdbcTemplate.queryForList(pc_limit, namedParameters, Map.class);
        for (Map<String, Object> row : rows) {
            calndr.setCalndrDayInWk((String) row.get("CALNDR_DAY_IN_WK"));
        }
        return calndr;
    }

    public PcLimit getPcLimit(String price_cd, Integer pcVrsn, String voygr_id, double distance, double qty) {
        final String pc_limit = "SELECT * FROM `pc_limit` where price_cd=:price_cd, pc_vrsn=:pc_vrsn, " +
                "voygr_id=:voygr_id, pc_dstnc=:pc_dstnc, pc_no_in_grp=:pc_no_in_grp";
        PcLimit pcLimit = new PcLimit();
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("price_cd", price_cd)
                .addValue("pc_vrsn", pcVrsn)
                .addValue("voygr_id", voygr_id)
                .addValue("pc_dstnc", distance)
                .addValue("pc_no_in_grp", qty);
        List<Map> rows = jdbcTemplate.queryForList(pc_limit, namedParameters, Map.class);
        for (Map<String, Object> row : rows) {
            pcLimit.setPcLimitAmtEur((Double) row.get("PC_LIMIT_AMT_EUR"));
            pcLimit.setPcLimitAmt((Integer) row.get("PC_LIMIT_AMT"));
        }
        return pcLimit;
    }
    public PcFtktPrice getPcFtktPrice(String price_cd, Integer pcVrsn, String class_id) {
        final String pc_limit = "SELECT * FROM `pc_ftkt_price` where price_cd=:price_cd, pc_vrsn=:pc_vrsn, " +
                "class_id=:class_id";
        PcFtktPrice pcFtktPrice = new PcFtktPrice();
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("price_cd", price_cd)
                .addValue("pc_vrsn", pcVrsn)
                .addValue("class_id", class_id);
        List<Map> rows = jdbcTemplate.queryForList(pc_limit, namedParameters, Map.class);
        for (Map<String, Object> row : rows) {
            pcFtktPrice.setFtktPriceEur((Double) row.get("FTKT_PRICE_EUR"));
            pcFtktPrice.setFtktPrice((Integer) row.get("FTKT_PRICE"));
        }
        return pcFtktPrice;
    }
}
