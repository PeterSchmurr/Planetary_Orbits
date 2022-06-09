import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.awt.geom.*;
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
		//int elapsedDays = 1;
		//assertEquals(mercury.nextX())
	}
	@Test
	void testIntersection () {
		List<Point2D> ep = null;
		try {
			ep = LineCircleIntersection.intersection( 
					LineCircleIntersection.makePoint(-10,11),
					LineCircleIntersection.makePoint(10,-9),
					LineCircleIntersection.makePoint(3,-5), 3.0, false);
		} catch (NoninvertibleTransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(3,roundSpecial(ep.get(0).getX()));
		assertEquals(-2,roundSpecial(ep.get(0).getY()));
		
		assertEquals(6,roundSpecial(ep.get(1).getX()));
		assertEquals(-5,roundSpecial(ep.get(1).getY()));
		
	}
	
 int roundSpecial(double d)
 {
	 if (d>=0)
	 {return (int)d;}
	 else
	 {return -1*(int) Math.round( (-1.0*d));}
		 }	 
 }


