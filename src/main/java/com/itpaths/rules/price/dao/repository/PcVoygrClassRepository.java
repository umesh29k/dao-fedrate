package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.PcVoygrClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PcVoygrClassRepository extends JpaRepository<PcVoygrClass, Integer>, JpaSpecificationExecutor<PcVoygrClass> {
    @Query("SELECT * FROM `pc_voygr_class` where price_cd=:price_cd, voygr_id=:id, pc_vrsn=:pc_vrsn, class_id=:class_id")
    public PcVoygrClass find(@Param("lastName") String id, String priceCode, String classId, String pcVrsn);
}