package com.methodscript.mssms.twilio;

import com.methodscript.mssms.ActionGatewayException;
import com.methodscript.mssms.SMSActions;
import com.twilio.exception.ApiException;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 */
public class TwilioSMSActions implements SMSActions {


	private final TwilioProfile profile;
	private TwilioRestClient client;

	public TwilioSMSActions(TwilioProfile profile) {
		this.profile = profile;
	}

	private void init() {
		if(client == null) {
			client = new TwilioRestClient.Builder(profile.getSid(), profile.getAuth())
				.build();
		}
	}

	@Override
	public void sendMessage(String from, String to, String message) throws ActionGatewayException {
		init();
		try {
			Message.creator(new PhoneNumber(to), new PhoneNumber(from), message).create(client);
		} catch (ApiException ex) {
			throw new ActionGatewayException(ex.getMessage(), ex);
		}
	}

}
