package createStore;

import com.intuit.karate.junit5.Karate;

public class CreateStoreFeature {
	@Karate.Test
	Karate testCreateStore() {
		return Karate.run("createStore").relativeTo(getClass());
	}

}
