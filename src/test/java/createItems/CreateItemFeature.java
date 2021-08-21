package createItems;

import com.intuit.karate.junit5.Karate;

public class CreateItemFeature {
	@Karate.Test
	Karate testCreateAccount() {
		return Karate.run("createItem").relativeTo(getClass());
	}
}
