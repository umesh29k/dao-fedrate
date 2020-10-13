package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.TtFormula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TtFormulaRepository extends JpaRepository<TtFormula, Integer>, JpaSpecificationExecutor<TtFormula> {

}