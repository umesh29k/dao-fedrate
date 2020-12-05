package com.jrstep.dao.fedrate.repository;

import com.jrstep.dao.fedrate.model.Calndr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface FCalndrRepository{//} extends JpaRepository<Calndr, Integer>, JpaSpecificationExecutor<Calndr> {
    /*@Query("SELECT c from Calndr c where c.calndrDate = :calndrDate")
    public Calndr findByCalndrDate(String calndrDate);

    @Modifying
    @Query(value = "insert into Calndr (id, calndrDate)" +
            " values ( :id,:calndrDate)", nativeQuery = true)
    public void insertCalndr(@Param("id") Integer id, @Param("calndrDate") String calndrDate);*/
}