package com.mooc.administration.util;

import javax.faces.context.FacesContext;

import com.mooc.domain.CommitteeMember;
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

}
