package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.PcFtktPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PcFtktPriceRepository extends JpaRepository<PcFtktPrice, Integer>, JpaSpecificationExecutor<PcFtktPrice> {
    /*@Query("SELECT p FROM pc_ftkt_price p where p.price_cd=:price_cd, p.pc_vrsn=:pc_vrsn, p.class_id=:class_id")
    public PcFtktPrice findPcFtktPriceByPriceCdANDPcVrsnAndClassId(@Param("price_cd") String priceCd, @Param("pc_vrsn") Integer pcVrsn,
                            @Param("class_id") String classId);*/
    /*@Query("SELECT * FROM PcFtktPrice where priceCd=?1, pcVrsn=?2, classId=?3")
    public PcFtktPrice findPcFtktPriceByPriceCdANDPcVrsnAndClassId(String priceCd, Integer pcVrsn, String classId);*/
}