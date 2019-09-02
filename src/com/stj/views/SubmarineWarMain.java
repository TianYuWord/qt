package com.stj.views;

import java.util.Observable;

public class SubmarineWarMain
{

	public static void main(String[] args) 
	{
		Observable ob = new FrameObservable();//创建通知类
		MyFrame mf = new MyFrame(ob);//创建游戏的控制类
		mf.showMyFrame();//显示游戏的welcome界面
		
	}

}
