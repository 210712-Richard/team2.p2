package removeFromShoppingCart;

import com.intuit.karate.junit5.Karate;

public class RemoveFromShoppingCart {
	@Karate.Test
	Karate testBrowseAll() {
		return Karate.run("removeFromShoppingCart").relativeTo(getClass());
	}

}
