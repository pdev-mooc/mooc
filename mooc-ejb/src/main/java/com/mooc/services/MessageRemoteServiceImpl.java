package com.mooc.services;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.mooc.domain.Message;
import com.mooc.domain.Person;

@Stateless
public class MessageRemoteServiceImpl extends GenericRemoteService<Message> implements MessageRemoteService {

	public MessageRemoteServiceImpl() {
		super(Message.class);
	}


	@Override
	public List<Message> findMessage(Person reciver, Person sender) {
		TypedQuery<Message> q = entityManager
				.createQuery("select u from Message u where (u.reciver=?0 and u.sender=?1) or (u.reciver=?1 and u.sender=?0) ORDER BY u.id DESC", Message.class)
				.setParameter(0, reciver).setParameter(1, sender);
		List<Message> list = q.getResultList();
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}
	
	@Override
	public Message findLastMessageBySender(Person sender , Person reciver ) {
		TypedQuery<Message> q = entityManager
				.createQuery("select u from Message u where u.reciver=?0 and u.sender=?1 ORDER BY u.id DESC", Message.class)
				.setParameter(0, reciver).setParameter(1, sender);
		List<Message> list = q.getResultList();
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public void sendMessage(Person reciver, Person sender, String content) {
		
		
		
	}

}
