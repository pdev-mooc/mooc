package com.mooc.services;

import javax.ejb.Remote;

import com.mooc.domain.Comment;
import com.mooc.domain.Learner;
import com.mooc.domain.Session;
import com.mooc.domain.Trainer;

@Remote
public interface SessionRemoteService extends EntityRemoteService<Session> {

	

}
