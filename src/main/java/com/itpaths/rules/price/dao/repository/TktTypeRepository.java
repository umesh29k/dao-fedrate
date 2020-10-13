package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.TktType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TktTypeRepository extends JpaRepository<TktType, Void>, JpaSpecificationExecutor<TktType> {

}