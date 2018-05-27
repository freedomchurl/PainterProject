import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

// 예외적으로, myColorTable의 액션리스너는, Table클래스내에 넣었다. -> 색상 이외의 기능 X


public class pController {

	MyMainFrame mainframe = new MyMainFrame(); // Main Frame
	LayerFrame layer = null; // Layer Frame
	myViewPanel myPanel = null;
	
	Point start = null;
	Point end = null;
	
	ArrayList<Shape> mylist = new ArrayList<Shape>(); // My List!
	ArrayList<Shape> removedlist = new ArrayList<Shape>();
	
	boolean resizeable = false;
	boolean rotatable = false;
	
	JButton input = null;
	
	boolean shift = false; // 쉬프트키가 눌려있는지.
	
	MyKeyListener keylistener = new MyKeyListener(); 
	
	Shape lastShape = null;
	
	Shape selectedShape = null;
	
	Shape movechange = null;
	
	String Imageinput = null;
	
	
	pController()
	{
		myPanel = new myViewPanel(mylist);
		// Initialize
		this.Initial();	
		
	
		
		//mainframe.add(myPanel,BorderLayout.CENTER);
		
		mainframe.myMenu.resizing.addActionListener(new Resize());
		mainframe.myMenu.makelayer.addActionListener(new ShowLayer());
		mainframe.myMenu.rotating.addActionListener(new Rotate());
		
		for(int i=0;i<10;i++)
		{
			if(i!=7 && i!=4 && i!=9 &&i!=6)
				mainframe.myMenu.myButtons[i].addActionListener(new Button()); // 버튼들
			else if(i==7)
				mainframe.myMenu.myButtons[i].addActionListener(new PaintInner());
			else if(i==4)
				mainframe.myMenu.myButtons[i].addActionListener(new RemoveButton());
			else if(i==9)
				mainframe.myMenu.myButtons[i].addActionListener(new RedoButton());
			else if(i==6)
				mainframe.myMenu.myButtons[i].addActionListener(new MoveButton());
		}
		
		myPanel.addMouseListener(new MyMouse());
		myPanel.addMouseMotionListener(new MyMouse());
		
		//mainframe.requestFocus(); // 키보드 동작을 하게해줌

		mainframe.addKeyListener(keylistener);
		
		mainframe.myMenu.ImageAdd.addActionListener(new ImageAddButton());
	
		
	}
	
	public void Initial()
	{
		mainframe.add(myPanel,BorderLayout.CENTER);
	}
	
	public void ResetRotate()
	{
		rotatable = false;
		mainframe.myMenu.OffRotate();
	}
	public void ResetResize()
	{
		this.resizeable = false;
		mainframe.myMenu.Off();
	}
	
	class Delete implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int i = layer.SelectedLayer();
			
