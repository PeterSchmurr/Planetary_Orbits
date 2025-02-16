//display solar system

import static org.junit.Assert.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.*;
import javax.swing.Timer;
import java.util.*;
import java.util.List;




public class GUI extends JPanel implements KeyListener 
{
	
	
	private static Point2D makePoint(double x, double y) {
        return new Point2D.Double(x, y);}

	public Timer tm;
	
	public LocalDate date= LocalDate.now();
	public static double elapsedDays=ChronoUnit.DAYS.between(LocalDate.of(2025,1,1),LocalDate.now());
	
	public String[][] sunrise_sunset= {{"8:48","8:36","8:05","7:21","6:43","6:22","6:25","6:47","7:11","7:34","8:02","8:31"},
			                           {"6:36","7:07","7:34","8:01","8:26","8:50","8:58","8:40","8:02","7:18","6:40","6:23"}};
	
	private SolarSystem s;
	
	int month = date.getMonthValue();
	
	JLabel dateDisplay = new JLabel("");
	JLabel sunriseDisplay = new JLabel();
	
	JLabel percentMoonCycle = new JLabel("");
	JLabel percentMoonCycleLabel = new JLabel("");
	
	JLabel Sirius = new JLabel("Sirius");
	JLabel Betelgeuse = new JLabel("Betelgeuse");
	JLabel Aldebaran = new JLabel("Aldebaran");
	JLabel Almach = new JLabel("Almach");
	JLabel Capella = new JLabel("Capella");
	// goto 160
	
	JLabel noonZenith = new JLabel("noon zenith");
	JLabel midnightZenith = new JLabel("midnight zenith");
	JLabel sunriseZenith = new JLabel("sunrise zenith");
	JLabel sunsetZenith = new JLabel("sunset zenith");
	
	JButton pause = new JButton("Pause");
	JButton resume = new JButton("Resume");
	JSlider speedControl = new JSlider(JSlider.HORIZONTAL);
	
	JRadioButton forward = new JRadioButton("forward");
	JRadioButton backward = new JRadioButton("backward");
	
