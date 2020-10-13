package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.CrrgClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CrrgClassRepository extends JpaRepository<CrrgClass, Integer>, JpaSpecificationExecutor<CrrgClass> {

}