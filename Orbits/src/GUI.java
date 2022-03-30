//package components;


// Class for displaying the simulation in 2-D

import static org.junit.Assert.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
//import javax.swing.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;
import java.util.Date;
import java.util.Calendar;

public class GUI extends JPanel //implements ActionListener
{
	public Timer tm;
	public static double elapsedDays;
	public LocalDate date= LocalDate.of(2019,1,1);
	
	private SolarSystem s;
	
	JLabel dateDisplay = new JLabel("hi");
	JButton pause = new JButton("Pause");
	JButton resume = new JButton("Resume");
	JSlider speedControl = new JSlider(JSlider.HORIZONTAL);
	
	JRadioButton forward = new JRadioButton("forward");
	JRadioButton backward = new JRadioButton("backward");
	
	ButtonGroup timeDirection = new ButtonGroup();
	boolean movingAhead = true;
	
	
	
	
	
	
	//dateDisplay.setBounds(50,100,100,30);
	
	
	public GUI(SolarSystem s)
	{
		this.s = s;
		
		
		
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				if (movingAhead)
				{
					elapsedDays = elapsedDays + 1;
					date = date.plusDays(1);
				}
				else
				{
					elapsedDays = elapsedDays - 1;
					date = date.plusDays(-1);
				}
				
				repaint();
			}};
			
			
			 tm = new Timer(100,taskPerformer);
			tm.start();
			
		pause.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
	            
	        	 
	                //System.out.println("63");
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
	        }
	    });
		
		backward.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
	            
	                movingAhead = false;              
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
		timeDirection.add(forward);
		timeDirection.add(backward);
		
		timeDirection.clearSelection();
		forward.setSelected(true);
	
		
	}
	
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
		g.drawOval(500,500, 5, 5);
		g.fillArc(500,500,5,5,0,360);
		dateDisplay.setBounds(50,100,100,30);
		dateDisplay.setText(date.toString());
		dateDisplay.setFont(new Font("Serif",Font.BOLD,20));
		dateDisplay.setForeground(Color.WHITE);			
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
		jf.setSize(1000,1000);	
		jf.setVisible(true);;
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(g);
		
		
	}

//	@Override
	//public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	//}

}

