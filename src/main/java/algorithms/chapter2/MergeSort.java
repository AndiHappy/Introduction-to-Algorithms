package algorithms.chapter2;

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
	
	
	public static void main(String[] args) {
		
	}
}
