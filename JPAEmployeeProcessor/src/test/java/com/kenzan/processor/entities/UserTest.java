package com.kenzan.processor.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);

//		| id | username | password                                                     |
//		|  1 | kenzan   | $2a$10$KqSKT7QByBooYVE6WDicNucr5gqcME6EcdquH0M5t//OS18E1OlZ6 |
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	@DisplayName("Testing valid user annotations")
	void test() {
		assertNotNull(user);
		assertEquals(1, user.getId());
		assertEquals("kenzan", user.getUsername());
		assertEquals("$2a$10$KqSKT7QByBooYVE6WDicNucr5gqcME6EcdquH0M5t//OS18E1OlZ6", user.getPassword());
	}

}
