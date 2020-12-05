package com.jrstep.dao.fedrate.repository;

import com.jrstep.dao.fedrate.model.Orgnsm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FOrgnsmRepository extends JpaRepository<Orgnsm, Integer>, JpaSpecificationExecutor<Orgnsm> {
    public List<Orgnsm> findByOrgnsmId(String orgnsmId);
}