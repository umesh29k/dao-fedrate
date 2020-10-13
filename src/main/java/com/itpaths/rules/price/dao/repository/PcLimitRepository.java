package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.PcLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PcLimitRepository extends JpaRepository<PcLimit, Integer>, JpaSpecificationExecutor<PcLimit> {
    /*@Query("SELECT * FROM pc_limit where price_cd=:price_cd, pc_vrsn=:pc_vrsn, voygr_id=:voygr_id, pc_dstnc=:pc_dstnc, pc_no_in_grp=:pc_no_in_grp")
    public PcLimit find(@Param("price_cd") String priceCode, @Param("pc_vrsn") Integer pcVrsn,
                        @Param("voygr_id") String voygrId, @Param("pc_dstnc")  Integer distance,
                        @Param("pc_no_in_grp") Integer qty);*/
}