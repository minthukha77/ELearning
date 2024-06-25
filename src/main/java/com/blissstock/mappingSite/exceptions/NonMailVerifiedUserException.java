package com.blissstock.mappingSite.exceptions;

import org.springframework.security.core.AuthenticationException;

public class NonMailVerifiedUserException extends AuthenticationException{

	public NonMailVerifiedUserException(String msg) {
		super(msg);
	}

	public NonMailVerifiedUserException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
