package com.kenzan.processor.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="middle_initial")
	private String middleInitial;

	@Column(name="last_name")
	private String lastName;

	@Temporal(TemporalType.DATE)  
	@Column(name="date_of_birth")
	private Date dateOfBirth;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_employment")
	private Date dateOfEmployment;

	private Boolean status;
	

	public Employee() {}
	
	public Employee(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Employee(int id, String firstName, String middleInitial, String lastName, Date dateOfBirth,
			Date dateOfEmployment, Boolean status) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfEmployment = dateOfEmployment;
		this.status = status;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleInitial() {
		return middleInitial;
	}
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Date getDateOfEmployment() {
		return dateOfEmployment;
	}
	public void setDateOfEmployment(Date dateOfEmployment) {
		this.dateOfEmployment = dateOfEmployment;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", middleInitial=" + middleInitial + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", dateOfEmployment=" + dateOfEmployment + ", status="
				+ status + "]";
	}
}
