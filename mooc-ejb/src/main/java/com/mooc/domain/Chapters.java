package com.mooc.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "chapters")
@Entity
public class Chapters implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	private String title;
	private String recap;
	private String body;
	@OneToMany(mappedBy = "chapters",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Comment> comments;
	
	
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	private CourseOfSession courseOfSession;
	
	public Chapters() {
	}

	public Chapters(String title, String recap, String body,  CourseOfSession courseOfSession) {
		super();
		this.title = title;
		this.recap = recap;
		this.body = body;
		
		this.courseOfSession = courseOfSession;
	}


	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRecap() {
		return recap;
	}

	public void setRecap(String recap) {
		this.recap = recap;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}



}

