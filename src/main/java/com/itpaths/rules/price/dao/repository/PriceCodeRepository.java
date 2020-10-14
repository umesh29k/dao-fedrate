package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.PriceCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PriceCodeRepository extends JpaRepository<PriceCode, Integer>, JpaSpecificationExecutor<PriceCode> {
    public List<PriceCode> findByPriceCd(String priceCd);
    public List<PriceCode> findByTktTypeId(String tktTypeId);
}