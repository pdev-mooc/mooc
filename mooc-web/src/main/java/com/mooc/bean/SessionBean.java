package com.mooc.bean;

import java.time.LocalDate;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mooc.domain.Session;
import com.mooc.domain.Trainer;
import com.mooc.enumeration.UserRole;
import com.mooc.services.SessionRemoteService;
import com.mooc.services.TrainerRemoteService;



@ManagedBean
@SessionScoped
public class SessionBean {
	private LocalDate startDate;
	private LocalDate endDate;
	private Trainer trainer;
	private String description;

	
	public SessionBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@EJB
	private SessionRemoteService sessionService;
	
	public String addSession(){

	
		
		Session session = new Session();
		session.setEndDate(endDate);
		session.setStartDate(startDate);
		
		sessionService.create(session);
		
		return null;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
/*
	public boolean findTrainerByEmail() {
		if(trainerService.findByEmail(email) != null){
			return true;
		}
		return false;
		
		
	}
*/







	


}
