package com.stj.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.sun.awt.AWTUtilities;

/*
 * 暂停游戏的提示对话框
 */
		

public class MyDialog extends JDialog 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton label1;
	private JButton label2;
	private JLabel text1;
	private MyPanel   panel;
	private JPanel    emptyPanel;
	
	public MyDialog(Frame frame,boolean modal,MyPanel panel)
	{
		super(frame,modal);
		this.panel = panel;
		this.label1 = new JButton("是");
		this.label2 = new JButton("否");
		
		this.text1 = new JLabel("继续游戏?");
		this.text1.setFont(new Font("楷体",Font.BOLD,25));
		this.text1.setHorizontalAlignment(JLabel.CENTER);
		this.text1.setPreferredSize(new Dimension(300,100));
		
		
		this.emptyPanel = new JPanel();//画笔
		
		this.emptyPanel.setOpaque(true);
		this.emptyPanel.setPreferredSize(new Dimension(200,50));
		
		
		this.label1.setBackground(Color.orange);
		this.label1.setHorizontalAlignment(JLabel.CENTER);
		this.label1.setPreferredSize(new Dimension(80,30));
		this.label1.setForeground(new Color(61,145,64));
		this.label1.setFont(new Font(" ",0,20));
		
	
		this.label2.setBackground(Color.orange);
		this.label2.setHorizontalAlignment(JLabel.CENTER);
		this.label2.setPreferredSize(new Dimension(80,30));
		this.label2.setForeground(new Color(61,145,64));
		this.label2.setFont(new Font(" ",0,20));
		
		
		 JLabel south = new JLabel();
		 south.setPreferredSize(new Dimension(300, 50));
		 JLabel east = new JLabel();
		 east.setPreferredSize(new Dimension(50, 75));
		 JLabel west = new JLabel();
		 west.setPreferredSize(new Dimension(60, 75));
		

		Container c = this.getContentPane();
		
		
		this.emptyPanel.add(this.label1);
		this.emptyPanel.add(this.label2);
		emptyPanel.setOpaque(true);
		this.emptyPanel.setLayout(new FlowLayout(40));
		
		c.add(this.text1,BorderLayout.NORTH);
		c.add(this.emptyPanel,BorderLayout.CENTER);
		c.add(south,BorderLayout.SOUTH);
		c.add(east,BorderLayout.EAST);
		c.add(west,BorderLayout.WEST);
		
		
		
		this.setContentPane(c);
		this.setLocation(frame.getBounds().x + 180,frame.getBounds().y + 200);
		this.setUndecorated(true);
		//this.getContentPane().setBackground(Color.BLUE);
		//AWTUtilities.setWindowOpaque(this, false);
//		AWTUtilities.setWindowOpacity(this, 0.7F);
		this.setSize(300, 200);
		
		
		//this.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		//System.out.println("1111");
		
		this.label1.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						//System.out.println("1111");
						MyDialog.this.panel.goOn();
						
						MyDialog.this.setVisible(false);//继续游戏
					}
					
				}
			
			);
		
		this.label2.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						System.exit(0);//结束
					}
					
				}
			
			);
		this.setVisiableRigeon(this.getWidth(), this.getHeight());
		this.setVisible(true);
	}
	
	public void setVisiableRigeon(int width,int height)
	{
		
		
		Shape shape = new RoundRectangle2D.Float(20,20,this.getWidth() - 40,this.getHeight()-40,15.0f,15.0f);

//		AWTUtilities.setWindowShape(this,shape);
		
		
	}
	
}
