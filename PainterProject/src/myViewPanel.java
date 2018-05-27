import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class myViewPanel extends JPanel{

	
	ArrayList<Shape> mylist = null;
	
	myViewPanel(ArrayList<Shape> mylist)
	{
		this.mylist = mylist;
		this.setBackground(Color.white);
	}
	
	
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//g.fillRect(0, 0, 100, 100);
		
		for(int i=0;i<mylist.size();i++)
		{
			mylist.get(i).Draw(g);
		}
		
	}
	
	/*public void getI()
	{
		System.out.println(this.getWidth() + "  "+ this.getHeight());
		BufferedImage img = new BufferedImage(1500,1500,BufferedImage.TYPE_INT_RGB);
	
		this.paint(img.createGraphics());
		
		try {
			ImageIO.write(img, "PNG", new File("1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}*/
}
