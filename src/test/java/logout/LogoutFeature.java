package logout;

import com.intuit.karate.junit5.Karate;

public class LogoutFeature {
	@Karate.Test
	Karate testLogout() {
		return Karate.run("logout").relativeTo(getClass());
	}

}
