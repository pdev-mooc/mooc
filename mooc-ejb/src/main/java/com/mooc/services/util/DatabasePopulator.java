package com.mooc.services.util;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.mooc.domain.CommitteeMember;
import com.mooc.domain.Student;
import com.mooc.domain.Tutor;
import com.mooc.services.UserRemoteService;

@Singleton
@Startup
public class DatabasePopulator {
	@EJB
	private UserRemoteService userService;

	public DatabasePopulator() {
	}

	@PostConstruct
	public void createData() {
		// Tutor
		userService.create(new Tutor("George", "Orwell", "gorwell@mooc.com", "pw"));
		// Committee member
		userService.create(new CommitteeMember("Admin", "Admin", "admin@mooc.com", "pw"));
		// Student
		userService.create(new Student("Albert", "Einstein", "aeinstein@mooc.com", "pw"));
	}

}
