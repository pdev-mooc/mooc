package com.mooc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "tutor")
@Entity
public class Tutor extends Person {

	private static final long serialVersionUID = 1L;

	public Tutor() {		
	}

	public Tutor(String firstName, String lastName, String login, String password) {
		super(firstName, lastName, login, password);
	}

}
