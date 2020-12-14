package com.joe.leetbook.Strings;

import org.junit.Test;

import java.util.*;

/**
 * @author ckh
 * @since 2020/12/14
 */
public class ReverseWords {

    public String reverseWords(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }

        Deque<String> stack = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();

        while (left <= right) {
            char c = s.charAt(left);
            if ((word.length() != 0) && (c == ' ')) {
                // 将单词 push 到队列的头部
                stack.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
        stack.offerFirst(word.toString());
        return String.join(" ", stack);
    }

    public String reverseWordsV1(String s) {
        s = s.trim();
        String[] split = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = split.length - 1; i > 0; i--) {
            if (split[i].length() > 0)
                sb.append(split[i]).append(' ');
        }
        sb.append(split[0]);
        return sb.toString();
    }


    @Test
    public void test() {
        String s = "  hello world  ";
        System.out.println(reverseWords(s));
    }
}
