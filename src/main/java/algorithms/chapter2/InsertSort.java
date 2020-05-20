package algorithms.chapter2;

import algorithms.util.Util;

/******************************************************************************
 *  插入排序，具体的伪代码如下：
 *  
 *  INSERTION-SORT(A)
 *  1 for j=2 to A:length
 *  2 key = A[j]
 *  3 // Insert A[j] 􏰀into the sorted sequence A[1....j-1]􏰀.
 *  4 i = j-1 // i sorted sequence length
 *  5 while i >0 and A[i]􏰀> key
 *  6 	A[i+1] = A[i􏰀]
 *  7 	i = i-1
 *  8 A[i+1] = key
 *
 * 循环不变式：At the start of each iteration of the for loop of lines 1–8, 
 * the subarray A[1...j-1] consists of the elements originally in A[1...j-1]􏰀, but in sorted order.
 ******************************************************************************/



/******************************************************************************
 *  2.1-4 具体的伪代码如下：
 *  假设数的存储是从低位开始的，可以简单的理解为1代表各位，2代表十位
 *  
 * C ADD_TWO_ARRAY(A,B)
 *  1 key = 0 // carry
 *  2 while i <= A:length and j <= A:length
 *  3 	C[i] = (A[i]+B[j]+key)%2;
 *  4		key = (A[i]+B[j]+key)/2;
 *  5 	i = i+1,j=j+1
 *  6 C[i+1] = key
 *  7 return C
 * 循环不变式：At the start of each iteration of the for loop of lines 1–8, 
 * the subarray A[1...j-1] consists of the elements originally in A[1...j-1]􏰀, but in sorted order.
 ******************************************************************************/

public class InsertSort {
	
	public static void insert_sort(final int[] a) {
		insert_sort(a, 0, a.length - 1);
	}

	/**
	 * @param a : input array
	 * @param i : from index
	 * @param j : end index
	 */
	private static void insert_sort(final int[] a, final int i, int j) {
		if (a == null || i == j)
			return;
		if (j > a.length - 1)
			j = a.length - 1;
		int insertIndex = i + 1;
		for (; insertIndex <= j; insertIndex++) {
			final int key = a[insertIndex];
			int insertcur = insertIndex - 1;
			while (insertcur >= i && a[insertcur] > key) {
				a[insertcur + 1] = a[insertcur];
				insertcur--;
			}
			a[insertcur + 1] = key;
		}
	}

	/**
	 * 实现的过程：a b 不一定是等长的数组
	 */
	public static int[] ADD_TWO_ARRAY(final int[] a, final int[] b) {
		int carry = 0;
		final int cl = a.length > b.length ? a.length : b.length;
		final int[] result = new int[cl];
		for (int i = 0, j = 0, k = 0; i < a.length || j < b.length; i++, j++, k++) {
			final int v = ((i < a.length ? a[i] : 0) + (j < b.length ? b[j] : 0) + carry) % 10;
			carry = ((i < a.length ? a[i] : 0) + (j < b.length ? b[j] : 0) + carry) / 10;
			result[k] = v;
		}
		if (carry == 0) {
			return result;
		} else {
			final int[] dest = new int[cl + 1];
			System.arraycopy(result, 0, dest, 0, cl);
			dest[cl] = carry;
			return dest;
		}
	}

	public static void main(final String[] args) {
		// int [] a = Util.randomIntArray();
		// Util.printToConsole(a);
		// insert_sort(a);
		// Util.printToConsole(a);
		//
		final int[] aa = Util.randomIntArray(10, 10);
		Util.printToConsole(aa);
		final int[] aa1 = Util.randomIntArray(10, 10);
		Util.printToConsole(aa1);
		System.out.println();
		Util.printToConsole(ADD_TWO_ARRAY(aa, aa1));
	}
	
}
