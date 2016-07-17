package com.mooc.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.mooc.domain.Course;
import com.mooc.services.CourseRemoteService;

@ManagedBean @SessionScoped
public class CourseView {

	@EJB
	private CourseRemoteService courseService;
	private Course course;

	public String loadCourse(Integer courseId) {
		course = courseService.findById(courseId);
		return "website-take-course.html";
	}

	public Course getCourse() {
		return course;
	}

}
