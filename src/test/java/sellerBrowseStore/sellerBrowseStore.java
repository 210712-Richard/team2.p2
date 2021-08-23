package sellerBrowseStore;

import com.intuit.karate.junit5.Karate;

public class sellerBrowseStore {
	@Karate.Test
	Karate testListItems() {
		return Karate.run("sellerBrowseStore").relativeTo(getClass());
	}

}
