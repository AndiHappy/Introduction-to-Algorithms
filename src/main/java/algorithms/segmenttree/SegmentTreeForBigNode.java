package algorithms.segmenttree;

// 节点区间定义
// [start, end] 代表节点的区间范围
// max 是节点在(start,end)区间上的最大值
// left , right 是当前节点区间划分之后的左右节点区间
public class SegmentTreeForBigNode {
    // 节点存储的值
    public int start, end, max;
    public SegmentTreeForBigNode left, right;
    public SegmentTreeForBigNode(int start, int end, int max) {
        this.start = start;
        this.end = end;
        this.max = max;
        this.left = this.right = null;
    }
}