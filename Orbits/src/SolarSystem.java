//package animation;

import java.util.ArrayList;
import java.awt.*;

public class SolarSystem 
{
	public ArrayList<Planet>planetArray = new ArrayList<Planet>();
	static int AU = 50;
//	public static String ;
//	public static String February;
//	public static String March;
//	public static String January;
//	public static String January;
//	public static String January;
//	public static String January;
//	public static String January;
//	public static String January;
//	public static String January;
	

	public int mercuryYpos = 500;
	
	public SolarSystem()
	{
		
	}
	
	Planet mercury = new Planet(3.93,
								.39,
								88,
								Color.BLACK,
								500 +.39*AU,
								500);
	Planet venus = new Planet(2.44,
								.723,
								225,
								Color.WHITE,
								500 +.723*AU,
								500);
	Planet earth = new Planet(1.74,
								1,
								365,
								Color.GREEN,
								500 +AU,
								500);
	Planet mars = new Planet(0.7308,
								1.54,
								687,
								Color.RED,
								500 +1.54*AU,
								500);
	Planet jupiter = new Planet(4.30,
								5.20,
								4333,
								Color.YELLOW,
								500 +5.20*AU,
								500);
	Planet saturn = new Planet(4.89,
								9.54,
								10759,
								Color.ORANGE,
								500 +9.54*AU,
								500);
	
    public void buildSolarSystem()
    {
    	planetArray.add(mercury);
    	planetArray.add(venus);
    	planetArray.add(earth);
    	planetArray.add(mars);
    	planetArray.add(jupiter);
    	planetArray.add(saturn);
    	
    }
    
    public ArrayList<Planet> getPlanetArray()
    {
    	buildSolarSystem();
    	return planetArray;
    }
    
}