package com.mooc.bean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mooc.domain.Trainer;
import com.mooc.enumeration.UserRole;
import com.mooc.services.TrainerRemoteService;



@ManagedBean
@SessionScoped
public class SessionBean {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String role;
	
	public SessionBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@EJB
	private TrainerRemoteService trainerService;
	
	public String addTrainer(){

	
		UserRole selectedRole=null;
		for(UserRole r:UserRole.values()){
			
			if(r.toString().equals(role)){selectedRole=r;}
		}
		Trainer trainer = new Trainer();
		trainer.setFirstName(firstName);
		trainer.setLastName(lastName);
		trainer.setEmail(email);
		trainer.setPassword(password);	
		trainer.setUserRole(selectedRole);
		
		trainerService.create(trainer);
		
		return null;
	}
	
/*
	public boolean findTrainerByEmail() {
		if(trainerService.findByEmail(email) != null){
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

	public TrainerRemoteService getTrainerService() {
		return trainerService;
	}

	public void setTrainerService(TrainerRemoteService trainerService) {
		this.trainerService = trainerService;
	}
	


}
