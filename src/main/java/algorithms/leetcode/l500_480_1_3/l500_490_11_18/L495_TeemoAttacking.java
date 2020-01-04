package algorithms.leetcode.l500_480_1_3.l500_490_11_18;


/*

In LOL world, there is a hero called Teemo and his attacking can make his enemy Ashe be in poisoned condition.

在游戏OL中，有一个英雄叫做Teemo，他的攻击的方式可以是他的敌人Ashe处于中毒的状态

Now, given the Teemo's attacking ascending time series towards Ashe
and the poisoning time duration per Teemo's attacking,
you need to output the total time that Ashe is in poisoned condition.

现在，提供Teemo 针对Asher攻击的升序的时间序列，以及每次Teemo攻击的持续时间
需要输出：Ashe处于中毒状态下的总时间

 You may assume that Teemo attacks at the very beginning of a specific time point,
 and makes Ashe be in poisoned condition immediately.

你可以假设Teemo在一个特殊的时间点攻击，攻击后，Ashe立即的处于中毒的状态。

 Example 1:


Input: [1,4], 2
Output: 4
Explanation: At time point 1, Teemo starts attacking Ashe and makes Ashe be poisoned immediately.
This poisoned status will last 2 seconds until the end of time point 2.
And at time point 4, Teemo attacks Ashe again, and causes Ashe to be in poisoned status for another 2 seconds.
So you finally need to output 4.




 Example 2:


Input: [1,2], 2
Output: 3
Explanation: At time point 1, Teemo starts attacking Ashe and makes Ashe be poisoned.
This poisoned status will last 2 seconds until the end of time point 2.
However, at the beginning of time point 2, Teemo attacks Ashe again who is already in poisoned status.
Since the poisoned status won't add up together, though the second poisoning attack will still work at time point 2,
it will stop at the end of time point 3.
So you finally need to output 3.




 Note:


 You may assume the length of given time series array won't exceed 10000.
 You may assume the numbers in the Teemo's attacking time series and his poisoning time duration
 per attacking are non-negative integers, which won't exceed 10,000,000.

 Related Topics Array
*/


//leetcode submit region begin(Prohibit modification and deletion)
public class L495_TeemoAttacking {

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0 || duration == 0) return 0;
        int sum = 0;
        for (int i = 0; i < timeSeries.length-1 ;i++){
            sum = sum + Math.min(duration,Math.max((timeSeries[i+1] - timeSeries[i]),0));
        }
        sum+=duration;
        return sum;

    }

    public static void main(String[] args) {
        L495_TeemoAttacking test = new L495_TeemoAttacking();
        int res = test.findPoisonedDuration(new int[]{1,4},2);
        System.out.println(res);

        res = test.findPoisonedDuration(new int[]{1,2},2);
        System.out.println(res);
    }
}
