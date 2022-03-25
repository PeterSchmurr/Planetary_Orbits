//package animation;


// Class for displaying the simulation in 2-D

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class GUI extends JPanel implements ActionListener
{
	//Timer tm = new Timer(5,this);
	
	int x = 0, velX = 1;
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setColor(Color.YELLOW);
		g.drawOval(500,500, 5, 5);
		g.fillArc(500,500,5,5,0,360);
		
		g.setColor(Color.ORANGE);
		g.drawOval(396,242, 5, 5);
		g.fillArc(396,242,5,5,0,360);
		
		//tm.start();
			
			
	}
	// equivalent to tick handler in Dr. Racket
	public void actionPerformed(ActionEvent e)
	{
		x = x+velX;
		repaint();
	}
	
	public static void main(String[] args)
	{
		GUI g = new GUI();
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