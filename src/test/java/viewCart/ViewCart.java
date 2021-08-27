package viewCart;

import com.intuit.karate.junit5.Karate;

public class ViewCart {
	@Karate.Test
	Karate testBrowseAll() {
		return Karate.run("viewCart").relativeTo(getClass());
	}

}
