package com.mooc.services;

import javax.ejb.Stateless;

import com.mooc.domain.Learner;


@Stateless

public class LearnerRemoteServiceImpl extends GenericRemoteService<Learner> implements LearnerRemoteService {

	protected LearnerRemoteServiceImpl() {
		super(Learner.class);
	}

	@Override
	public Learner findByEmail(String email) {
		return entityManager.find(Learner.class, email);
	}

	

	

}