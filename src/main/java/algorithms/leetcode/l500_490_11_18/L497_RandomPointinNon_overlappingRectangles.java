package algorithms.leetcode.l500_490_11_18;

import java.util.Random;
import java.util.TreeMap;

public class L497_RandomPointinNon_overlappingRectangles {



/*
 Given a list of non-overlapping axis-aligned rectangles rects,
    不重复的轴对齐矩形
 write a function pick which randomly and uniformily picks an integer point
 in the space covered by the rectangles.
    随机的均匀的挑选矩形中的点

 Note:


 An integer point is a point that has integer coordinates 坐标.
 A point on the perimeter of a rectangle is included in the space covered by the rectangles.
 ith rectangle = rects[i] = [x1,y1,x2,y2], where [x1, y1] are the
 integer coordinates of the bottom-left corner,
 and [x2, y2] are the integer coordinates of the top-right corner.
 length and width of each rectangle does not exceed 2000.
 1 <= rects.length <= 100
 pick return a point as an array of integer coordinates [p_x, p_y]
 pick is called at most 10000 times.



 Example 1:


Input:
["Solution","pick","pick","pick"]
[[[[1,1,5,5]]],[],[],[]]
Output:
[null,[4,1],[4,1],[3,3]]



 Example 2:


Input:
["Solution","pick","pick","pick","pick","pick"]
[[[[-2,-2,-1,-1],[1,0,3,0]]],[],[],[],[],[]]
Output:
[null,[-1,-2],[2,0],[-2,-1],[3,0],[-2,-2]]



 Explanation of Input Syntax:

 The input is two lists: the subroutines called and their arguments.
 Solution's constructor has one argument, the array of rectangles rects.
 pick has no arguments. Arguments are always wrapped with a list, even if there aren't any.



 题目没有看懂，这真是一个悲剧：


 Related Topics Binary Search Random
*/

    /**
     * @param areaSum: accumulate sum of every rectangles
     * @param treeMap: store current rectangle area sum as key, current rectangle index as value
     * @param rand: random seed, in order to random an integer number between 0 and total area sum
     * @param myRects: store original rects array, for the pick() function
     * */
    TreeMap<Integer, Integer> treeMap;
    Random rand;
    int areaSum;
    int[][] myRects;

    /**
     * The core strategy is that we pick a rectangle randomly, and then pick a point from that rectangle randomly.
     * Step 1: We can calculate area of each rectangle, and then accumulate their areas, put into the treeMap with
     * corresponding index.
     * Step 2: pick a random number from 0 to total areaSum, and then use binary search to find the position of rectangle
     * with random areaSum (by using treeMap to find ceilingKey, we can do it in O(lgn) time)
     * Step 3: after picking a random rectangle, we can randomly pick a point by randomly picking its x value and y value
     * in the picked rectangle
     * */
    public L497_RandomPointinNon_overlappingRectangles(int[][] rects) {
        treeMap = new TreeMap<>();
        rand = new Random();
        myRects = rects;

        /* add weight for each rectangle by their area
           WARNING: we need to add 1 for every length and width, because number of points if one greater then value of size
           eg: if len = 2, then there exist max number of points = 3 on this line */
        int rectIndex = 0;
        for (int[] rect : rects) {
            areaSum += (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
            treeMap.put(areaSum, rectIndex);
            rectIndex++;
        }
    }

    /* pick a rectangle, and then pick a point in this rectangle */
    public int[] pick() {
        // ceilingKey returns the least key greater than or equal to the given key, or null if there is no such key.
        // WARNING: since nextInt() API is open bracket for the right bound, we need to add 1 for areaSum

        int randomNum = treeMap.ceilingKey(rand.nextInt(areaSum + 1));
        int[] curRect = myRects[treeMap.get(randomNum)];
        int leftBound = curRect[0];
        int rightBound = curRect[2];
        int bottomBound = curRect[1];
        int topBound = curRect[3];

        // calculate the range of length and range of width, and then random pick an x value and a y value in the range
        int pointX = leftBound + rand.nextInt(rightBound - leftBound + 1);
        int pointY = bottomBound + rand.nextInt(topBound - bottomBound + 1);
        return new int[]{pointX, pointY};
    }
}
