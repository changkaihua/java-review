package com.joe.leetcode.daily;

import org.junit.Test;

import java.util.Arrays;

/**
 * 684. Redundant Connection
 * 图论, 并查集
 *
 * @author ckh
 * @since 2021/1/13
 */
public class RedundantConnection {
    /**
     * 在一棵树中，边的数量比节点的数量少 1。
     * 如果一棵树有 N 个节点，则这棵树有 N−1 条边。
     * 这道题中的图在树的基础上多了一条附加的边，因此边的数量也是 N
     * <p>
     * 树是一个连通且无环的无向图，在树中多了一条附加的边之后就会出现环，因此附加的边即为导致环出现的边
     */
    public int[] findRedundantConnection(int[][] edges) {
        int nodesCount = edges.length;
        // 并查集 init
        int[] parent = new int[nodesCount + 1];
        for (int i = 1; i <= nodesCount; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int node1 = edge[0], node2 = edge[1];
            if (find(parent, node1) != find(parent, node2)) {
                union(parent, node1, node2);
            } else {
                return edge;
            }
        }

        return new int[0];
    }

    public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    @Test
    public void test() {
        int[][] edges = {
                {1, 2}, {1, 3}, {2, 3}
        };
        int[] connection = findRedundantConnection(edges);
        System.out.println("connection = " + Arrays.toString(connection));


        int i = 0 % 5;
        System.out.println("i = " + i);
    }

}
