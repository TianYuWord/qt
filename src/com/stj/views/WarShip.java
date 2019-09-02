package com.stj.views;

import javax.swing.*;
import java.awt.*;

/*
 * 第一步初始化军舰
 * 战舰对象
 */

public class WarShip   
{
	
	//由分数来控制战舰的升级
	
	private int beginX ;  //初始位置和高度，宽度
	private int beginY ; 
	private int width ;
	private int height = 20;
	//private Color color = Color.GREEN;
	private int bombNum;  //炸弹数量
	private MyPanel panel; //游戏的开始界面类
	private Image image;  //图片对象
	private Integer iSpeed = 50; //战舰移动速渡
	public int i = 0;//设置初始战舰
	
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
	
	//选择战舰的时候触发   TODO
	//创建一个方法用于配置初始化潜艇的位置，在点击选择对应的战舰的时候被触发
	//传入画笔
	public void BeginXPosition() {
		switch(this.i) {
		 case 0:
			 this.beginY =  150 - this.getHeight();//设置战舰的初始化高度
			 image = Toolkit.getDefaultToolkit().getImage("imgs/战舰.png");//设置战舰图片
			 break;
		 case 1:
			 this.beginY =  150 - this.getHeight() - 50;//设置战舰的初始化高度
			 image = Toolkit.getDefaultToolkit().getImage("imgs/超级战舰");//设置战舰图片
			 break;
		 case 2:
			 this.beginY =  150 - this.getHeight() - 60;//设置战舰的初始化高度
			 image = Toolkit.getDefaultToolkit().getImage("imgs/无敌战舰.png");//设置战舰图片
			 System.out.println(image + "imgs/无敌战舰.png");
			 break;
		 }
		this.width = image.getWidth(this.panel);
		this.height = image.getHeight(this.panel);
		this.beginX = (panel.getWidth() - this.getWidth()) / 2;
	}
	
	public void drawShip(Graphics2D g)
	{	
		if(this.i == 0) { 
			image = Toolkit.getDefaultToolkit().getImage("imgs/战舰.png");//设置战舰图片
			image = new ImageIcon(image).getImage();//获取战舰图标
			g.drawImage(image,this.beginX ,this.beginY,this.panel);//构建图片
			System.out.println("");
		}else if(this.i == 1) {
			image = Toolkit.getDefaultToolkit().getImage("imgs/超级战舰.png");//设置战舰图片
			image = new ImageIcon(image).getImage();
			g.drawImage(image,this.beginX ,this.beginY,this.panel);
		}else if(this.i == 2) {
			image = Toolkit.getDefaultToolkit().getImage("imgs/无敌战舰.png");//设置战舰图片
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
