package com.stj.views;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class leve implements Runnable {
	
	//�Զ������
	
	private MyPanel panel;
	private int evel = 0;
	private Image image = Toolkit.getDefaultToolkit().getImage("imgs/ս��.png");
	private int speed = 10;//�������ٶ� �� ��Ҫ���� �� 
	private int health = 3;//���������� �� ��ʱ������
	private int runbom = 6;//�����ĵ����� , ��ʱ������
	private int time = 0;//ʱ��Ĭ��Ϊ0   ÿ���Ӽ�1
	private bulids bulid; // ���������� �ӵ�Ч��
	private int diren;//���˵ĵȼ�
	private boolean flag = false;//�Ƿ����н���
	
	//��ʼ������
	public leve(MyPanel panel) {
		this.panel = panel;
	}
	
	//����ͼƬ�ķ���
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
