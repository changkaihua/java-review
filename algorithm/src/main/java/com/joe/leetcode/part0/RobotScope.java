package com.joe.leetcode.part0;

import java.util.*;

/**
 * 剑指 Offer 13. 机器人的运动范围
 *
 * @author ckh
 * @create 11/4/20 8:20 PM
 */
public class RobotScope {

    int count = 0;


    public int movingCount(int m, int n, int k) {

        int count = 0;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new ArrayDeque<>();

        // x, y, 数位和
        queue.add(new int[]{0, 0, 0});
        while (queue.size() > 0) {
            int[] x = queue.poll();
            int i = x[0], j = x[1], sum = x[2];
            if (i >= m || j >= n || k < sum || visited[i][j]) continue;
            visited[i][j] = true;
            count++;
            queue.add(new int[]{i + 1, j, getSum(i + 1, j)});
            queue.add(new int[]{i, j + 1, getSum(i, j + 1)});
        }
        return count;
    }

    static class Node {
        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x &&
                    y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    /**
     * 巨慢, 仿写框架
     */
    private void bfsOld(int k, Node start, int rows, int columns) {
        Queue<Node> queue = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();

                if (getSum(curr.x, curr.y) > k) continue;

                if (curr.x == rows && curr.y == columns) {
                    return;
                }

                int[][] paths = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
                for (int[] path : paths) {
                    int newI = curr.x + path[0], newJ = curr.y + path[1];
                    if (newI >= 0 && newI < rows && newJ >= 0 && newJ < columns) {
                        Node newNode = new Node(curr.x + path[0], curr.y + path[1]);
                        if (!visited.contains(newNode)) {
                            queue.offer(newNode);
                            visited.add(newNode);
                        }
                    }
                }
                count++;
            }
        }
    }

    int getSum(int x, int y) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        while (y != 0) {
            res += y % 10;
            y /= 10;
        }
        return res;
    }


    public static void main(String[] args) {
        RobotScope scope = new RobotScope();
        int count = scope.movingCount(35, 35, 4);
        System.out.println(count);
    }
}
