package com.itpaths.rules.price.serice;

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
    private static final String tkt_param_by_id = "select * from tt_formula where FRML_ID=:id";

    public List<String> isData(String id) {
        List<String> objs = new ArrayList();
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        List<Map> rows = jdbcTemplate.queryForList(tt_formula_by_id,
                namedParameters, Map.class);

        for (Map<String, Object> row : rows) {
            String frmlId = (String) row.get("FRML_ID");
            objs.add(frmlId);
        }

        return objs;
    }
}
