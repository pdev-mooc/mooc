package com.mooc.services;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.criteria.CriteriaQuery;

@Remote
public interface EntityRemoteService<T> {

	boolean create(T entity);
	List<T> findAll();
	List<T> findBy(CriteriaQuery<T> criteria);
	boolean remove(T entity);
	boolean update(T entity);
	boolean persist(T entity);
	CriteriaQuery<T> createCriteriaQuery();
}
