package com.stj.views;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class leve implements Runnable {
	
	//自定义迭代
	
	private MyPanel panel;
	private int evel = 0;
	private Image image = Toolkit.getDefaultToolkit().getImage("imgs/战舰.png");
	private int speed = 10;//军舰的速度 ， 需要升级 ， 
	private int health = 3;//军舰的生命 ， 定时升级，
	private int runbom = 6;//军舰的导弹数 , 定时升级，
	private int time = 0;//时间默认为0   每分钟加1
	private bulids bulid; // 究级军舰的 子弹效果
	private int diren;//敌人的等级
	private boolean flag = false;//是否运行结束
	
	//初始化数据
	public leve(MyPanel panel) {
		this.panel = panel;
	}
	
	//更新图片的方法
	public void upImage() {
		
	}
	
	@Override
	public void run() {
		while(!flag) {
			
			if(this.panel.isStop()) {
				flag = true;
			}
			
			try {
				Thread.sleep(60000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void drawevel(Graphics2D g2) {
		
	}
}
