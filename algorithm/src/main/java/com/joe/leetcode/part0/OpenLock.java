package com.joe.leetcode.part0;

import java.util.*;

/**
 * 752. Open the Lock
 *
 * @author ckh
 * @create 11/1/20 8:50 PM
 */
public class OpenLock {

    // 将 s[j] 向上拨动一次
    String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }

    // 将 s[i] 向下拨动一次
    String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
    }

    public int openLock(String[] deadends, String target) {
        // 记录需要跳过的死亡密码
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        // 记录已经穷举过的密码，防止走回头路
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        // 从起点开始启动广度优先搜索
        int step = 0;
        q.offer("0000");
        visited.add("0000");

        while (!q.isEmpty()) {
            int sz = q.size();
            /* 将当前队列中的所有节点向周围扩散 */
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();

                /* 如果在死亡队列中, 则跳过 */
                if (deads.contains(cur)) continue;
                /* 判断是否到达终点 */
                if (target.equals(cur)) return step;

                /* 将一个节点的未遍历相邻节点加入队列 */
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            /* 在这里增加步数 */
            step++;
        }
        // 如果穷举完都没找到目标密码，那就是找不到了
        return -1;
    }

    /**
     * 使用双向队列优化BFS, 必须明确重点才可以双向
     */
    public int openLockMethod2(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));

        // 用集合不用队列，可以快速判断元素是否存在
        Set<String> front = new HashSet<>();
        Set<String> back = new HashSet<>();
        Set<String> visited = new HashSet<>();

        int step = 0;
        // 添加起点和终点
        front.add("0000");
        back.add(target);

        while (!front.isEmpty() && !back.isEmpty()) {
            // 哈希集合在遍历的过程中不能修改，用 temp 存储扩散结果
            Set<String> temp = new HashSet<>();

            /*
              按照 BFS 的逻辑，队列（集合）中的元素越多，扩散之后新的队列（集合）中的元素就越多
              在双向 BFS 算法中，如果我们每次都选择一个较小的集合进行扩散，那么占用的空间增长速度就会慢一些，效率就会高一些
              但是在这个题中, 加了这个是错的 -_-!!
             */
            /*if (front.size() > back.size()) {
                // 交换 front 和 back
                temp = front;
                front = back;
                back = temp;
            }*/

            /* 将 front 中的所有节点向周围扩散 */
            for (String cur : front) {
                /* 判断是否到达终点 */
                if (dead.contains(cur))
                    continue;
                if (back.contains(cur))
                    return step;
                visited.add(cur);

                /* 将一个节点的未遍历相邻节点加入集合 */
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up))
                        temp.add(up);
                    String down = minusOne(cur, j);
                    if (!visited.contains(down))
                        temp.add(down);
                }
            }
            /* 在这里增加步数 */
            step++;
            // temp 相当于 front
            // 这里交换 front back，下一轮 while 就是扩散 back
            front = back;
            back = temp;
        }
        return -1;

    }


    public static void main(String[] args) {
        String[] deadends = {"0000"};
//        String[] deadends = {"0201","0101","0102","1212","2002"};
        System.out.println(new OpenLock().openLock(deadends, "8888"));


    }


}
