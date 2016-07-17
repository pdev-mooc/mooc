package com.mooc.services;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.mooc.domain.Chapter;

@Stateless
public class ChapterRemoteServiceImpl extends GenericRemoteService<Chapter> implements ChapterRemoteService {

	public ChapterRemoteServiceImpl() {
		super(Chapter.class);
	}

	@Override
	public Chapter findChapter(Integer courseId, Integer chapterId) {
		TypedQuery<Chapter> q = entityManager
				.createQuery("select u from Chapter u where u.id=?0 and u.course.id=?1", Chapter.class)
				.setParameter(0, chapterId).setParameter(1, courseId);
		return q.getSingleResult();
	}

}
