package com.mooc.gui.tutor;
import java.awt.Choice;

public final class StudentFactoryCourses {
	/**
	 * @wbp.factory
	 */
	public static Choice createChoice() {
		Choice choice = new Choice();
		choice.add("course1");
		choice.add("course2");
		choice.add("course3");

		return choice;
	}
}