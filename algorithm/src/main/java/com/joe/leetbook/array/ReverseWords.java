package com.joe.leetbook.array;

import org.junit.Test;

/**
 * 反转字符串中的单词 III
 *
 * @author ckh
 * @since 2020/12/17
 */
public class ReverseWords {
    public String reverseWords(String s) {
        char[] array = s.toCharArray();
        int left = 0;
        for (int right = 0; right < array.length; right++) {
            if (array[right] == ' ') {
                reverse(array, left, right - 1);
                left = right + 1;
            }
        }
        reverse(array, left, array.length - 1);
        return new String(array);
    }

    private void reverse(char[] array, int left, int right) {
        while (left < right) {
            char temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }

    @Test
    public void test(){
        String s = "Let's take LeetCode contest";
        String s1 = reverseWords(s);
        System.out.println(s1);
    }
}
