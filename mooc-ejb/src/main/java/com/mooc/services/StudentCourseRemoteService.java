package com.mooc.services;

import javax.ejb.Remote;

import com.mooc.domain.StudentCourse;

@Remote
public interface StudentCourseRemoteService extends EntityRemoteService<StudentCourse> {

}
