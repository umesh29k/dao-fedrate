package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.StationsDistances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StationsDistancesRepository extends JpaRepository<StationsDistances, Integer>, JpaSpecificationExecutor<StationsDistances> {
    public StationsDistances findByDstncFromTstatnIdAndDstncToTstatnId(String dstncFromTstatnId, String dstncToTstatnId);
}