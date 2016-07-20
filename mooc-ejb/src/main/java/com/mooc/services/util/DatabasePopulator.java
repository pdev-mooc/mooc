package com.mooc.services.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.mooc.domain.Chapter;
import com.mooc.domain.CommitteeMember;
import com.mooc.domain.Course;
import com.mooc.domain.Message;
import com.mooc.domain.Person;
import com.mooc.domain.Student;
import com.mooc.domain.Tutor;
import com.mooc.services.CourseRemoteService;
import com.mooc.services.MessageRemoteService;
import com.mooc.services.UserRemoteService;

@Singleton
@Startup
public class DatabasePopulator {
	@EJB
	private UserRemoteService userService;
	@EJB
	private CourseRemoteService courseService;
	@EJB
	private MessageRemoteService messageService;

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
		course1.getChapters().add(new Chapter("Chapter 1", "Introduction to Java",
				"Like any programming language, the Java language has its own structure, syntax rules,"
				+ "and programming paradigm. The Java language's programming paradigm is based on the concept of OOP, which the language's features support."
				+ "The Java language is a C-language derivative, so its syntax rules look much like C's."
				+ "For example, code blocks are modularized into methods and delimited by braces ({ and }), and variables are declared before they are used."
				+ "Structurally, the Java language starts with packages. A package is the Java language's namespace mechanism."
				+ " Within packages are classes, and within classes are methods, variables, constants, and more."
				+ "You learn about the parts of the Java language in this tutorial.", course1));
		course1.getChapters().add(new Chapter("Chapter 1", "Introduction to PHP",
				"PHP is a programming language that can do all sorts of things: evaluate form data sent from a browser,"
				+ "build custom web content to serve the browser, talk to a database,"
				+ "and even send and receive cookies (little packets of data that your browser uses to remember things,"
				+ "like if you're logged in to Codecademy)."
				+ "Check out the code in the editor. Looks familiar, doesn't it?"
				+ "That's because a lot of it is regular old HTML! The PHP code is written in the <?php and ?> ."
				+ "See how it generates numbers, creates lists, and adds text directly to your webpage?",
				course2));
		course1.getChapters().add(new Chapter("Chapter 1", "Introduction to C#",
				"C# (pronounced \"C sharp\") is a simple, modern, object-oriented, and type-safe programming language."
						+ "It will immediately be familiar to C and C++ programmers."
						+ "C# combines the high productivity of Rapid Application Development (RAD)"
						+ "languages and the raw power of C++.",
				course3));
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
		
		//Person sender = new Person("dahmani", "said", "said@mooc.com", "pw");
		//Person reciver = new Person("belanes", "salem", "salem@mooc.com", "pw");
	
		Person sender = userService.findUserById(1);
		Person reciver =userService.findUserById(2);
		Message message = new Message("blabla", sender, reciver);
		messageService.persist(message);
		
		
	}

}
