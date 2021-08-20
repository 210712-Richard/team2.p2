package createAccount;

import com.intuit.karate.junit5.Karate;

public class CreateAccountFeature {
	@Karate.Test
	Karate testCreateAccount() {
		return Karate.run("createAccount").relativeTo(getClass());
	}

}
