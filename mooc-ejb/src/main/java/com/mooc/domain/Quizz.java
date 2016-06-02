package com.mooc.domain;

import java.io.Serializable;
import java.util.List;

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
	private List<String> choices;
	private Integer correctQuestionId;

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

	public List<String> getChoices() {
		return choices;
	}

	public void setChoices(List<String> choices) {
		this.choices = choices;
	}

	public Integer getCorrectQuestionId() {
		return correctQuestionId;
	}

	public void setCorrectQuestionId(Integer correctQuestionId) {
		this.correctQuestionId = correctQuestionId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
