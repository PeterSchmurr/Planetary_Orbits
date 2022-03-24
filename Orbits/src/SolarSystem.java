//package animation;

import java.util.ArrayList;
import java.awt.*;

public class SolarSystem 
{
	ArrayList<Planet> planetArray;
	static int AU = 50;
	int mercuryXpos = (int) (500 +.39*AU);
	int mercuryYpos = 500;
	Color merColor  = Color.RED;
			
	Planet mercury = new Planet(.39,88.0,merColor,mercuryXpos,mercuryYpos);
	

}