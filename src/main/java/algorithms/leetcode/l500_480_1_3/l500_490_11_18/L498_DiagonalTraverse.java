package algorithms.leetcode.l500_480_1_3.l500_490_11_18;

import java.util.Arrays;
import java.util.Stack;

public class L498_DiagonalTraverse {

// Given a matrix of M x N elements (M rows, N columns),
// return all elements of the matrix in diagonal order
// as shown in the below image.
//
//
//
// Example:
//
//
//Input:
//[
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
//]
//
//Output:  [1,2,4,7,5,3,6,8,9]
//
//Explanation:
//
//
//
//
//
// Note:
//
// The total number of elements of the given matrix will not exceed 10,000.
//


    public int[] findDiagonalOrder_s(int[][] matrix) {
        if(matrix==null) return null;
        if(matrix.length==1) return matrix[0];
        int r=0,c=0,row = matrix.length,col = matrix[0].length;
        int[] result = new int[row*col];
        for (int i = 0; i < row*col ; i++) {
            result[i]= matrix[r][c];
            if((c+r) %2 == 0){
                if(c+1 < col && r-1 >-1){
                    c++;
                    r--;
                }else if(c+1 < col && r-1 <0){
                    c++;
                }else if(c+1 >= col && r+1 < row){
                    r++;
                }
            }else if((c+r) % 2 == 1){
                if(r+1 < row && c-1 > -1){
                    r++;
                    c--;
                }else if (r+1 >= row && c+1 < col){
                    c++;
                }else if(r+1 < row && c-1 < 0){
                    r++;
                }
            }
        }
        return result;
    }

    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[0];
        int[] result = new int[matrix.length*matrix[0].length];
        int k = 0;
        int i = 0;
        boolean res = false;
        for (; i < matrix[0].length; i++) {
            int ii = i,jj =0;
            if (res){
                for (;ii>=0&&jj<matrix.length;jj++ ,ii-- ){
                    result[k] = matrix[jj][ii];
                    k++;
                }
                res = false;
            }else{
                Stack<Integer> tmp = new Stack<>();
                for (;ii>=0&&jj<matrix.length;jj++ ,ii-- ){
                    tmp.push(matrix[jj][ii]);
                }
                while (!tmp.isEmpty()){
                    result[k] =tmp.pop();
                    k++;
                }
                res = true;
            }

        }
        for (int j = 1; j < matrix.length; j++) {
            int ii = i-1,jj =j;
            if (res){
                for (;ii>=0&&jj<matrix.length;jj++ ,ii-- ){
                    result[k] = matrix[jj][ii];
                    k++;
                }
                res = false;
            }else{
                Stack<Integer> tmp = new Stack<>();
                for (;ii>=0&&jj<matrix.length;jj++ ,ii-- ){
                    tmp.push(matrix[jj][ii]);
                }
                while (!tmp.isEmpty()){
                    result[k] =tmp.pop();
                    k++;
                }
                res = true;
            }
        }
        return result;
    }

    /**
     * 我们需要英文描述自己的思路，然后才能够编码
     * */
    public int[] findDiagonalOrder_think(int[][] matrix) {
        if (matrix == null || matrix.length == 0){
            return new int[0];
        }

        //Idea is the following
        //notice all values in the same diagonal share the same sum value of x index + y index
        //direction of going up right or going down left depends whether the index sum is even or odd
        //for each even or odd diagonal, there are three cases:
        // 1. there is room to go that direction
        // 2. there is no row space to go further but there is col space
        // 3. there is no col space to go further but there is row space
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] res = new int[rows * cols];
        int r = 0;
        int c = 0;
        for (int i = 0; i < rows * cols; i++) {
            res[i] = matrix[r][c];
            // even sum diagonal
            if ((r + c) % 2 == 0) {
                if (r - 1 >= 0 && c + 1 < cols) {
                    c = c + 1;
                    r = r - 1;
                } else if (r - 1 < 0 && c + 1 < cols) {
                    c = c + 1;
                } else if (r + 1 < rows && c + 1 > cols - 1) {
                    r = r + 1;
                }
            }
            // odd sum diagonal
            else if ((r + c) % 2 != 0) {
                if (r + 1 < rows && c - 1 >= 0) {
                    c = c - 1;
                    r = r + 1;
                } else if (r + 1 < rows && c - 1 < 0) {
                    r = r + 1;
                } else if (r + 1 > rows - 1 && c +1 < cols) {
                    c = c + 1;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] arrays = new int[][]{
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5}
        };
        L498_DiagonalTraverse test = new L498_DiagonalTraverse();
        int[] v = test.findDiagonalOrder(arrays);
        System.out.println(Arrays.toString(v));

        v = test.findDiagonalOrder_s(arrays);
        System.out.println(Arrays.toString(v));

        arrays = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        v = test.findDiagonalOrder(arrays);
        System.out.println(Arrays.toString(v));
        v = test.findDiagonalOrder_s(arrays);
        System.out.println(Arrays.toString(v));

    }
}