//package animation;
import java.awt.*;

public class Planet
{
	
			//private String name;
			//private double initialAngle;
			private double initial_angle;
			private double distance;
			private double orbitalPeriod;
			private Color color;
			private int xPos;
			private int yPos;
	public Planet ()
	{
		// null property constructor
	}
	public  Planet(double initial_angle,double distance,double orbitalPeriod,Color color,int xPos,int yPos)
	{
		
		this.initial_angle=initial_angle;				
		this.distance = distance;
		this.orbitalPeriod = orbitalPeriod;
		this.color = color;
		this.xPos = xPos;
		this.yPos = yPos;
		
	}
	
	public int nextX()
	{
		return 0;
	}
	
	public int nextY()
	{
		return 0;
	}
}