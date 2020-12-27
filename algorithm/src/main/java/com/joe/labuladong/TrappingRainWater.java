package com.joe.labuladong;

import org.junit.Test;

import java.util.Map;

/**
 * 42. Trapping Rain Water
 *
 * @author ckh
 * @since 2020/12/25
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        int count = 0;
        for (int i = 1; i < height.length; i++) {
            int maxLeft = 0, maxRight = 0;
            for (int j = i; j >= 0; j--) {
                maxLeft = Math.max(maxLeft, height[j]);
            }
            for (int j = i; j < height.length; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }
            count += Math.min(maxLeft, maxRight) - height[i];
        }
        return count;
    }

    @Test
    public void test() {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height));
    }
}
