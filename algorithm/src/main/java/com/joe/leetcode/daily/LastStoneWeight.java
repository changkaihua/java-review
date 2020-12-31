package com.joe.leetcode.daily;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1046. 最后一块石头的重量
 *
 * @author ckh
 * @since 2020/12/30
 */
public class LastStoneWeight {
    public int lastStoneWeightV1(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) {
            pq.offer(stone);
        }
        while (pq.size() > 1) {
            // poll() offer() 的时间复杂度为 log n
            int a = pq.poll();
            int b = pq.poll();
            if (a > b) {
                pq.offer(a - b);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }

    public int lastStoneWeightV2(int[] stones) {
        int idx = stones.length - 1;
        for (int i = 0; i < idx; i++) {
            Arrays.sort(stones);
            if (stones[idx - 1] == 0) break;
            stones[idx] -= stones[idx - 1];
            // 第二大的石头每次必然被销毁
            stones[idx - 1] = 0;
        }
        return stones[idx];
    }

}
