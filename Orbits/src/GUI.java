//package animation;

// Class for displaying the simulation 

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
		
		g.setColor(Color.BLUE);
		g.drawRect(x, 30, 50, 30);
		
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
		
		JFrame jf = new JFrame();
		
		jf.setTitle("title");
		jf.setSize(1000,1000);
		jf.setVisible(true);;
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(g);
		
		
	}

}