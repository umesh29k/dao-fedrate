package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.Calndr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CalndrRepository extends JpaRepository<Calndr, Void>, JpaSpecificationExecutor<Calndr> {

}