import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Vector;

import javax.swing.ImageIcon;

public class Curve extends Shape{

	
	Vector<Point> lines = new Vector<Point>();
	Point start = null;
	
	Curve(int x, int y, int height, int width, Color line, Color color) {
		super(x, y, height, width, line, color);
		// TODO Auto-generated constructor stub
	}
	
	Curve(int x,int y,int height, int width)
	{
		super(x,y,height,width);
		
		start = new Point(x,y);
		
		lines.add(start);
		
		this.isCurve = true;
	}
	
	
	
	@Override
	public void Draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(this.linecolor);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(lineBold));
		
		Point tmp = lines.get(0);
		
		for(int i=1;i<lines.size();i++)
		{
			Point aim = lines.get(i);
			
			g2d.drawLine(tmp.x, tmp.y, aim.x, aim.y);
			
			tmp = aim;
		}
		
	}
	
	public void SetPoint(Point end)
	{
		super.SetPoint(end);
		
		lines.add(end);
		
	}
	
	public ImageIcon SetLabel()
	{
		ImageIcon Rect = new ImageIcon("Resource/8.png");
		
		return Rect;
	}

	public Color GetViewColor()
	{
		return this.linecolor;
	}
	
}
