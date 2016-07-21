package com.mooc.util;

import javax.faces.context.FacesContext;

import com.mooc.domain.CommitteeMember;
import com.mooc.domain.Student;
import com.mooc.domain.Tutor;

public class SessionUtils {

	public static void setCommitteeMember(CommitteeMember cm) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", cm);
	}

	public static CommitteeMember getCommitteeMember() {
		return 	(CommitteeMember) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("admin");
	}

	public static void setTutor(Tutor tutor) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tutor", tutor);
	}

	public static Tutor getTutor() {
		return 	(Tutor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tutor");
	}

	public static void setLoggedInStudent(Student student) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("student", student);
	}

	public static Student getLoggedInStudent() {
		return (Student) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("student");
	}

	public static void clearSession() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
	}

}
