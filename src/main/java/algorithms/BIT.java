package algorithms;

/**
 * @author guizhai
 * 
 * binary-indexed-trees 数据结构
 *
 */
public class BIT {
	/**
	 * @param args
	 */
	int[] tree;
	int[] data;
	int MaxIdx;

	public BIT(int[] value) {
		this.data = value;
		this.MaxIdx = value.length;
		this.tree = caclueBinaryIndexTree(data);
	}

	private int[] caclueBinaryIndexTree(int[] data) {
		return null;
	}

	public int sum(int idx) {
		int sum = 0;
		while (idx > 0) {
			sum += tree[idx];
			idx -= (idx & -idx);
		}
		return sum;
	}

	public void update(int idx, int val) {
		data[idx] += idx;
		while (idx <= MaxIdx) {
			tree[idx] += val;
			idx += (idx & -idx);
		}
	}

	int readSingle(int idx) {
		int sum = tree[idx]; // this sum will be decreased
		if (idx > 0) { // the special case
			int z = idx - (idx & -idx);
			idx--; // idx is not important anymore, so instead y, you can use idx
			while (idx != z) { // at some iteration idx (y) will become z
				sum -= tree[idx];
				// substruct tree frequency which is between y and "the same path"
				idx -= (idx & -idx);
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
