package com.stj.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Label;
import javax.swing.JMenu;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.Icon;

public class test extends JPanel implements KeyListener,MouseListener {

	private JPanel contentPane;
	private JTable table;
	private Observable obs;
	private String Sship;
	private boolean IsStart;
	private MyFrame frame;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public test(Observable obs,MyFrame frame) {
		
		this.obs = obs;
		this.frame = frame;
		
		setLayout(null);
		
		Label label = new Label("\u9009\u62E9\u4F60\u7684\u6218\u8230");
		label.setBounds(257, 10, 95, 25);
		add(label);
		
		table = new JTable();
		table.setBounds(51, 94, 1, 1);
		add(table);
		
		Label label_1 = new Label("\u5C0F\u6218\u8230",Label.CENTER);
		label_1.setForeground(Color.MAGENTA);
		label_1.setBackground(Color.ORANGE);
		label_1.setBounds(10, 113, 141, 25);
		add(label_1);
		
		Label label_2 = new Label("\u7EC8\u7EA7\u6218\u8230", Label.CENTER);
		label_2.setForeground(Color.MAGENTA);
		label_2.setBackground(Color.ORANGE);
		label_2.setBounds(214, 70, 141, 25);
		add(label_2);
		
		Label label_3 = new Label("\u7A76\u7EA7\u6218\u8230", Label.CENTER);
		label_3.setForeground(Color.MAGENTA);
		label_3.setBackground(Color.ORANGE);
		label_3.setBounds(432, 36, 141, 25);
		add(label_3);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("\u6218\u8230\u63CF\u8FF0\uFF1A\u4F4E\u7EA7\u6218\u8230");
		textPane.setBounds(10, 256, 144, 85);
		add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("\u6218\u8230\u63CF\u8FF0\uFF1A\u55EF\uFF0C\u6709\u70B9\u5389\u5BB3");
		textPane_1.setBounds(214, 256, 144, 85);
		add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("\u6218\u8230\u63CF\u8FF0\uFF1A\u65E0\u654C\u662F\u591A\u4E48\u5BC2\u5BDE");
		textPane_2.setBounds(429, 256, 144, 85);
		add(textPane_2);
		
		JLabel lblZxcs = new JLabel(new ImageIcon("imgs/战舰.png"));
		lblZxcs.setText("");
		lblZxcs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblZxcs.setBackground(new Color(0, 191, 255));
		lblZxcs.setBounds(37, 131, 72, 128);
		add(lblZxcs);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(""));
		lblNewLabel.setBounds(125, 43, 72, 18);
		add(lblNewLabel);
		
		JLabel label_4 = new JLabel(new ImageIcon("imgs/超级战舰.png"));
		label_4.setText("");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBackground(Color.GREEN);
		label_4.setBounds(157, 113, 205, 128);
		add(label_4);
		
		JLabel label_5 = new JLabel(new ImageIcon("imgs/无敌战舰.png"));
		label_5.setText("");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBackground(Color.GREEN);
		label_5.setBounds(324, 59, 310, 184);
		add(label_5);
		
		JButton button = new JButton("选择");
		button.setBounds(20, 354, 113, 27);
		add(button);
		
		JButton button_1 = new JButton("选择");
		button_1.setBounds(231, 354, 113, 27);
		add(button_1);
		
		JButton button_2 = new JButton("选择");
		button_2.setBounds(449, 354, 113, 27);
		add(button_2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.repaint();
		
		
		//普通战舰
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				test.this.setIsStart(true);//设置运行状态为true
				System.out.println("普通战舰被点击");
				test.this.setSship("putong");
				test.this.obs.notifyObservers(test.this);
			}
		});
		
		//终级战舰
		button_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				test.this.setIsStart(true);//设置运行状态为true
				System.out.println("终级战舰被点击");
				test.this.setSship("zhongji");
				test.this.obs.notifyObservers(test.this);
			}
		});
		
		//究级战舰
		button_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				test.this.setIsStart(true);
				System.out.println("究级战舰被点击");//设置被点击的效果
				test.this.setSship("jiuji");
				test.this.obs.notifyObservers(test.this);
			}
			
		});
	}

	private void setContentPane(JPanel contentPane2) {
		// TODO Auto-generated method stub
		
	}

	private void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub
		
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public String getSship() {
		return Sship;
	}

	public void setSship(String sship) {
		Sship = sship;
	}

	public boolean isIsStart() {
		return IsStart;
	}

	public void setIsStart(boolean isStart) {
		IsStart = isStart;
	}
	
}
