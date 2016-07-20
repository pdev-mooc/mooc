package com.mooc.services;

import java.util.List;

import javax.ejb.Remote;

import com.mooc.domain.Message;
import com.mooc.domain.Person;


@Remote
public interface MessageRemoteService extends EntityRemoteService<Message> {

	List<Message> findMessage(Person reciver, Person sender);
	void sendMessage(Person reciver, Person sender, String content);
}
