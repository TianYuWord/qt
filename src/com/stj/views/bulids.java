package com.stj.views;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 飞机子弹，
 * 
 * @author 天宇科技
 *
 */
//
public class bulids implements Runnable {
	private int disX = 0;
	private int disY = 0;
	private MyPlane plane;// 获取到飞机类
	private MyPanel panel;// 获取到画板类
	private int position; // 战机运行方向 0 向左 1向右
	private boolean isStop;
	private int speed = 1;// 子弹的运行速度
	private int bulidposition = 0;//子弹的 0水平 1垂直 运行方向
	private Image image;// 创建子弹图片对象
	private int width;
	private int height;
	public boolean isFlag = false;
	private int ispeed = 5;
	private int fanwei = 500;
	
	// 拿到画板类,飞机类
	public bulids(MyPanel panel, MyPlane plane) {
		this.plane = plane;
		this.panel = panel;

		this.disY = panel.getY();
		this.isStop = panel.isStop();
		this.position = plane.a; // 当前飞机的当前方向是 0 为向左边 1为向右边
		

		// 根据飞机的方向来决定子弹运行的方向
		if (this.position == 0) {// 向左运行

			// 存储一下子弹运行的初值

			this.disX = plane.getpX(); // 获取当前战机的坐标
			this.disY = plane.getpY();
			// 随机 生成子弹 , 子弹有两种，一种是向下运行，一种是向上运行
			int temp = new Random().nextInt(2);
			if (temp == 0) { // 向左运行的水平子弹 -> 线程一 -> 不断生成 -> 子弹向右运行
				this.bulidposition = 0;
				Image image = Toolkit.getDefaultToolkit().getImage("imgs/buildleft.png");
				this.image = new ImageIcon(image).getImage();
			}
			if (temp == 1) { // 垂直方向的子弹 -> 线程二 -> 只生产一个子弹向下运行
				this.bulidposition = 1;
				Image image = Toolkit.getDefaultToolkit().getImage("imgs/炸弹.png");
				this.image = new ImageIcon(image).getImage();
			}
		}
		
		// 根据飞机的方向来决定子弹运行的方向
		if (this.position == 1) {// 向右运行

			// 存储一下子弹运行的初值

			this.disX = plane.getpX(); // 获取当前战机的坐标
			this.disY = plane.getpY();

			// 随机 生成子弹 , 子弹有两种，一种是向下运行，一种是向上运行
			int temp = new Random().nextInt(2);
			if (temp == 0) { // 向右运行的水平子弹 -> 线程一 -> 不断生成 -> 子弹向右运行
				this.bulidposition = 0;
				Image image = Toolkit.getDefaultToolkit().getImage("imgs/buildright.png");
				this.image = new ImageIcon(image).getImage();
			}
			if (temp == 1) { // 垂直方向的子弹 -> 线程二 -> 只生产一个子弹向下运行
				this.bulidposition = 1;
				Image image = Toolkit.getDefaultToolkit().getImage("imgs/炸弹.png");
				this.image = new ImageIcon(image).getImage();
			}
		}

		this.width = this.image.getWidth(this.panel);
		this.height = this.image.getHeight(this.panel);
		
		Thread re = new Thread();//
		
	}


	// 子弹的垂直运行方式
	public void Thread1() {// 可以定义成class
		if(this.disY > fanwei) {
			this.isFlag = true;
		}
		this.disY += this.speed;
//		System.out.println("垂直运行的子弹");
	}
	
	
	
	
	// 子弹的向左水平运行方式
	public void Thread2() {// 可以定义成class
		if(this.disX < 0) {
			this.isFlag = true;
		}
//		System.out.println("水平左运行的子弹");
		this.disX -=10;
	}
	
	// 子弹的向右水平运行方式
	public void Thread3() {// 可以定义成class
		if(this.disX > (this.panel.getWidth() - this.getWidth())) {
			this.isFlag = true;
		}
//		System.out.println("水平向右运行的子弹");
		this.disX += 10;
	}
	
	@Override
	public void run() {
		while (!isFlag) {
			if(!this.panel.isSuspendFlag()) {//是否暂停
				
			}
			if(this.position == 0) {//向左运行
				if(this.bulidposition == 1) {//垂直运行
					this.Thread1();
				}
				if(this.bulidposition == 0) {//水平运行
					this.Thread2();
				}
			}
			
			if(this.position == 1) {//向右运行
				if(this.bulidposition == 1) {//垂直运行
					this.Thread1();
				}
				if(this.bulidposition == 0) {//水平运行
					this.Thread3();
				}
			}
			
			//每执行刷新一次坐标的时候就判断一下用户是否暂停
			if(this.panel.isStop()) {
				synchronized (this.panel.subLock) {//锁死该线程
					try {
						this.panel.subLock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean isFlag() {
		return isFlag;
	}

	public void setFlag(boolean isFlag) {
		this.isFlag = isFlag;
	}

	public void drawImage(Graphics2D g2) {
		// 设置外部用来更新数据的方法
		g2.drawImage(this.image, this.disX, this.disY, this.panel);
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


	public int getDisX() {
		return disX;
	}


	public void setDisX(int disX) {
		this.disX = disX;
	}


	public int getDisY() {
		return disY;
	}


	public void setDisY(int disY) {
		this.disY = disY;
	}

}
