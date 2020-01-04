package algorithms.leetcode.l500_480_1_3.l500_490_11_18;

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


import java.util.Arrays;
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
     */
    public int findTargetSumWays(int[] nums, int s) {
        if (nums == null || nums.length == 0) return 0;
        int sum = 0;
        for (int i : nums) sum += i;
        if (s > sum || s < -sum) return 0;

        int res = findTargetSumWays(nums, nums.length - 1, s);
        return res;
    }

    private int findTargetSumWays(int[] nums, int i, int s) {
        if (i == -1 && (s == 0)) {
            return 1;
        } else if (i >= 0) {
            int v1 = findTargetSumWays(nums, i - 1, s - nums[i]);
            int v2 = findTargetSumWays(nums, i - 1, s + nums[i]);
            return v1 + v2;
        }

        return 0;

    }


    /**
     * 递归的缓存写法,中间出现了一个文件，想声明缓存为一个数组i 和 sum 来进行二维的缓存，但是
     * 具体的方程中 sum 为 负值，就不能作为数组的索引了，所以另外的想办法：采用map的数据结构
     *
     */
    public int findTargetSumWays_cache(int[] nums, int s) {
        if(nums == null || nums.length == 0) return 0;
        int sum = 0;
        for (int i : nums) sum += i;
        if (s > sum || s < -sum) return 0;

        Map<String,Integer> cache = new HashMap<>();
        int res = findTargetSumWays_cahce(nums,nums.length-1,s,cache);
        return res;
    }

    private int findTargetSumWays_cahce(int[] nums, int i, int s,Map<String,Integer> cache) {
        if(i== -1 && (s== 0)){
            return 1;
        }else if(i >=0){
            String key = i+"=>"+s;
            if(cache.containsKey(key)) return cache.get(key);
            int v1 = findTargetSumWays_cahce(nums,i-1,s-nums[i],cache);
            int v2 = findTargetSumWays_cahce(nums,i-1,s+nums[i],cache);
            int value = v1+v2;
            cache.put(key,value);
            return value;
        }else{
            return 0;
        }
    }

    /**
     * 然后才是DP的解决方案，我们从低到高的形式来构建
     * 解决的办法，s不是为负值的时候，不能使用数组吗？
     * 那么，我们就采用一种变通的方案：s+最大的和值，sum，保持一种一一对应的关系即可。
     * */
    public int findTargetSumWays_DP(int[] nums, int s) {
        int sum = 0;
        for(int i: nums) sum+=i;
        if(s>sum || s<-sum) return 0;
        int[] dp = new int[2*sum+1];
        dp[0+sum] = 1;
        // 首先是从低到高的构建，直接的循环构建DP的临时数组即可
        // 其实也就是dp[2*sum+1][0,,i,,nums[i] 格式的构建，更容易理解了
        for(int i = 0; i<nums.length; i++){
            int[] next = new int[2*sum+1];
            for(int k = 0; k<2*sum+1; k++){
                if(dp[k]!=0){
                    next[k + nums[i]] += dp[k];
                    next[k - nums[i]] += dp[k];
                }
            }
            dp = next;
        }
        return dp[sum+s];
    }

    /**
     * 采用二维数组的样式
     * */
    public int findTargetSumWays_DP_two_dimensional_array(int[] nums, int s) {
        int sum = 0;
        for(int i: nums) sum+=i;
        if(s>sum || s<-sum) return 0;

        //按照自己梳理出来的
        int[][] dp = new int[nums.length+1][2*sum+1];
        // dp 就是cache标记的数字
        // sum + nums[i] 就是第二维的数值，并且能保证不为0
        for (int[] tmp :dp){
            Arrays.fill(tmp,0);
        }

        dp[0][sum] = 1;
        for(int i = 0; i<nums.length; i++){
            for(int k = 0; k<2*sum+1; k++){
                if(dp[i][k] != 0){
                    dp[i+1][k+nums[i]] +=dp[i][k];
                    dp[i+1][k-nums[i]] += dp[i][k];
                }
            }

        }

        return dp[nums.length][sum+s];
    }

    public static void main(String[] args) {

        L494_Target_Sum test = new L494_Target_Sum();

        int res = test.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);
        int res1 = test.findTargetSumWays_cache(new int[]{1, 1, 1, 1, 1},3);
        int res2 = test.findTargetSumWays_DP_two_dimensional_array(new int[]{1, 1, 1, 1, 1},3);
        System.out.println(res + " "+ res1 + " "+ res2 );

        res = test.findTargetSumWays(new int[]{1, 1}, 0);
        res1 = test.findTargetSumWays_cache(new int[]{1, 1},0);
        res2 = test.findTargetSumWays_DP_two_dimensional_array(new int[]{1, 1},0);
        System.out.println(res + " "+ res1 + " "+ res2 );

        res = test.findTargetSumWays(new int[]{0,0,0,0,0,0,0,0,1}, 1);
        res1 = test.findTargetSumWays_cache(new int[]{0,0,0,0,0,0,0,0,1}, 1);
        res2 = test.findTargetSumWays_DP_two_dimensional_array(new int[]{0,0,0,0,0,0,0,0,1}, 1);
        System.out.println(res + " "+ res1 + " "+ res2 );

        res = test.findTargetSumWays(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, 0);
        res1 = test.findTargetSumWays_cache(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, 0);
        res2 = test.findTargetSumWays_DP_two_dimensional_array(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, 0);
        System.out.println(res + " "+ res1 + " "+ res2 );


//        res = test.findTargetSumWays(new int[]{1, 1, 1, 1, 1},5);
//        res1 = test.findTargetSumWays_cache(new int[]{1, 1, 1, 1, 1},4);
//        System.out.println(res + " "+ res1);
    }
}
