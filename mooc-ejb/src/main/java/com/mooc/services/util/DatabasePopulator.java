package com.mooc.services.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.mooc.domain.Chapter;
import com.mooc.domain.CommitteeMember;
import com.mooc.domain.Course;
import com.mooc.domain.Learner;
import com.mooc.domain.Role;
import com.mooc.domain.Student;
import com.mooc.domain.Tutor;
import com.mooc.enumeration.UserRole;
import com.mooc.services.CourseRemoteService;
import com.mooc.services.LearnerRemoteService;
import com.mooc.services.RoleRemoteService;
import com.mooc.services.UserRemoteService;

@Singleton
@Startup
public class DatabasePopulator {
	@EJB
	private UserRemoteService userService;
	@EJB
	private CourseRemoteService courseService;
	@EJB
	private LearnerRemoteService learnerRemoteService;
	@EJB
	private RoleRemoteService roleRemoteService;
	public DatabasePopulator() {
	}

	@PostConstruct
	public void createData() {
		// Committee member
		userService.create(new CommitteeMember("Admin", "Admin", "admin@mooc.com", "pw"));
		// Tutor
		Tutor tutor = new Tutor("George", "Orwell", "gorwell@mooc.com", "pw");
		// Add courses
		Course course1 = new Course("Java", "Java Course", tutor);
		Course course2 = new Course("PHP", "PHP Course", tutor);
		Course course3 = new Course("C#", "C# Course", tutor);
		course1.getChapters().add(new Chapter("Chapter 1", "Introduction to Java", "", course1));
		course1.getChapters().add(new Chapter("Chapter 1", "Introduction to PHP", "", course2));
		course1.getChapters().add(new Chapter("Chapter 1", "Introduction to C#", "", course3));
		tutor.getCourses().add(course1);
		tutor.getCourses().add(course2);
		tutor.getCourses().add(course3);
		userService.create(tutor);
		// Student
		Student student = new Student("Belanes", "Salem", "sbelanes@mooc.com", "pw");
		List<Course> courses = courseService.findAll();
		List<Course> coursesList = new ArrayList<>();
		coursesList.add(courses.get(0));
		coursesList.add(courses.get(1));
		student.setCourses(coursesList);
		userService.persist(student);	
		/*****learner***/
		Learner l = new Learner();
		l.setFirstName("malek");
		l.setEmail("malek@gmail.com");
		l.setLastName("belanes");
		l.setPassword("malek");
		Role r = new Role();
		r.setRoleName("learner");
		l.setUserRole(r);		
		learnerRemoteService.create(l);
		
		/*Learner2*/
		Learner l2 = new Learner();
		l2.setFirstName("malek2");
		l2.setEmail("malek2@gmail.com");
		l2.setLastName("belanes");
		l2.setPassword("malek2");	
		
		l2.setUserRole(roleRemoteService.findByName(r.getRoleName()));
		learnerRemoteService.create(l2);
	}

}
