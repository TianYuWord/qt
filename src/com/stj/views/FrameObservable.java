package com.stj.views;

import java.util.Observable;
/*
 *������ģʽ��������Ϸ�����л�������Ϸ���뻭�浽��Ϸ������,��MyFrame�������MainPanel
 */

public class FrameObservable extends Observable
{
	//�÷�������update��������ǰ������
	public void notifyObservers(Object arg)
	{
		/**
		 * �˷����������еĹ۲��߶���
		 */
		//System.out.println("notify");
		this.setChanged();//���ñ�ʶ��
		super.notifyObservers(arg);//argΪ���۲�Ķ���
	}
}
