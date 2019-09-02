package com.stj.views;

import javax.swing.*;
import java.awt.*;

/*
 * ��һ����ʼ������
 * ս������
 */

public class WarShip   
{
	
	//�ɷ���������ս��������
	
	private int beginX ;  //��ʼλ�ú͸߶ȣ����
	private int beginY ; 
	private int width ;
	private int height = 20;
	//private Color color = Color.GREEN;
	private int bombNum;  //ը������
	private MyPanel panel; //��Ϸ�Ŀ�ʼ������
	private Image image;  //ͼƬ����
	private Integer iSpeed = 50; //ս���ƶ��ٶ�
	public int i = 0;//���ó�ʼս��
	
	public WarShip(MyPanel panel)
	{
		 this.panel = panel;
		 
	}

	public int getBeginX() {
		return beginX;
	}

	public void setBeginX(int beginX) {
		this.beginX = beginX;
	}

	public int getBeginY() {
		return beginY;
	}

	public void setBeginY(int beginY) {
		this.beginY = beginY;
	}
	
	//ѡ��ս����ʱ�򴥷�   TODO
	//����һ�������������ó�ʼ��Ǳͧ��λ�ã��ڵ��ѡ���Ӧ��ս����ʱ�򱻴���
	//���뻭��
	public void BeginXPosition() {
		switch(this.i) {
		 case 0:
			 this.beginY =  150 - this.getHeight();//����ս���ĳ�ʼ���߶�
			 image = Toolkit.getDefaultToolkit().getImage("imgs/ս��.png");//����ս��ͼƬ
			 break;
		 case 1:
			 this.beginY =  150 - this.getHeight() - 50;//����ս���ĳ�ʼ���߶�
			 image = Toolkit.getDefaultToolkit().getImage("imgs/����ս��");//����ս��ͼƬ
			 break;
		 case 2:
			 this.beginY =  150 - this.getHeight() - 60;//����ս���ĳ�ʼ���߶�
			 image = Toolkit.getDefaultToolkit().getImage("imgs/�޵�ս��.png");//����ս��ͼƬ
			 System.out.println(image + "imgs/�޵�ս��.png");
			 break;
		 }
		this.width = image.getWidth(this.panel);
		this.height = image.getHeight(this.panel);
		this.beginX = (panel.getWidth() - this.getWidth()) / 2;
	}
	
	public void drawShip(Graphics2D g)
	{	
		if(this.i == 0) { 
			image = Toolkit.getDefaultToolkit().getImage("imgs/ս��.png");//����ս��ͼƬ
			image = new ImageIcon(image).getImage();//��ȡս��ͼ��
			g.drawImage(image,this.beginX ,this.beginY,this.panel);//����ͼƬ
			System.out.println("");
		}else if(this.i == 1) {
			image = Toolkit.getDefaultToolkit().getImage("imgs/����ս��.png");//����ս��ͼƬ
			image = new ImageIcon(image).getImage();
			g.drawImage(image,this.beginX ,this.beginY,this.panel);
		}else if(this.i == 2) {
			image = Toolkit.getDefaultToolkit().getImage("imgs/�޵�ս��.png");//����ս��ͼƬ
			image = new ImageIcon(image).getImage();
			g.drawImage(image,this.beginX ,this.beginY,this.panel);
		}
		this.width = image.getWidth(this.panel);
		this.height = image.getHeight(this.panel);
	}
	
	public void moveRight()
	{
		System.out.println("i\t" + i);
		this.beginX += iSpeed;
		if((this.beginX + this.width) > this.panel.getWidth())
		{
			System.out.println("panel:"+this.panel.getWidth());
			this.beginX = this.panel.getWidth() - this.width;
		}	
	}
	
	public void moveLeft()
	{
		System.out.println("i\t" + i);
		this.beginX -= iSpeed;
		if(this.beginX < 0)
		{
			this.beginX = 0;
		}
		
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

	public int getBombNum() {//
		return bombNum;
	}

	public void setBombNum(int bombNum) {
		this.bombNum = bombNum;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public MyPanel getPanel() {
		return panel;
	}

	public void setPanel(MyPanel panel) {
		this.panel = panel;
	}

	public Integer getiSpeed() {
		return iSpeed;
	}

	public void setiSpeed(Integer iSpeed) {
		this.iSpeed = iSpeed;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	
	

	
}
