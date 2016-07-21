package com.mooc.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.mooc.domain.forumtopic;

@Stateless
public class forum_topicRemoteServiceImpl  extends GenericRemoteService<forumtopic> implements forum_topicRemoteService {

	public forum_topicRemoteServiceImpl() {
		super(forumtopic.class);
	}
	
	@Override
	public List<forumtopic> getAll() {
		TypedQuery<forumtopic> ft = entityManager
				.createQuery("select ft from forumtopic ft", forumtopic.class);
		List<forumtopic> list = ft.getResultList();
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}
}
