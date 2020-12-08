package com.joe.leetbook.array;

/**
 * @author ckh
 * @since 2020/12/8
 */
public class OnceNumber {
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
