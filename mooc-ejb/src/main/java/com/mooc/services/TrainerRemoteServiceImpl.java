package com.mooc.services;

import javax.ejb.Stateless;

import com.mooc.domain.Learner;
import com.mooc.domain.Trainer;


@Stateless

public class TrainerRemoteServiceImpl extends GenericRemoteService<Trainer> implements TrainerRemoteService {

	protected TrainerRemoteServiceImpl() {
		super(Trainer.class);
	}

	@Override
	public Trainer findByEmail(String email) {
		return entityManager.find(Trainer.class, email);
	}

	

	

}