package com.stj.views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


/*
 * 游戏窗体  ，  界面切换中转通知 的同时 控制线程
 */
public class MyFrame extends JFrame implements Observer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyPanel panel;      //开始界面
	private MainPanel mainpanel; //欢迎页面
	private test 	testa; 		//选择战舰
	private JMenuBar bar;      	//菜单栏     
	private JMenu    menu1;     //菜单1,2
	private JMenu    menu2;
	protected boolean  isStart;
	protected boolean  isExit;
	private Observable obs;
	
	private JMenuItem item1;    //菜单项1,2,3,4,5
	private JMenuItem item2;
	private JMenuItem item3;
	private JMenuItem item4;
	private JMenuItem item5;
	
	
	public MyFrame(Observable ob)
	{
		ob.addObserver(this);//把界面添加到监听者列表中
		obs = ob;//保存观察者
		System.out.println(ob + "obs");
		this.panel = new MyPanel();//创建游戏的主体窗口   主线程
		this.mainpanel = new MainPanel(ob);//游戏的Welcome 页面   page1
		this.testa = new test(ob,this);//选择战舰页面  page2
		this.isStart = mainpanel.getIsStart();//是否是开始运行的状态，用于判断是否初始化
		this.isExit  = mainpanel.isExit();//是否退出
	}
	
	//top上面的菜单
	public void showMyFrame()//在mian方法里面被调用
	{
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		//游戏bar
		this.bar = new JMenuBar();
		//设置bar里面的文字
		this.menu1 = new JMenu("菜单");
		this.menu2 = new JMenu("帮助");
		
		this.item1 = new JMenuItem("开始");
		this.item2 = new JMenuItem("暂停");
		this.item3 = new JMenuItem("退出");
		this.item4 = new JMenuItem("关于我们");
		this.item5 = new JMenuItem("游戏规则");
		//把按钮添加到Item里面，可用于展开
		this.menu1.add(item1);
		this.menu1.add(item2);
		this.menu1.add(item3);
		this.menu2.add(item4);
		this.menu2.add(item5);
		
		//添加第一个菜单到bar里面
		bar.add(menu1);
		//添加
		bar.add(menu2);
		
		this.bar.setVisible(false);//暂时不隐藏，等待开始游戏的时候进行隐藏
		
		this.setJMenuBar(this.bar);//把顶部菜单信息添加到顶部布局
			
		//开始游戏
		item1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				MyFrame.this.panel.requestFocus(); //获取焦点
				
				MyFrame.this.panel.startGame();	  //开始线程初始换启动游戏的所有线程
				
			}
		}
		);
		
		//暂停
		item2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MyFrame.this.panel.stopGame();
			}
		}
		);
		
		//退出
		item3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("退出被点击");
				System.exit(0);
			}
		}
		);
		
		//关于我们
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
		
		//游戏规则
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
		
		this.setSize(645,511);//窗体大小
		this.setVisible(true);//设置是否显示 ,  设置不再显示 ， 等待页面切换的时候在显示
		
		this.setResizable(false);//设置大小不可拖动
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
	}
	
	
	public void changedPanel()
	{
		
	}

	//游戏开始，切换到游戏主界面,接收到按钮事件
	public void update(Observable arg0, Object arg1)
	{	
		
		if(arg1 instanceof test) {
			
			test testa = (test) arg1;
			if(testa.isIsStart()) {
				String a = testa.getSship();
				if(a.equals("putong")) {
					System.out.println("普通战舰");
					
					this.getContentPane().remove(this.testa);
					
					//设置战舰属性
					
					this.panel.ship.i = 0; // 选择战舰 0 代表普通战舰 1代表超级战舰 2代表究级战舰
					this.panel.ship.BeginXPosition();
					this.panel.repaint();
					this.getContentPane().add(this.panel,BorderLayout.CENTER);//设置画板跟新
					
					this.panel.ship.setBombNum(5);
					System.out.println(this.panel.ship.getBombNum());
					
					MyFrame.this.panel.requestFocus(); //获取焦点
					
					MyFrame.this.panel.startGame();	  //开始线程初始换启动游戏的所有线程
					
				}else if(a.equals("zhongji")) {
					System.out.println("终级战舰设置");
					
					this.getContentPane().remove(this.testa);
					
					//设置战舰属性
					this.panel.ship.i = 1; // 选择战舰 0 代表普通战舰 1代表超级战舰 2代表究级战舰
					this.panel.ship.BeginXPosition();
					System.out.println(this.panel.ship.i + "i================");
					this.panel.repaint();
					this.getContentPane().add(this.panel,BorderLayout.CENTER);
					
					this.panel.ship.setBombNum(50);
					System.out.println(this.panel.ship.getBombNum());
					
					MyFrame.this.panel.requestFocus(); //获取焦点
					
					MyFrame.this.panel.startGame();	  //开始线程初始换启动游戏的所有线程
					
				}else if(a.equals("jiuji")) {
					System.out.println("究级战舰");
					this.getContentPane().remove(this.testa);
					//设置战舰属性
					this.panel.ship.i = 2; // 选择战舰 0 代表普通战舰 1代表超级战舰 2代表究级战舰
					this.panel.ship.BeginXPosition();
					System.out.println(this.panel.ship.i + "i================");
					this.panel.repaint();
					this.getContentPane().add(this.panel,BorderLayout.CENTER);
					
					this.panel.ship.setBombNum(1000);
					System.out.println(this.panel.ship.getBombNum());
					
					MyFrame.this.panel.requestFocus(); //获取焦点
					
					MyFrame.this.panel.startGame();	  //开始线程初始换启动游戏的所有线程
					
				}
			}
		}
		
		if(arg1 instanceof MainPanel) {
			//开始界面
			MainPanel panel = (MainPanel) arg1;//强制转换为wilecome界面
			
			//判断学选择了什么
			
			//System.out.println("update" + panel.getIsStart());
			
			if(panel.getIsStart())//获取到主界面的是否开始的消息
			{
				//System.out.println("update" + panel.getIsStart());
				this.getContentPane().remove(this.mainpanel);//删除主方法
				//this.panel.setPreferredSize(new Dimension(645,511));
				
				this.bar.setVisible(true);//显示菜单栏，加载
				this.getContentPane().add(this.testa, BorderLayout.CENTER);
				this.repaint();//刷新
			}
			
		}
		
		
	}

	
	
}
