package com.methodscript.mssms;

/**
 * Thrown if the underlying gateway threw another exception.
 */
public class ActionGatewayException extends Exception {
	public ActionGatewayException(String message, Throwable cause) {
		super(message, cause);
	}
}
