package sortByPrice;

import com.intuit.karate.junit5.Karate;

public class sortByPrice {
	@Karate.Test
	Karate testSortByPrice() {
		return Karate.run("sortByPrice").relativeTo(getClass());	
	}
}
