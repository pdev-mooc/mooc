package com.mooc.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.mooc.domain.Course;
import com.mooc.domain.Quizz;
import com.mooc.services.CourseRemoteService;
import com.mooc.services.QuizzRemoteService;

@ManagedBean @SessionScoped
public class QuizView {

	@EJB
	private QuizzRemoteService quizzService;
	@EJB
	private CourseRemoteService CourseService;
	private Quizz quizz;

	public String loadQuizz(Integer quizzId) {	
		quizz = quizzService.findById(quizzId);
		return "website-take-quiz.html";
	}
	
	public Quizz getQuizz(){		
		return quizz;
	}
	
	public Quizz saveResponse(Integer quizzId,Integer response){
		quizzService.saveResponse(quizzId,response);
		return quizz;
	}
}
