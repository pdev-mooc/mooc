package com.mooc.administration.bean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.mooc.administration.util.SessionUtils;
import com.mooc.domain.Chapter;
import com.mooc.domain.Course;
import com.mooc.domain.Tutor;
import com.mooc.services.CourseRemoteService;

@ManagedBean
@SessionScoped
public class CourseView {

	@EJB
	private CourseRemoteService courseService;
	private Course course = new Course();

	public String createCourse() {
		// Retrieve the current tutor
		Tutor currentTutor = SessionUtils.getTutor();

		course.setTutor(currentTutor);

		if (!courseService.create(course)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Course already exists", "Course already exists"));
			return "/views/create_course";
		}
		course = new Course();
		return "/views/tutor_home";
	}

	public String addChapter(ActionEvent action) {
		Chapter chapter = (Chapter) action.getComponent().getAttributes().get("chapter");
		course.getChapters().add(chapter);
		return "views/tutor_home";
		
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
