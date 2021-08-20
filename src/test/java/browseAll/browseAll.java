package browseAll;

import com.intuit.karate.junit5.Karate;

public class browseAll {
	@Karate.Test
	Karate testBrowseAll() {
		return Karate.run("browseAll").relativeTo(getClass());
	}
}
