package com.mooc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "committee_member")
@Entity
public class CommitteeMember extends Person {

	private static final long serialVersionUID = 1L;

	public CommitteeMember() {
	}

	public CommitteeMember(String firstName, String lastName, String login, String password) {
		super(firstName, lastName, login, password);
	}

}
