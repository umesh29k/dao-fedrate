package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.PcLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PcLimitRepository extends JpaRepository<PcLimit, Void>, JpaSpecificationExecutor<PcLimit> {

}