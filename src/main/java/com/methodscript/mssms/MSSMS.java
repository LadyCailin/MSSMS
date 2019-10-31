
package com.methodscript.mssms;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import java.util.Map;

/**
 *
 */
@MSExtension("MSSMS")
public class MSSMS extends AbstractExtension {
    @Override
    public void onStartup() {

    }

    @Override
    public void onShutdown() {

    }

    @Override
    public Version getVersion() {
        return MSSMSVersion.V0_0_1;
    }

	@Override
	public Map<String, String> getHelpTopics() {
		return null;
	}

	public static enum MSSMSVersion implements Version {
		V0_0_1(0, 0, 1);

		private final SimpleVersion version;
		private MSSMSVersion(int maj, int min, int patch) {
			version = new SimpleVersion(maj, min, patch);
		}

		@Override
		public int getMajor() {
			return version.getMajor();
		}

		@Override
		public int getMinor() {
			return version.getMinor();
		}

		@Override
		public int getSupplemental() {
			return version.getSupplemental();
		}

		@Override
		public boolean lt(Version other) {
			return version.lt(other);
		}

		@Override
		public boolean lte(Version other) {
			return version.lte(other);
		}

		@Override
		public boolean gt(Version other) {
			return version.gt(other);
		}

		@Override
		public boolean gte(Version other) {
			return version.gte(other);
		}

		@Override
		public String toString() {
			return version.toString();
		}

	}
}
