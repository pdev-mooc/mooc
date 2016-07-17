package com.mooc.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.mooc.domain.Chapter;
import com.mooc.services.ChapterRemoteService;

@ManagedBean @SessionScoped
public class ChapterView {

	@EJB
	private ChapterRemoteService chapterRemoteService;
	private Chapter chapter;

	public String loadChapter(Integer courseId, Integer chapterId) {
		chapter = chapterRemoteService.findChapter(courseId, chapterId);
		return "website-read-chapter.html";
	}

	public Chapter getChapter() {
		return chapter;
	}

}
