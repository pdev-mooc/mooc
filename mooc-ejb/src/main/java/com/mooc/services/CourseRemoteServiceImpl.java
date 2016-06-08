package com.mooc.services;

import javax.ejb.Stateless;

import com.mooc.domain.Course;

@Stateless
public class CourseRemoteServiceImpl extends GenericRemoteService<Course> implements CourseRemoteService {

	public CourseRemoteServiceImpl() {
		super(Course.class);
	}

}
