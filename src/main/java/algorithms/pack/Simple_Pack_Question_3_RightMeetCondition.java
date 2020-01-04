package algorithms.pack;

import java.util.Arrays;

public class Simple_Pack_Question_3_RightMeetCondition {

    /**
     * 有的题目 要求“恰好装满背包”时的最优解，有的题目则并没有要求必须把背包装满。
     * 一种区别 这两种问法的实现方法是在初始化的时候有所不同。
     * <p>
     * 如果是第一种问法，要求恰好装满背包，那么在初始化时除了 F[0] 为 0，其它 F [1..V ] 均设为 −∞，
     * 这样就可以保证最终得到的 F [V ] 是一种恰好装满背包的最优解。
     * <p>
     * 如果并没有要求必须把背包装满，而是只希望价格尽量大，初始化时应该将 F [0..V ] 全部设为 0。
     * 这是为什么呢?可以这样理解:
     * <p>
     * 初始化的 F 数组事实上就是在没有任何物品可以放 入背包时的合法状态。
     * 如果要求背包恰好装满，那么此时只有容量为 0 的背包可以在什 么也不装且价值为 0 的情况下被“恰好装满”，
     * 其它容量的背包均没有合法的解，属于 未定义的状态，应该被赋值为 -∞ 了。如果背包并非必须被装满，
     * 那么任何容量的背包 都有一个合法解“什么都不装”，这个解的价值为 0，所以初始时状态的值也就全部为 0 了。
     */
    public static int pack_meetCondition(int V, int[] c, int[] w) {
        int n = c.length;
        int[] f = new int[V + 1];
        Arrays.fill(f, Integer.MIN_VALUE);
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int v = V; v >= c[i - 1]; v--) {
                if (f[v - c[i - 1]] >= 0) {
                    f[v] = Math.max(f[v], f[v - c[i - 1]] + w[i - 1]);  //加跟不加
                }
            }
            for (int j = 0; j < f.length; j++) {
                System.out.print(f[j] + " ");
            }
            System.out.println();
        }


        return f[V];
    }

    public static void main(String[] args) {
        int[] c = new int[]{2, 3, 4, 5, 8};
        int[] v = new int[]{3, 4, 5, 6, 10};
        int value = pack_meetCondition(30, c, v);
        System.out.println(value);

    }
}