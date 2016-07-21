package com.mooc.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.mooc.domain.ForumComment;
import com.mooc.domain.Student;
import com.mooc.services.ForumCommentRemoteService;
import com.mooc.util.SessionUtils;

@ManagedBean @SessionScoped
public class ForumView {

	private ForumComment forumComment = new ForumComment();

	@EJB
	private ForumCommentRemoteService forumCommentService;

	public boolean sendComment() {
		Student student = SessionUtils.getLoggedInStudent();
		forumComment.setSender(student);
		boolean result = forumCommentService.create(forumComment);
		forumComment = new ForumComment();
		return result;
	}

	public ForumComment getForumComment() {
		return forumComment;
	}

	public List<ForumComment> getCommentList() {
		return forumCommentService.findAll();
	}

}
