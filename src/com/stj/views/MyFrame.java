package com.stj.views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


/*
 * ��Ϸ����  ��  �����л���ת֪ͨ ��ͬʱ �����߳�
 */
public class MyFrame extends JFrame implements Observer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyPanel panel;      //��ʼ����
	private MainPanel mainpanel; //��ӭҳ��
	private test 	testa; 		//ѡ��ս��
	private JMenuBar bar;      	//�˵���     
	private JMenu    menu1;     //�˵�1,2
	private JMenu    menu2;
	protected boolean  isStart;
	protected boolean  isExit;
	private Observable obs;
	
	private JMenuItem item1;    //�˵���1,2,3,4,5
	private JMenuItem item2;
	private JMenuItem item3;
	private JMenuItem item4;
	private JMenuItem item5;
	
	
	public MyFrame(Observable ob)
	{
		ob.addObserver(this);//�ѽ�����ӵ��������б���
		obs = ob;//����۲���
		System.out.println(ob + "obs");
		this.panel = new MyPanel();//������Ϸ�����崰��   ���߳�
		this.mainpanel = new MainPanel(ob);//��Ϸ��Welcome ҳ��   page1
		this.testa = new test(ob,this);//ѡ��ս��ҳ��  page2
		this.isStart = mainpanel.getIsStart();//�Ƿ��ǿ�ʼ���е�״̬�������ж��Ƿ��ʼ��
		this.isExit  = mainpanel.isExit();//�Ƿ��˳�
	}
	
	//top����Ĳ˵�
	public void showMyFrame()//��mian�������汻����
	{
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		//��Ϸbar
		this.bar = new JMenuBar();
		//����bar���������
		this.menu1 = new JMenu("�˵�");
		this.menu2 = new JMenu("����");
		
		this.item1 = new JMenuItem("��ʼ");
		this.item2 = new JMenuItem("��ͣ");
		this.item3 = new JMenuItem("�˳�");
		this.item4 = new JMenuItem("��������");
		this.item5 = new JMenuItem("��Ϸ����");
		//�Ѱ�ť��ӵ�Item���棬������չ��
		this.menu1.add(item1);
		this.menu1.add(item2);
		this.menu1.add(item3);
		this.menu2.add(item4);
		this.menu2.add(item5);
		
		//��ӵ�һ���˵���bar����
		bar.add(menu1);
		//���
		bar.add(menu2);
		
		this.bar.setVisible(false);//��ʱ�����أ��ȴ���ʼ��Ϸ��ʱ���������
		
		this.setJMenuBar(this.bar);//�Ѷ����˵���Ϣ��ӵ���������
			
		//��ʼ��Ϸ
		item1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				MyFrame.this.panel.requestFocus(); //��ȡ����
				
				MyFrame.this.panel.startGame();	  //��ʼ�̳߳�ʼ��������Ϸ�������߳�
				
			}
		}
		);
		
		//��ͣ
		item2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MyFrame.this.panel.stopGame();
			}
		}
		);
		
		//�˳�
		item3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("�˳������");
				System.exit(0);
			}
		}
		);
		
		//��������
		item4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				//MyFrame.this.panel.setFocusable(false);
				MyFrame.this.panel.setSuspendFlag(true);
				new InfoDialog(MyFrame.this,true,MyFrame.this.panel);
				
			}
		}
		);
		
		//��Ϸ����
		item5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//MyFrame.this.panel.setFocusable(false);
				MyFrame.this.panel.setSuspendFlag(true);
				new HelpDialog(MyFrame.this,true,MyFrame.this.panel);

			}
		}
		);
		
		c.add(this.mainpanel,BorderLayout.CENTER);
		
		this.setSize(645,511);//�����С
		this.setVisible(true);//�����Ƿ���ʾ ,  ���ò�����ʾ �� �ȴ�ҳ���л���ʱ������ʾ
		
		this.setResizable(false);//���ô�С�����϶�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
	}
	
	
	public void changedPanel()
	{
		
	}

	//��Ϸ��ʼ���л�����Ϸ������,���յ���ť�¼�
	public void update(Observable arg0, Object arg1)
	{	
		
		if(arg1 instanceof test) {
			
			test testa = (test) arg1;
			if(testa.isIsStart()) {
				String a = testa.getSship();
				if(a.equals("putong")) {
					System.out.println("��ͨս��");
					
					this.getContentPane().remove(this.testa);
					
					//����ս������
					
					this.panel.ship.i = 0; // ѡ��ս�� 0 ������ͨս�� 1������ս�� 2������ս��
					this.panel.ship.BeginXPosition();
					this.panel.repaint();
					this.getContentPane().add(this.panel,BorderLayout.CENTER);//���û������
					
					this.panel.ship.setBombNum(5);
					System.out.println(this.panel.ship.getBombNum());
					
					MyFrame.this.panel.requestFocus(); //��ȡ����
					
					MyFrame.this.panel.startGame();	  //��ʼ�̳߳�ʼ��������Ϸ�������߳�
					
				}else if(a.equals("zhongji")) {
					System.out.println("�ռ�ս������");
					
					this.getContentPane().remove(this.testa);
					
					//����ս������
					this.panel.ship.i = 1; // ѡ��ս�� 0 ������ͨս�� 1������ս�� 2������ս��
					this.panel.ship.BeginXPosition();
					System.out.println(this.panel.ship.i + "i================");
					this.panel.repaint();
					this.getContentPane().add(this.panel,BorderLayout.CENTER);
					
					this.panel.ship.setBombNum(50);
					System.out.println(this.panel.ship.getBombNum());
					
					MyFrame.this.panel.requestFocus(); //��ȡ����
					
					MyFrame.this.panel.startGame();	  //��ʼ�̳߳�ʼ��������Ϸ�������߳�
					
				}else if(a.equals("jiuji")) {
					System.out.println("����ս��");
					this.getContentPane().remove(this.testa);
					//����ս������
					this.panel.ship.i = 2; // ѡ��ս�� 0 ������ͨս�� 1������ս�� 2������ս��
					this.panel.ship.BeginXPosition();
					System.out.println(this.panel.ship.i + "i================");
					this.panel.repaint();
					this.getContentPane().add(this.panel,BorderLayout.CENTER);
					
					this.panel.ship.setBombNum(1000);
					System.out.println(this.panel.ship.getBombNum());
					
					MyFrame.this.panel.requestFocus(); //��ȡ����
					
					MyFrame.this.panel.startGame();	  //��ʼ�̳߳�ʼ��������Ϸ�������߳�
					
				}
			}
		}
		
		if(arg1 instanceof MainPanel) {
			//��ʼ����
			MainPanel panel = (MainPanel) arg1;//ǿ��ת��Ϊwilecome����
			
			//�ж�ѧѡ����ʲô
			
			//System.out.println("update" + panel.getIsStart());
			
			if(panel.getIsStart())//��ȡ����������Ƿ�ʼ����Ϣ
			{
				//System.out.println("update" + panel.getIsStart());
				this.getContentPane().remove(this.mainpanel);//ɾ��������
				//this.panel.setPreferredSize(new Dimension(645,511));
				
				this.bar.setVisible(true);//��ʾ�˵���������
				this.getContentPane().add(this.testa, BorderLayout.CENTER);
				this.repaint();//ˢ��
			}
			
		}
		
		
	}

	
	
}
