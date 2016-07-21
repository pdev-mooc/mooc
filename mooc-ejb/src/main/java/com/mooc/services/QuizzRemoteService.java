package com.mooc.services;


import javax.ejb.Remote;

import com.mooc.domain.Quizz;

@Remote
public interface QuizzRemoteService extends EntityRemoteService<Quizz>  {
	Quizz findById(Integer id);
	Quizz saveResponse(Integer id,Integer response);
//	List<Quizz> getQuizzs(Integer idChapter);
}
