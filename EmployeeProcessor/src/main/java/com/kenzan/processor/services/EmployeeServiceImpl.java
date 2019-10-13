package com.kenzan.processor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenzan.processor.entities.Employee;
import com.kenzan.processor.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository repo;
	

	@Override
	public List<Employee> getAllEmployees(){
		return repo.queryForActiveEmployees();
	}
	
	@Override
	public Employee getEmployeeById(int id) {
		Employee employee = null;
		if (repo.findById(id).isPresent()) {
			employee = repo.queryForActiveEmployeeById(id);
		}
		return employee; 
	}
	
	@Override
	public Employee createEmployee(Employee employee) {
		employee.setStatus(true);
		Employee created = repo.saveAndFlush(employee);
		return created;
	}
	
	@Override
	public Employee updateEmployee(int id, Employee employee) {
		Optional<Employee> managedOpt = repo.findById(id);
		Employee managed = null;
		if (managedOpt.isPresent()) {
			managed = managedOpt.get();

			managed.setFirstName(employee.getFirstName());
			managed.setLastName(employee.getLastName());
			managed.setMiddleInitial(employee.getMiddleInitial());
			managed.setDateOfBirth(employee.getDateOfBirth());
			managed.setDateOfEmployment(employee.getDateOfEmployment());
			managed.setStatus(employee.getStatus());
			
			repo.saveAndFlush(managed);
		}
		return managed;
	}

	@Override
	public boolean disableEmployee(int id) {
		Optional<Employee> disabledOpt = repo.findById(id);
		Employee disabledEmp = null;

		if (disabledOpt.isPresent()) {
			disabledEmp = disabledOpt.get();
			disabledEmp.setStatus(false);
			repo.saveAndFlush(disabledEmp);
			return true;
		}
		return false;
	}
}
