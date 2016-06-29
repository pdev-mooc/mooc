package com.mooc.bean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mooc.domain.Learner;
import com.mooc.enumeration.UserRole;
import com.mooc.services.LearnerRemoteService;



@ManagedBean
@SessionScoped
public class LearnerBean {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String role;
	
	public LearnerBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@EJB
	private LearnerRemoteService learnerService;
	
	public String addLearner(){

	
		UserRole selectedRole=null;
		for(UserRole r:UserRole.values()){
			
			if(r.toString().equals(role)){selectedRole=r;}
		}
		Learner learner = new Learner();
		learner.setFirstName(firstName);
		learner.setLastName(lastName);
		learner.setEmail(email);
		learner.setPassword(password);	
		learner.setUserRole(selectedRole);
		
		learnerService.create(learner);
		
		return null;
	}
	
/*
	public boolean findLearnerByEmail() {
		if(learnerService.findByEmail(email) != null){
			return true;
		}
		return false;
		
		
	}
*/






	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LearnerRemoteService getLearnerService() {
		return learnerService;
	}

	public void setLearnerService(LearnerRemoteService learnerService) {
		this.learnerService = learnerService;
	}
	


}
