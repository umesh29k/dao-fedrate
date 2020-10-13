package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.PcVoygrClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PcVoygrClassRepository extends JpaRepository<PcVoygrClass, Integer>, JpaSpecificationExecutor<PcVoygrClass> {
    /*@Query("SELECT * FROM pc_voygr_class where price_cd=:price_cd, voygr_id=:voygr_id, pc_vrsn=:pc_vrsn, class_id=:class_id")
    public List<PcVoygrClass> find(@Param("voygr_id") String voygrId,@Param("price_cd") String priceCd, @Param(
            "class_id") String classId, @Param("pc_vrsn") Integer pcVrsn);*/
}