package com.methodscript.mssms;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.Profiles;
import com.laytonsmith.core.constructs.CSecureString;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.environments.StaticRuntimeEnv;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import com.laytonsmith.core.exceptions.CRE.CREPluginInternalException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.core.natives.interfaces.Mixed;
import com.methodscript.mssms.twilio.TwilioProfile;
import com.methodscript.mssms.twilio.TwilioSMSActions;

/**
 *
 */
public class MSSMSFunctions {

	@api
	public static class send_sms extends AbstractFunction {

		@Override
		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{CREIllegalArgumentException.class, CREPluginInternalException.class};
		}

		@Override
		public boolean isRestricted() {
			return true;
		}

		@Override
		public Boolean runAsync() {
			return null;
		}

		@Override
		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			String id = args[0].val();
			String from;
			if(args[1] instanceof CSecureString) {
				from = new String(((CSecureString) args[1]).getDecryptedCharArray());
			} else {
				from = args[1].val();
			}
			String to;
			if(args[2] instanceof CSecureString) {
				to = new String(((CSecureString) args[2]).getDecryptedCharArray());
			} else {
				to = args[2].val();
			}
			String message = args[3].val();
			Profiles profiles = environment.getEnv(StaticRuntimeEnv.class).getProfiles();
			SMSActions action;
			try {
				Profiles.Profile prof = profiles.getProfileById(id);
				if(prof instanceof TwilioProfile) {
					action = new TwilioSMSActions((TwilioProfile) prof);
				} else {
					throw new CREIllegalArgumentException("Unsupported profile type", t);
				}
			} catch (Profiles.InvalidProfileException ex) {
				throw new CREIllegalArgumentException(ex.getMessage(), t);
			}
			try {
				action.sendMessage(from, to, message);
			} catch (ActionGatewayException ex) {
				throw new CREPluginInternalException(ex.getMessage(), t, ex);
			}
			return CVoid.VOID;
		}

		@Override
		public String getName() {
			return "send_sms";
		}

		@Override
		public Integer[] numArgs() {
			return new Integer[]{4};
		}

		@Override
		public String docs() {
			return "void {id, from, to, message} Sends an SMS from the specified phone number to the specified"
					+ " phone number, with the given message. ---- The profile selected is based on the id, and"
					+ " the phone numbers must be fully qualified, i.e. \"+14556557555\". The phone numbers"
					+ " may be secure_strings.";
		}

		@Override
		public Version since() {
			return MSSMS.MSSMSVersion.V0_0_1;
		}

	}

}
