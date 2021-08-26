package loginStore;

import com.intuit.karate.junit5.Karate;

public class LoginStoreFeature {
@Karate.Test
	
	Karate testLogin() {
		return Karate.run("loginStore").relativeTo(getClass());
	}

}
