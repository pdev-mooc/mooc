package com.mooc.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "trainer")
@Entity
public class Trainer extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	private Session session;
	
	@OneToOne
	@JoinColumn(name="session_id", referencedColumnName="id")
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}

}
