package com.joe.leetcode.daily;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. Non-overlapping Intervals
 *
 * @author ckh
 * @since 2020/12/31
 */
public class NoOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        // 先按照区间起始位置排序
        // Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0])
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        int count = 0;
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                // 重叠了
                count++;
//                if (intervals[i + 1][1] > intervals[i][1]) {
//                  //  intervals[i + 1][0] = intervals[i][0];  这句没用,只比较右端就好了
//                    intervals[i + 1][1] = intervals[i][1];
//                }
                intervals[i + 1][1] = Math.min(intervals[i + 1][1], intervals[i][1]);
            }

        }
        return count;
    }

    @Test
    public void test() {
        int[][] intervers = {
//                {1,2},{2,3},{3,4},{1,3}
//                {1,2},{1,2},{1,2}
                {1, 100}, {11, 22}, {1, 11}, {2, 12}
        };
        System.out.println(eraseOverlapIntervals(intervers));
    }
}
