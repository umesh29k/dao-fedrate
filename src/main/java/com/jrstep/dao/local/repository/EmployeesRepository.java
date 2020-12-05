package com.jrstep.dao.local.repository;

import com.jrstep.dao.local.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeesRepository extends JpaRepository<Employees, Integer>, JpaSpecificationExecutor<Employees> {

}