package algorithms.chapter2;

import algorithms.util.Util;

/**
 * @author guizhai
 *
 */
public class MergeSort {
	/**
	 * 	The merge sort algorithm closely follows the divide-and-conquer paradigm
	 */
	
/*
 Merge(A,p,q,r)
 1 n1 = q-p+1
 2 n2 = r-q
 3 Let L[1...n1+1] and R[1... n2+1] be new arrays
 4 for i=1 to n1
 5 		L[i] = A[p+i-1]
 6 for j=1 to n2
 7		R[j] = A[q+i]
 8 L[n1+1] = sentinel
 9 R[n2+1] = sentinel
10 i=1 j=1
11 for k=p to r
		if L[i] <= R[j]
				A[k]=L[i]
				i++
		else 
				A[k] = R[j]
 				j++
*/	
	public void merge(int[] a,int p,int q,int r ) {
		int n1 = q-p;
		int n2 = r-q+1;
		int[] larray = new int[n1+1];
		for (int i = 0; i < larray.length; i++) {
			larray[i] = a[p+i];
		}
		
		int[] rarray = new int[n2+1];
		for (int i = 0; i < rarray.length; i++) {
			rarray[i] = a[q+i];
		}
		larray[n1]=Integer.MAX_VALUE;
		rarray[n2]=Integer.MAX_VALUE;
		int i=0,j=0;
		for (int k = p; k <=r; k++) {
			if(larray[i] <= rarray[j]) {
				a[k] = larray[i];
				i++;
			}else {
				a[k]=rarray[j];
				j++;
			}
		}
	}

	private void merger_Recursive(int[] nums, int start, int end) {
		if (start >= end) return ;

		int mid = start + ((end - start) >> 1);
		merger_Recursive(nums, start, mid);
		merger_Recursive(nums, mid + 1, end);

		int firsti = start, sencondi = mid + 1, k = 0;
		// 合并两个数组的临时的变量
		int[] merge = new int[end - start + 1];

		while (firsti <= mid) {
			while (sencondi <= end && nums[firsti] >= nums[sencondi]){
				merge[k++] = nums[sencondi++];
			}
			merge[k++] = nums[firsti++];
		}

		while (sencondi <= end) merge[k++] = nums[sencondi++];
		System.arraycopy(merge, 0, nums, start, merge.length);
	}


	public static void main(String[] args) {
		MergeSort mergesort = new MergeSort();
		int[] a = new int[] {1,2,6,7,8,2,7,8,9};
		mergesort.merger_Recursive(a , 0, a.length-1);
		Util.printToConsole(a);
	}
}
