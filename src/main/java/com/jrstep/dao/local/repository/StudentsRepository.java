package com.jrstep.dao.local.repository;

import com.jrstep.dao.local.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentsRepository extends JpaRepository<Students, Integer>, JpaSpecificationExecutor<Students> {

}