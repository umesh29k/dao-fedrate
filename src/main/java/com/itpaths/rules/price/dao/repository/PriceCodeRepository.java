package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.PriceCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PriceCodeRepository extends JpaRepository<PriceCode, Integer>, JpaSpecificationExecutor<PriceCode> {
    @Query("SELECT pc FROM PriceCode pc where pc.pcVrsn in (SELECT max(pcv.pcVrsn) from PriceCode pcv where pcv.priceCd=:priceCd)")
    public List<PriceCode> findByPriceCd(String priceCd);

    public List<PriceCode> findByTktTypeId(String tktTypeId);
}