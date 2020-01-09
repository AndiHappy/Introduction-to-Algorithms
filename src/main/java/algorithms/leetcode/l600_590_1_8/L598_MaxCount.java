package algorithms.leetcode.l600_590_1_8;

/*

Given an m * n matrix M initialized with all 0's and
several update operations.
Operations are represented by a 2D array, and each operation is represented
by an array with two positive integers a and b, which means M[i][j] should be
added by one for all 0 <= i < a and 0 <= j < b.

You need to count and return the number of maximum integers in the matrix
 after performing all the operations.

 Example 1:

Input:
m = 3, n = 3
operations = [[2,2],[3,3]]
Output: 4
Explanation:
Initially, M =
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]

After performing [2,2], M =
[[1, 1, 0],
 [1, 1, 0],
 [0, 0, 0]]

After performing [3,3], M =
[[2, 2, 1],
 [2, 2, 1],
 [1, 1, 1]]

So the maximum integer in M is 2, and there are four of it in M. So return 4.



 Note:

 The range of m and n is [1,40000].

 The range of a is [1,m], and the range of b is [1,n].

 The range of operations size won't exceed 10,000.

 Related Topics Math
*/

public class L598_MaxCount {

    public int maxCount(int m, int n, int[][] ops) {
        if (ops == null || ops.length == 0) return m * n;
        //The range of a is [1,m], and the range of b is [1,n].
        int[] pre = null;
        for (int i = 0; i < ops.length; i++) {
            int[] operator = ops[i];
            if(pre == null){
                pre = operator;
            }else{
                if(operator[0] <= pre[0]) pre[0] = operator[0];
                if(operator[1] <= pre[1]) pre[1] = operator[1];
            }
        }
        if(pre != null){
            return pre[0]*pre[1];
        }
        return -1;
    }
}
