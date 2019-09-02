package com.stj.views;

import java.util.Observable;
/*
 *监听者模式来监听游戏画面切换，从游戏进入画面到游戏主画面,用MyFrame对象监听MainPanel
 */

public class FrameObservable extends Observable
{
	//该方法会在update方法调用前被调用
	public void notifyObservers(Object arg)
	{
		/**
		 * 此方法监听所有的观察者对象
		 */
		//System.out.println("notify");
		this.setChanged();//设置标识符
		super.notifyObservers(arg);//arg为被观察的对象
	}
}
