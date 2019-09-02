package com.stj.views;


import java.util.Random;

/*
 * 计时器 产生潜艇，运行战舰 , 同时也运行飞机
 */
public class TimeManager implements Runnable
{
    private WarShip ship;//战舰类
	private MyPanel panel;//主界面
	private int speed = 1000;//潜艇的速度
	
	//拿到战舰和画板进行游戏运行的绘制
	public TimeManager(WarShip ship,MyPanel panel)
	{
		this.ship = ship;
		this.panel = panel;
	}
	
	public void run() //开始执行游戏
	{
		Random r = new Random();//创建一个随机数
	
		while(this.panel.isRunning())
		{
			if(this.panel.isStop() == true)//判断是否是暂停游戏
			{
				//System.out.println("777");
				synchronized(MyPanel.subLock)
				{
						try
						{
							MyPanel.subLock.wait();//程序入口文件里面的锁
						}
						catch(Exception e)
						{
							e.printStackTrace();
							//this.flag = true;
							this.panel.endGame();
						}
				}
			}	
				Submarine sm = new Submarine(this.ship,this.panel);//传入潜艇类，和游戏主画板  同样的在使用Thread来进行线程的使用，创造出多个潜艇
				MyPlane pl = new MyPlane(this.panel);//飞机线程，用于生成一张飞机
				this.panel.getPlanes().add(pl);//添加到主窗口里面
				this.panel.getSubmarineArray().add(sm);//每执行一次就往集合里面添加   ，把该类的所有执行状态发放到ArrAyList里面
				
				Thread t = new Thread(sm);//原来的游戏作者制作的线程，用来管理潜艇的相关操作
				Thread t1 = new Thread(pl);
				t.start();
				t1.start();
				
				try
				{
					Thread.sleep(this.speed + r.nextInt(this.speed * 3));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		}
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {//设置速度
		this.speed = speed;
	}
}
