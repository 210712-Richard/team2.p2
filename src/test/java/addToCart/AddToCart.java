package addToCart;

import com.intuit.karate.junit5.Karate;

public class AddToCart {
	@Karate.Test
	Karate testBrowseAll() {
		return Karate.run("addToCart").relativeTo(getClass());
	}

}