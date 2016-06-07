package com.mooc.services;

import javax.ejb.Stateless;

import com.mooc.domain.Chapter;

@Stateless
public class ChapterRemoteServiceImpl extends GenericRemoteService<Chapter> implements ChapterRemoteService {

	public ChapterRemoteServiceImpl() {
		super(Chapter.class);
	}

}
