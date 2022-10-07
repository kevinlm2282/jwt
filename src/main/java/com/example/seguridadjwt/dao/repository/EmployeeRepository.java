package com.example.seguridadjwt.dao.repository;

import com.example.seguridadjwt.dao.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
}

