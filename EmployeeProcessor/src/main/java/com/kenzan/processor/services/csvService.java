package com.kenzan.processor.services;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.kenzan.processor.entities.Employee;

@Service
public class csvService {
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private EmployeeService serv;

	csvService() {
	}

	@PostConstruct
	public void init() {
		Resource resource = resourceLoader.getResource("classpath:assets/csv/employees.csv");

		CsvMapper csvMapper = new CsvMapper();
		CsvSchema schema = CsvSchema.emptySchema().withHeader();

		ObjectReader oReader = csvMapper.readerFor(Employee.class).with(schema);
		try (Reader reader = new FileReader(resource.getFile())) {
			MappingIterator<Employee> mi = oReader.readValues(reader);
			while (mi.hasNext()) {
				Employee emp = mi.next();
				emp.setStatus(true);
				serv.createEmployee(emp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
