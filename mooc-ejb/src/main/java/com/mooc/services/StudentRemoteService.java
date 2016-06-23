package com.mooc.services;

import java.util.List;

import javax.ejb.Remote;

import com.mooc.domain.Student;

@Remote
public interface StudentRemoteService extends EntityRemoteService<Student> {

	List<Student> findEnrolledStudentsByCourseId(Integer courseId);
}
