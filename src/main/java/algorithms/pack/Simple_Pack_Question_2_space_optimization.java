package algorithms.pack;

public class Simple_Pack_Question_2_space_optimization {

    /**
     * 再次的梳理一下，我们的实现，我们有一个主循环，i从0到N，我们设置的是F[i][v]的值，具体是怎么设置的尼
     * 当 f[i][v] = Math.max(f[i - 1][v], f[i - 1][v - c[i - 1]] + w[i - 1])
     *
     * 那么，如果只用一个数组 F[0...V ]，
     * 能不能保证第 i 次循环结束后 F[v] 中表示的就是我们定义的状态 F[i,v] 呢?
     * F[i,v] 是由 F[i − 1,v] 和 F [i − 1, v − Ci] 两个子问题递推而来，
     * 能否保证在推 F [i, v] 时(也即在第 i 次主循环中 推 F[v] 时)
     * 能够取用 F[i−1,v] 和 F[i−1,v−Ci] 的值呢?
     *
     * 其实我们“倒过来循环V的值“即可：
     *
     * 事实上，这要求在每次主循环中我们以 v ← V . . . 0 的递减顺序计算 F [v]，
     * 这样才 能保证计算 F[v] 时 F[v − Ci] 保存的是状态 F[i − 1,v − Ci] 的值。伪代码如下:
     F [0..V ] ←0
     for i ←1 to N
        for v ←V to Ci
           F[v] ←max{ F[v], F[v−Ci] + Wi }
     *
     * */
    public static int pack(int V, int[] c, int[] w) {

        int n = c.length;
        int[] f = new int[V + 1];
        for(int i=1;i<=n;i++)
            for(int v=V;v>=c[i-1];v--){
                f[v]=Math.max(f[v],f[v-c[i-1]]+w[i-1]);  //加跟不加
            }

        for (int i = 0; i < f.length; i++) {
            System.out.print(f[i] + " ");
        }

        return f[V];
    }

    public static void main(String[] args) {
        int[] c = new int[]{2, 3, 4, 5};
        int[] v = new int[]{3, 4, 5, 6};
        int value = pack(8, c, v);
        System.out.println(value);

    }
}
