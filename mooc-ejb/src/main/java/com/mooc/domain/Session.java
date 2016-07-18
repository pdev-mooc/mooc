package com.mooc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Table(name = "session")
@Entity
public class Session implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	
	private LocalDate startDate;
	private LocalDate endDate;
	@OneToOne(mappedBy="session")
	private Trainer trainer;
	private List <Learner> learners;
	private String description;
	
	private List<CourseOfSession> coursesOfSession;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	


	public Trainer getTrainer() {
		return trainer;
	}
	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}
	@OneToMany(mappedBy = "session",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	public List<CourseOfSession> getCoursesOfSession() {
		return coursesOfSession;
	}
	public void setCoursesOfSession(List<CourseOfSession> coursesOfSession) {
		this.coursesOfSession = coursesOfSession;
	}
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	public List <Learner> getLearners() {
		return learners;
	}
	public void setLearners(List <Learner> learners) {
		this.learners = learners;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	

}
