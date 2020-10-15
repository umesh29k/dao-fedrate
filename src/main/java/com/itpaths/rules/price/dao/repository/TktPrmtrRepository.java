package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.TktPrmtr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TktPrmtrRepository extends JpaRepository<TktPrmtr, Integer>, JpaSpecificationExecutor<TktPrmtr> {
    @Query("SELECT t FROM TktPrmtr t where t.vldtyDate in (SELECT MAX( tpc.vldtyDate ) as mvd from TktPrmtr tpc)")
    public TktPrmtr find();
}