package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.PcVoygr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PcVoygrRepository extends JpaRepository<PcVoygr, Integer>, JpaSpecificationExecutor<PcVoygr> {

}