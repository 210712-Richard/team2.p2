package removeFromCart;

import com.intuit.karate.junit5.Karate;

public class RemoveFromCart {
	@Karate.Test
	Karate testBrowseAll() {
		return Karate.run("removeFromCart").relativeTo(getClass());
	}

}
