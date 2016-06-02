package com.mooc.services;

import javax.ejb.Stateless;

import com.mooc.domain.Quizz;

@Stateless
public class QuizzRemoteServiceImpl extends GenericRemoteService<Quizz> implements QuizzRemoteService {

	public QuizzRemoteServiceImpl() {
		super(Quizz.class);
	}

}
