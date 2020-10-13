package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.CityNetSupplmnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CityNetSupplmntRepository extends JpaRepository<CityNetSupplmnt, Integer>, JpaSpecificationExecutor<CityNetSupplmnt> {
    public List<CityNetSupplmnt> findById(int id);
}