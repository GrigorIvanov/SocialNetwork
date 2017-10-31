package com.example.exceptions;

public class InvalidLikeException extends Exception{
	private static final long serialVersionUID = -2991246827653654358L;

	public InvalidLikeException() {
		super();
	}

	public InvalidLikeException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidLikeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidLikeException(String arg0) {
		super(arg0);
	}

	public InvalidLikeException(Throwable arg0) {
		super(arg0);
	}

	

}
