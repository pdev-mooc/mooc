package com.mooc.services;

import javax.ejb.Remote;

import com.mooc.domain.Comment;
import com.mooc.domain.Learner;

@Remote
public interface LearnerRemoteService extends EntityRemoteService<Learner> {

	public Learner findByEmail(String email);

}
