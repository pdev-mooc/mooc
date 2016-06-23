package com.mooc.services;

import javax.ejb.Stateless;

import com.mooc.domain.StudentCourse;

@Stateless
public class StudentCourseRemoteServiceImpl extends GenericRemoteService<StudentCourse> implements StudentCourseRemoteService {

	protected StudentCourseRemoteServiceImpl() {
		super(StudentCourse.class);
	}

}
