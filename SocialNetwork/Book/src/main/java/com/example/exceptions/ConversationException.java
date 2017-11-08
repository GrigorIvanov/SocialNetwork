package com.example.exceptions;

public class ConversationException extends Exception{

	private static final long serialVersionUID = 14864570528010446L;

	public ConversationException() {
		super();
	}

	public ConversationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ConversationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConversationException(String message) {
		super(message);
	}

	public ConversationException(Throwable cause) {
		super(cause);
	}
	
	
}