import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Image extends Shape{

	BufferedImage img;
	
	int img_height;
	int img_width;
	
	float rate;
	
	String imginput = null;
	
	Image(int x, int y, int height, int width, Color line, Color color) {
		super(x, y, height, width, line, color);
		// TODO Auto-generated constructor stub
		width = 100;
	}
	
	Image(int x,int y,int height, int width)
	{
		super(x,y,height,width);
		width  =100;
	}
	
	Image(int x,int y,int height, int width,String input)
	{
		super(x,y,height,width);
		this.width = 200;
		
		imginput = input;
		
		try {
			img = ImageIO.read(new File(input));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		img_height = img.getHeight();
		img_width = img.getWidth(); // 가로세로를 구하고
		System.out.println(img_height + " " + img_width);
		rate = ((float) img_height) / img_width; // 비율을 구한다.
		
		this.height = (int) (this.width * rate);
		
		System.out.println(this.width + " " + this.height);
		
	}
	
	@Override
	public void Draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		
		AffineTransform tmp = g2d.getTransform(); // 원래 변환 도형을 얻고나서
		g2d.translate(start_x + width/2, start_y + +height/2);
		g2d.rotate(Math.toRadians(this.degree));
		g2d.translate(-(start_x + width/2), -(start_y + height/2));
		
		g2d.setStroke(new BasicStroke(lineBold));
		
	
		
		g.drawImage(this.img, start_x,start_y,width, (int)((float)width*rate),null);
		
		g2d.setTransform(tmp);
		
	}
	
	public void Update()
	{
		this.start_x = Math.min(x_pos, x2_pos);
		this.start_y = Math.min(y_pos, y2_pos);
		
		this.end_x = start_x + 200;
		this.end_y = start_y + (int)(200*this.rate);
		
	}
	
	public ImageIcon SetLabel()
	{
		ImageIcon Rect = new ImageIcon(imginput);
		
		return Rect;
	}

}
