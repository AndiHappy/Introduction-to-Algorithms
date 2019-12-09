package algorithms.segmenttree;

public class Node {
    int val, cnt;// val 为节点值，cnt为比val大，或者和val一样大的节点数
    Node left, right; // 左节点（值比val大）右节点（值比val小）

    Node(int val) {
        this.val = val;
        this.cnt = 1;
    }

    private int search(Node root, long val) {
        if (root == null) {
            return 0;
        } else if (val == root.val) {
            return root.cnt;
        } else if (val < root.val) {
            return root.cnt + search(root.left, val);
        } else {
            return search(root.right, val);
        }
    }

    private Node insert(Node root, int val) {
        if (root == null) {
            root = new Node(val);
        } else if (val == root.val) {
            root.cnt++;
        } else if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.cnt++;
            root.right = insert(root.right, val);
        }

        return root;
    }

    public int reversePairs(int[] nums) {
        int res = 0;
        Node root = null;
        for (int ele : nums) {
            res += search(root, 2L * ele + 1);
            root = insert(root, ele);
        }
        return res;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        int[] array = new int[]{3,2,3,1};

        for (int tmp: array){
            root.insert(root,tmp);
        }

        System.out.println(root);

    }

}
