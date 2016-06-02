package com.mooc.services.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.mooc.domain.Admin;
import com.mooc.domain.User;
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
		userService.create(new User("George", "Orwell", "gorwell@mooc.com", "pw"));
		userService.create(new Admin("Admin1FN", "Admin1LN", "admin1@mooc.com", "pw"));
	}

}
