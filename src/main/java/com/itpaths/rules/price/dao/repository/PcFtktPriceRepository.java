package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.PcFtktPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PcFtktPriceRepository extends JpaRepository<PcFtktPrice, Integer>, JpaSpecificationExecutor<PcFtktPrice> {
    public PcFtktPrice findByPriceCdAndPcVrsnAndClassId(String priceCd, Integer pcVrsn, String classId);
}