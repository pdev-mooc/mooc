package com.mooc.administration.bean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mooc.domain.CommitteeMember;
import com.mooc.domain.Person;
import com.mooc.domain.Tutor;
import com.mooc.services.UserRemoteService;

@ManagedBean @SessionScoped
public class CommitteeMemberLoginView {

	@EJB
	private UserRemoteService userService;
	private Person currentUser = new Person();
	private Tutor tutor = new Tutor();

	public String login() {
		System.out.println("hello");
		Person user = userService.findUser(currentUser.getEmail(), currentUser.getPassword());
		if (user == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Incorrect Email or Passowrd", "Incorrect Email or Passowrd"));
			return "/views/login";
		}
		if (user instanceof CommitteeMember == false) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Permission denied", "Permission denied"));
			return "/views/login";
		}
		currentUser = user;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", currentUser);
		return "/views/tutor_signup";
	}

	public String createTutor() {
		Person user = userService.findUser(tutor.getEmail(), tutor.getPassword());
		if (user != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Tutor already exists!"));
		}
		userService.create(tutor);
		tutor = new Tutor();
		return "/views/home";
	}

	public Person getCurrentUser() {
		return currentUser;
	}

	public Tutor getTutor() {
		return tutor;
	}

}
