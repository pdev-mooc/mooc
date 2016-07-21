package com.mooc.services;


import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.mooc.domain.Quizz;

@Stateless
public class QuizzRemoteServiceImpl extends GenericRemoteService<Quizz> implements QuizzRemoteService {

	public QuizzRemoteServiceImpl() {
		super(Quizz.class);
		
	}
	
	@Override
	public Quizz findById(Integer id) {
		TypedQuery<Quizz> q = entityManager
				.createQuery("select q from Quizz q where q.id=?0", Quizz.class)
				.setParameter(0, id);
		return q.getSingleResult();
	}

	@Override
	public Quizz saveResponse(Integer id,Integer response) {
		TypedQuery<Quizz> q = entityManager
				.createQuery("Update quizz set response=?0 where u.id=?1", Quizz.class)
				.setParameter(0, response)
				.setParameter(1, id);
		return q.getSingleResult();
	}
	
//	@Override
//	public List<Quizz> getQuizzs(Integer idChapter) {
//		TypedQuery<Quizz> q = entityManager
//				.createQuery("SELECT ch.* FROM `chapter` INNER JOIN `chapter_quizz` cq ON cq.Chapter_id=ch.id INNER JOIN `quizz` q ON q.id=cq.quizzs_id WHERE ch.id=?0", Quizz.class)
//				.setParameter(0, idChapter);
//		List<Quizz> list = q.getResultList();
//		if (list.isEmpty()) {
//			return null;
//		}
//		return list;
//	}

}
