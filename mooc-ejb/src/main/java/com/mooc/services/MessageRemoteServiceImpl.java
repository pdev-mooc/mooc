package com.mooc.services;


import java.util.List;

import javax.ejb.Stateless;

import com.mooc.domain.Message;
import com.mooc.domain.Person;

@Stateless
public class MessageRemoteServiceImpl extends GenericRemoteService<Message> implements MessageRemoteService {

	public MessageRemoteServiceImpl() {
		super(Message.class);
	}


	@Override
	public List<Message> findMessage(Person reciver, Person sender) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void sendMessage(Person reciver, Person sender, String content) {
		
		
		
	}

}
