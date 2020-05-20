package algorithms.leetcode.l600_590_1_8;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class L594_Find_LHS {

/*

We define a harmounious array as an array
where the difference between its max
imum value and its minimum value is exactly 1.

 Now, given an integer array, you need to find the length of its longest
 harmonious subsequence among all its possible subsequences.

 Example 1:


Input: [1,3,2,2,5,2,3,7]
Output: 5
Explanation: The longest harmonious subsequence is [3,2,2,2,3].




 Note: The length of the input array will not exceed 20,000.
 Related Topics Hash Table
*/

    public int findLHS(int[] nums) {
        if(nums==null || nums.length ==0) return 0;
        HashMap<Integer,Integer> res = new HashMap<Integer, Integer>();
        for (int tmp: nums ) {
            res.put(tmp,res.getOrDefault(tmp,0)+1);
        }

        int restmp = 0;
        for (int key:res.keySet()) {
            if(res.containsKey(key+1)){
                restmp = Math.max(restmp,res.get(key)+res.get(key+1));
            }
        }

        return restmp;
    }

//    @Test
//    public void TestInputNull(){
//        Assert.assertEquals(0,findLHS(null));
//    }

    @Test
    public void TestForm(){
        Assert.assertEquals(7,findLHS(new int[]{1,3,2,2,1,3,3,5,2,3,7}));
        Assert.assertEquals(0,findLHS(new int[]{1,1,1,1}));
        Assert.assertEquals(4,findLHS(new int[]{1,1,1,0}));
        Assert.assertEquals(4,findLHS(new int[]{1,1,1,0,2}));
        Assert.assertEquals(20,findLHS(new int[]{2,2,2,2,2,2,2,3,1,0,0,0,3,1,-1,0,1,1,0,0,1,1,2,2,2,0,1,2,2,3,2}));

    }

}
