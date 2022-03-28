//package animation;


// Class for displaying the simulation in 2-D

import java.awt.*;
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
	
	public static double elapsedDays;
	public LocalDate date= LocalDate.of(2019,1,1);
	Timer tm = new Timer(50,this);
	private SolarSystem s;
	JLabel dateDisplay = new JLabel("hi");
	
	//dateDisplay.setBounds(50,100,100,30);
	
	
	public GUI(SolarSystem s)
	{
		this.s = s;
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
		this.add(dateDisplay);
		
		
		
		tm.start();
			
			
	}
	// equivalent to tick handler in Dr. Racket
	public void actionPerformed(ActionEvent e)
	{
		elapsedDays = elapsedDays + 1;
		date = date.plusDays(1);
		System.out.println(date);
		repaint();

		    
		       
		        
		    		
		//System.out.print(elapsedDays);
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

}

//package simpletesting;
//import java.time.LocalDate;
//
//public class SimpleTesting {
//    public static void main(String[] args) {
//        LocalDate today = LocalDate.of(2019,1,1);     //Today
//        LocalDate tomorrow = today.plusDays(1);     //Plus 1 day
//        LocalDate yesterday = today.minusDays(1);   //Minus 1 day
//        System.out.println("Today:     "+today);          
//        System.out.println("Tomorrow:  "+tomorrow);      
//        System.out.println("Yesterday: "+yesterday);          
//    }
//} 