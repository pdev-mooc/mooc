package com.mooc.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "message")
@Entity
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	private String content;
	@ManyToOne
	private Person sender;
	@ManyToOne
	private Person reciver;
	private LocalDate sendDate;
	public String getcontent() {
		return content;
	}
	public void setcontent(String content) {
		this.content = content;
	}
	public Person getSender() {
		return sender;
	}
	public void setSender(Person sender) {
		this.sender = sender;
	}
	public Person getReciver() {
		return reciver;
	}
	public void setReciver(Person reciver) {
		this.reciver = reciver;
	}
	public LocalDate getSendDate() {
		return sendDate;
	}
	public void setSendDate(LocalDate sendDate) {
		this.sendDate = sendDate;
	}
	public Message() {		
	}
	public Message(String content, Person sender, Person reciver) {
		super();
		this.content = content;
		this.sender = sender;
		this.reciver = reciver;
		this.sendDate = LocalDate.now() ;
		
	}

	
	

}
