import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;

abstract class Shape {
	
	Color innercolor; // 내부색상
	Color linecolor;
	int x_pos,y_pos; // 왼쪽위의 좌표
	int x2_pos,y2_pos; // 다른 반대쪽의 좌표
	
	int deltaX=0,deltaY=0;
	
	int start_x , start_y;
	int end_x, end_y;
	
	int height,width; // 높이와 너비
	double degree = 0; // 회전 각도
	Color noColor = new Color(255,255,255,0);
	
	boolean Same = false;
	
	float xRate = 1;
	float yRate = 1;
	
	float lineBold = 1;
	
	boolean isCurve = false;
	
	Shape()
	{
		
	}
	
	Shape(int x,int y,int height,int width,Color line,Color color)
	{
		x_pos = x;
		y_pos = y;
		x2_pos = x;
		y2_pos = y;
		this.height = height;
		this.width = width;
		this.innercolor = color;
		this.linecolor = line;
	}
	
	Shape(int x,int y,int height,int width)
	{
		x_pos = x;
		y_pos = y;
		x2_pos = x;
		y2_pos = y;
		this.height = height;
		this.width = width;
		this.linecolor = Color.BLACK;
		this.innercolor = noColor;
	}
	
	public boolean isContain(Point p)
	{
		int inputX = p.x;
		int inputY = p.y;
		
		
		if((p.x >= this.start_x) && (p.x<=this.end_x) && (p.y >= this.start_y) && (p.y <= (this.end_y)))
		{
			//System.out.println("잡앗다요놈");
			return true;
		}
		
		return false;
	}
	
	abstract public void Draw(Graphics g);
	
	
	public abstract ImageIcon SetLabel();
	
	public void SetHeight(int y)
	{
		//int x3 = Math.max(x_pos, x2_pos);
		
		
		this.height = Math.abs(y_pos-y2_pos);
	}
	public void SetWidth(int x)
	{
		this.width = Math.abs(x_pos-x2_pos);
	}
	public void lineColor(Color c)
	{
		this.linecolor = c;
	}
	public void InnerColor(Color c)
	{
		this.innercolor = c;
	}
	
	public void SetPoint(Point end)
	{
		x2_pos = end.x;
		y2_pos = end.y;
	}
	public int getHeight()
	{
		return this.height;
	}
	public int getWidth()
	{
		return this.width;
	}
	
	public void Update()
	{
		this.start_x = Math.min(x_pos, x2_pos);
		this.start_y = Math.min(y_pos, y2_pos);
		
		this.end_x = Math.max(x_pos, x2_pos);
		this.end_y = Math.max(y_pos, y2_pos);
		
		
		this.height =  Math.abs(start_y - end_y);
		this.width = Math.abs(start_x - end_x);
	}
	
	public Color GetViewColor()
	{
		return this.innercolor;
	}
	
	public void UpdateSame()
	{
		
		int average = Math.max(height, width);
		//int average = (height + width) / 2; // Average of height and width!
		
		if(x_pos < x2_pos) 
		{
			if(y_pos < y2_pos) // 1번쨰 Normal Case
			{
				end_x = start_x + average;
				end_y = start_y + average;
			}
			else // 3번째 케이스
			{
				start_x = x_pos;
				start_y = y_pos - average;
				
				end_x = x_pos + average;
				end_y = y_pos;
			}
		}
		else
		{
			if(y_pos < y2_pos) // 2번째 케이스
			{
				start_x = x_pos - average;
				start_y = y_pos;
				
				end_x = x_pos;
				end_y = y_pos -average;
			}
			else // 4번째 케이스
			{
				start_x = x_pos - average;
				start_y = y_pos - average;
			}
		}
		
		this.height =  Math.abs(start_y - end_y);
		this.width = Math.abs(start_x - end_x);
		
		
		this.Same = true;
		
	}
	
	public void Move(int c_x,int c_y)
	{
		/*
		this.x_pos+=c_x;
		this.x2_pos+=c_x;
		this.y_pos+=c_y;
		this.y2_pos+=c_y;
		*/
		
		this.start_x += c_x;
		this.start_y+=c_y;
		this.end_x += c_x;
		this.end_y+=c_y;
	}
	
	public boolean isSame()
	{
		
		if(Same== true)
			return true;
		else 
			return false;
		
		
	}
	
	public void ResetDelta()
	{
		deltaX = 0;
		deltaY = 0;
	}
	
	public void Scaling(int xValue,int yValue)
	{
		
		this.height = (int)(this.height /*-deltaY*/ + yValue);
		this.width = (int)(this.width /*-deltaX*/ + xValue);
		if(height<=0)
			height = 5;
		if(width<=0)
			width = 5;
		
		this.end_x = this.start_x + this.width;
		this.end_y = this.start_y + this.height;
		

	}
	public int NumAxis(Point o)
	{
		int center_x = this.start_x+width/2;
		int center_y = this.start_y+height/2;
		
		// 좌표 반대임을 주의!
		if(o.y < center_y)
		{
			if(o.x>center_x)
			{
				// 1사분면
				return 1;
			}
			else
				return 2;
		}
		else
		{
			if(o.x>center_x)
			{
				//4
				return 4;
			}
			else
				return 3;
		}
		
	}
	
	public void LineStroke(int num)
	{
		this.lineBold = (float) num;
	}
	
	public void Rotating(Point pre,Point now)
	{
		int center_x = this.start_x+width/2;
		int center_y = this.start_y+height/2;
		//중심점을 찾고
		
		int change_x = now.x-pre.x;
		int change_y = now.y-pre.y;
		
		//if()
		
		double d_pre,d_now; 
		
		//System.out.println(NumAxis(pre));
		if(NumAxis(pre)==1 )
		{
			//double tmp = (double)( pre.y-center_y ) / (double) (pre.x-center_x);
			d_pre = Math.atan2(Math.abs(center_y-pre.y),Math.abs(center_x-pre.x));
			//System.out.println(d_pre);
		}
		else if(NumAxis(pre) == 2)
		{
			d_pre = Math.atan2(Math.abs(center_y-pre.y),Math.abs(center_x-pre.x));
			d_pre = Math.PI - d_pre;
		}
		else if(NumAxis(pre) == 3)
		{
			d_pre = Math.atan2(Math.abs(center_y-pre.y),Math.abs(center_x-pre.x));
			d_pre = Math.PI+d_pre;
		}
		else
		{
			d_pre = Math.atan2(Math.abs(center_y-pre.y),Math.abs(center_x-pre.x));
			d_pre = 2*Math.PI-d_pre;
		}
		
		if(NumAxis(now)==1 )
		{
			//double tmp = (double)( now.y-center_y ) / (double) (now.x-center_x);
			d_now = Math.atan2(Math.abs(center_y-now.y),Math.abs(center_x-now.x));
			//System.out.println(d_now);
		}
		else if(NumAxis(now) == 2)
		{
			d_now = Math.atan2(Math.abs(center_y-now.y),Math.abs(center_x-now.x));
			d_now = Math.PI - d_now;
		}
		else if(NumAxis(now) == 3)
		{
			d_now = Math.atan2(Math.abs(center_y-now.y),Math.abs(center_x-now.x));
			d_now = Math.PI+d_now;
		}
		else
		{
			d_now = Math.atan2(Math.abs(center_y-now.y),Math.abs(center_x-now.x));
			d_now = 2*Math.PI-d_now;
		}
		
		
		//System.out.println(d_now + "   " + d_pre);
		//this.degree+=(float) d_ - (float)d_pre;
		
		this.degree+= Math.toDegrees(d_pre - d_now);
	
	}
	
	
}
