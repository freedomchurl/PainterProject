import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LayerFrame extends JFrame{
	
	JPanel main = new JPanel();
	ArrayList<Shape> mylist = null;
	
	JTable mytable = new JTable();
	
	JPanel tablePanel =new JPanel();
	
	Color pink = new Color(238,85,110);
	
	JPanel layerButton = new JPanel();
	
	JButton up = new JButton("����");
	JButton down = new JButton("�Ʒ���");
	JButton delete = new JButton("����");
	
	JScrollPane scroll = new JScrollPane(tablePanel);

	Color inner = new Color(242,189,106);
	
	int selectedLayer = -1;
	
	
	Vector<ShowWindow> myWindow = new Vector<ShowWindow>();
	
	LayerFrame(ArrayList<Shape> list)
	{
		super("Layer");
		
		
		
		mylist = list;
		
		main.setBackground(pink);
		
		main.setLayout(new BorderLayout());
		
		layerButton.setPreferredSize(new Dimension(350,70));
		layerButton.setBackground(pink);
		
		layerButton.setLayout(new GridLayout(1,3));
		
		layerButton.add(up);
		layerButton.add(down);
		layerButton.add(delete);
		
		up.setBackground(pink);
		up.setForeground(Color.WHITE);
		up.setFont(new Font("�������",Font.BOLD,14));
		up.setOpaque(true);
		
		down.setBackground(pink);
		down.setForeground(Color.WHITE);
		down.setFont(new Font("�������",Font.BOLD,14));
		down.setOpaque(true);
		
		delete.setBackground(pink);
		delete.setForeground(Color.WHITE);
		delete.setFont(new Font("�������",Font.BOLD,14));
		delete.setOpaque(true);
		
		main.add(layerButton, BorderLayout.SOUTH);
		main.add(scroll, BorderLayout.CENTER);
		JLabel upper = new JLabel("                ����             �� ��        ���� ��     ");
		//upper.setHorizontalAlignment(JLabel.CENTER);
		upper.setForeground(Color.WHITE);
		upper.setFont(new Font("�������",Font.BOLD,15));
		upper.setPreferredSize(new Dimension(340,30));
		main.add(upper,BorderLayout.NORTH);		
		this.add(main);
		
		ResetList();
		
		//JColorChooser a = new JColorChooser();
		//main.add(a);
		
		this.setVisible(true);
		this.setLocation(1540,8);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setSize(350, 600);
		this.setResizable(false);
	}
	
	public int SelectedLayer()
	{
		return this.selectedLayer;
	}
	
	public void ResetList()
	{
		int num = mylist.size(); // ����Ʈ�� ����� ����.
		int tmpnum = num;
		if(num<6)
			tmpnum = 6; // ũ�� �ұ����� ���� ����
		
		tablePanel.removeAll();
		tablePanel.setLayout(new GridLayout(tmpnum,1));
		this.myWindow.clear();
		
		
		for(int i=num-1;i>=0;i--)
		{
			//System.out.println(num + " " + i);
			//myWindow.add(new ShowWindow(mylist.get(i),i));
			tablePanel.add(new ShowWindow(mylist.get(i),i));
		}
		
		scroll.repaint();
		scroll.updateUI();
		
		tablePanel.repaint();
		main.add(scroll, BorderLayout.CENTER);
	}
	
	class ShowWindow extends JPanel
	{
		LView layerPainter = null;
		JButton choose = new JButton("����");
		
		Shape myshape = null;
		int num = 0;
		
		ShowWindow(Shape c,int i)
		{
			num = i;
			myshape = c;
			
			layerPainter = new LView(myshape);
			//System.out.println(tmp.getText());
			
			this.setLayout(new BorderLayout());
			
			choose.setPreferredSize(new Dimension(70,80));
			choose.setBackground(pink);
			choose.setForeground(Color.WHITE);
			choose.setFont(new Font("�������",Font.BOLD,14));
			choose.setOpaque(true);
			
			this.add(layerPainter,BorderLayout.CENTER);
			this.add(choose,BorderLayout.EAST);
			this.setPreferredSize(new Dimension(310,80));
			
			choose.addActionListener(new Select());
			
		}
		
		class Select implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				selectedLayer = num;
				//System.out.println(num);
			}
		}
		
		
	}
	
	class LView extends JPanel
	{
		Shape myshape = null;
		
		JLabel mylabel = new JLabel();
		JLabel myinnercolor = new JLabel();
		JLabel mylinecolor = new JLabel();
		LView(Shape c)
		{
			myshape = c;
			

			mylinecolor.setBackground(myshape.linecolor);
			mylinecolor.setOpaque(true);
			
			myinnercolor.setBackground(myshape.innercolor);
			myinnercolor.setOpaque(true);
			mylabel.setIcon(c.SetLabel());
			this.setLayout(new GridLayout(1,4));
			this.add(new JLabel());
			this.add(mylabel,BorderLayout.CENTER);
			this.add(mylinecolor);
			this.add(myinnercolor);
			
			//this.setBackground(inner);
			
			
		
		}
		
		
	}
}
