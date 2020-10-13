package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.Orgnsm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrgnsmRepository extends JpaRepository<Orgnsm, Integer>, JpaSpecificationExecutor<Orgnsm> {
    public List<Orgnsm> findByOrgnsmId(String orgnsmId);
}