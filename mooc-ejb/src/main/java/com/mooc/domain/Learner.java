package com.mooc.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.mooc.enumeration.UserRole;

@Entity
public class Learner extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String picture;
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Session> sessions;
	@OneToMany(mappedBy = "learner",cascade=CascadeType.ALL)
	private List<Comment> comments;
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Learner() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Learner(String firstName, String lastName, String email, String password, Role userRole) {
		super(firstName, lastName, email, password, userRole);
		// TODO Auto-generated constructor stub
	}
	public List<Session> getSessions() {
		return sessions;
	}
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER,mappedBy="learner")
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	

}
