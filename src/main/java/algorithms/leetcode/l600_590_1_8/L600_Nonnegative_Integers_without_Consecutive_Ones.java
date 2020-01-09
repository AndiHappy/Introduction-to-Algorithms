package algorithms.leetcode.l600_590_1_8;

/*

Given a positive integer n, find the number of non-negative integers less than
 or equal to n, whose binary representations do NOT contain consecutive ones.

 Example 1:

Input: 5
Output: 5
Explanation:
Here are the non-negative integers <= 5 with their corresponding binary repres
entations:
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
Among them, only integer 3 disobeys the rule (two consecutive ones) and the ot
her 5 satisfy the rule.



 Note:
1 <= n <= 109

 Related Topics Dynamic Programming
*/
public class L600_Nonnegative_Integers_without_Consecutive_Ones {
    class Solution {

        public int findIntegers(int num) {
            StringBuilder sb = new StringBuilder(Integer.toBinaryString(num)).reverse();
            int n = sb.length();

            int a[] = new int[n];
            int b[] = new int[n];
            a[0] = b[0] = 1;

            for (int i = 1; i < n; i++) {
                a[i] = a[i - 1] + b[i - 1];
                b[i] = a[i - 1];
            }

            int result = a[n - 1] + b[n - 1];

            for (int i = n - 2; i >= 0; i--) {
                if (sb.charAt(i) == '1' && sb.charAt(i + 1) == '1') break;
                if (sb.charAt(i) == '0' && sb.charAt(i + 1) == '0') result -= b[i];
            }

            return result;
        }

        public int findIntegers_think(int num) {
            int[] f = new int[num+1];
            if(num==0) return 1;
            if(num==1) return 2;
            f[0] =1;
            f[1] =2;//0+1
            for (int i = 2; i<= num;i++){
                f[i] = isconsecutiveOnes(i)?1+f[i-1]:f[i-1];
            }
            return f[num];
        }

        Boolean isconsecutiveOnes(int num){
            boolean result = true;
            if(num <=1)return false;
            int tmp = num;int pre = 0;
            while(num != 0){
                int re = num%2;
                if(re==1&&pre==1){
                    result = false;
                }
                pre = re;
                num = num/2;
            }
            return  result;
        }
    }
}
