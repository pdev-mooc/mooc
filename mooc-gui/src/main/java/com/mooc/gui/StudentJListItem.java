package com.mooc.gui;

import com.mooc.domain.Student;

public class StudentJListItem {

	private Student student;

	public StudentJListItem(Student student) {
		this.student = student;
	}

	public Student getStudent() {
		return student;
	}

	public String toString() {
		return student.getFirstName() + " " + student.getLastName();
	}

}
