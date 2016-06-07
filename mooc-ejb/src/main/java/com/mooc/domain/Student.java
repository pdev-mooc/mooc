package com.mooc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "student")
@Entity
public class Student extends Person {

	private static final long serialVersionUID = 1L;

	public Student() {		
	}

	public Student(String firstName, String lastName, String login, String password) {
		super(firstName, lastName, login, password);
	}

}