	ButtonGroup timeDirection = new ButtonGroup();
	boolean movingAhead = true;
	
	
	public GUI(SolarSystem s)
	{
		
	
		
		this.s = s;
		
			ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				if (movingAhead)
				{
					//elapsedDays = getElapsedDays();
					
					elapsedDays = (elapsedDays + 1);
					date = date.plusDays(1);
					
				}
				else
				{
					elapsedDays = (elapsedDays - 1);
					date = date.plusDays(-1);
				}
				sunriseZenith.setText("sunrise zenith: " + sunrise_sunset[0][date.getMonthValue()-1]);
				sunsetZenith.setText("sunset zenith: " + sunrise_sunset[1][date.getMonthValue()-1]);
				
				repaint();
			}};
			
			
			 tm = new Timer(100,taskPerformer);
			 tm.stop();
			
		pause.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
	            
	        	 
	                
	                pauseButtonPressed();
	        }
	    });
		
		resume.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
	            
	                resumeButtonPressed();              
	        }
	    });
		
		forward.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
	            
	                movingAhead = true;
	                pause.requestFocusInWindow();
	        }
	    });
		
		backward.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
	            
	                movingAhead = false;
	                pause.requestFocusInWindow();
	        }
	    });
		
		
		
		class SliderListener implements ChangeListener {
		    public void stateChanged(ChangeEvent e) {
		        JSlider source = (JSlider)e.getSource();
		        if (!source.getValueIsAdjusting()) {
		            int speed = (int)source.getValue();
		            tm.setDelay(100 - speed);
		        }    
		    }}
		
		
		speedControl.addChangeListener(new SliderListener());
		speedControl.setBorder(BorderFactory.createTitledBorder("Speed"));
		
		
		this.add(dateDisplay);
		this.add(pause);
		this.add(resume);
		this.add(speedControl);
		this.add(forward);
		this.add(backward);
		this.add(sunsetZenith);
		this.add(sunriseZenith);
		this.add(midnightZenith);
		this.add(noonZenith);
		
		this.add(this.percentMoonCycle);
		this.add(this.percentMoonCycleLabel);
		
		this.add(Sirius);
		this.add(Betelgeuse);
		this.add(Aldebaran);
		this.add(Almach);
		this.add(Capella);
		
		//goto 375
		
		

		
		pause.addKeyListener(this);
		timeDirection.add(forward);
		timeDirection.add(backward);
		
		timeDirection.clearSelection();
		forward.setSelected(true);
	}
	private void adjustElapsedDays(int delta)
	{
		if (movingAhead)
		{
		elapsedDays = elapsedDays + delta;
	
		date = date.plusDays(delta);
		repaint();
		}
		
		
		
		else
		{
			elapsedDays = elapsedDays -delta;
			date = date.plusDays(-delta);
			repaint();
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		switch(key) 
		{
			case KeyEvent.VK_D:
				adjustElapsedDays(1);

						break;						

			case KeyEvent.VK_Y:
				adjustElapsedDays(365);
								
		}
	}
			
// series of methods to determine endpoint coordinates for secant segment	
	 public  Point2D findASecondSecantPoint()
	    
	    {
	    	double secantSlope =-1*(1/((s.earth.yPos - 505.0)/(s.earth.xPos - 505.0)));
	    	int secantYIntercept = (int) (s.earth.yPos - secantSlope*s.earth.xPos);
	    	Point2D secantSecondPoint = makePoint(s.earth.xPos + 1 , secantSlope + s.earth.yPos);
	    	return secantSecondPoint;
	    }
	
	 public List<Point2D> determineSecantEndPoints()
	 {
		 try {
			return LineCircleIntersection.intersection(makePoint(
					s.earth.xPos,s.earth.yPos), 
					findASecondSecantPoint(),
					 makePoint(500,500), 50*s.saturn.distance, false);
		} catch (NoninvertibleTransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	 }
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		
		ArrayList<Planet> a = s.getPlanetArray();
		ArrayList <ZenithLabel>z = s.getZenithLabelArray();
		
		for (Planet p : a)
		{
			g.setColor(p.getColor());
			g.drawOval((int)p.nextX(),(int)p.nextY(), 5, 5);
			g.fillArc((int)p.nextX(),(int)p.nextY(),5,5,0,360);
			
		}
		
		
 
		  for(Planet lbl : z)
		  { lbl.setFont((new Font("Serif",Font.BOLD,20)));
		  lbl.setForeground(Color.WHITE); 
		  lbl.setLocation((int)nextX(), (int)nextY()); }
		 
		
			
		g.setColor(Color.ORANGE);
		g.drawOval(500,500, 10, 10);
		g.fillArc(500,500,10,10,0,360);
		
		double te = s.earth.theta;
		int ds = (int)s.saturn.distance;
		int de = (int)s.earth.distance;
		
		//noon and midnight lines
		
		double x01 = 50*(de+ds)*Math.cos(s.earth.nextTheta()) +500;
		double y01 = 50*(de+ds)*Math.sin(s.earth.nextTheta()) +500;
		double x02 = 50*(ds-de)*Math.cos((s.earth.nextTheta()+Math.PI)) +500;
		double y02 = 50*(ds-de)*Math.sin((s.earth.nextTheta()+Math.PI)) +500;
		g.drawLine((int)x01,(int)y01,(int)x02,(int)y02);
		
		
		final double sunriseLineInitialAngle = 1.47;
		final double noonLineInitialAngle = 3.14;
		final double sunsetLineInitialAngle = -1.47;
		final double midnightLineInitialAngle = 0;
				
//	rewrite sunrise and sunset lines as single segment using intersection class method
		
		/*
		 * public static List<Point2D> intersection(
		 *  Point2D secantP1, earth's current coordinates
		 *  Point2D secantP2, another point calculated from equation of sunrise/sunset line
		 * Point2D solarCenter, (505,505)
		 * double radius, Saturn's  distance from sun
		 * boolean isSegment) TRUE
		 */
		
		
//		 try {
//			List<Point2D> endPoints = 
//					LineCircleIntersection.intersection
//					(makePoint(-10,11),
//							makePoint(10,-9),
//							makePoint(3,-5),3.0,
//							false);
//			System.out.println(endPoints);
//		} catch (HeadlessException | NoninvertibleTransformException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 
		
		
		//sunrise line	
		
		  double x11 = s.earth.nextX(); 
		  double y11 = s.earth.nextY(); 
		  double x12 = extremeX(sunriseLineInitialAngle) ; 
		  double y12 = extremeY(sunriseLineInitialAngle);
		  
		  // draw a line between secant points
		  List<Point2D> endpoints= determineSecantEndPoints();
		  g.drawLine(
				  (int)endpoints.get(0).getX(),
				  (int)endpoints.get(0).getY(),
				  (int)endpoints.get(1).getX(),
				  (int)endpoints.get(1).getY());
		  
	 
		
		// zenith labels
		

		this.sunsetZenith.setFont((new Font("Serif",Font.BOLD,20)));
		this.sunsetZenith.setForeground(Color.WHITE);
		this.sunsetZenith.setLocation((int)z.get(1).nextX() , (int)z.get(1).nextY());
		

		this.sunriseZenith.setFont((new Font("Serif",Font.BOLD,20)));
		this.sunriseZenith.setForeground(Color.WHITE);
		this.sunriseZenith.setLocation((int)z.get(0).nextX() , (int)z.get(0).nextY());
		
		double noonLabelX = x02;
		double noonLabelY = y02;
		this.noonZenith.setFont((new Font("Serif",Font.BOLD,20)));
		this.noonZenith.setForeground(Color.WHITE);
		this.noonZenith.setLocation((int)noonLabelX, (int)noonLabelY);
		
		double midnightLabelX = x01;
		double midnightLabelY = y01;
		this.midnightZenith.setFont((new Font("Serif",Font.BOLD,20)));
		this.midnightZenith.setForeground(Color.WHITE);
		this.midnightZenith.setLocation((int)midnightLabelX, (int)midnightLabelY);
		
		
	    this.percentMoonCycle.setLocation(50, 200);
	    this.percentMoonCycle.setText(String.valueOf(PhaseCalculator.currentPhase(elapsedDays)));
	    this.percentMoonCycle.setFont(new Font("Serif", 1, 20));
	    this.percentMoonCycle.setForeground(Color.WHITE);
	    
	    this.percentMoonCycleLabel.setLocation(50, 180);
	    this.percentMoonCycleLabel.setText("Lunar Phase");
	    this.percentMoonCycleLabel.setFont(new Font("Serif", 1, 20));
	    this.percentMoonCycleLabel.setForeground(Color.WHITE);
	    
	    this.Sirius.setFont((new Font("Serif",Font.BOLD,20)));
		this.Sirius.setForeground(Color.YELLOW);
		this.Sirius.setLocation(366,823);

	    this.Betelgeuse.setFont((new Font("Serif",Font.BOLD,20)));
		this.Betelgeuse.setForeground(Color.YELLOW);
		this.Betelgeuse.setLocation(450,850);
		
	    this.Aldebaran.setFont((new Font("Serif",Font.BOLD,20)));
		this.Aldebaran.setForeground(Color.YELLOW);
		this.Aldebaran.setLocation(570,840);
		
	    this.Almach.setFont((new Font("Serif",Font.BOLD,20)));
		this.Almach.setForeground(Color.YELLOW);
		this.Almach.setLocation(830,390);
		
	    this.Capella.setFont((new Font("Serif",Font.BOLD,20)));
		this.Capella.setForeground(Color.YELLOW);
		this.Capella.setLocation(500,375);
		
		dateDisplay.setBounds(50,100,100,30);
		dateDisplay.setText(date.toString());
		dateDisplay.setFont(new Font("Serif",Font.BOLD,20));
		dateDisplay.setForeground(Color.WHITE);	
		

	}
	
	
	//calculations for extremeX
	
	private int nextY() {
		// TODO Auto-generated method stub
		return 0;
	}
	private int nextX() {
		// TODO Auto-generated method stub
		return 0;
	}
	private Point2D makePoint(double nextX) {
		// TODO Auto-generated method stub
		return null;
	}
	public double theta(double lineInitialAngle)
	{
		double t = lineInitialAngle + 2*Math.PI*elapsedDays/(s.earth.period);
		return t;
	}
	
	public double next_x( double lineInitAngle)
	{
		double x= 500+(s.saturn.distance)*50*Math.cos(theta(lineInitAngle));		
		return x;
	}

	public double extremeX(double lineInitAngle)
	{
		return next_x(lineInitAngle+s.earth.theta);
		
	}
	
//Calculations for extremeY	
	

	
	public double next_y( double lineInitAngle)
	{
		double x= 500+(s.saturn.distance)*50*Math.sin(theta(lineInitAngle));		
		return x;
	}

	public double extremeY(double lineInitAngle)
	{
		return next_y(lineInitAngle+s.earth.theta);
		
	}
	
	private void resumeButtonPressed() {
		tm.start();
		
	}

	private void pauseButtonPressed() {
		tm.stop();
	}


	public static void main(String[] args)
	{
		SolarSystem s = new SolarSystem();
		GUI g = new GUI(s);
		
		g.setOpaque(false);
		
		
		JFrame jf = new JFrame();
		jf.getContentPane().setBackground(new Color(100,100,150));
		jf.setTitle("Planetary Orbits");
		jf.setSize(1200,1000);	
		jf.setVisible(true);;
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(g);	
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	//public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	//}

}
