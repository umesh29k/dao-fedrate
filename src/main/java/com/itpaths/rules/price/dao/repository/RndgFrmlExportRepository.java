package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.RndgFrmlExport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RndgFrmlExportRepository extends JpaRepository<RndgFrmlExport, Integer>, JpaSpecificationExecutor<RndgFrmlExport> {

}