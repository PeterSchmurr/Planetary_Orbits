//package animation;
import java.awt.*;

public class Planet
{
	
			//private String name;
			//private double initialAngle;
			public double theta;
			public double distance;
			public int period;
			public Color color;
			public double xPos;
			public double yPos;
			public double delta;
	public Planet ()
	{
		// null property constructor
	}
	public  Planet( double theta,
					double distance,
					int period,
					Color color,
					double xPos,
					double yPos)
	{
		
		this.theta=theta;				
		this.distance = distance;
		this.period = period;
		this.color = color;
		this.xPos = xPos;
		this.yPos = yPos;
		this.delta = 2*3.14/this.period;
				
		
	}
	

	public Color getColor()
	{
		return this.color;
	}
	
	public double nextTheta()
	{
		double t = (this.theta + GUI.elapsedDays*delta);
		return t;
	}
	
	public double nextX()
	{
		this.xPos = 500 + this.distance*50*Math.cos(nextTheta());
		return this.xPos;
	}
	
	public double nextY()
	{
		this.yPos = 500 + this.distance*50*Math.sin(nextTheta());
		return this.yPos;
	}
}