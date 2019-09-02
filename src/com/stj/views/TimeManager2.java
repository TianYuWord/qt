package com.stj.views;


import java.util.ArrayList;
import java.util.Random;
/*
 * ��ʱ�� ��������
 */
public class TimeManager2 implements Runnable
{
	private WarShip ship;
	private ArrayList<Torpedo> torpedoArray ;

	private MyPanel panel;
	private Submarine sm;
	
	//�õ�Ǳͧ�����࣬��Ҫ�Ļ��壬���׵�����
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
				
				//���������壬ս���࣬Ǳͧ����ʱ����
				Torpedo tp = new Torpedo(this.panel,this.ship,this.sm);
				this.torpedoArray.add(tp);//��ʱ�������
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
