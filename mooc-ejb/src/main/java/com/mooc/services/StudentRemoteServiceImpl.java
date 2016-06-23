package com.mooc.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.mooc.domain.Course;
import com.mooc.domain.Student;

@Stateless
public class StudentRemoteServiceImpl extends GenericRemoteService<Student> implements StudentRemoteService {

	protected StudentRemoteServiceImpl() {
		super(Student.class);
	}

	@Override
	public List<Student> findEnrolledStudentsByCourseId(Integer courseId) {
		List<Student> enrolledStudents = new ArrayList<>();

		List<Student> studentList = findAll();
		for (Student s : studentList) {
			for (Course c : s.getCourses()) {
				if (c.getId().equals(courseId)) {
					enrolledStudents.add(s);
					break;
				}
			}
		}
		return enrolledStudents;
	}

}
