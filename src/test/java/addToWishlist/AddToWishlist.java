package addToWishlist;

import com.intuit.karate.junit5.Karate;

public class AddToWishlist {
	@Karate.Test
	Karate testBrowseAll() {
		return Karate.run("addToWishlist").relativeTo(getClass());
	}

}
