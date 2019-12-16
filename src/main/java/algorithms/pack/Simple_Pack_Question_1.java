package algorithms.pack;

public class Simple_Pack_Question_1 {

    /**
     F [i, v] 表示前 i 件物品恰放入一个容量为 v 的背包可以获得 的最大价值。
     for i ←1 to N
     for v ← Ci to V
     F[i,v] ← max{F[i − 1,v],F[i − 1,v − Ci] + Wi}
     * */
    public static int pack(int V, int[] c, int[] w) {

        int n = c.length;
        int[][] f = new int[n + 1][V + 1];
        for (int i = 1; i <= n; i++)
            for (int v = 1; v <= V; v++) {
                if (v - c[i - 1] >= 0) {
                    //可以考虑是否加入第i件物品
                    f[i][v] = Math.max(f[i - 1][v], f[i - 1][v - c[i - 1]] + w[i - 1]);  //加跟不加
                } else {
                    f[i][v] = f[i - 1][v];
                }

            }

        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[0].length; j++) {
                System.out.print(f[i][j] + " ");
            }
            System.out.println();
        }

        return f[n][V];
    }

    public static void main(String[] args) {
        int[] c = new int[]{2, 3, 4, 5};
        int[] v = new int[]{3, 4, 5, 6};
        int value = pack(8, c, v);
        System.out.println(value);

    }
}
