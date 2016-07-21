package com.mooc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "quizz")
@Entity
public class Quizz implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	private String question;
	private String propositions;
	private Integer response;
	private String title;
	
	public Quizz() {		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getPropositions() {
		return propositions;
	}
	public void setPropositions(String propositions) {
		this.propositions = propositions;
	}
	public Integer getResponse() {
		return response;
	}
	public void setResponse(Integer response) {
		this.response = response;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	
}

