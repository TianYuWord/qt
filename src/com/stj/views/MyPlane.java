package com.stj.views;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *  �ɻ���
 * @author ����Ƽ�
 *
 */
//���಻��Ҫ���У�ֻ����ɶ�Ӧ��ͼƬ������   -> ʵ�ַɻ���������������ƶ�
public class MyPlane implements Runnable { // ���зɻ����� ʵ�������ƶ�
	private int pX;							//�ɻ���X����
	private int pY;							//�ɻ���y����
	private boolean isSrop;					//�Ƿ�ֹͣ
	private boolean isStart;				//�Ƿ�������״̬
	private MyPanel plane;					//������  ������
	private Image image;					//�ɻ���ͼƬ
	private int width;						//�ɻ��Ŀ��
	private int height;						//�ɻ��ĸ߶�
	private boolean flag = false;  			//����÷ɻ��Ƿ�����
	private int posation = 0;				//ս�����з���
	private Random r = new Random();
	public int a = 0;						//���÷��� 0 Ϊ����� 1Ϊ���ұ�
	private int speed = 1;					//�ɻ����ƶ��ٶ�
	private bulids bulid;					//��ʼ���ӵ���
	
	//���뻭�壬���л���
	public MyPlane(MyPanel planel) {
		this.plane = planel;				//���滭����
		this.pY = r.nextInt(120);			//��յĸ߶ȵĸ߶�Ϊ150
		//����������ҵķ���
		this.a = r.nextInt(2);
		if(a == 0) {//����ߵķɻ�
			this.pX = this.plane.getWidth() - this.getWidth();
			int c = new Random().nextInt(2);
//			System.out.println(Thread.currentThread().getName() + "��ǰ����߷ɵķɻ������" + c + "��ʼֵX��" + this.pX);
			if(c == 0) {
				Image image = Toolkit.getDefaultToolkit().getImage("imgs/leftplay.png");//����ɻ�ͼƬ
				this.image = new ImageIcon(image).getImage();
			}
			if(c == 1) {
				Image image = Toolkit.getDefaultToolkit().getImage("imgs/leftplay.png");//����ɻ�ͼƬ
				this.image = new ImageIcon(image).getImage();
			}
		}
		
		
		if(a == 1) {//���ұߵķɻ�
			this.pX = 0;//���ó�ʼ��ʱ���λ��
			int c = new Random().nextInt(2);
//			System.out.println(Thread.currentThread().getName() + "��ǰ���ұ߷ɵķɻ������" + c + "��ʼֵX��" + this.pX);
//			System.out.println(new File("imgs/plane.png").isFile());
//			System.out.println(c + "c");
			if(c == 0) {
				Image image = Toolkit.getDefaultToolkit().getImage("imgs/ringhtplay.png");//����ɻ�ͼƬ
				this.image = new ImageIcon(image).getImage();
			}
			if(c == 1) {
				Image image = Toolkit.getDefaultToolkit().getImage("imgs/ringhtplay.png");//����ɻ�ͼƬ  
				this.image = new ImageIcon(image).getImage();
			}
		}
//		System.out.println(this.image);
		this.image = new ImageIcon(this.image).getImage();
		
		this.height = this.image.getHeight(plane);//�ӻ��������ȡͼƬ�ĸ߶�
		this.width = this.image.getWidth(plane);//�ӻ��������ȡͼƬ�Ŀ��
		
		//�������Y�������
		
//		System.out.println("������ɵķɻ�����" + this.pY);
	}
	
	//��������ӵ�, ����� , ��������
	public void bulidddd() {
		int temp = new Random().nextInt(this.plane.getWidth() - this.getWidth());//�ڻ���ָ���ĳ��������ʼ�ӵ�
		if(this.getpX() == temp) {
			this.bulid = new bulids(this.plane,this);
			this.plane.getBulids().add(bulid);//���ӵ�����ӵ�����֮�У����߳̾ͻ���л���
			Thread t = new Thread(bulid);
			t.start();
		}
	}
	
	//�ɻ����ҷ�
	public void rightMove() {
		if(!flag) {
			if(this.pX > this.plane.getWidth()) {
				flag = true;//�������棬�ɻ�������
			}
			this.bulidddd();
			this.pX += this.speed;
		}
	}
	
	//�ɻ������
	public void leftMove() {
		if(!flag) {//���÷�������
			if(this.pX < 0) {
				flag = true;//��������ɻ������ˣ��ⲿ��ֱ��ɾ������
			}
			this.bulidddd();
			this.pX -= this.speed;
		}
	}
	
	//����ͼƬ����ӵ�
	
	public void bulid() {
		
	}
	
	@Override
	public void run() {
		//������Ϸɻ�ͼƬ�Ժ���д���
		while(!flag) {
			if(this.a == 0) {//�ɻ�����ɣ�Ҳ���� ��ǰx�����겻�� - speed ����
				this.leftMove();
			}
			if(this.a == 1) {//f�ɻ����ҷ�
				this.rightMove();
			}
			//ÿִ��ˢ��һ�������ʱ����ж�һ���û��Ƿ���ͣ
			if(this.plane.isStop())//�ж���Ϸ��ǰ�Ƿ���������
			{
				synchronized(MyPanel.subLock)//�������߳̽�������
				{
					try
					{
						MyPanel.subLock.wait();
					}
					catch(Exception e)
					{
						e.printStackTrace();
						this.flag = true;
					}
				}

			}
			try {
				Thread.sleep(10);//ʮ����ִ��һ��
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.flag = true;
			}
		}
	}
	//����ͼƬ
	public void drawImage(Graphics2D g2) {
		g2.drawImage(this.image, this.pX, this.pY,this.plane);//�ⲿ�Ļ����̻߳᲻�ϵ�ˢ�������ͼ���Ѵﵽ�������ݵ�Ч��
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


	
	
	public int getpX() {
		return pX;
	}

	public void setpX(int pX) {
		this.pX = pX;
	}

	public int getpY() {
		return pY;
	}

	public void setpY(int pY) {
		this.pY = pY;
	}

	public boolean isSrop() {
		return isSrop;
	}

	public void setSrop(boolean isSrop) {
		this.isSrop = isSrop;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public MyPanel getPlane() {
		return plane;
	}

	public void setPlane(MyPanel plane) {
		this.plane = plane;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getPosation() {
		return posation;
	}

	public void setPosation(int posation) {
		this.posation = posation;
	}
	
}

