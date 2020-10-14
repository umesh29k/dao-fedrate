package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.TtFormula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TtFormulaRepository extends JpaRepository<TtFormula, Integer>, JpaSpecificationExecutor<TtFormula> {
    public List<TtFormula> findByFrmlId(String frmlId);
}