package listItems;

import com.intuit.karate.junit5.Karate;

public class ListItemsFeature {
	@Karate.Test
	Karate testListItems() {
		return Karate.run("listItems").relativeTo(getClass());
	}
}
