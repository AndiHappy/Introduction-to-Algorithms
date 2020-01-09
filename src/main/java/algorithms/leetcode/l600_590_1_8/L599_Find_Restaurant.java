package algorithms.leetcode.l600_590_1_8;

import java.util.ArrayList;
import java.util.Hashtable;

/*

Suppose Andy and Doris want to choose a restaurant for dinner, and they both h
ave a list of favorite restaurants represented by strings.


You need to help them find out their common interest with the least list index
 sum. If there is a choice tie between answers, output all of them with no order
 requirement. You could assume there always exists an answer.



 Example 1:

Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
Output: ["Shogun"]
Explanation: The only restaurant they both like is "Shogun".



 Example 2:

Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]
Output: ["Shogun"]
Explanation: The restaurant they both like and have the least index sum is "Sh
ogun" with index sum 1 (0+1).




 Note:

 The length of both lists will be in the range of [1, 1000].
 The length of strings in both lists will be in the range of [1, 30].
 The index is starting from 0 to the list length minus 1.
 No duplicates in both lists.

 Related Topics Hash Table
*/

public class L599_Find_Restaurant {

    public String[] findRestaurant(String[] list1, String[] list2) {
        Hashtable<String, Integer> value = new Hashtable<>();
        ArrayList<String> res = new ArrayList<>();
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < list1.length; i++) {
            value.put(list1[i], i);
        }
        for (int i = 0; i < list2.length; i++) {
            String t2 = list2[i];
            if (value.containsKey(t2) && value.get(t2) + i < result) {
                res.clear();
                res.add(t2);
                result = value.get(t2) + i;
            } else if (value.containsKey(t2) && value.get(t2) + i == result) {
                res.add(t2);
            }
        }
        return res.toArray(new String[0]);
    }
}
