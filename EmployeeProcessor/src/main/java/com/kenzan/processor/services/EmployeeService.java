package com.kenzan.processor.services;

import java.util.List;

import com.kenzan.processor.entities.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	Employee getEmployeeById(int id);
	Employee createEmployee(Employee emp);
	Employee updateEmployee(int id, Employee emp);
	boolean disableEmployee(int id);
}
