package com.stj.views;

import java.awt.*;
import java.util.Random;
import java.awt.Graphics2D;
import javax.swing.*;

/*
 * �����˶��ķ���
 */
public class Submarine implements Runnable
{
	private int X;  //λ��x,y
	private int Y;
	private int dx; //�ƶ�����
	private int m;  //����:0�������� 1�������� 2������ 3��
	private WarShip ship; 
	private MyPanel panel;
	private int weight = 65; //Ĭ�ϳ��ȺͿ�ȣ���������ͼƬ��С
	private int height = 20; //Ǳͧ�ĸ߶�
	public boolean flag = false; //���б��
	private Image image;  //ͼƬ����
	private Random r1 = new Random();//����������
	private Integer ex1 = 0;//�˶���Ϊ��ʶ
	private Integer ex2 = 0;//�˶���Ϊ��ʶ2
	private static final Object obj = new Object();
	private Integer[] arr;
	//private static int   num = 0;
	
	
	//�õ���Ϸ�������Ǳͧ�࿪ʼ����Ǳͧ
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
		//�������ǱͧͼƬ���˶�����
		this.m = (int) (Math.random() * 4);
	    if(this.m == 0)//����
	    {
	    	//
	    	Random r = new Random();
			int  num = r.nextInt(3);
			if(num == 0)
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/Ǳͧ1.png");//Toolkit.getDefaultToolkit().createImage( "C:\\1.JPG ")�����첽�ķ�ʽ����ͼƬ�����߳�ִ�е�_img.getWidth(this)���ʱ������ͼƬ���̻߳�û׼����ͼƬ���Ի᷵��-1��
				image = new ImageIcon(image).getImage();

			}
			else if(num == 1)
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/Ǳͧ2.png");
				image = new ImageIcon(image).getImage();
			}
			else if(num == 2 )
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/Ǳͧ8.png");
				image = new ImageIcon(image).getImage();
			}
	    }
	    if(this.m == 1)//����
	    {
	    	Random r1 = new Random();
			int  num = r1.nextInt(2);
	    	if(num == 0 )
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/Ǳͧ3.png");
				image = new ImageIcon(image).getImage();

			}
			else if(num == 1 )
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/Ǳͧ4.png");
				image = new ImageIcon(image).getImage();
	
			}
	    }
	    if(this.m == 2) {//����
//	    	System.out.println("m == 2  ����Ǳͧ����������");
	    	int num = r1.nextInt(3);
			if(num == 0)
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/Ǳͧ1.png");
				image = new ImageIcon(image).getImage();
			}
			else if(num == 1)
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/Ǳͧ2.png");//���ɶ�ӦǱͧͼƬ
				image = new ImageIcon(image).getImage();//��ȡ��ͼƬ
			}else if(num == 2) {
				image = Toolkit.getDefaultToolkit().getImage("imgs/Ǳͧ8.png");//���ɶ�ӦǱͧͼƬ
				image = new ImageIcon(image).getImage();//��ȡ��ͼƬ
			}
	    }
	    
	    if(this.m == 3) {//����
//	    	System.out.println("m == 2  ����Ǳͧ����������");
	    	int num = r1.nextInt(2);
			if(num == 0)
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/Ǳͧ3.png");
				image = new ImageIcon(image).getImage();
			}
			else if(num == 1)
			{
				image = Toolkit.getDefaultToolkit().getImage("imgs/Ǳͧ4.png");//���ɶ�ӦǱͧͼƬ
				image = new ImageIcon(image).getImage();//��ȡ��ͼƬ
			}
	    }
		
		this.weight =  image.getWidth(panel);//��ȡǱͧ�Ŀ��
		this.height =  image.getHeight(panel);//��ȡǱͧ�ĸ߶�
		
		
		if(m == 0)//����
		{
			//������ - Ǳͧ��� = ͼƬxλ��
			this.X = this.panel.getWidth() - this.weight;
		}
		if(m == 1)//����
		{
			//������ - Ǳͧ��� = ͼƬxλ��  ,
			this.X = 0;
		}
		
		if(m == 2) {//��ʼλ�þ��ڻ������ߣ� �����е�һ�����ʱ��ͬʱ�������У�x,yͬʱ�ı䣩 ��y����һ�����ʱ��ֻ��xֵ�ڱ������  ��ʱx һֱ���� ��ȥ
//			System.out.println("���������");
			this.X = this.panel.getWidth() - this.weight;//��ȡǱͧλ�õĳ�ֵ
			this.ex1 = r1.nextInt(this.panel.getWidth() - this.getWeight());//����������ʱ��x�����y����ͬʱ����
//			System.out.println(this.ex2 + "====================================�������߳�" + Thread.currentThread().getName() + "ex2��ֵ�����Ϊ" + this.ex2);
		}
		
		if(m == 3) {//��ʼλ�þ��ڻ�����ұߣ� �����е�һ�����ʱ��ͬʱ�������У�x,yͬʱ�ı䣩 ��y����һ�����ʱ��ֻ��xֵ�ڱ������ ��ʱxһֱ���� һֱ �����
//			System.out.println("���ұ�����");
			this.X = 0;//Ǳͧλ�õĳ�ֵ
	    	this.ex1 = r1.nextInt(this.panel.getWidth() - this.getWeight());//����������ʱ��x�����y����ͬʱ����
//	    	System.out.println(this.ex2 + "====================================�������߳�" + Thread.currentThread().getName() + "ex2��ֵ�����Ϊ" + this.ex2);
		}
		
		
		Random ry = new Random();//�������ս����Y������
		int y1 = ry.nextInt(panel.getHeight()) + 180; //��Ǳͧ��λ�ö�����ˮ֮��

		//
		while((y1+this.getHeight())  >= panel.getHeight())//�޶�Ǳͧ�ķ�ΧҪ�ں�ˮ֮��
		{
			//ս���� Y ���� = �����Χ�ڻ���ĸ߶� + 180
//			System.out.println("y1" + y1 + "panelHeight" + panel.getHeight() );
			y1 = ry.nextInt(panel.getHeight()) + 180; // �ѷ�Χ���µ����ʵĸ߶�
			
		}
		this.Y = y1;
		//��ʱ��ÿ��һ��ʱ��������׶���
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
		//System.out.println("Ǳˮͧ�˶�");
		this.X -= dx;
		//System.out.println(this.X);
		this.panel.repaint();
		if(this.X < 0)
		{
			this.flag = true;
		}
		
	}
	
	public void moveright()//Ǳͧ�����ƶ�
	{
		this.X += dx;
		//this.panel.repaint();
		if(this.X > this.panel.getWidth())
		{
			this.flag = true;
		}
	}
	
	public void shipmoveright() {//���ϵ��˶���ʽ
		//��ʼλ�þ��ڻ������ߣ� �����е�һ�����ʱ��ͬʱ�������У�x,yͬʱ�ı䣩 ��y����һ�����ʱ��ֻ��xֵ�ڱ������  ��ʱx һֱ�� ��ȥ
		
		//�������һ���� ��Χ�������� - Ǳͧ���)
		this.X += dx;
		if(this.X >= this.ex1) {
//			System.out.println("this.Y" + this.Y + " this.ex2" +  this.ex2);
			while((this.ex2 = arr[new Random().nextInt(290)]) == null) {
				this.ex2 = arr[new Random().nextInt(290)];
			}
			if(this.Y >= this.ex2) {
//				System.out.println(this.Y + " : " + this.ex2);
//				System.out.println("Ǳͧ�����˶�");
				this.Y -= dx;//Ǳͧ�����˶�
			}
		}
		if(this.X > this.panel.getWidth())
		{
			System.out.println("���ҵ�Ǳͧ���Ƴ�");
			this.flag = true;
		}
		
	}
	
	
	public void shipmoveleft() {//���ϵ��˶���ʽ
		//��ʼλ�þ��ڻ�����ұߣ������е�һ�����ʱ��ͬʱ�������У�x,yͬʱ�ı䣩 ��y����һ�����ʱ��ֻ��xֵ�ڱ������  ��ʱx һֱ�� ��ȥ
		
		//�������һ���� ��Χ�������� - Ǳͧ���)
		while((this.ex2 = arr[new Random().nextInt(290)]) == null) {
			this.ex2 = arr[new Random().nextInt(290)];
		}
		this.X -= dx;
		if(this.X <= this.ex1) {
//			System.out.println("this.Y" + this.Y + " this.ex2" +  this.ex2);
			if(this.Y >= this.ex2) {
//				System.out.println("Ǳͧ�����˶� this.Y" + this.Y + "this.X" + this.ex2);
				this.Y -= dx;//Ǳͧ�����˶�
			}
		}
		if(this.X < 0)
		{
			System.out.println("����Ǳͧ���Ƴ�");
			this.flag = true;
		}
	}
	public  void run() //���߳̿��Ƶ����˶�
	{
		//System.out.println("�̼߳���");
		
		
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
					this.shipmoveleft();//Ǳͧ���ϵ��˶���ʽ
				}
				if(this.m == 3) {
					this.shipmoveright();
				}
				//ûִ��ˢ��һ�������ʱ����ж�һ���û��Ƿ���ͣ
				if(this.panel.isStop())//�ж���Ϸ��ǰ�Ƿ���������
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
