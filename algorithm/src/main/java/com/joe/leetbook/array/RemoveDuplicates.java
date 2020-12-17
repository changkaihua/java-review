package com.joe.leetbook.array;

import org.junit.Test;

import javax.swing.plaf.SliderUI;
import java.util.Arrays;

/**
 * @author ckh
 * @since 2020/12/17
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
//        int left = 0, len = nums.length;
//        for (int right = 1; right < nums.length; right++) {
//            if (nums[left] == nums[right]) {
//                len--;
//            }
//            if (len != nums.length) {
//                nums[left] = nums[right];
//            }
//            left++;
//        }
//        return len;

        if (nums.length == 0) return 0;
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            if (nums[right] != nums[left]) {
                left++;
                nums[left] = nums[right];
            }
        }
        return left + 1;
    }

    @Test
    public void test() {
        int[] arr = {1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(arr));
        System.out.println("arr = " + Arrays.toString(arr));
    }
}
