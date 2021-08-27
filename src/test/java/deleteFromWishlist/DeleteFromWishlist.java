package deleteFromWishlist;

import com.intuit.karate.junit5.Karate;

public class DeleteFromWishlist {
	@Karate.Test
	Karate testBrowseAll() {
		return Karate.run("deleteFromWishlist").relativeTo(getClass());
	}

}
