package com.mooc.services;

import javax.ejb.Remote;

import com.mooc.domain.Course;

@Remote
public interface CourseRemoteService extends EntityRemoteService<Course> {
	public static void Test(){
		System.out.println("Hello world");
		
		
	}

}
