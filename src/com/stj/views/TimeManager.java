package com.stj.views;


import java.util.Random;

/*
 * ��ʱ�� ����Ǳͧ������ս�� , ͬʱҲ���зɻ�
 */
public class TimeManager implements Runnable
{
    private WarShip ship;//ս����
	private MyPanel panel;//������
	private int speed = 1000;//Ǳͧ���ٶ�
	
	//�õ�ս���ͻ��������Ϸ���еĻ���
	public TimeManager(WarShip ship,MyPanel panel)
	{
		this.ship = ship;
		this.panel = panel;
	}
	
	public void run() //��ʼִ����Ϸ
	{
		Random r = new Random();//����һ�������
	
		while(this.panel.isRunning())
		{
			if(this.panel.isStop() == true)//�ж��Ƿ�����ͣ��Ϸ
			{
				//System.out.println("777");
				synchronized(MyPanel.subLock)
				{
						try
						{
							MyPanel.subLock.wait();//��������ļ��������
						}
						catch(Exception e)
						{
							e.printStackTrace();
							//this.flag = true;
							this.panel.endGame();
						}
				}
			}	
				Submarine sm = new Submarine(this.ship,this.panel);//����Ǳͧ�࣬����Ϸ������  ͬ������ʹ��Thread�������̵߳�ʹ�ã���������Ǳͧ
				MyPlane pl = new MyPlane(this.panel);//�ɻ��̣߳���������һ�ŷɻ�
				this.panel.getPlanes().add(pl);//��ӵ�����������
				this.panel.getSubmarineArray().add(sm);//ÿִ��һ�ξ��������������   ���Ѹ��������ִ��״̬���ŵ�ArrAyList����
				
				Thread t = new Thread(sm);//ԭ������Ϸ�����������̣߳���������Ǳͧ����ز���
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

	public void setSpeed(int speed) {//�����ٶ�
		this.speed = speed;
	}
}
