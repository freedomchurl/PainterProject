import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class Line extends Shape{

	Line(int x, int y, int height, int width, Color line, Color color) {
		super(x, y, height, width, line, color);
		// TODO Auto-generated constructor stub
	}
	
	Line(int x,int y,int height, int width)
	{
		super(x,y,height,width);
	}

	@Override
	public void Draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(this.linecolor);
		
		Graphics2D g2d = (Graphics2D) g;
		
		AffineTransform tmp = g2d.getTransform(); // 원래 변환 도형을 얻고나서
		g2d.translate(start_x + width/2, start_y + +height/2);
		g2d.rotate(Math.toRadians(this.degree));
		g2d.translate(-(start_x + width/2), -(start_y + height/2));
		
		g2d.setStroke(new BasicStroke(lineBold));
		
		g2d.drawLine(start_x,start_y,end_x,end_y);
		
		g2d.setTransform(tmp);
		
	}
	
	public ImageIcon SetLabel()
	{
		ImageIcon Rect = new ImageIcon("Resource/3.png");
		
		return Rect;
	}
	
	public Color GetViewColor()
	{
		return this.linecolor;
	}

}
