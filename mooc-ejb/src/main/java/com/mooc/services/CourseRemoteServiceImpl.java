package com.mooc.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.mooc.domain.Course;

@Stateless
public class CourseRemoteServiceImpl extends GenericRemoteService<Course> implements CourseRemoteService {

	public CourseRemoteServiceImpl() {
		super(Course.class);
	}

	@Override
	public Course findFirst(String title) {
		TypedQuery<Course> q = entityManager
				.createQuery("select u from Course u where u.title=?0", Course.class)
				.setParameter(0, title);
		List<Course> list = q.getResultList();
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

}
