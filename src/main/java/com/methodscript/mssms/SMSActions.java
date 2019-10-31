package com.methodscript.mssms;

/**
 *
 */
public interface SMSActions {

	/**
	 * Sends an SMS to the specified recipient.
	 * @param from The sender of the message.
	 * @param to The recipient.
	 * @param message The message to send.
	 * @throws com.methodscript.mssms.ActionGatewayException
	 */
	void sendMessage(String from, String to, String message) throws ActionGatewayException;
}
