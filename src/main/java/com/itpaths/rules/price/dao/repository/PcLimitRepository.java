package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.PcLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PcLimitRepository extends JpaRepository<PcLimit, Integer>, JpaSpecificationExecutor<PcLimit> {
    public PcLimit findByPriceCdAndPcVrsnAndVoygrIdAndPcDstncAndPcNoInGrp(String priceCd, Integer pcVrsn,
                                                                          String voygrId,
                                                                          Integer pcDstnc, Integer pcNoInGrp);
}