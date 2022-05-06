
import static org.junit.Assert.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;
import java.util.Date;
import java.util.Calendar;

import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.embed.swing.JFXPanel;

import java.time.LocalDate;



public class GUI extends JPanel implements KeyListener 
{
	

	public Timer tm;
	


	public LocalDate date= LocalDate.now();
	public static double elapsedDays=ChronoUnit.DAYS.between(LocalDate.of(2019,1,1),LocalDate.now());
	
	public String[][] sunrise_sunset= {{"8:48","8:36","8:05","7:21","6:43","6:22","6:25","6:47","7:11","7:34","8:02","8:31"},
			                           {"6:36","7:07","7:34","8:01","8:26","8:50","8:58","8:40","8:02","7:18","6:40","6:23"}};
	
	private SolarSystem s;
	

	
	
	int month = date.getMonthValue();
	

	
	JLabel dateDisplay = new JLabel("");
	JLabel sunriseDisplay = new JLabel();
	
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
		            //System.out.println(speed);
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
		
		

		
		pause.addKeyListener(this);
		timeDirection.add(forward);
		timeDirection.add(backward);
		
		timeDirection.clearSelection();
		forward.setSelected(true);
	}

	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		switch(key) {
			case KeyEvent.VK_D:
				if (pause.isFocusOwner())
					{
						if (movingAhead)
						{
						elapsedDays = elapsedDays + 1;
		
						date = date.plusDays(1);
						repaint();
						}
						
						
						
						else
						{
							elapsedDays = elapsedDays -1;
							date = date.plusDays(-1);
							repaint();
						}
						break;
						
						}
			case KeyEvent.VK_Y:
				if (pause.isFocusOwner())
				{
					if (movingAhead)
					{
					elapsedDays = elapsedDays + 365;
	
					date = date.plusDays(365);
					repaint();
					}
					
					
					
					else
					{
						elapsedDays = elapsedDays -365;
						date = date.plusDays(-365);
						repaint();
					}
					
					}
					
					}
		}
			
				
//		if(key == KeyEvent.VK_D)
//		{
//			if (pause.isFocusOwner())
//			{
//				if (movingAhead)
//				{
//				elapsedDays = elapsedDays + 1;
//
//				date = date.plusDays(1);
//				repaint();
//				}
//				
//				
//				
//				else
//				{
//					elapsedDays = elapsedDays -1;
//					date = date.plusDays(-1);
//					repaint();
//				}
//				
//				}
//			
//		}
			
		

	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		
		ArrayList<Planet> a = s.getPlanetArray();
		
		for (Planet p : a)
		{
			g.setColor(p.getColor());
			g.drawOval((int)p.nextX(),(int)p.nextY(), 5, 5);
			g.fillArc((int)p.nextX(),(int)p.nextY(),5,5,0,360);
			
		}
		
			
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
				
		 
//		//sunrise line	
//		
//		double x11 = s.earth.nextX();
//		double y11 = s.earth.nextY();
//		double x12 = extremeX(sunriseLineInitialAngle) ;
//		double y12 = extremeY(sunriseLineInitialAngle);	
//		g.drawLine((int)x11,(int)y11,(int)x12,(int)y12);
//	
//		//sunset line
//		
//		double x21 = s.earth.nextX();
//		double y21 = s.earth.nextY();
//		double x22 = extremeX(sunsetLineInitialAngle) ;
//		double y22 = extremeY(sunsetLineInitialAngle);
//		g.drawLine((int)x21,(int)y21,(int)x22,(int)y22);
		
		// zenith labels
//		
//		double sunsetLabelX = x22;
//		double sunsetLabelY = y22;
//		this.sunsetZenith.setFont((new Font("Serif",Font.BOLD,20)));
//		this.sunsetZenith.setForeground(Color.WHITE);
//		this.sunsetZenith.setLocation((int)sunsetLabelX, (int)sunsetLabelY);
//		
//		double sunriseLabelX = x12;
//		double sunriseLabelY = y12;
//		this.sunriseZenith.setFont((new Font("Serif",Font.BOLD,20)));
//		this.sunriseZenith.setForeground(Color.WHITE);
//		this.sunriseZenith.setLocation((int)sunriseLabelX, (int)sunriseLabelY);
		
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
		
		
		
		dateDisplay.setBounds(50,100,100,30);
		dateDisplay.setText(date.toString());
		dateDisplay.setFont(new Font("Serif",Font.BOLD,20));
		dateDisplay.setForeground(Color.WHITE);	
		

	}
	
	
	//calculations for extremeX
	
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
		jf.setTitle("title");
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
