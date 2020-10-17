package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.Calndr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;

public interface CalndrRepository extends JpaRepository<Calndr, Integer>, JpaSpecificationExecutor<Calndr> {
    @Query("SELECT c from Calndr c where c.calndrDate = :calndrDate")
    public Calndr findByCalndrDate(String calndrDate);
}