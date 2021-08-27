package addCategoryTags;

import com.intuit.karate.junit5.Karate;

public class AddCategoryTags {
	@Karate.Test
	Karate testBrowseAll() {
		return Karate.run("addCategoryTags").relativeTo(getClass());
	}

}
