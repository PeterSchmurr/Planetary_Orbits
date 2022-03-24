import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FirstJunit5Test {

	@Test
	void test() {
		Planet p1 = new Planet();
		assertNotNull(p1);
		assertEquals(p1.nextX(),0);
	}

}
