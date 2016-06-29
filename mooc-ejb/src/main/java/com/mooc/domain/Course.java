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

@Table(name = "course")
@Entity

public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String description;
	@ManyToOne
	private Tutor tutor;
	@OneToMany(fetch = FetchType.EAGER,targetEntity = Chapter.class, mappedBy = "course", cascade=CascadeType.ALL)
	private List<Chapter> chapters = new ArrayList<>();

	public Course() {		
	}

	public Course(String title, String description, Tutor tutor) {
		this.title = title;
		this.description = description;
		this.tutor = tutor;
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

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Course)) {
			return false;
		}
		Course other = (Course) obj;
		return getId() == other.getId();
	}

}
