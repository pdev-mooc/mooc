package com.mooc.administration.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.mooc.domain.Chapter;
import com.mooc.services.CourseRemoteService;

@ManagedBean
@SessionScoped
public class ChapterView {

	@EJB
	private CourseRemoteService courseService;
	private Chapter chapter = new Chapter();

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

}
