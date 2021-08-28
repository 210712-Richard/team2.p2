package addToInventory;

import com.intuit.karate.junit5.Karate;

public class addToInventory {
	@Karate.Test
	Karate testAddToInventory() {
		return Karate.run("addToInventory").relativeTo(getClass());
	}
}
