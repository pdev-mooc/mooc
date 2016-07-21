package com.mooc.services;

import javax.ejb.Remote;

import com.mooc.domain.ForumComment;

@Remote
public interface ForumCommentRemoteService extends EntityRemoteService<ForumComment> {

}
