package algorithms.leetcode.l500_480_1_3.l500_490_11_18;

public class L493_Reverse_Pairs {
    /***
     *
     Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

     You need to return the number of important reverse pairs in the given array.

     Example1:

     Input: [1,3,2,3,1]
     Output: 2
     Example2:

     Input: [2,4,3,5,1]
     Output: 3
     Note:
     The length of the given array will not exceed 50,000.
     All the numbers in the input array are in the range of 32-bit integer.
     *
     */

    public int reversePairs(int[] nums) {
        return reversePairsSub(nums, 0, nums.length - 1);
    }

    private int reversePairsSub(int[] nums, int start, int end) {
        if (start >= end) return 0;

        int mid = start + ((end - start) >> 1);
        int res = reversePairsSub(nums, start, mid) + reversePairsSub(nums, mid + 1, end);

        int firsti = start, sencondi = mid + 1, k = 0, p = mid + 1;
        // 合并两个数组的临时的变量
        int[] merge = new int[end - start + 1];

        while (firsti <= mid) {
            while (p <= end && nums[firsti] > 2l* nums[p]) p++;
            res += p - (mid + 1);

            while (sencondi <= end && nums[firsti] >= nums[sencondi]){
                merge[k++] = nums[sencondi++];
            }
            merge[k++] = nums[firsti++];
        }

        while (sencondi <= end) merge[k++] = nums[sencondi++];

        System.arraycopy(merge, 0, nums, start, merge.length);

        return res;
    }

    public static void main(String[] args) {
        L493_Reverse_Pairs test = new L493_Reverse_Pairs();
        int value = test.reversePairs(new int[]{2147483647,2147483647,2147483647,2147483647,2147483647,2147483647});
        System.out.println(value);
    }
}
