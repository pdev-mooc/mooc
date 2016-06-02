package com.mooc.services;

import javax.ejb.Remote;

import com.mooc.domain.Chapter;

@Remote
public interface ChapterRemoteService extends EntityRemoteService<Chapter> {

}
