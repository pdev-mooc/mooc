package com.mooc.domain;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name = "forumComment")
@Entity
public class ForumComment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	@ManyToOne
	private Person sender;
	private String message;
	@NotNull
	@Column(name = "date_time", nullable = false)
	private LocalDateTime dateTime;

	public ForumComment() {
		this.dateTime = LocalDateTime.now();
	}

	public ForumComment(Person sender, String comment) {
		this.sender = sender;
		this.message = comment;
		this.dateTime = LocalDateTime.now();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Person getSender() {
		return sender;
	}

	public void setSender(Person sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Duration getDuration() {
		return Duration.between(dateTime, LocalDateTime.now());
	}

}
