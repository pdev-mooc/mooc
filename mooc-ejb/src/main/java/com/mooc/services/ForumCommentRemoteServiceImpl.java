package com.mooc.services;

import javax.ejb.Stateless;

import com.mooc.domain.ForumComment;

@Stateless
public class ForumCommentRemoteServiceImpl extends GenericRemoteService<ForumComment> implements ForumCommentRemoteService {

	protected ForumCommentRemoteServiceImpl() {
		super(ForumComment.class);
	}

}
