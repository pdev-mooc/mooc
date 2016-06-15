package com.mooc.gui;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.event.ListDataListener;

import com.mooc.domain.Course;

public class CourseListModel extends AbstractListModel<String> {

	private final List<Course> courses;

	public CourseListModel(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public int getSize() {
		return courses.size();
	}

	@Override
	public String getElementAt(int index) {
		if (index < courses.size()) {
			Course c = courses.get(index);
			return c.getTitle();
		}
		return "";
	}

	@Override
	public void addListDataListener(ListDataListener l) {
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
	}

}
