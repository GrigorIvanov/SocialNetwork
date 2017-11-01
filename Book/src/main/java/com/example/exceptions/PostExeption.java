package com.example.exceptions;

public class PostExeption extends Exception {

	private static final long serialVersionUID = 7730638863166976316L;

	public PostExeption() {
		super();
	}

	public PostExeption(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public PostExeption(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public PostExeption(String arg0) {
		super(arg0);
	}

	public PostExeption(Throwable arg0) {
		super(arg0);
	}


}