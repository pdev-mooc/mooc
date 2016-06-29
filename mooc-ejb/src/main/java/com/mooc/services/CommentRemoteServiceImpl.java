package com.mooc.services;

import com.mooc.domain.Comment;

public class CommentRemoteServiceImpl extends GenericRemoteService<Comment> {

	public CommentRemoteServiceImpl() {
		super(Comment.class);
	}
}
