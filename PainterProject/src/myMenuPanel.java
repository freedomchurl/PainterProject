import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class myMenuPanel extends JPanel{

	//myColorTable myColor = new myColorTable();
	
	JPanel left = new JPanel();
	JPanel right = new JPanel();
	JPanel right_left = new JPanel();
	
	JPanel left_left = new JPanel();
	JPanel left_center = new JPanel();
	JPanel left_right = new JPanel();
	
	JPanel right_right = new JPanel();
	
	JPanel stroke = new JPanel();
	
	JButton [] myButtons = new JButton[10];
	Font myFont = new Font("나눔고딕",Font.PLAIN,20);
	
	String [] ButtonsName = {"원","사각형","삼각형","직선","뒤로","마름모","이동","색칠하기","붓","앞으로"};
	
	Color ini = new Color(67,125,224);
	
	myColorTable myColor = new myColorTable();
	
	JButton makelayer = new JButton(new ImageIcon("Resource/layer.jpg"));
	JButton resizing = new JButton();
	
	ImageIcon on = new ImageIcon("Resource/resizeon.jpg");
	ImageIcon off = new ImageIcon("Resource/resizeoff.jpg");
	
	Color pink = new Color(238,85,110);
	
	String [] linenum = {"1","2","3","5","7","10","20","30","40",};
	JComboBox numStroke = new JComboBox(linenum);
	
	JButton ImageAdd = new JButton(new ImageIcon("Resource/image.jpg"));
	
	JButton rotating = new JButton();
	
	ImageIcon r_on = new ImageIcon("Resource/rotateon.jpg");
	ImageIcon r_off = new ImageIcon("Resource/rotateoff.jpg");
	
	boolean isRotate = false;
	boolean isResize = false;
	
	myMenuPanel()
	{
		this.setBackground(pink);
		this.setPreferredSize(new Dimension(1500,120));
		
		this.setLayout(new GridLayout(1,3));
		
		left.setBackground(pink);
		this.add(left);
		this.add(right);
		this.add(myColor);
		
		right.setLayout(new GridLayout(1,2));
		right.add(right_left);
		right.add(right_right);
		
		right_right.setBackground(pink);
		right_right.setLayout(new GridLayout(1,2)); // Layer랑 확대 박스 넣을곳
		
		rotating.setIcon(r_off);
		rotating.setBackground(pink);
		
		right_right.add(rotating);
		makelayer.setBackground(pink);
		
		resizing.setIcon(off);
		resizing.setBackground(pink);
		right_right.add(resizing);
		
		
		//
		rotating.setFocusable(false);
		makelayer.setFocusable(false);
		resizing.setFocusable(false); // 둘다 shift키를 잘 눌리게 하기 위함이다!
		//
		
		
		right_left.setBackground(pink);
		//right.add(myColor);
		
		right_left.setLayout(new GridLayout(2,5));
		//right_left.add(right_left_left);
		//right_left.add(right_left_right);
		
		//right_left_right.setBackground(ini);
		//right_left_left.setLayout(new GridLayout(2,5));
		
		Initial();
		
		left.setLayout(new GridLayout(1,4));
		
		JLabel mylogo = new JLabel(new ImageIcon("Resource/Bear.png"));
		mylogo.setBackground(pink);
		mylogo.setOpaque(true);
		left.add(mylogo);
			
		
		left.add(stroke);
		
		stroke.setLayout(new GridLayout(5,1));
		
		JLabel stroke_up1 = new JLabel();
		stroke_up1.setBackground(pink);
		stroke_up1.setOpaque(true);
		JLabel stroke_up2 = new JLabel();
		stroke_up2.setBackground(pink);
		stroke_up2.setOpaque(true);
		JLabel stroke_up3 = new JLabel();
		stroke_up3.setBackground(pink);
		stroke_up3.setOpaque(true);
		
		
		JLabel stroke_up = new JLabel("선 굵기");
		stroke_up.setBackground(pink);
		stroke_up.setOpaque(true);
		stroke_up.setFont(new Font("나눔고딕",Font.BOLD,16));
		stroke_up.setHorizontalAlignment(JLabel.CENTER);
		stroke_up.setForeground(Color.WHITE);
		
		stroke.add(stroke_up1);
		stroke.add(stroke_up);
		stroke.add(stroke_up2);
		
		stroke.add(this.numStroke);
		numStroke.setFont(new Font("나눔고딕",Font.BOLD,13));
		stroke.add(stroke_up3);
		
		numStroke.setFocusable(false);
		
		//left.add(new JLabel());
		left.add(makelayer);
		
		ImageAdd.setBackground(pink);
		ImageAdd.setFocusable(false);  // Shift키 인식을 위한 방법
		
		left.add(this.ImageAdd);
		
	}
	public int GetStroke()
	{
		String tmp = (String)numStroke.getSelectedItem();
		
		return Integer.parseInt(tmp);
	}
	
	public void Off()
	{
		this.resizing.setIcon(off);
		this.isResize = false;
	}
	public void On()
	{
		this.resizing.setIcon(on);
		this.isResize = true;
	}
	
	public void OffRotate()
	{
		this.rotating.setIcon(this.r_off);
		this.isRotate = false;
	}
	
	public void OnRotate()
	{
		this.rotating.setIcon(this.r_on);
		this.isRotate = true;
	}
	
	
	
	public void Initial()
	{
		for(int i=0;i<10;i++)
		{
			myButtons[i] = new JButton(new ImageIcon("Resource/" + Integer.toString(i) +".png"));
			myButtons[i].setFont(myFont);
			//myButtons[i].setForeground(Color.WHITE);
			// Bonus Action! Add!
			
			//myButtons[i].setText(ButtonsName[i]);
			myButtons[i].setToolTipText(ButtonsName[i]);;
			myButtons[i].setBackground(new Color(255,255,255));
			//myButtons[i].setBorderPainted(false);
			myButtons[i].setFocusPainted(false);
			myButtons[i].setFocusable(false); // 키 입력을 받기 위함!
			right_left.add(myButtons[i]);
		}
		
		
		
	
		
	}
	
	
	
	}
