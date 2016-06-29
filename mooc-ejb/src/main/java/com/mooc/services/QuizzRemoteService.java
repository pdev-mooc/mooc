package com.mooc.services;

import javax.ejb.Remote;

import com.mooc.domain.Quizz;

@Remote
public interface QuizzRemoteService extends EntityRemoteService<Quizz>  {

}
