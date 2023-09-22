package com.simplogics.baseapplication.security.utils;

import lombok.Getter;

@Getter
public class Subject {

	private Long id;

	private String role;

	public Subject(Long id, String role) {
		this.id = id;
		this.role = role;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", role=" + role + "]";
	}
	
	
}
