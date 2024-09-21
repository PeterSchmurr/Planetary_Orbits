import java.math.*;

public class sandbox {
	
	private static double[] polarToRectangularCoordinates(double radius,double theta)
	{
		double coords[] = {0,0};
		
		double thetaInRadians = Math.toRadians(theta);
		
		double xcoord = radius*Math.cos(thetaInRadians);
		double ycoord = radius*Math.sin(thetaInRadians);
			
		coords[0] = xcoord+500;	
		coords[1] = ycoord+500;
			
		return coords;
		
	}
	
	public static void main(String[] args)
	{
		double[] coords;
		coords = polarToRectangularCoordinates(350,359);
		System.out.println(coords[0]);
		System.out.println(coords[1]);	
	}

}


