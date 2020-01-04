package algorithms.leetcode.l500_480_1_3.l500_490_11_18;

import java.util.*;

public class L491_Increasing_Subsequences {

/*
    Given an integer array,
    your task is to find all the different possible increasing subsequences of the given array,
    and the length of an increasing subsequence should be at least 2.



    Example:

    Input: [4, 6, 7, 7]
    Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]


    Note:

    The length of the given array will not exceed 15.
    The range of integer in the given array is [-100,100].
    The given array may contain duplicates, and two equal integers
    should also be considered as a special case of increasing sequence.

*/

public List<List<Integer>> findSubsequences( int[] nums){
    List<List<Integer>> result = new ArrayList<List<Integer>>();
//    findSubsequences(nums, result, new HashSet<Integer>(),new ArrayList<Integer>());
    return result;
}



    public List<List<Integer>> quanpailie(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        quanpailie(nums, result, new HashSet<Integer>(),new ArrayList<Integer>());
        return result;
    }

    private void quanpailie(int[] nums,  List<List<Integer>> result, Set<Integer>integers,List<Integer> res) {
        if (res.size() == nums.length) {
            result.add(new ArrayList<>(res));
            return;
        }else{
            for (int j = 0; j < nums.length; j++) {
                if(integers.contains(j)) continue;
                integers.add(j);
                res.add(nums[j]);
                quanpailie(nums, result, integers,res);
                integers.remove(j);
                res.remove(res.size()-1);
            }
        }

    }

    public static void main(String[] args) {

        L491_Increasing_Subsequences test = new L491_Increasing_Subsequences();
        List<List<Integer>> value = test.quanpailie(new int[]{1,2,3,4});
        System.out.println(value);
        System.out.println(value.size());
    }
}
