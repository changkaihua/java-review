package com.joe.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 947. Most Stones Removed with Same Row or Column
 *
 * @author ckh
 * @since 2021/1/15
 */
public class MostStonesRemovedSameRowColumn {

    int[] p = new int[1000];

    int find(int index) {
        if (index != p[index]) p[index] = find(p[index]);
        return p[index];
    }

    void union(int idx1, int idx2) {
        p[find(idx1)] = find(idx2);
    }

    public int removeStones(int[][] stones) {
        int n = stones.length;
        for (int i = 0; i < n; i++) p[i] = i;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 只要有一个坐标点连通, 则视为连通
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    union(i, j);
                }
            }
        }

        // 连通域个数
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (p[i] == i) count++;
        }

        return n - count;
    }

    public int removeStonesV2(int[][] stones) {
        UnionFind unionFind = new UnionFind();

        for (int[] stone : stones) {
            // 下面这三种写法任选其一
            // unionFind.union(~stone[0], stone[1]);
            // unionFind.union(stone[0] - 10001, stone[1]);
            unionFind.union(stone[0] + 10001, stone[1]);
        }
        return stones.length - unionFind.getCount();
    }


    private static class UnionFind {

        private final Map<Integer, Integer> parent;
        private int count;

        public UnionFind() {
            this.parent = new HashMap<>();
            this.count = 0;
        }

        public int getCount() {
            return count;
        }

        public int find(int x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                // 并查集集中新加入一个结点，结点的父亲结点是它自己，所以连通分量的总数 +1
                count++;
            }

            int curVal = parent.get(x);
            // 不是父结点
            if (x != curVal) {
                parent.put(x, find(curVal));
            }
            return curVal;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent.put(rootX, rootY);
            // 两个连通分量合并成为一个，连通分量的总数 -1
            count--;
        }
    }
}
