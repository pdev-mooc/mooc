package com.mooc.services;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.TypedQuery;

import com.mooc.domain.Person;

@Remote
public interface UserRemoteService extends EntityRemoteService<Person> {

	Person findUser(String email, String password);
	
	Person findUserById(Integer id );
}
