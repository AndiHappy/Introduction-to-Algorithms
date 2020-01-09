package algorithms.chapter2;

import array.Util;

/**
 * @author guizhai
 *
 */
public class SelectSort {
	/**
	 * 习题2.2-2 
	 * Consider sorting n numbers stored in array A by first finding the smallest element of A 
	 * and exchanging it with the element in A[1]􏰀. Then find the second smallest element of A, 
	 * and exchange it with A[2]􏰀. 
	 * Continue in this manner for the first n 􏰐 1 elements of A. 
	 * Write pseudocode for this algorithm, which is known as selection sort. 
	 * What loop invariant does this algorithm maintain? 
	 * Why does it need to run for only the first n-1 elements,
	 *  rather than for all n elements? 
	 *  Give the best-case and worst-case running times of selection sort in ‚theta-notation.
	 */
	
/*
 pseudocode:
	SELECT_SORT(A)
  1. for i=1 TO A.length-1
  2. 		smallKey = i // form i+1 to A.length smallest value`s index;
  3.		fromIndex = i;
  3.		while(fromIndex <= A.length)
  4.    		if(A[fromIndex] < A[smallKey]
  5.						smallKey=fromIndex
  6.		if i != smallKey
  7.			A[i]<->A[smallKey]
  
  loop invariant: a[1...i] be sorted value array. value is in a
  theta n2 
*/	
	
	public static void select_sort(int[] a) {
		if(a == null || a.length <= 1) return;
		for (int i = 0; i < a.length-1; i++) {
			int smallKey = i;int fromkey = i;
			while(fromkey < a.length) {
				if(a[fromkey] < a[smallKey])
					smallKey = fromkey;
				fromkey++;
			}
			if( i != smallKey)
				Util.swap(a,i,smallKey);
		}
	}
	
	public static void main(String[] args) {
		int[] arrays = Util.randomIntArray();
		Util.printToConsole(arrays);
		select_sort(arrays);
		Util.printToConsole(arrays);

	}
}
