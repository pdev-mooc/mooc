package com.mooc.domain;

import java.io.Serializable;
import java.util.ArrayList;
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

@Table(name = "courseofsession")
@Entity

public class CourseOfSession implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String description;
	@ManyToOne(cascade = CascadeType.ALL)
	private Session session;
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;
	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "courseOfSession", cascade=CascadeType.ALL)
	private List<Chapters> chapters = new ArrayList<>();

	public CourseOfSession() {		
	}

	public CourseOfSession(String title, String description) {
		this.title = title;
		this.description = description;
		
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public List<Chapters> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapters> chapters) {
		this.chapters = chapters;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CourseOfSession)) {
			return false;
		}
		CourseOfSession other = (CourseOfSession) obj;
		return getId() == other.getId();
	}

	public CourseOfSession(Session session) {
		super();
		this.session = session;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