			if(i!=-1) // 선택이 되었으면
			{
				mylist.remove(i); // 그걸 지우고
				layer.ResetList();
				layer.repaint();
				myPanel.repaint();
				layer.selectedLayer=-1; // i값 초기화.
			}
		}
		
	}
	
	class Up implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int i = layer.SelectedLayer();
			
			if(i!=-1) // 선택이 되었으면
			{
				if(i<mylist.size()-1)
				{
					Shape tmp = mylist.remove(i); // 그걸 지우고
					mylist.add(i+1, tmp);
					layer.ResetList();
					layer.repaint();
					myPanel.repaint();
				}
				layer.selectedLayer=-1; // i값 초기화.
			}
		}
		
	}
	
	class Down implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int i = layer.SelectedLayer();
			
			if(i!=-1) // 선택이 되었으면
			{
				if(i>=1)
				{
					Shape tmp = mylist.remove(i); // 그걸 지우고
					mylist.add(i-1, tmp);
					layer.ResetList();
					layer.repaint();
					myPanel.repaint();
				}
				layer.selectedLayer=-1; // i값 초기화.
			}
		}
		
	}
	

	
	class Rotate implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub			
			ResetResize();
			
			if(rotatable == false)
			{
				rotatable = true;
				mainframe.myMenu.OnRotate();
				input = (JButton) e.getSource(); // 리사이징을 할것이므로 넣는다.
			}
			else
			{
				rotatable = false;
				mainframe.myMenu.OffRotate();
				input = null;
			}
		}
		
	}
	
	class Resize implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			ResetRotate();
			
			if(resizeable == false)
			{
				resizeable = true;
				mainframe.myMenu.On();
				input = (JButton) e.getSource(); // 리사이징을 할것이므로 넣는다.
			}
			else
			{
				resizeable = false;
				mainframe.myMenu.Off();
				input = null;
			}
		}
		
	}
	class ShowLayer implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ResetRotate();
			ResetResize();
			
			if(layer == null)
			{
				layer = new LayerFrame(mylist);
				layer.addWindowListener(new LayerCloser());
				layer.delete.addActionListener(new Delete());
				layer.up.addActionListener(new Up());
				layer.down.addActionListener(new Down());
			}
		}
	}
	
	// Layer창을 닫을때, Layer를 null로 바꿔주어야, 다음번 생성이 용이함
	class LayerCloser extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			layer = null;
		}
	}

	class ImageAddButton implements ActionListener
	{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			input = (JButton) e.getSource();
			
			ResetRotate();
			ResetResize();
						
			FileDialog file;
			file = new FileDialog(mainframe,"Load",0);
			
			file.show();
			String path = file.getDirectory();
			String fileName = file.getFile();
			
			Imageinput = path + fileName;
		}
		
		
	}
	
	
	
	class Button implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ResetRotate();
			ResetResize();
			input = (JButton) e.getSource();
			
		}
	}
	class PaintInner implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ResetRotate();
			ResetResize();
			
			if(mylist.size()!=0)
			{
				if(layer == null)
					lastShape = mylist.get(mylist.size()-1); // 마지막 것
				else
				{
					if(layer.SelectedLayer() ==-1)
					{
						lastShape = mylist.get(mylist.size()-1); // 마지막 것
					}
					else 
					{
						lastShape = mylist.get(layer.SelectedLayer());
					}
				}
			}
			
			input = (JButton) e.getSource();
			
			if(mylist.size()!=0)
				lastShape.innercolor = mainframe.myMenu.myColor.selected; // 내부색상
			myPanel.repaint();
			if(layer!=null)
			{
				//System.out.println("111");
				layer.ResetList();
				layer.repaint();
				layer.selectedLayer=-1; // i값 초기화.
			}
			
		}
		
	}
	
	class RemoveButton implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			input = (JButton) e.getSource();
			ResetRotate();
			ResetResize();
			
			if(input == mainframe.myMenu.myButtons[4]) //  뒤로가기
			{
				if(mylist.size()!=0)
				{
					Shape last = mylist.remove(mylist.size()-1);
					removedlist.add(last);
					myPanel.repaint();
				}
			}
			input =null;
			if(layer!=null)
			{
				layer.ResetList();
				layer.repaint();
			}
			
		}
		
	}
	
	class RedoButton implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			input = (JButton) e.getSource();
			
			ResetRotate();
			ResetResize();
			
			if(input == mainframe.myMenu.myButtons[9])  // 앞으로 돌리기
			{
				if(removedlist.size()!=0)
				{
					Shape last = removedlist.remove(removedlist.size()-1);
					mylist.add(last);
					myPanel.repaint();
				}
			}
			input =null;
			
			if(layer!=null)
			{
				layer.ResetList();
				layer.repaint();
			}
			
			
		}
		
	}
	
	class MoveButton implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			input = (JButton) e.getSource();
			
			ResetRotate();
			ResetResize();
			
			if(mylist.size()!=0)
			{
				if(layer == null)
					lastShape = mylist.get(mylist.size()-1); // 마지막 것
				else
				{
					if(layer.SelectedLayer() ==-1)
					{
						lastShape = mylist.get(mylist.size()-1); // 마지막 것
					}
					else 
					{
						lastShape = mylist.get(layer.SelectedLayer());
					}
				}
				
				
			}
		}
		
	}
	
	public boolean isDrawButton() // 그리기와 관련된 버튼이면 리턴 true, 아니면  false
	{
		for(int i=0;i<10;i++)
		{
			if(input == mainframe.myMenu.myButtons[i])
			{
				if(i==6 || i==7)
					return false; 
			}
		}
		return true;
	}
	
	class MyMouse extends MouseAdapter
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
				
			
			
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			start = e.getPoint();
			
			int x = start.x;
			int y = start.y;
			
			Color c = mainframe.myMenu.myColor.selected;
			
			if(input == mainframe.myMenu.myButtons[0]) // 원의 경우에
			{
				mylist.add(new Circle(x,y,0,0));
				lastShape = mylist.get(mylist.size()-1);
				lastShape.lineColor(c);
				lastShape.LineStroke(mainframe.myMenu.GetStroke());
			}
			else if(input == mainframe.myMenu.myButtons[1]) // 사각형
			{
				mylist.add(new Square(x,y,0,0));
				lastShape = mylist.get(mylist.size()-1);
				lastShape.lineColor(c);
				lastShape.LineStroke(mainframe.myMenu.GetStroke());
			}
			else if(input == mainframe.myMenu.myButtons[2]) // 삼각형
			{
				mylist.add(new Triangle(x,y,0,0));
				lastShape = mylist.get(mylist.size()-1);
				lastShape.lineColor(c);
				lastShape.LineStroke(mainframe.myMenu.GetStroke());
			}
			else if(input == mainframe.myMenu.myButtons[3])  // 직선
			{
				mylist.add(new Line(x,y,0,0));
				lastShape = mylist.get(mylist.size()-1);
				lastShape.lineColor(c);
				lastShape.LineStroke(mainframe.myMenu.GetStroke());
			}
			
			else if(input == mainframe.myMenu.myButtons[5])  // 마름모
			{
				mylist.add(new Diamond(x,y,0,0));
				lastShape = mylist.get(mylist.size()-1);
				lastShape.lineColor(c);
				lastShape.LineStroke(mainframe.myMenu.GetStroke());
			}
			else if(input == mainframe.myMenu.myButtons[6])  // 이동
			{
				
			}
			else if(input == mainframe.myMenu.myButtons[7])  // 페인트
			{
				
			}
			else if(input == mainframe.myMenu.myButtons[8])  // 곡선
			{
				mylist.add(new Curve(x,y,0,0));
				lastShape = mylist.get(mylist.size()-1);
				lastShape.lineColor(c);
				lastShape.LineStroke(mainframe.myMenu.GetStroke());
			}
			else if(input == mainframe.myMenu.ImageAdd) // 눌렀던 버튼이 이거라면
			{
				//System.out.println("11");
				mylist.add(new Image(x,y,0,0,Imageinput));
				lastShape = mylist.get(mylist.size()-1);
				lastShape.Update();
				myPanel.repaint();
			}
			
			if(layer!=null)
			{
				layer.ResetList();
				layer.repaint();
				//layer.selectedLayer=-1; // i값 초기화.
			}
			
			lastShape = null;
			
		
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			//input = null; // 눌린버튼을 초기화
			if(mylist.size()!=0)
				lastShape = mylist.get(mylist.size()-1);
			input = null;
			lastShape = null;
			selectedShape = null; // 떼지는 순간 도형도 초기화
			movechange = null;
			
			if(layer !=null)
					layer.selectedLayer = -1;
			ResetRotate();
			ResetResize();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			end = e.getPoint();
		
			int x = end.x;
			int y = end.y;
			
			
			
			int tmp_x = start.x;
			int tmp_y = start.y;
			
			/*
			if(input == mainframe.myMenu.myButtons[4])
				return;
			*/
			
			if(input == mainframe.myMenu.myButtons[6]) // 이동키가 눌려있을경우
			{
				if(mylist.size()!=0)
				{
					int change_x = end.x-start.x;
					int change_y = end.y-start.y;
				
					// 변화량을 구한후에
					//lastShape = mylist.get(mylist.size()-1);
					if(layer == null)
					{
						/*
						for(int i = mylist.size()-1;i>=0;i--)
						{
							if(mylist.get(i).isContain(end))
								lastShape = mylist.get(i); // 마지막 것
							//else
								//lastShape = mylist.get(mylist.size()-1); // 아무것도 범위내에 없을경우
						}*/
						if(movechange != null)
						{
							lastShape = movechange;
						}
						else
						{
							for(int i = mylist.size()-1;i>=0;i--)
							{
								if(mylist.get(i).isContain(end))
									movechange = mylist.get(i); // 마지막 것
								//else
									//lastShape = mylist.get(mylist.size()-1); // 아무것도 범위내에 없을경우
							}
							lastShape = movechange;
						}
						
					}
					else
					{
						if(layer.SelectedLayer() ==-1)
						{
							/*
							for(int i = mylist.size()-1;i>=0;i--)
								
							{
								if(mylist.get(i).isContain(end))
									lastShape = mylist.get(i); // 마지막 것
								//else
									//lastShape = mylist.get(mylist.size()-1); // 아무것도 범위내에 없을경우
							}
							*/
							if(movechange != null)
							{
								lastShape = movechange;
							}
							else
							{
								for(int i = mylist.size()-1;i>=0;i--)
								{
									if(mylist.get(i).isContain(end))
										movechange = mylist.get(i); // 마지막 것
									//else
										//lastShape = mylist.get(mylist.size()-1); // 아무것도 범위내에 없을경우
								}
								lastShape = movechange;
							}
							
						}
						else 
						{
							
							lastShape = mylist.get(layer.SelectedLayer());
						}
					}
					
					
					lastShape.Move(change_x,change_y);
				//	lastShape.Update();
					
					//if(lastShape.isSame())
						//lastShape.UpdateSame();
					
					start = end;
				}
				
			}
			else if(input == mainframe.myMenu.rotating)  // 회전버튼이 눌려있을경우
			{
					
				if(mylist.size()!=0)
				{
					/*
					if(layer == null)
						lastShape = mylist.get(mylist.size()-1); // 마지막 것
					else
					{
						if(layer.SelectedLayer() ==-1)
						{
							lastShape = mylist.get(mylist.size()-1); // 마지막 것
						}
						else 
						{
							lastShape = mylist.get(layer.SelectedLayer());
						}
					}
					*/
					if(layer == null)
					{
						/*
						for(int i = mylist.size()-1;i>=0;i--)
						{
							if(mylist.get(i).isContain(end))
								lastShape = mylist.get(i); // 마지막 것
							//else
								//lastShape = mylist.get(mylist.size()-1); // 아무것도 범위내에 없을경우
						}*/
						if(movechange != null)
						{
							lastShape = movechange;
						}
						else
						{
							for(int i = mylist.size()-1;i>=0;i--)
							{
								if(mylist.get(i).isContain(end))
									movechange = mylist.get(i); // 마지막 것
								//else
									//lastShape = mylist.get(mylist.size()-1); // 아무것도 범위내에 없을경우
							}
							lastShape = movechange;
						}
						
					}
					else
					{
						if(layer.SelectedLayer() ==-1)
						{
							/*
							for(int i = mylist.size()-1;i>=0;i--)
								
							{
								if(mylist.get(i).isContain(end))
									lastShape = mylist.get(i); // 마지막 것
								//else
									//lastShape = mylist.get(mylist.size()-1); // 아무것도 범위내에 없을경우
							}
							*/
							if(movechange != null)
							{
								lastShape = movechange;
							}
							else
							{
								for(int i = mylist.size()-1;i>=0;i--)
								{
									if(mylist.get(i).isContain(end))
										movechange = mylist.get(i); // 마지막 것
									//else
										//lastShape = mylist.get(mylist.size()-1); // 아무것도 범위내에 없을경우
								}
								lastShape = movechange;
							}
							
						}
						else 
						{
							
							lastShape = mylist.get(layer.SelectedLayer());
						}
					}
					
					int change_x = end.x-start.x;
					int change_y = end.y-start.y;
					
					
					lastShape.Rotating(start, end);
					
					
					start = end;
				}
			}
			else if(input == mainframe.myMenu.resizing) // 리사이징버튼이 눌려있을경우
			{
				if(mylist.size()!=0)
				{
					/*
					if(layer == null)
						lastShape = mylist.get(mylist.size()-1); // 마지막 것
					else
					{
						if(layer.SelectedLayer() ==-1)
						{
							lastShape = mylist.get(mylist.size()-1); // 마지막 것
						}
						else 
						{
							lastShape = mylist.get(layer.SelectedLayer());
						}
					}
					*/
					
					if(layer == null)
					{
						/*
						for(int i = mylist.size()-1;i>=0;i--)
						{
							if(mylist.get(i).isContain(end))
								lastShape = mylist.get(i); // 마지막 것
							//else
								//lastShape = mylist.get(mylist.size()-1); // 아무것도 범위내에 없을경우
						}*/
						if(movechange != null)
						{
							lastShape = movechange;
						}
						else
						{
							for(int i = mylist.size()-1;i>=0;i--)
							{
								if(mylist.get(i).isContain(end))
									movechange = mylist.get(i); // 마지막 것
								//else
									//lastShape = mylist.get(mylist.size()-1); // 아무것도 범위내에 없을경우
							}
							lastShape = movechange;
						}
						
					}
					else
					{
						if(layer.SelectedLayer() ==-1)
						{
							/*
							for(int i = mylist.size()-1;i>=0;i--)
								
							{
								if(mylist.get(i).isContain(end))
									lastShape = mylist.get(i); // 마지막 것
								//else
									//lastShape = mylist.get(mylist.size()-1); // 아무것도 범위내에 없을경우
							}
							*/
							if(movechange != null)
							{
								lastShape = movechange;
							}
							else
							{
								for(int i = mylist.size()-1;i>=0;i--)
								{
									if(mylist.get(i).isContain(end))
										movechange = mylist.get(i); // 마지막 것
									//else
										//lastShape = mylist.get(mylist.size()-1); // 아무것도 범위내에 없을경우
								}
								lastShape = movechange;
							}
							
						}
						else 
						{
							
							lastShape = mylist.get(layer.SelectedLayer());
						}
					}
					
					
					int change_x = end.x-start.x;
					int change_y = end.y-start.y;
					
					lastShape.Scaling(change_x, change_y);
					
					start = end;
				}
			}
			else // 이동키 이외의 경우에
			{
				if(input != null && mylist.size()!=0 && isDrawButton()==true)
				{
					//System.out.println("우우우");
					lastShape = mylist.get(mylist.size()-1);
					lastShape.SetPoint(end);
				
					if(shift == false)
					{
						lastShape.Update();
					}
					else
					{
						
						lastShape.Update();
						lastShape.UpdateSame();
					}
				}
			}
			myPanel.repaint();
			if(layer!=null)
			{
				layer.ResetList();
				layer.repaint();
				//layer.selectedLayer=-1; // i값 초기화.
			}
			
			lastShape = null;
			
		}

		
	}

	class MyKeyListener extends KeyAdapter
	{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getKeyCode() == KeyEvent.VK_SHIFT )
			{
				shift = true;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == KeyEvent.VK_SHIFT)
			{
				shift = false;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
