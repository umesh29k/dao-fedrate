package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.PcFtktPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PcFtktPriceRepository extends JpaRepository<PcFtktPrice, Integer>, JpaSpecificationExecutor<PcFtktPrice> {

}