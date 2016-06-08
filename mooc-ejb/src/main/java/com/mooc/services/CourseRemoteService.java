package com.mooc.services;

import javax.ejb.Remote;

import com.mooc.domain.Course;

@Remote
public interface CourseRemoteService extends EntityRemoteService<Course> {

}
