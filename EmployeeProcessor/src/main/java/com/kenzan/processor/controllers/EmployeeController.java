package com.kenzan.processor.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}









