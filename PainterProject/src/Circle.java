import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class Circle extends Shape{

	Circle(int x, int y, int height, int width,Color line, Color color) {
		super(x, y, height, width, line, color);
		// TODO Auto-generated constructor stub
	}
	
	Circle(int x, int y, int height, int width) {
		super(x, y, height, width);
		// TODO Auto-generated constructor stub
	}

	public boolean isContain(Point p)
	{
		int inputX = p.x;
		int inputY = p.y;
		//System.out.println("Lets Check");
		//this.Update();
		
		
		if((p.x >= this.start_x) && (p.x<=this.end_x) && (p.y >= this.start_y) && (p.y <= (this.end_y)))
		{
			//System.out.println("원잡앗다요놈");
			return true;
		}
		
		return false;
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
		
		
		g2d.drawOval(start_x,start_y,width,height);//x_pos, y_pos, width, height);
		
		
		g.setColor(this.innercolor);
		
		g2d.fillOval(start_x,start_y,width,height);//x_pos, y_pos, width, height);
		
		g2d.setTransform(tmp);
		
	}
	
	public ImageIcon SetLabel()
	{
		ImageIcon Rect = new ImageIcon("Resource/0.png");
		
		return Rect;
	}
	
	
}
