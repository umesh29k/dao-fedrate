package com.jrstep.dao.local.repository;

import com.jrstep.dao.local.model.StudentsSeq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentsSeqRepository extends JpaRepository<StudentsSeq, Void>, JpaSpecificationExecutor<StudentsSeq> {

}