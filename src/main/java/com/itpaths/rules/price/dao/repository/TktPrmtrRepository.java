package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.TktPrmtr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TktPrmtrRepository extends JpaRepository<TktPrmtr, Integer>, JpaSpecificationExecutor<TktPrmtr> {

}