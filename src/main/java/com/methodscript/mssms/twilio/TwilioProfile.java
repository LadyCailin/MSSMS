package com.methodscript.mssms.twilio;

import com.laytonsmith.core.Profiles;
import java.util.Map;

/**
 *
 */
@Profiles.ProfileType(type = "twilio")
public class TwilioProfile extends Profiles.Profile {

	private String sid;
	private String auth;

	public TwilioProfile(String id, Map<String, String> elements) throws Profiles.InvalidProfileException {
		super(id);
		if(!elements.containsKey("sid")) {
			throw new Profiles.InvalidProfileException("\"sid\" parameter is required for profile \"" + id + "\"");
		}
		if(!elements.containsKey("auth")) {
			throw new Profiles.InvalidProfileException("\"auth\" parameter is required for profile \"" + id + "\"");
		}
		this.sid = elements.get("sid");
		this.auth = elements.get("auth");
	}

	public String getSid() {
		return sid;
	}

	public String getAuth() {
		return auth;
	}

}
