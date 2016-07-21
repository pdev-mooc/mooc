package com.mooc.services;


import java.util.List;

import com.mooc.domain.forumtopic;

public interface forum_topicRemoteService  extends EntityRemoteService<forumtopic> {
	List<forumtopic> getAll();
}
