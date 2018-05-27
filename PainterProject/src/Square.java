import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class Square extends Shape{

	Square(int x, int y, int height, int width) {
		super(x, y, height, width);
		// TODO Auto-generated constructor stub
	}
	
	Square(int x,int y,int height, int width, Color line, Color color)
	{
		super(x,y,height,width,line,color);
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
		
		/*
		g2d.translate(start_x, start_y);
		g2d.scale(this.xRate, this.yRate);
		g2d.translate(-(start_x), -(start_y));
		 */
		
		g2d.setStroke(new BasicStroke(lineBold));
		
		
		g2d.drawRect(start_x,start_y,width,height);//x_pos, y_pos, width, height);
		
		
		g.setColor(this.innercolor);
		
		g2d.fillRect(start_x,start_y,width,height);//x_pos, y_pos, width, height);
		
		g2d.setTransform(tmp);
		
	}
	
	public ImageIcon SetLabel()
	{
		ImageIcon Rect = new ImageIcon("Resource/1.png");
		
		return Rect;
	}
	
	
	

	
}
