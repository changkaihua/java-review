package com.joe.tophot;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author ckh
 * @since 2021/1/4
 */
public class MedianTwoSortedArrays {

    public double findMedianSortedArraysV3(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;

        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }

    }

    public double findMedianSortedArraysV2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length,
                len = m + n, idx1 = 0, idx2 = 0,
                left = -1, right = -1;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (idx1 < m && (idx2 >= n || nums1[idx1] < nums2[idx2])) {
                right = nums1[idx1++];
            } else {
                right = nums2[idx2++];
            }
        }
        if ((len & 1) == 0) {
            return (left + right) / 2.0;
        } else {
            return right;
        }
    }

    public double findMedianSortedArraysV1(int[] nums1, int[] nums2) {
        int[] temp = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, temp, 0, nums1.length);
        System.arraycopy(nums2, 0, temp, nums1.length, nums2.length);
        Arrays.sort(temp);

        return helper(temp);
    }

    private double helper(int[] nums) {
        if (nums == null || nums.length == 0) return 0.0;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            left++;
            right--;
        }
        // System.out.println("left: " + left + "  right: " + right);
        return (nums[left] + nums[right]) / 2.0;
    }

    @Test
    public void test() {
        int[] nums = {1, 3};
        int[] nums1 = {2, 7};
        System.out.println(findMedianSortedArraysV2(nums1, nums));
    }

}
