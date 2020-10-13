package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.CityNetSupplmnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CityNetSupplmntRepository extends JpaRepository<CityNetSupplmnt, Void>, JpaSpecificationExecutor<CityNetSupplmnt> {

}