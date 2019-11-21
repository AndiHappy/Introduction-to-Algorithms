package algorithms.leetcode.l500_490_11_18;

/*

You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2.
Find all the next greater numbers for nums1's elements in the corresponding places of nums2.



The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.


 Example 1:

Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.



 Example 2:

Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.




 Note:

 All elements in nums1 and nums2 are unique.
 The length of both nums1 and nums2 would not exceed 1000.

 Related Topics Stack
*/


import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class L496_Next_Greater_Element_I {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if(nums1==null || nums2 == null || nums1.length == 0 || nums2.length ==0) return new int[0];

        HashMap<Integer,Integer> tmp = new HashMap<>();
        for(int i = nums2.length-1;i>=0;i--){
            tmp.put(nums2[i],getNextGreaterElement(nums2,i));
        }

        for (int i = 0;i<nums1.length;i++){
            nums1[i] = tmp.getOrDefault(nums1[i],-1);
        }

        return nums1;
    }

    private Integer getNextGreaterElement(int[] nums2, int i) {
        for (int j = i+1;j < nums2.length;j++){
            if(nums2[j]>nums2[i]){
                return nums2[j];
            }
        }
        return -1;

    }

    /**
     * 参考下面的Related Topics Stack
     * 如果我们使用栈进行解决，进行思路的分析，我们的事件复杂度在于查找NextGreateElement，这个时间复杂度是O(n),我们又遍历了一遍n，那么
     * 事件复杂度就是O(N*N)
     *
     * 如果我们使用栈，我们重点的优化放在查找NextGreateElement，我们直接使用栈，把这个查找设置为O(1)
     *
     * */
    public int[] nextGreaterElement_stack(int[] nums1, int[] nums2) {
        if(nums1==null || nums2 == null || nums1.length == 0 || nums2.length ==0) return new int[0];

        HashMap<Integer,Integer> tmp = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num)
                tmp.put(stack.pop(), num);
            stack.push(num);
        }

        for (int i = 0;i<nums1.length;i++){
            nums1[i] = tmp.getOrDefault(nums1[i],-1);
        }

        return nums1;
    }


    public static void main(String[] args) {
        L496_Next_Greater_Element_I test = new L496_Next_Greater_Element_I();
        int[] res = test.nextGreaterElement_stack(new int[]{4,1,2},new int[] {1,3,4,2});
        System.out.println(Arrays.toString(res));

        res = test.nextGreaterElement_stack(new int[]{4,2},new int[] {1,2,3,2});
        System.out.println(Arrays.toString(res));
    }
}
