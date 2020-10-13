package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.PriceNatr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PriceNatrRepository extends JpaRepository<PriceNatr, Void>, JpaSpecificationExecutor<PriceNatr> {

}