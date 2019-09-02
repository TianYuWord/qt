package com.stj.views;

import java.awt.*;
import java.util.Random;
import java.awt.Graphics2D;
import javax.swing.*;

/*
 * 敌人运动的方向
 */
public class Submarine implements Runnable
{
	private int X;  //位置x,y
	private int Y;
	private int dx; //移动距离
	private int m;  //方向:0代表向左 1代表向右 2代表上 3下
	private WarShip ship; 
	private MyPanel panel;
	private int weight = 65; //默认长度和宽度，数据来自图片大小
	private int height = 20; //潜艇的高度
	public boolean flag = false; //运行标记
	private Image image;  //图片对象
	private Random r1 = new Random();//标记随机对象
	private Integer ex1 = 0;//运动行为标识
	private Integer ex2 = 0;//运动行为标识2
	private static final Object obj = new Object();
	private Integer[] arr;
	//private static int   num = 0;
	
	
	//拿到游戏主画板和潜艇类开始绘制潜艇
	public  Submarine(WarShip ship,MyPanel panel)
	{
		
		this.ship = ship;
		this.panel = panel;
		
		this.arr = new Integer[290];
		for(int i = 150; i<arr.length; i++) {
			arr[i] = i;
		}
		System.out.println();
		this.dx = 1;
		//随机产生潜艇图片和运动方向
		this.m = (int) (Math.random() * 4);
	    if(this.m == 0)//向左
	    {
	    	//
	    	Random r = new Random();
			int  num = r.nextInt(3);
			if(num == 0)
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/潜艇1.png");//Toolkit.getDefaultToolkit().createImage( "C:\\1.JPG ")，用异步的方式创建图片。当线程执行到_img.getWidth(this)语句时，创建图片的线程还没准备好图片所以会返回-1。
				image = new ImageIcon(image).getImage();

			}
			else if(num == 1)
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/潜艇2.png");
				image = new ImageIcon(image).getImage();
			}
			else if(num == 2 )
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/潜艇8.png");
				image = new ImageIcon(image).getImage();
			}
	    }
	    if(this.m == 1)//向右
	    {
	    	Random r1 = new Random();
			int  num = r1.nextInt(2);
	    	if(num == 0 )
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/潜艇3.png");
				image = new ImageIcon(image).getImage();

			}
			else if(num == 1 )
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/潜艇4.png");
				image = new ImageIcon(image).getImage();
	
			}
	    }
	    if(this.m == 2) {//向左
//	    	System.out.println("m == 2  生成潜艇向左上运行");
	    	int num = r1.nextInt(3);
			if(num == 0)
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/潜艇1.png");
				image = new ImageIcon(image).getImage();
			}
			else if(num == 1)
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/潜艇2.png");//生成对应潜艇图片
				image = new ImageIcon(image).getImage();//获取到图片
			}else if(num == 2) {
				image = Toolkit.getDefaultToolkit().getImage("imgs/潜艇8.png");//生成对应潜艇图片
				image = new ImageIcon(image).getImage();//获取到图片
			}
	    }
	    
	    if(this.m == 3) {//向右
//	    	System.out.println("m == 2  生成潜艇向右上运行");
	    	int num = r1.nextInt(2);
			if(num == 0)
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/潜艇3.png");
				image = new ImageIcon(image).getImage();
			}
			else if(num == 1)
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/潜艇4.png");//生成对应潜艇图片
				image = new ImageIcon(image).getImage();//获取到图片
			}
	    }
		
		this.weight =  image.getWidth(panel);//获取潜艇的宽度
		this.height =  image.getHeight(panel);//获取潜艇的高度
		
		
		if(m == 0)//向左
		{
			//画板宽度 - 潜艇宽度 = 图片x位置
			this.X = this.panel.getWidth() - this.weight;
		}
		if(m == 1)//向右
		{
			//画板宽度 - 潜艇宽度 = 图片x位置  ,
			this.X = 0;
		}
		
		if(m == 2) {//开始位置就在画板的左边， 在运行到一个点的时候同时向上运行（x,y同时改变） 当y到达一个点的时候，只有x值在变就行了  此时x 一直向左 减去
//			System.out.println("向左边运行");
			this.X = this.panel.getWidth() - this.weight;//获取潜艇位置的初值
			this.ex1 = r1.nextInt(this.panel.getWidth() - this.getWeight());//到达该坐标的时候x坐标和y坐标同时增加
//			System.out.println(this.ex2 + "====================================保存了线程" + Thread.currentThread().getName() + "ex2的值随机数为" + this.ex2);
		}
		
		if(m == 3) {//开始位置就在画板的右边， 在运行到一个点的时候同时向上运行（x,y同时改变） 当y到达一个点的时候，只有x值在变就行了 此时x一直向右 一直 在相加
//			System.out.println("向右边运行");
			this.X = 0;//潜艇位置的初值
	    	this.ex1 = r1.nextInt(this.panel.getWidth() - this.getWeight());//到达该坐标的时候x坐标和y坐标同时增加
//	    	System.out.println(this.ex2 + "====================================保存了线程" + Thread.currentThread().getName() + "ex2的值随机数为" + this.ex2);
		}
		
		
		Random ry = new Random();//随机生成战舰的Y轴坐标
		int y1 = ry.nextInt(panel.getHeight()) + 180; //把潜艇的位置定到海水之下

		//
		while((y1+this.getHeight())  >= panel.getHeight())//限定潜艇的范围要在海水之下
		{
			//战舰的 Y 坐标 = 随机范围内画板的高度 + 180
//			System.out.println("y1" + y1 + "panelHeight" + panel.getHeight() );
			y1 = ry.nextInt(panel.getHeight()) + 180; // 把范围更新到合适的高度
			
		}
		this.Y = y1;
		//计时器每隔一段时间产生鱼雷对象
		TimeManager2 tm2 = new TimeManager2(this,this.panel,this.ship,this.panel.getTorpedoArray());
		Thread  t = new Thread(tm2);
		t.start();

	}
	
	public void drawSubmarine(Graphics2D g)
	{
		g.drawImage(image,this.X, this.Y, panel);
	}
	
	public void moveLeft()
	{
		//System.out.println("潜水艇运动");
		this.X -= dx;
		//System.out.println(this.X);
		this.panel.repaint();
		if(this.X < 0)
		{
			this.flag = true;
		}
		
	}
	
	public void moveright()//潜艇向右移动
	{
		this.X += dx;
		//this.panel.repaint();
		if(this.X > this.panel.getWidth())
		{
			this.flag = true;
		}
	}
	
	public void shipmoveright() {//右上的运动方式
		//开始位置就在画板的左边， 在运行到一个点的时候同时向上运行（x,y同时改变） 当y到达一个点的时候，只有x值在变就行了  此时x 一直向 减去
		
		//随机生成一个点 范围（画板宽度 - 潜艇宽度)
		this.X += dx;
		if(this.X >= this.ex1) {
//			System.out.println("this.Y" + this.Y + " this.ex2" +  this.ex2);
			while((this.ex2 = arr[new Random().nextInt(290)]) == null) {
				this.ex2 = arr[new Random().nextInt(290)];
			}
			if(this.Y >= this.ex2) {
//				System.out.println(this.Y + " : " + this.ex2);
//				System.out.println("潜艇向上运动");
				this.Y -= dx;//潜艇向上运动
			}
		}
		if(this.X > this.panel.getWidth())
		{
			System.out.println("往右的潜艇已移除");
			this.flag = true;
		}
		
	}
	
	
	public void shipmoveleft() {//左上的运动方式
		//开始位置就在画板的右边，在运行到一个点的时候同时向上运行（x,y同时改变） 当y到达一个点的时候，只有x值在变就行了  此时x 一直向 减去
		
		//随机生成一个点 范围（画板宽度 - 潜艇宽度)
		while((this.ex2 = arr[new Random().nextInt(290)]) == null) {
			this.ex2 = arr[new Random().nextInt(290)];
		}
		this.X -= dx;
		if(this.X <= this.ex1) {
//			System.out.println("this.Y" + this.Y + " this.ex2" +  this.ex2);
			if(this.Y >= this.ex2) {
//				System.out.println("潜艇向上运动 this.Y" + this.Y + "this.X" + this.ex2);
				this.Y -= dx;//潜艇向上运动
			}
		}
		if(this.X < 0)
		{
			System.out.println("往左潜艇已移除");
			this.flag = true;
		}
	}
	public  void run() //该线程控制敌人运动
	{
		//System.out.println("线程激活");
		
		
			while(!flag)
			{
				//System.out.println("222");
				if(this.m == 0)
				{
					this.moveLeft();
				}
				if(this.m == 1)
				{
					this.moveright();
				}
				if(this.m == 2) {
					this.shipmoveleft();//潜艇左上的运动方式
				}
				if(this.m == 3) {
					this.shipmoveright();
				}
				//没执行刷新一次坐标的时候就判断一下用户是否暂停
				if(this.panel.isStop())//判断游戏当前是否正在运行
				{
					synchronized(MyPanel.subLock)
					{
						try
						{
							MyPanel.subLock.wait();
						}
						catch(Exception e)
						{
							e.printStackTrace();
							this.flag = true;
						}
					}
	
				}
			

				try
				{
					Thread.sleep(10);//
				}
				catch(Exception e)
				{
					e.printStackTrace();
					this.flag = true;
				}
			}
		}


	

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
