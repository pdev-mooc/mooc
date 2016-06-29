package com.mooc.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	private String title;
	private String description;
	private String img;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade=CascadeType.ALL)
	private List<CourseOfSession> courseOfSession;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<CourseOfSession> getCourseOfSession() {
		return courseOfSession;
	}
	public void setCourseOfSession(List<CourseOfSession> courseOfSession) {
		this.courseOfSession = courseOfSession;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}



}
