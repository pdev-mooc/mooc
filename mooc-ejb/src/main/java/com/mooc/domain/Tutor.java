package com.mooc.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "tutor")
@Entity
public class Tutor extends Person {

	private static final long serialVersionUID = 1L;

	private String biography;

	@OneToMany(fetch=FetchType.EAGER, targetEntity=Course.class, mappedBy="tutor", cascade=CascadeType.ALL)
	private List<Course> courses = new ArrayList<>();

	public Tutor() {
	}

	public Tutor(String firstName, String lastName, String login, String password) {
		super(firstName, lastName, login, password);
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

}
