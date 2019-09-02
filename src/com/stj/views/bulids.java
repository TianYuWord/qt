package com.stj.views;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * �ɻ��ӵ���
 * 
 * @author ����Ƽ�
 *
 */
//
public class bulids implements Runnable {
	private int disX = 0;
	private int disY = 0;
	private MyPlane plane;// ��ȡ���ɻ���
	private MyPanel panel;// ��ȡ��������
	private int position; // ս�����з��� 0 ���� 1����
	private boolean isStop;
	private int speed = 1;// �ӵ��������ٶ�
	private int bulidposition = 0;//�ӵ��� 0ˮƽ 1��ֱ ���з���
	private Image image;// �����ӵ�ͼƬ����
	private int width;
	private int height;
	public boolean isFlag = false;
	private int ispeed = 5;
	private int fanwei = 500;
	
	// �õ�������,�ɻ���
	public bulids(MyPanel panel, MyPlane plane) {
		this.plane = plane;
		this.panel = panel;

		this.disY = panel.getY();
		this.isStop = panel.isStop();
		this.position = plane.a; // ��ǰ�ɻ��ĵ�ǰ������ 0 Ϊ����� 1Ϊ���ұ�
		

		// ���ݷɻ��ķ����������ӵ����еķ���
		if (this.position == 0) {// ��������

			// �洢һ���ӵ����еĳ�ֵ

			this.disX = plane.getpX(); // ��ȡ��ǰս��������
			this.disY = plane.getpY();
			// ��� �����ӵ� , �ӵ������֣�һ�����������У�һ������������
			int temp = new Random().nextInt(2);
			if (temp == 0) { // �������е�ˮƽ�ӵ� -> �߳�һ -> �������� -> �ӵ���������
				this.bulidposition = 0;
				Image image = Toolkit.getDefaultToolkit().getImage("imgs/buildleft.png");
				this.image = new ImageIcon(image).getImage();
			}
			if (temp == 1) { // ��ֱ������ӵ� -> �̶߳� -> ֻ����һ���ӵ���������
				this.bulidposition = 1;
				Image image = Toolkit.getDefaultToolkit().getImage("imgs/ը��.png");
				this.image = new ImageIcon(image).getImage();
			}
		}
		
		// ���ݷɻ��ķ����������ӵ����еķ���
		if (this.position == 1) {// ��������

			// �洢һ���ӵ����еĳ�ֵ

			this.disX = plane.getpX(); // ��ȡ��ǰս��������
			this.disY = plane.getpY();

			// ��� �����ӵ� , �ӵ������֣�һ�����������У�һ������������
			int temp = new Random().nextInt(2);
			if (temp == 0) { // �������е�ˮƽ�ӵ� -> �߳�һ -> �������� -> �ӵ���������
				this.bulidposition = 0;
				Image image = Toolkit.getDefaultToolkit().getImage("imgs/buildright.png");
				this.image = new ImageIcon(image).getImage();
			}
			if (temp == 1) { // ��ֱ������ӵ� -> �̶߳� -> ֻ����һ���ӵ���������
				this.bulidposition = 1;
				Image image = Toolkit.getDefaultToolkit().getImage("imgs/ը��.png");
				this.image = new ImageIcon(image).getImage();
			}
		}

		this.width = this.image.getWidth(this.panel);
		this.height = this.image.getHeight(this.panel);
		
		Thread re = new Thread();//
		
	}


	// �ӵ��Ĵ�ֱ���з�ʽ
	public void Thread1() {// ���Զ����class
		if(this.disY > fanwei) {
			this.isFlag = true;
		}
		this.disY += this.speed;
//		System.out.println("��ֱ���е��ӵ�");
	}
	
	
	
	
	// �ӵ�������ˮƽ���з�ʽ
	public void Thread2() {// ���Զ����class
		if(this.disX < 0) {
			this.isFlag = true;
		}
//		System.out.println("ˮƽ�����е��ӵ�");
		this.disX -=10;
	}
	
	// �ӵ�������ˮƽ���з�ʽ
	public void Thread3() {// ���Զ����class
		if(this.disX > (this.panel.getWidth() - this.getWidth())) {
			this.isFlag = true;
		}
//		System.out.println("ˮƽ�������е��ӵ�");
		this.disX += 10;
	}
	
	@Override
	public void run() {
		while (!isFlag) {
			if(!this.panel.isSuspendFlag()) {//�Ƿ���ͣ
				
			}
			if(this.position == 0) {//��������
				if(this.bulidposition == 1) {//��ֱ����
					this.Thread1();
				}
				if(this.bulidposition == 0) {//ˮƽ����
					this.Thread2();
				}
			}
			
			if(this.position == 1) {//��������
				if(this.bulidposition == 1) {//��ֱ����
					this.Thread1();
				}
				if(this.bulidposition == 0) {//ˮƽ����
					this.Thread3();
				}
			}
			
			//ÿִ��ˢ��һ�������ʱ����ж�һ���û��Ƿ���ͣ
			if(this.panel.isStop()) {
				synchronized (this.panel.subLock) {//�������߳�
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
		// �����ⲿ�����������ݵķ���
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
