package util;

import java.util.Arrays;
import java.util.Random;

/**
 * 辅助功能
 * */
public class Util {
	
	public static Random random = new Random();
	
	/**
	 * 随机生成一个数组：元素为0~50，个数为10
	 * */
	public static int[] randomIntArray() {
		return randomIntArray(10, 50);
	}
	
	public static int[] randomIntArray(int length,int bound) {
		int[] result = new int[length];
		for (int i = 0; i < result.length; i++) {
			result[i] = random.nextInt(bound);
		}
		return result;
	}

	/**
	 * 控制台直接的打印数组
	 * */
	public static void printToConsole(int[] a) {
		System.out.println(Arrays.toString(a));
	}
}
