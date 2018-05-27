import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


	public class myColorTable extends JPanel{

		JPanel left = new JPanel();
		JPanel right = new JPanel();
		
		
		JPanel left_up = new JPanel();
		
		
		JPanel left_down = new JPanel();
		
		JPanel left_down_left = new JPanel();
		JPanel left_down_right = new JPanel();
		
		
		JButton [] colors = new JButton[21];
		
		Color selected = Color.BLACK;//new Color(255,255,255,0); // Default Color is no Color!
		
		Color[] mycolors = new Color[21]; // Color Save!
		
		JLabel now = new JLabel("현재 색상");
		JLabel now2 = new JLabel();
		
		ColorTextBox myBox = new ColorTextBox();
		
		Color ini = new Color(67,125,224);
		Color pink = new Color(238,85,110);
		
		myColorTable()
		{
			this.setPreferredSize(new Dimension(375,120));
			
			left.setPreferredSize(new Dimension(275,120));
			right.setPreferredSize(new Dimension(100,120));
			
			for(int i=0;i<21;i++)
			{
				colors[i] = new JButton();	
				colors[i].setFocusable(false);
			}
			this.Initial();
			
			
			now.setHorizontalAlignment(JLabel.CENTER);
			now.setFont(new Font("나눔고딕",Font.BOLD,14));
			//now.setBackground(new Color(136,173,236));
			now.setBackground(new Color(238,85,110));
			now.setForeground(Color.WHITE);
			now.setOpaque(true);
			
			now2.setOpaque(true);
			now2.setBackground(selected);
			
			
			this.setLayout(new BorderLayout());
			this.add(left,BorderLayout.CENTER);
			this.add(right,BorderLayout.EAST);
			
			right.setLayout(new GridLayout(2,1));
			right.add(now2);
			right.add(now);
			
			
			left_down_right.setBackground(pink);
			/*
			 * From Here, it is text color box
			 */
			left_down_right.setLayout(new BorderLayout());
			left_down_right.add(myBox,BorderLayout.CENTER);
			
			
			
			
			//to here
			
			
			left_down.setLayout(new BorderLayout());
			left_down.add(colors[20],BorderLayout.WEST);
			left_down.add(left_down_right,BorderLayout.CENTER);
			
			colors[20].setPreferredSize(new Dimension(70,60)); // 절반크기 (275,60) 그중 70,60
			colors[20].setBackground(new Color(255,255,255,0));
			colors[20].setOpaque(false);
			colors[20].setFont(new Font("나눔고딕",Font.BOLD,15));
			colors[20].setText("투명");
			
			
			//left_up.setPreferredSize(new Dimension(275,60));
			left_up.setLayout(new GridLayout(2,10));
			left_up.setBackground(Color.WHITE);
			
			for(int i=0;i<20;i++)
			{
				left_up.add(colors[i]);
			}
			
			left.setLayout(new GridLayout(2,1));
			left.add(left_up);
			left.add(left_down);
			
			
			// Add!!!!!!
			for(int i=0;i<21;i++)
			{
				colors[i].addActionListener(new SelectColor(i));
			}
			myBox.confirm.addActionListener(new ConfirmColor());
			
			
		}
		
		class SelectColor implements ActionListener
		{

			int i;
			
			SelectColor(int input)
			{
				i=input;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color tmp = mycolors[i];
				
				selected = tmp;
				
				if(i==20) // if i == 20, it's no color! so we need to setOpaque false!
					now2.setOpaque(false);
				else
					now2.setOpaque(true);

				now2.setBackground(tmp);
			}	
		}
		
		class ConfirmColor implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Color tmp = myBox.getColor();
				
				
				
				now2.setOpaque(true);
				
				selected = tmp;
				
				now2.setBackground(tmp);
				
				//now2.repaint();
			}
			
		}
		
		
		/////////////////////// To here!!!!
		
		
		
		/*RGB ColorText Box*/
		class ColorTextBox extends JPanel
		{
			JPanel up = new JPanel();
			JPanel down = new JPanel();
			
			JLabel down1 = new JLabel();
			JLabel down3 = new JLabel();
			JButton confirm = new JButton("선택");
			JPanel down2 = new JPanel();
			
			JLabel R = new JLabel("R :");
			JTextField RT = new JTextField();
			
			JLabel G = new JLabel("G :");
			JTextField GT = new JTextField();
			
			JLabel B = new JLabel("B :");
			JTextField BT = new JTextField();
			
			
			ColorTextBox()
			{
				this.setPreferredSize(new Dimension(205,60));
				this.setBackground(pink);
				
				this.setLayout(new GridLayout(2,1));
				
				this.add(up);
				this.add(down);
				
				down.setLayout(new GridLayout(1,3));
				
				down.add(down1);
				down1.setBackground(pink);
				down1.setOpaque(true);
				down2.setBackground(pink);
				down2.setOpaque(true);
				down.add(down2);
				down.add(down3);
				
				confirm.setFocusable(false);
				
				down2.setLayout(new BorderLayout());
				
				JLabel tmp1 = new JLabel();
				tmp1.setOpaque(true);
				tmp1.setBackground(pink);
				
				JLabel tmp2  = new JLabel();
				tmp2.setOpaque(true);
				tmp2.setBackground(pink);
				
				tmp1.setPreferredSize(new Dimension(69,3));
				tmp2.setPreferredSize(new Dimension(69,4));
				
				
				down2.add(tmp1, BorderLayout.NORTH);
				down2.add(confirm, BorderLayout.CENTER);
				down2.add(tmp2, BorderLayout.SOUTH);
				confirm.setOpaque(true);
				confirm.setBackground(Color.WHITE);
				confirm.setFont(new Font("나눔고딕",Font.PLAIN,14));
				
				
				up.setBackground(pink);
				down.setBackground(pink);
				
				up.setLayout(new GridLayout(1,6));
				//up.setLayout(new GridLayout(1,8));
				up.add(R);
				R.setBackground(pink);
				R.setOpaque(true);
				R.setFont(new Font("나눔고딕",Font.BOLD,14));
				R.setForeground(Color.white);
				R.setHorizontalAlignment(JLabel.CENTER);
				
				
				up.add(RT);
				up.add(G);
				G.setBackground(pink);
				G.setOpaque(true);
				G.setFont(new Font("나눔고딕",Font.BOLD,14));
				G.setForeground(Color.white);
				G.setHorizontalAlignment(JLabel.CENTER);
				
				up.add(GT);
				up.add(B);
				B.setBackground(pink);
				B.setOpaque(true);
				B.setFont(new Font("나눔고딕",Font.BOLD,14));
				B.setForeground(Color.WHITE);
				B.setHorizontalAlignment(JLabel.CENTER);
				
				up.add(BT);
				
				/////
				
				
			}
			
			public Color getColor()
			{
				int R,G,B;
				
				if(RT.getText().length()==0)
					R=0;
				else
					R = Integer.parseInt(RT.getText());
				
				if(GT.getText().length()==0)
					G=0;
				else
					G = Integer.parseInt(GT.getText());
				
				if(BT.getText().length()==0)
					B=0;
				else
					B = Integer.parseInt(BT.getText());
				
				
				
				if(R>255)
					R = 255;
				if(G>255)
					G = 255;
				if(B>255)
					B = 255;
				
				if(R<0)
					R=0;
				if(G<0)
					G=0;
				if(B<0)
					B=0;
				
				// Exception !
				
				Color Return = new Color(R,G,B);
				
				return Return;
			}
		}
		
		
		
		
		public void Initial()
		{
			colors[0].setBackground(mycolors[0] = new Color(0,0,0)); // Black
			colors[0].setToolTipText("검정");
			
			colors[1].setBackground(mycolors[1] = new Color(128,128,128)); // Gray
			colors[1].setToolTipText("회색");
			
			colors[2].setBackground(mycolors[2] = new Color(128,0,0)); // Orange
			colors[2].setToolTipText("갈색");
			
			colors[3].setBackground(mycolors[3] = new Color(237,28,36)); // Red
			colors[3].setToolTipText("빨강");
			
			colors[4].setBackground(mycolors[4] = new Color(255,128,0)); // Orange
			colors[4].setToolTipText("주황");
			
			colors[5].setBackground(mycolors[5] = new Color(255,255,0)); // Orange
			colors[5].setToolTipText("노랑");
			
			colors[6].setBackground(mycolors[6] = new Color(0,128,0)); // Green
			colors[6].setToolTipText("초록");
			
			colors[7].setBackground(mycolors[7] = new Color(0,128,255)); // Blue
			colors[7].setToolTipText("파랑");
			
			colors[8].setBackground(mycolors[8] = new Color(0,0,160)); // Orange
			colors[8].setToolTipText("진한 파랑");
			
			colors[9].setBackground(mycolors[9] = new Color(128,0,128)); // Orange
			colors[9].setToolTipText("보라색");
			
			colors[10].setBackground(mycolors[10] = new Color(255,255,255)); // Orange
			colors[10].setToolTipText("흰색");
			
			colors[11].setBackground(mycolors[11] = new Color(192,192,192));
			colors[11].setToolTipText("회색");
			
			colors[12].setBackground(mycolors[12] = new Color(128,64,64)); // Orange
			colors[12].setToolTipText("연한 갈색");
			
			colors[13].setBackground(mycolors[13] = new Color(255,128,192)); // Orange
			colors[13].setToolTipText("분홍색");
			
			colors[14].setBackground(mycolors[14] = new Color(255,201,14)); // Orange
			colors[14].setToolTipText("연한 주황");
			
			colors[15].setBackground(mycolors[15] = new Color(239,228,176)); // Orange
			colors[15].setToolTipText("상아색");
			
			colors[16].setBackground(mycolors[16] = new Color(181,230,29)); // Orange
			colors[16].setToolTipText("연두색");
			
			colors[17].setBackground(mycolors[17] = new Color(153,217,234)); // Orange
			colors[17].setToolTipText("하늘색");
			
			colors[18].setBackground(mycolors[18] = new Color(112,146,190)); // Orange
			colors[18].setToolTipText("연청색");
			
			colors[19].setBackground(mycolors[19] = new Color(200,191,231)); // Orange
			colors[19].setToolTipText("연보라색");
			
			colors[20].setBackground(mycolors[20] = new Color(255,255,255,0)); // Orange
			colors[20].setToolTipText("투명");
		}
		
		public Color getSelected() // 현재 색을 리턴받는 함수.
		{
			return this.selected;
		}
	}
