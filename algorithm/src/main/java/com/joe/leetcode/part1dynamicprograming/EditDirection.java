package com.joe.leetcode.part1dynamicprograming;

import java.util.Arrays;

/**
 * 72. 编辑距离
 *
 * @author ckh
 * @create 11/5/20 9:54 AM
 */
public class EditDirection {


    char[] s1;
    char[] s2;

    public int minDistance(String word1, String word2) {
        s1 = word1.toCharArray();
        s2 = word2.toCharArray();

//        return dp1(s1.length-1,s2.length-1);
//        return dp2();
//        return dp3(s1.length, s2.length);
        Node node = dp4(s1.length, s2.length);
        return node.step;

    }

    /**
     * dp1 和 dp2 都是用dp函数做的, 这次用 dp数组做
     * 初始值不太好理解, dp[i][j] 的定义和dp函数的定义相同
     */
    private int dp3(int m, int n) {

        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }

        // 自底向上求解
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1));
                }

        return dp[m][n];
    }

    /**
     * dp1 超时, 存在大量重复计算, 使用 备忘录优化
     */
    private int dp2() {
        // 备忘录
        int[][] buff = new int[s1.length][s2.length];
        for (int[] ints : buff) {
            // 用最大值填充, 因为题目说了s1,s2长度最大为500 , 所以这里用600,也可以用Integer.MaxValue
            Arrays.fill(ints, 600);
        }

        return dpMethod2(s1.length - 1, s2.length - 1, buff);
    }

    /**
     * 使用备忘录优化重复计算
     */
    private int dpMethod2(int i, int j, int[][] buff) {
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;
        if (buff[i][j] != 600) return buff[i][j];

        if (s1[i] == s2[j]) {
            buff[i][j] = dpMethod2(i - 1, j - 1, buff);
        } else {
            buff[i][j] = Math.min(dpMethod2(i, j - 1, buff) + 1, Math.min(dpMethod2(i - 1, j, buff) + 1, dpMethod2(i - 1, j - 1, buff) + 1));
        }

        System.out.println(Arrays.deepToString(buff));
        return buff[i][j];
    }

    /**
     * 用 dp 函数计算
     * 返回 s1[0..i] 和 s2[0..j] 的最小编辑距离
     * s1 -> s2 的操作数 == s2 -> s1
     * 本函数是 s1 -> s2
     * <p>
     * 存在重复计算, 超时
     */
    int dp1(int i, int j) {
        // 如果 s1 先走完, 则把 s2 剩下的字符全部插入, 需要 j+1 步
        // 如果 s2 先走完, 则把 s1 剩下的字符全部删除, 需要 i+1 步
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;

        if (s1[i] == s2[j]) {
            // 该位相等则跳过该位
            return dp1(i - 1, j - 1);
        } else {
            // 有 3 种选择,
            // 插入: dp(i, j - 1), 在 s1 插入了一个字符, 则与 j 位相同, j 前移.
            // 删除: dp(i - 1, j), 在 s1 删除了一个字符, i 前移.
            // 修改: dp(i - 1, j - 1)
            // 返回3种中最小值
            return Math.min(dp1(i, j - 1) + 1, Math.min(dp1(i - 1, j) + 1, dp1(i - 1, j - 1) + 1));
        }
    }



    /*
        在计算距离的同时, 保存操作
     */

    static class Node {
        int step;
        // 1: 跳过 2: 插入 3:修改 4:删除
        Choice choice;

        public Node(int step, Choice choice) {
            this.step = step;
            this.choice = choice;
        }

        public Node(int step) {
            this.step = step;
        }

        @Override
        public String toString() {
            return "{" +
                    "step=" + step +
                    ", choice=" + choice +
                    '}';
        }
    }

    enum Choice {
        SKIP(1),
        INSERT(2),
        EDIT(3),
        DELETE(4);

        int val;

        Choice(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }

    public Node dp4(int m, int n) {
        Node[][] dp = new Node[m + 1][n + 1];
        dp[0][0] = new Node(0);

        // base case
        for (int i = 1; i <= m; i++) {
            dp[i][0] = new Node(i, Choice.DELETE);
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = new Node(j, Choice.INSERT);
        }

        // 自底向上求解
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = new Node(dp[i - 1][j - 1].step, Choice.SKIP);
                } else {
                    int delStep = dp[i - 1][j].step;
                    int insertStep = dp[i][j - 1].step;
                    int editStep = dp[i - 1][j - 1].step;
                    int min = Math.min(delStep, Math.min(insertStep, editStep));

                    if (min == delStep) {
                        dp[i][j] = new Node(min + 1, Choice.DELETE);
                    } else if (min == insertStep) {
                        dp[i][j] = new Node(min + 1, Choice.INSERT);
                    } else if (min == editStep) {
                        dp[i][j] = new Node(min + 1, Choice.EDIT);
                    }
                }

        for (Node[] nodes : dp) {
            System.out.println(Arrays.toString(nodes));
        }

        return dp[m][n];
    }


    public static void main(String[] args) {
        EditDirection direction = new EditDirection();
        System.out.println(direction.minDistance("dinitrophenylhydrazine", "benzalphenylhydrazone"));
    }
}
