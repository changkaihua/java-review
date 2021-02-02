package com.joe.leetcode.daily;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 * 888. 公平的糖果棒交换
 *
 * @author ckh
 * @since 2021/2/1
 */
public class FairCandySwap {

    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int delta = (sumA - sumB) / 2;
        int[] ans = new int[2];
        Set<Integer> map = new HashSet<>();
        Arrays.stream(A).parallel().forEach(map::add);
        Arrays.stream(B).forEach(
                num -> {
                    int temp = delta + num;
                    if (map.contains(temp)) {
                        ans[0] = temp;
                        ans[1] = num;
                    }
                }
        );
        return ans;
    }

    public int[] fairCandySwapV2(int[] A, int[]B){
        int maxLen = 100001;
        BitSet set = new BitSet(maxLen);

        int sumA = 0, sumB = 0;
        for (int value : A) {
            sumA += value;
            set.set(value);
        }
        for (int value : B) {
            sumB += value;
        }
        int delta = (sumA - sumB) / 2;
        for (int num : B) {
            if (num + delta > 0 && set.get(num + delta)) return new int[]{num + delta, num};
        }
        return null;
    }
}
