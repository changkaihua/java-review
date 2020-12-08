package com.joe.leetbook.array;

import jdk.nashorn.internal.objects.NativeUint8Array;
import org.junit.Test;

/**
 * @author ckh
 * @since 2020/12/8
 */
public class PivotIndex {

    public int pivotIndex(int[] nums) {
        int sum = 0, leftsum = 0;
        for (int x: nums) {
            sum += x;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (leftsum == sum - leftsum - nums[i]) {
                return i;
            }
            leftsum += nums[i];
        }
        return -1;
    }

    @Test
    public void test() {
        int[] arr = {-1, -1, -1, -1, -1, 0};
        System.out.println(pivotIndex(arr));
    }
}
