package com.mooc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "user")
@Entity
public class User extends Person {

	private static final long serialVersionUID = 1L;

	public User() {		
	}

	public User(String firstName, String lastName, String login, String password) {
		super(firstName, lastName, login, password);
	}

}
