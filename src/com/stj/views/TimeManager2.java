package com.stj.views;


import java.util.ArrayList;
import java.util.Random;
/*
 * 计时器 产生鱼雷
 */
public class TimeManager2 implements Runnable
{
	private WarShip ship;
	private ArrayList<Torpedo> torpedoArray ;

	private MyPanel panel;
	private Submarine sm;
	
	//拿到潜艇运行类，主要的画板，鱼雷的数组
	public TimeManager2(Submarine sm,MyPanel panel,WarShip ship,ArrayList<Torpedo> torpedoArray)
	{
		this.sm = sm;
	    this.torpedoArray =  torpedoArray;
		this.panel    = panel;
		this.ship     = ship;
		
	}
	
	public void run() 
	{
		
		Random r = new Random();
		
		
			while(!this.sm.flag)
			{
				//System.out.println("333");
			
				
					if(this.panel.isStop() == true)
					{
						synchronized(MyPanel.subLock)
						{
							//System.out.println("stop");
							try
							{
								MyPanel.subLock.wait();
							}
							catch(Exception e)
							{
								e.printStackTrace();
								//this.flag = true;
								this.panel.endGame();
							}
						}
				
					}
				
				//传入主画板，战舰类，潜艇运行时的类
				Torpedo tp = new Torpedo(this.panel,this.ship,this.sm);
				this.torpedoArray.add(tp);//定时添加鱼雷
				Thread t = new Thread(tp);
				t.start();
				try
				{
					int time = r.nextInt(4000) + 2000;
					Thread.sleep(time);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	
	

}
