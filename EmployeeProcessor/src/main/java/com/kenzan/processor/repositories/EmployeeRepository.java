package com.kenzan.processor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kenzan.processor.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	@Query("SELECT emp FROM Employee emp WHERE emp.status = true")
	List<Employee> queryForActiveEmployees();
	
	@Query("SELECT emp FROM Employee emp WHERE emp.status = true AND emp.id = :id")
	Employee queryForActiveEmployeeById(@Param("id") int id);
}
