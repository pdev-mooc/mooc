package com.mooc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "administrator")
@Entity
public class Administrator extends User {

	private static final long serialVersionUID = 1L;
	private String img;
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
