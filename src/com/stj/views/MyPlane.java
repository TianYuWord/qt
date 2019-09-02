package com.stj.views;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *  飞机类
 * @author 天宇科技
 *
 */
//此类不需要运行，只需完成对应的图片就行了   -> 实现飞机的左右随机左右移动
public class MyPlane implements Runnable { // 运行飞机的类 实现左右移动
	private int pX;							//飞机的X坐标
	private int pY;							//飞机的y坐标
	private boolean isSrop;					//是否停止
	private boolean isStart;				//是否是运行状态
	private MyPanel plane;					//画板类  主界面
	private Image image;					//飞机的图片
	private int width;						//飞机的宽度
	private int height;						//飞机的高度
	private boolean flag = false;  			//定义该飞机是否跑完
	private int posation = 0;				//战机运行方向
	private Random r = new Random();
	public int a = 0;						//设置方向 0 为向左边 1为向右边
	private int speed = 1;					//飞机的移动速度
	private bulids bulid;					//初始化子弹类
	
	//传入画板，进行绘制
	public MyPlane(MyPanel planel) {
		this.plane = planel;				//保存画板类
		this.pY = r.nextInt(120);			//天空的高度的高度为150
		//随机生成左右的方向
		this.a = r.nextInt(2);
		if(a == 0) {//向左边的飞机
			this.pX = this.plane.getWidth() - this.getWidth();
			int c = new Random().nextInt(2);
//			System.out.println(Thread.currentThread().getName() + "当前是左边飞的飞机随机数" + c + "初始值X：" + this.pX);
			if(c == 0) {
				Image image = Toolkit.getDefaultToolkit().getImage("imgs/leftplay.png");//保存飞机图片
				this.image = new ImageIcon(image).getImage();
			}
			if(c == 1) {
				Image image = Toolkit.getDefaultToolkit().getImage("imgs/leftplay.png");//保存飞机图片
				this.image = new ImageIcon(image).getImage();
			}
		}
		
		
		if(a == 1) {//向右边的飞机
			this.pX = 0;//设置初始的时候的位置
			int c = new Random().nextInt(2);
//			System.out.println(Thread.currentThread().getName() + "当前是右边飞的飞机随机数" + c + "初始值X：" + this.pX);
//			System.out.println(new File("imgs/plane.png").isFile());
//			System.out.println(c + "c");
			if(c == 0) {
				Image image = Toolkit.getDefaultToolkit().getImage("imgs/ringhtplay.png");//保存飞机图片
				this.image = new ImageIcon(image).getImage();
			}
			if(c == 1) {
				Image image = Toolkit.getDefaultToolkit().getImage("imgs/ringhtplay.png");//保存飞机图片  
				this.image = new ImageIcon(image).getImage();
			}
		}
//		System.out.println(this.image);
		this.image = new ImageIcon(this.image).getImage();
		
		this.height = this.image.getHeight(plane);//从画板里面获取图片的高度
		this.width = this.image.getWidth(plane);//从画板里面获取图片的宽度
		
		//随机生成Y轴的坐标
		
//		System.out.println("随机生成的飞机坐标" + this.pY);
	}
	
	//随机生成子弹, 主面板 , 传入自身
	public void bulidddd() {
		int temp = new Random().nextInt(this.plane.getWidth() - this.getWidth());//在画板指定的长度随机开始子弹
		if(this.getpX() == temp) {
			this.bulid = new bulids(this.plane,this);
			this.plane.getBulids().add(bulid);//把子弹类添加到数据之中，主线程就会进行绘制
			Thread t = new Thread(bulid);
			t.start();
		}
	}
	
	//飞机向右飞
	public void rightMove() {
		if(!flag) {
			if(this.pX > this.plane.getWidth()) {
				flag = true;//超出界面，飞机跑完了
			}
			this.bulidddd();
			this.pX += this.speed;
		}
	}
	
	//飞机向左飞
	public void leftMove() {
		if(!flag) {//给该方法上锁
			if(this.pX < 0) {
				flag = true;//超出界面飞机跑完了，外部类直接删除对象
			}
			this.bulidddd();
			this.pX -= this.speed;
		}
	}
	
	//给该图片添加子弹
	
	public void bulid() {
		
	}
	
	@Override
	public void run() {
		//保存完毕飞机图片以后进行创建
		while(!flag) {
			if(this.a == 0) {//飞机向左飞，也就是 当前x的坐标不断 - speed 即可
				this.leftMove();
			}
			if(this.a == 1) {//f飞机向右飞
				this.rightMove();
			}
			//每执行刷新一次坐标的时候就判断一下用户是否暂停
			if(this.plane.isStop())//判断游戏当前是否正在运行
			{
				synchronized(MyPanel.subLock)//根据主线程进行锁死
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
			try {
				Thread.sleep(10);//十毫秒执行一次
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.flag = true;
			}
		}
	}
	//绘制图片
	public void drawImage(Graphics2D g2) {
		g2.drawImage(this.image, this.pX, this.pY,this.plane);//外部的绘制线程会不断的刷新这里的图像，已达到更新数据的效果
	}
	
	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	
	
	public int getpX() {
		return pX;
	}

	public void setpX(int pX) {
		this.pX = pX;
	}

	public int getpY() {
		return pY;
	}

	public void setpY(int pY) {
		this.pY = pY;
	}

	public boolean isSrop() {
		return isSrop;
	}

	public void setSrop(boolean isSrop) {
		this.isSrop = isSrop;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public MyPanel getPlane() {
		return plane;
	}

	public void setPlane(MyPanel plane) {
		this.plane = plane;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getPosation() {
		return posation;
	}

	public void setPosation(int posation) {
		this.posation = posation;
	}
	
}

