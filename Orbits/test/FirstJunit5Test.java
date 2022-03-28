import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

class FirstJunit5Test {

	@Test
	void test() {
		Planet mercury = new Planet(
				3.93,
				.39,
				88,
				Color.RED,
				500 +.39*50,
				500);
		
		assertEquals(mercury.xPos,519.5);
		int elapsedDays = 1;
		//assertEquals(mercury.nextX())
	}

}
