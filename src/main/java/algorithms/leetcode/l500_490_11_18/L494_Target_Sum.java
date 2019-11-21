package algorithms.leetcode.l500_490_11_18;

/*

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

被给与一组非负整数，a1, a2, ..., an 和一个目标值S
现在有2个符号，可以插入数字之间

 Find out how many ways to assign symbols to make sum of integers equal to target S.

求取能够得到最终值S的方法总数

 Example 1:

Input: nums is [1, 1, 1, 1, 1], S is 3.
Output: 5
Explanation:

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.



 Note:

 The length of the given array is positive and will not exceed 20.
 The sum of elements in the given array will not exceed 1000.
 Your output answer is guaranteed to be fitted in a 32-bit integer.

 Related Topics Dynamic Programming Depth-first Search
*/


import java.util.HashMap;
import java.util.Map;

public class L494_Target_Sum {

    /*

    大眼看过去，first look，i will think about Dynamic Programming
    有想法，但是没有不能落实下来，编不了码啊。落实不了，还是没有梳理出来子问题和状态转移方程
    1. 我们要求的是 int[] 下相加等于target 的总数 f(s，n) 标识0。。。。n 下总和为s的总的方法数
    2. 我们继续梳理状递归的方法 ：
    f(s，n) = f(s-nums[n],n-1) + f(s+nums[n],n-1)
    f(0,0) = 1
    f(nums[0],1) =1
    f(-nums[0],1) =1

    3. 递归的方式梳理完成以后，加上缓存
    * */

    /**
     * 递归的方案
     * */
    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0) return 0;
        int res = findTargetSumWays(nums,nums.length-1,S);
        return res;
    }

    private int findTargetSumWays(int[] nums, int i, int s) {
        if(i==0 && (s== nums[0] || s == -nums[0])){
            return 1;
        }else if(i >0){
            int v1 = findTargetSumWays(nums,i-1,s-nums[i]);
            int v2 = findTargetSumWays(nums,i-1,s+nums[i]);
            int value = v1+v2;
            return value;
        }else{
            return 0;
        }

    }

    /**
     * 递归的缓存写法
     * */
    public int findTargetSumWays_cache(int[] nums, int S) {
        if(nums == null || nums.length == 0) return 0;
        int[][] cache = new int[nums.length][1000];
        int res = findTargetSumWays_cahce(nums,nums.length-1,S,cache);
        return res;
    }

    private int findTargetSumWays_cahce(int[] nums, int i, int s,int[][] cache) {
        if(i==0 && (s== nums[0] || s == -nums[0])){
            return 1;
        }else if(i >0){
            if(cache[i][s] != 0) return cache[i][s];
            int v1 = findTargetSumWays_cahce(nums,i-1,s-nums[i],cache);
            int v2 = findTargetSumWays_cahce(nums,i-1,s+nums[i],cache);
            int value = v1+v2;
            cache[i][s] = value;
            return value;
        }else{
            return 0;
        }

    }



    public static void main(String[] args) {

        L494_Target_Sum test = new L494_Target_Sum();

        int res = test.findTargetSumWays(new int[]{1, 1, 1, 1, 1},3);
        int res1 = test.findTargetSumWays_cache(new int[]{1, 1, 1, 1, 1},3);
        System.out.println(res + " "+ res1);

        res = test.findTargetSumWays(new int[]{1, 1, 1, 1, 1},4);
        res1 = test.findTargetSumWays_cache(new int[]{1, 1, 1, 1, 1},4);
        System.out.println(res + " "+ res1);
    }
}
