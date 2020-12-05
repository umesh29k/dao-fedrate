package com.jrstep.dao.remote.repository;

import com.jrstep.dao.remote.model.Calndr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface CalndrRepository extends JpaRepository<Calndr, Integer>, JpaSpecificationExecutor<Calndr> {
    @Query("SELECT c from Calndr c where c.calndrDate = :calndrDate")
    public Calndr findByCalndrDate(String calndrDate);
}