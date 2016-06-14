package com.mooc.gui;

import com.mooc.domain.Chapter;

public class ChapterComboBoxModel {

	private final Chapter chapter;

	public ChapterComboBoxModel(Chapter chapter) {
		this.chapter = chapter;
	}

	public Chapter getChapter() {
		return chapter;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(chapter.getTitle());
		return sb.toString();
	}

}
