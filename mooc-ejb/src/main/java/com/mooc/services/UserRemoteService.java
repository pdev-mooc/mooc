package com.mooc.services;

import javax.ejb.Remote;

import com.mooc.domain.Person;

@Remote
public interface UserRemoteService extends EntityRemoteService<Person> {

	Person findUser(String email, String password);
}
