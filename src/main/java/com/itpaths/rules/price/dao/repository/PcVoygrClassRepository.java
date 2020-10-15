package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.PcVoygrClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PcVoygrClassRepository extends JpaRepository<PcVoygrClass, Integer>, JpaSpecificationExecutor<PcVoygrClass> {
    public List<PcVoygrClass> findByPriceCdAndVoygrIdAndPcVrsnAndClassId(String priceCd, String voygrId, Integer pcVrsn,
                                                         String classId);
}