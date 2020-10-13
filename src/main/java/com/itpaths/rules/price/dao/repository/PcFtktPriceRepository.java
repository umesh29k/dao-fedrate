package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.PcFtktPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PcFtktPriceRepository extends JpaRepository<PcFtktPrice, Integer>, JpaSpecificationExecutor<PcFtktPrice> {
    @Query("SELECT * FROM `pc_ftkt_price` where price_cd=:price_cd, pc_vrsn=:pc_vrsn, \" +\n" +
            "                \"class_id=:class_id")
    public PcFtktPrice find(@Param("price_cd") String priceCd, @Param("pc_vrsn") String pcVrsn,
                            @Param("class_id") String classId);
}