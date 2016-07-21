package com.mooc.services;

import java.util.List;

import javax.ejb.Remote;

import com.mooc.domain.Message;
import com.mooc.domain.Person;


@Remote
public interface MessageRemoteService extends EntityRemoteService<Message> {

	List<Message> findMessage(Person reciver, Person sender);
	Message findLastMessageBySender(Person sender , Person reciver );
	void sendMessage(Person reciver, Person sender, String content);
}
