package com.kenzan.processor.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kenzan.processor.entities.Employee;
import com.kenzan.processor.services.EmployeeService;

@RestController
@RequestMapping("api")
public class EmployeeController {
	@Autowired
	private EmployeeService serv;
	
	@GetMapping("employees")
	public List<Employee> index(HttpServletResponse resp) {
		return serv.getAllEmployees();
	}
	
	@GetMapping("employees/{id}")
	public Employee getEmployeeById(@PathVariable("id") int id, HttpServletResponse resp) {
		Employee employee = null;

		try {
			employee = serv.getEmployeeById(id);
			if (employee != null) {
				resp.setStatus(200);
			} else {
			resp.setStatus(404);
			}
		} catch (Exception e){
			System.err.println(e);
			resp.setStatus(400);
		}

		return employee;
	}
	
	@PostMapping("employees")
	public Employee addEmployee(@RequestBody Employee employee, HttpServletResponse resp, 
								HttpServletRequest req) {
		Employee newEmp = null;

		try {
			newEmp = serv.createEmployee(employee);
			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/" + newEmp.getId());
			resp.setHeader("Location", url.toString());

		} catch (Exception e) {
			System.err.println(e);
			resp.setStatus(400);
		}
		return newEmp;
	}
	
	@PutMapping("employees/{id}")
	public Employee updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee,
									HttpServletResponse resp) {
		Employee updated = null;
		try {
			updated = serv.updateEmployee(id, employee);
			if (updated != null) {
				resp.setStatus(200);
			} else {
				resp.setStatus(404);
			}
			
		} catch (Exception e) {
			System.err.println(e);
			resp.setStatus(400);
		}
		return updated;
	}
	
	@DeleteMapping("employees/{id}")
	public void disableEmployee(@PathVariable("id") int id, HttpServletResponse resp) {
		try {
			if (serv.disableEmployee(id)) {
				resp.setStatus(204);
			} else {
				resp.setStatus(404);
			}
			
		} catch (Exception e) {
			System.err.println(e);
			resp.setStatus(400);
		}
	}

}









