package com.mooc.bean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mooc.domain.Person;
import com.mooc.domain.Tutor;
import com.mooc.services.UserRemoteService;
import com.mooc.util.SessionUtils;

@ManagedBean @SessionScoped
public class TutorView {

	@EJB
	private UserRemoteService userService;
	private Tutor tutor = new Tutor();

	public String login() {
		Person user = userService.findUser(tutor.getEmail(), tutor.getPassword());
		if (user == null || user instanceof Tutor == false) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Incorrect Email or Passowrd", "Incorrect Email or Passowrd"));
			return "/views/tutor_signin";
		}
		tutor = (Tutor) user;
		SessionUtils.setTutor(tutor);
		return "/views/tutor_home";
	}

	public Tutor getTutor() {
		return tutor;
	}

}
