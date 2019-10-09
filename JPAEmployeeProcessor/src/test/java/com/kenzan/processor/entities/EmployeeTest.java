package com.kenzan.processor.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmployeeTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Employee employee;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("Processor");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		employee = em.find(Employee.class, 1);
		
//		| id | first_name | middle_initial | last_name | date_of_birth | date_of_employment | status |
//		|  1 | James      | E              | Taylor    | 1984-10-12    | 2015-01-30         |      1 |
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	@DisplayName("Testing valid Catch annotations")
	void test() {
		assertNotNull(employee);
		assertEquals(1, employee.getId());
		assertEquals("James", employee.getFirstName());
		assertEquals("E", employee.getMiddleInitial());
		assertEquals("Taylor", employee.getLastName());
		assertEquals("1984-10-12", employee.getDateOfBirth().toString());
		assertEquals("2015-01-30", employee.getDateOfEmployment().toString());
		assertTrue(employee.getStatus());
	}

}
