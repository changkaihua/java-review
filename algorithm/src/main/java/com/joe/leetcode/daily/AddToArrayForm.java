package com.joe.leetcode.daily;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 989. 数组形式的整数加法
 *
 * @author ckh
 * @since 2021/1/22
 */
public class AddToArrayForm {

    public List<Integer> addToArrayForm(int[] A, int K) {
        LinkedList<Integer> ans = new LinkedList<>();
        int remainder = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            int sum = A[i] + K % 10 + remainder;
            ans.addFirst(sum % 10);
            remainder = sum / 10;
            K /= 10;
        }
        while (K != 0) {
            int sum = K % 10 + remainder;
            ans.addFirst(sum % 10);
            K /= 10;
            remainder = sum / 10;
        }
        if (remainder != 0) ans.addFirst(remainder);
        return ans;

    }

    public List<Integer> addToArrayFormV2(int[] A, int K) {
        LinkedList<Integer> ans = new LinkedList<>();
        int remainder = 0;
        for (int i = A.length - 1; i >= 0 || K != 0; i--, K = K / 10) {
            int x = i >= 0 ? A[i] : 0;
            int y = K != 0 ? K % 10 : 0;

            int sum = x + y + remainder;
            ans.addFirst(sum % 10);
            remainder = sum / 10;
        }
        if (remainder != 0) ans.add(0, remainder);
        return ans;
    }


    @Test
    public void test() {
        int[] a = {3};
        System.out.println(addToArrayForm(a, 9));
    }

}
