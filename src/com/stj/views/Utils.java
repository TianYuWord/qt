package com.stj.views;

import java.util.Random;

/**
 * ����������
 * @author ����Ƽ�
 *
 */
public class Utils {
	
	public Utils() {
	}
	
	public static Integer RandomUtilsa(Integer[] arr) {
		return arr[new Random().nextInt(arr.length)];
	}
}
