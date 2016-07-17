package com.mooc.bean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mooc.domain.Person;
import com.mooc.domain.Student;
import com.mooc.services.UserRemoteService;
import com.mooc.util.SessionUtils;

@ManagedBean @SessionScoped
public class StudentView {

	@EJB
	private UserRemoteService userService;
	private Student student = new Student();

	public String login() {
		Person user = userService.findUser(student.getEmail(), student.getPassword());
		if (user == null || user instanceof Student == false) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Incorrect Email or Passowrd", "Incorrect Email or Passowrd"));
			return "/views/learning-1.1.0/html/login.html";
		}
		student = (Student) user;
		SessionUtils.setCurrentStudent(student);
		return "website-student-dashboard.html";
	}

	public Student getStudent() {
		return student;
	}

}
