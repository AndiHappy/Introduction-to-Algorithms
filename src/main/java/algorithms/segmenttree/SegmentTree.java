package algorithms.segmenttree;

/**
 Let us consider the following problem to understand Segment Trees.

 We have an array arr[0 . . . n-1]. We should be able to

 1 Find the sum of elements from index l to r where 0 <= l <= r <= n-1

 2 Change value of a specified element of the array to a new value x.
 We need to do arr[i] = x where 0 <= i <= n-1.

 给定一个数组，我们有两个操作：计算sum(i,j) 和 update(i,value)


 A simple solution is to run a loop from l to r and calculate the sum of elements in the given range.
 To update a value, simply do arr[i] = x. The first operation takes O(n) time and the second operation
 takes O(1) time.

 O(n) 的 sum(i,j){
        for k = i to j
            sum+= arr[k];
        return sum;
 }

 O(1) 的update(i,value){
    do arr[i] = value
 }

 Another solution is to create another array and store sum from start to i at the ith index in this array.
 The sum of a given range can now be calculated in O(1) time, but update operation takes O(n) time now.
 This works well if the number of query operations is large and very few updates.

 另外的一种办法，是偏向于记录一个sum(0,i)的临时数组，sum(i,j) = sum(0,j)-sum(0,i)
 不过更新数据的时候，需要O(n)来更新临时数组

 What if the number of query and updates are equal? Can we perform both the operations in O(log n)
 time once given the array? We can use a Segment Tree to do both operations in O(Logn) time.


 Representation of Segment trees
 1. Leaf Nodes are the elements of the input array.
 2. Each internal node represents some merging of the leaf nodes.

 折中的方案，也要临时的数组，但是临时的数组的数量是logn的方式，来组织数据。

 The merging may be different for different problems.
 For this problem, merging is sum of leaves under a node.

 An array representation of tree is used to represent Segment Trees.
 For each node at index i, the left child is at index 2*i+1, right child at 2*i+2
 and the parent is at [i-1]/2

 类似于堆的结构来组织数据，这里面有一个很好玩的概念，我们存储的是是什么：The merging may be different for different problems
 针对这个题目，我们存储的是sum值，如果其他的题目，我们存储的东西，可能是不一样的，这点可以说为以后的扩展
 提供了良好的基础。

 How does above segment tree look in memory?

 Like Heap, the segment tree is also represented as an array.
 The difference here is, it is not a complete binary tree.
 It is rather a full binary tree (every node has 0 or 2 children)
 and all levels are filled except possibly the last level.

 Unlike Heap, the last level may have gaps between nodes.
 Below are the values in the segment tree array for the above diagram.

 Below is memory representation of segment tree for input array {1, 3, 5, 7, 9, 11}
 st[] = {36, 9, 27, 4, 5, 16, 11, 1, 3, DUMMY, DUMMY, 7, 9, DUMMY, DUMMY}

 数据组织方式类似堆的数据结构，不是一个完整的二叉树。

 The DUMMY values are never accessed and have no use.
 This is some wastage of space due to simple array representation.
 We may optimize this wastage using some clever implementations,
 but code for sum and update becomes more complex.

 Construction of Segment Tree from given array
 We start with a segment arr[0 . . . n-1]. and
 every time we divide the current segment into two halves(if it has not yet become a segment of length 1),
 and then call the same procedure on both halves, and for each such segment,
 we store the sum in the corresponding node.
 All levels of the constructed segment tree will be completely filled except the last level.
 Also, the tree will be a Full Binary Tree because we always divide segments in two halves at every level.
 Since the constructed tree is always a full binary tree with n leaves,
 there will be n-1 internal nodes. So the total number of nodes will be 2*n – 1.
 Note that this does not include dummy nodes.

 在构建的时候，声明的array是2*n – 1




 * */
// Java Program to show segment tree operations like construction,
// query and update
public class SegmentTree
{
    // 此问题是为了描述保持最大值
    public SegmentTreeForBigNode build(int[] A) {
        return buildhelper(0, A.length - 1, A);
    }
    // 该代码实现是为了保存某一个区间的最大值
    public SegmentTreeForBigNode buildhelper(int left, int right, int[] A){
        if(left > right){
            return null;
        }
        // 根据节点区间的左边界的序列值为节点赋初值
        SegmentTreeForBigNode root = new SegmentTreeForBigNode(left, right, A[left]);
        if(left == right){
            return root; // 如果左边界和右边界相等,节点左边界的序列值就是线段树节点的接节点值
        }
        int mid = (left + right) / 2; // 划分当前区间的左右区间
        root.left = buildhelper(left, mid, A);
        root.right = buildhelper(mid + 1, right, A);
        root.max = Math.max(root.left.max, root.right.max); // 根据节点区间的左右区间的节点值得到当前节点的节点值
        return root;
    }

    // 单点更新的代码及注释
    public void modify(SegmentTreeForBigNode root, int index, int value) {
        // write your code here
        if(root.start == root.end && root.start == index) { // 找到被改动的叶子节点
            root.max = value; // 改变value值
            return ;
        }
        int mid = (root.start + root.end) / 2; // 将当前节点区间分割为2个区间的分割线
        if(index <= mid){ // 如果index在当前节点的左边
            modify(root.left, index, value); // 递归操作
            root.max = Math.max(root.right.max, root.left.max); // 可能对当前节点的影响
        }
        else {            // 如果index在当前节点的右边
            modify(root.right, index, value); // 递归操作
            root.max = Math.max(root.left.max, root.right.max); // 可能对当前节点的影响
        }
        return ;
    }

    // 区间查询的代码及注释
    public int query(SegmentTreeForBigNode root, int start, int end) {
        if (start <= root.start && root.end <= end) {
            // 如果查询区间在当前节点的区间之内,直接输出结果
            return root.max;
        }
        int mid = (root.start + root.end) / 2; // 将当前节点区间分割为左右2个区间的分割线
        int ans = Integer.MIN_VALUE; // 给结果赋初值
        if (mid >= start) {   // 如果查询区间和左边节点区间有交集,则寻找查询区间在左边区间上的最大值
            ans = Math.max(ans, query(root.left, start, end));
        }
        if (mid + 1 <= end) { // 如果查询区间和右边节点区间有交集,则寻找查询区间在右边区间上的最大值
            ans = Math.max(ans, query(root.right, start, end));
        }
        return ans; // 返回查询结果
    }

}

