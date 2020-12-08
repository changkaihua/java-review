package com.joe.leetbook.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author ckh
 * @since 2020/12/8
 */
public class MergeArray {


    public int[][] merge(int[][] intervals) {
        // 先按照区间起始位置排序
        // Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0])
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));

        int[][] res = new int[intervals.length][2];
        int index = -1;
        for (int[] interval : intervals) {
            if (index == -1 || interval[0] > res[index][1]) {
                res[++index] = interval;
            } else {
                res[index][1] = Math.max(res[index][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, index + 1);
    }

    @Test
    public void test() {
        int[][] intervals = {
                {1, 4},
                {0, 2},
                {3, 5}
        };

        System.out.println(Arrays.deepToString(merge(intervals)));
    }
}
