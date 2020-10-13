package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.TktPrmtr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface TktPrmtrRepository extends JpaRepository<TktPrmtr, Integer>, JpaSpecificationExecutor<TktPrmtr> {
    @Query("SELECT * FROM `tkt_prmtr` where VLDTY_DATE in (SELECT MAX( VLDTY_DATE ) from tkt_prmtr)")
    public TktPrmtr findByMaxVldtyDate();
}