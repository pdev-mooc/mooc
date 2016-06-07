package com.mooc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "admin")
@Entity
public class Admin extends Person {

	private static final long serialVersionUID = 1L;

	public Admin() {
	}

	public Admin(String firstName, String lastName, String login, String password) {
		super(firstName, lastName, login, password);
	}

}
