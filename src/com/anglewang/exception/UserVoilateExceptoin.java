package com.anglewang.exception;

public class UserVoilateExceptoin extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UserVoilateExceptoin(String msg) {
		super(msg);
	}
}
