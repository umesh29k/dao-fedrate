package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.TktCDBrkpnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TktCDBrkpntRepository extends JpaRepository<TktCDBrkpnt, Integer>, JpaSpecificationExecutor<TktCDBrkpnt> {
    @Query("SELECT pc FROM TktCDBrkpnt pc where pc.tktCdbSeqNo>=:tktCdbSeqNo and pc.tpVrsn=:tpVrsn")
    public List<TktCDBrkpnt> findByTktCdbSeqNoAndTpVrsn(int tktCdbSeqNo, int tpVrsn);
}