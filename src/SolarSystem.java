//package animation;

import java.util.ArrayList;
import java.awt.*;

public class SolarSystem 
{
	public ArrayList<Planet>planetArray = new ArrayList<Planet>();
	public ArrayList<ZenithLabel> zenithLabelArray = new ArrayList<ZenithLabel>();
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
	
	Planet mercury = new Planet(3.540,
								.39,
								88,
								Color.BLACK,
								500 +.39*AU,
								500);
	Planet venus = new Planet(0.898,
								.723,
								225,
								Color.WHITE,
								500 +.723*AU,
								500);
	Planet earth = new Planet(1.760,
								1,
								365,
								Color.GREEN,
								500 +AU,
								500);
	Planet mars = new Planet(1.907,
								1.54,
								687,
								Color.RED,
								500 +1.54*AU,
								500);
	Planet jupiter = new Planet(1.367,
								5.20,
								4333,
								Color.YELLOW,
								500 +5.20*AU,
								500);
	Planet saturn = new Planet(6.105,
								9.54,
								10759,
								Color.ORANGE,
								500 +9.54*AU,
								500);
	ZenithLabel sunsetLabel = new ZenithLabel("sunset",
												-3.14,
												9.54,
												365,
												500+9.54*AU,
												500.0);
	ZenithLabel sunriseLabel = new ZenithLabel("sunrise",
												0.0,
												9.54,
												365,
												500+9.54*AU,
												500.0);
	
    public void buildSolarSystem()
    {
    	planetArray.add(mercury);
    	planetArray.add(venus);
    	planetArray.add(earth);
    	planetArray.add(mars);
    	planetArray.add(jupiter);
    	planetArray.add(saturn);
    	zenithLabelArray.add(sunsetLabel);
    	zenithLabelArray.add(sunriseLabel);
    	
    	
    }
    
    public ArrayList<Planet> getPlanetArray()
    {
    	buildSolarSystem();
    	return planetArray;
    }
    
    public ArrayList<ZenithLabel> getZenithLabelArray() 
    {
        this.buildSolarSystem();
        return zenithLabelArray;  
    }
}