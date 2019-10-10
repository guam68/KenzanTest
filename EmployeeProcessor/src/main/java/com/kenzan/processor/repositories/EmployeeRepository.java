package com.kenzan.processor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kenzan.processor.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
}
