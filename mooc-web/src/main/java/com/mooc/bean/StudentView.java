package com.mooc.bean;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mooc.domain.Person;
import com.mooc.domain.Student;
import com.mooc.services.UserRemoteService;
import com.mooc.util.SessionUtils;

@ManagedBean(name="studentView")
@SessionScoped
public class StudentView {

	@EJB
	private UserRemoteService userService;
	private Student student = new Student();
	private String contactSelected="0";

	public String login() {
		Person user = userService.findUser(student.getEmail(), student.getPassword());
		if (user == null || user instanceof Student == false) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Incorrect Email or Passowrd", "Incorrect Email or Passowrd"));
			return "/views/learning-1.1.0/html/login.html";
		}
		student = (Student) user;
		SessionUtils.setCurrentStudent(student);
		return "website-student-dashboard.html?faces-redirect=true";
	}
	public Set<Person> getContacts(){
	
		Set<Person> listContact = new HashSet<Person>(userService.getUserContact(this.student.getId()));	
		return listContact;
		
	}
	
	public void contacter(String id){
		
		contactSelected=id;
		
	}
	
	public Student getStudent() {
		return student;
	}

	public void saveChanges() {
		userService.persist(student);
	}
	public String getContactSelected() {
		return contactSelected;
	}
	public void setContactSelected(String contactSelected) {
		this.contactSelected = contactSelected;
	}

}
