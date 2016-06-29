package com.mooc.services;

import javax.ejb.Remote;

import com.mooc.domain.Comment;

@Remote
public interface CommentRemoteService extends EntityRemoteService<Comment> {

}
