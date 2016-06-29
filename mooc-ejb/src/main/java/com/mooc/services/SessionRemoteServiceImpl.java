package com.mooc.services;

import javax.ejb.Stateless;

import com.mooc.domain.Learner;
import com.mooc.domain.Session;
import com.mooc.domain.Trainer;


@Stateless

public class SessionRemoteServiceImpl extends GenericRemoteService<Session> implements SessionRemoteService {

	protected SessionRemoteServiceImpl() {
		super(Session.class);
	}

	
	

	

}