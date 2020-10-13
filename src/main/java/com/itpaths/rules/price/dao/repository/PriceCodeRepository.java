package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.PriceCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PriceCodeRepository extends JpaRepository<PriceCode, Void>, JpaSpecificationExecutor<PriceCode> {

}