import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class Triangle extends Shape {

	int [] xpoint = new int[3];
	int [] ypoint = new int[3];
	
	
	
	Triangle()
	{
		
	}
	
	Triangle(int x, int y, int height, int width) {
		super(x, y, height, width);
		// TODO Auto-generated constructor stub
	}
	
	Triangle(int x,int y,int height, int width, Color line, Color color)
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
		
		g2d.setStroke(new BasicStroke(lineBold));
		

		g2d.translate(start_x, start_y);
		g2d.scale(this.xRate, this.yRate);
		g2d.translate(-(start_x), -(start_y));
		
	
		g2d.drawPolygon(xpoint, ypoint, 3);
		
		g.setColor(this.innercolor);
		
		g2d.fillPolygon(xpoint, ypoint, 3);
		g2d.setTransform(tmp);
	}
	
	public void Update()
	{
		super.Update();
		
		this.xpoint[0] = start_x;
		this.ypoint[0] = start_y;
		
		this.xpoint[2] = end_x;
		this.ypoint[2] = end_y;
		
		this.xpoint[1] = start_x;
		this.ypoint[1] = end_y;
		
		height  = Math.abs(ypoint[0]-ypoint[1]);
		width = Math.abs(xpoint[0]-xpoint[2]);
	}
	
	public void UpdateSame()
	{
		int average = Math.max(height, width);//(height + width) / 2; // Average of height and width!
		
		
		if(x_pos < x2_pos) 
		{
			if(y_pos < y2_pos) // 1번쨰 Normal Case
			{
					
				this.ypoint[1] = this.ypoint[0] + average;
				this.xpoint[2] = this.xpoint[0] + average;
				this.ypoint[2] = this.ypoint[0] + average;
				
				
			}
			else // 3번째 케이스
			{
				this.xpoint[2] = this.xpoint[1] + average;
				this.ypoint[0] = this.ypoint[1] - average;
			}
		}
		else
		{
			if(y_pos < y2_pos) // 2번째 케이스
			{
				
				this.xpoint[1] = this.xpoint[2] - average;
				this.ypoint[1] = this.ypoint[0] + average;
				
				this.xpoint[0] = this.xpoint[2] - average;
				this.ypoint[2] = this.ypoint[0] + average;
			}
			else // 4번째 케이스
			{
		
				this.xpoint[1] = this.xpoint[2] - average;
				this.xpoint[0] = this.xpoint[2] - average;
				this.ypoint[0] = this.ypoint[2] - average;
			}
		}
				
		
		
		height  = Math.abs(xpoint[0]-xpoint[1]);
		width = Math.abs(xpoint[0]-xpoint[2]);
	
	}
	
	public void Move(int c_x,int c_y)
	{
		super.Move(c_x, c_y);
		
		for(int i=0;i<3;i++)
		{
			this.xpoint[i] += c_x;
			this.ypoint[i] += c_y;
		}
	}
	
	public boolean isContain(Point p)
	{
		int inputX = p.x;
		int inputY = p.y;
		//System.out.println("Lets Check");
		//this.Update();
		
		int minX = Math.min(Math.min(xpoint[0], xpoint[1]),xpoint[2]);
		int minY = Math.min(Math.min(ypoint[0], ypoint[1]),ypoint[2]);
		

		int manX = Math.max(Math.max(xpoint[0], xpoint[1]),xpoint[2]);

		int maxY = Math.max(Math.max(ypoint[0], ypoint[1]),ypoint[2]);
		
		if((p.x >= minX) && (p.x<=manX) && (p.y >= minY) && (p.y <= (maxY)))
		{
			//System.out.println("잡앗다요놈");
			return true;
		}
		
		return false;
	}
	
	public void Scaling(int xValue,int yValue)
	{
		super.Scaling(xValue, yValue);
		
		this.xpoint[2] = this.xpoint[0] + this.width;
		this.ypoint[2] = this.ypoint[0] + this.height;
		
		this.ypoint[1] = this.ypoint[0] + this.height;
	}

	public ImageIcon SetLabel()
	{
		ImageIcon Rect = new ImageIcon("Resource/2.png");
		
		return Rect;
	}
	
}
