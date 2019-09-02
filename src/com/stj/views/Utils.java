package com.stj.views;

import java.util.Random;

/**
 * 创建工具类
 * @author 天宇科技
 *
 */
public class Utils {
	
	public Utils() {
	}
	
	public static Integer RandomUtilsa(Integer[] arr) {
		return arr[new Random().nextInt(arr.length)];
	}
}
