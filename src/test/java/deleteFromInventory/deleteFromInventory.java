package deleteFromInventory;

import com.intuit.karate.junit5.Karate;

public class deleteFromInventory {
	@Karate.Test
	Karate testDeleteFromInventory() {
		return Karate.run("deleteFromInventory").relativeTo(getClass());
	}
}
