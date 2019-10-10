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
		return repo.findAll();
	}
	
	@Override
	public Employee getEmployeeById(int id) {
		Employee employ = null;
		if (repo.findById(id).isPresent()) {
			employ = repo.findById(id).get();
		}
		return employ; 
	}
	
	@Override
	public Employee createEmployee(Employee employ) {
		Employee created = repo.saveAndFlush(employ);
		return created;
	}
	
	@Override
	public Employee updateEmployee(int id, Employee emp) {
		Optional<Employee> managedOpt = repo.findById(id);
		Employee managed = null;
		if (managedOpt.isPresent()) {
			managed = managedOpt.get();

			managed.setFirstName(emp.getFirstName());
			managed.setLastName(emp.getLastName());
			managed.setMiddleInitial(emp.getMiddleInitial());
			managed.setDateOfBirth(emp.getDateOfBirth());
			managed.setDateOfEmployment(emp.getDateOfEmployment());
			managed.setStatus(emp.getStatus());
			
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
