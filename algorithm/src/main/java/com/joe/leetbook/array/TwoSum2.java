package com.joe.leetbook.array;

/**
 * @author ckh
 * @since 2020/12/16
 */
public class TwoSum2 {
    public int[] twoSum(int[] numbers, int target) {
        int[] ans = new int[2];
        int left = 0, right = numbers.length - 1;
        while (left <= right) {
            if (numbers[left] + numbers[right] < target) {
                left++;
            } else if (numbers[left] + numbers[right] > target) {
                right--;
            } else {
                ans[0] = left+1;
                ans[1] = right+1;
                return ans;
            }
        }

        return ans;
    }

}
