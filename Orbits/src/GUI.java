//package animation;


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
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.*;
import java.util.Date;
import java.util.Calendar;

public class GUI extends JPanel implements ActionListener
{
	public Timer tm;
	public static double elapsedDays;
	public LocalDate date= LocalDate.of(2019,1,1);
	
	private SolarSystem s;
	
	JLabel dateDisplay = new JLabel("hi");
	JButton pause = new JButton("Pause");
	JButton resume = new JButton("Resume");
	JSlider speedControl = new JSlider(JSlider.HORIZONTAL);
	
	
	
	
	
	//dateDisplay.setBounds(50,100,100,30);
	
	
	public GUI(SolarSystem s)
	{
		this.s = s;
		
		
		
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				elapsedDays = elapsedDays + 1;
				date = date.plusDays(1);
				//System.out.println(date);
				repaint();
			}};
			 tm = new Timer(100,taskPerformer);
			tm.start();
			
		pause.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
	            
	        	 
	                System.out.println("63");
	                pauseButtonPressed();
	        }
	    });
		
		resume.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
	            
	                resumeButtonPressed();              
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
		pause.setEnabled(true);	
		resume.setEnabled(true);
		
		this.add(dateDisplay);
		this.add(pause);
		this.add(resume);
		this.add(speedControl);
		
		
		
		
			
			
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

