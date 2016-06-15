package com.mooc.services;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

public class GenericRemoteService<T> implements EntityRemoteService<T> {

	@PersistenceContext
	protected EntityManager entityManager;
	private final Class<T> clazz;

	protected GenericRemoteService(Class<T> clazz) {
		this.clazz = clazz;
	}

	public boolean create(T entity) {
		try {
			entityManager.persist(entity);
		} catch (EntityExistsException ex) {
			return false;
		}
		return true;
	}

	public List<T> findAll() {
		List<T> bookList = entityManager.createQuery("select e from " + clazz.getSimpleName()
		+ " e", clazz).getResultList();
		return bookList;
	}

	public List<T> findBy(CriteriaQuery<T> criteria) {
		TypedQuery<T> query = entityManager.createQuery(criteria);
		return query.getResultList();
	}

	public boolean remove(T entity) {
		try {
			entityManager.remove(entityManager.merge(entity));
		} catch (IllegalArgumentException | TransactionRequiredException ex) {
			return false;
		}
		return true;
	}

	public boolean update(T entity) {
		try {
			entityManager.merge(entity);
		} catch (IllegalArgumentException | TransactionRequiredException ex) {
			return false;
		}
		return true;
	}

	@Override
	public boolean persist(T entity) {
		try {
			entityManager.merge(entity);
		} catch (EntityExistsException ex) {
			return false;
		}
		return true;
		
	}
}
