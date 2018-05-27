import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;


public class MyMainFrame extends JFrame{

	
	myMenuPanel myMenu = null;
	//myColorTable myColor = null;

	
	MyMainFrame()
	{
		super("20144444 - My Painter"); // Set My Frame's title name!
		 
		myMenu = new myMenuPanel();
		//myColor = new myColorTable();
		
		
		this.setLayout(new BorderLayout());
		
		this.add(myMenu,BorderLayout.NORTH);
		
		//myMenu.add(myColor);
		this.setFocusable(true);
		//this.setAutoRequestFocus(true);
		this.setVisible(true);
		this.setLocation(20, 10);
		this.setSize(1500, 960);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		
	
	}
	

	
}
