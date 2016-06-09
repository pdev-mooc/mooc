package com.mooc.administration.bean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mooc.administration.util.SessionUtils;
import com.mooc.domain.CommitteeMember;
import com.mooc.domain.Person;
import com.mooc.services.UserRemoteService;

@ManagedBean @SessionScoped
public class CommitteeMemberView {

	@EJB
	private UserRemoteService userService;
	private CommitteeMember committeeMember = new CommitteeMember();

	public String login() {
		Person user = userService.findUser(committeeMember.getEmail(), committeeMember.getPassword());
		if (user == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Incorrect Email or Passowrd", "Incorrect Email or Passowrd"));
			return "/views/login";
		}
		if (user instanceof CommitteeMember == false) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Permission denied", "Permission denied"));
			return "/views/committee_member_login";
		}
		committeeMember = (CommitteeMember) user;
		SessionUtils.setCommitteeMember((CommitteeMember) user);
		return "/views/home";
	}

	public CommitteeMember getCommitteeMember() {
		return committeeMember;
	}

	public void setCommitteeMember(CommitteeMember committeeMember) {
		this.committeeMember = committeeMember;
	}

}
