package com.joe.tophot;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 17. Letter Combinations of a Phone Number
 *
 * @author ckh
 * @since 2021/1/7
 */
public class LetterCombinationsPhoneNumber {
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return Collections.emptyList();
        List<String> ans = new ArrayList<>();
        String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backtrack(ans, map, digits, 0, new StringBuilder());
        return ans;

    }

    private void backtrack(List<String> ans, String[] map, String digits, int index, StringBuilder str) {
        if (index == digits.length()) {
            ans.add(str.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = map[digit - '2'];
            for (int i = 0; i < letters.length(); i++) {
                str.append(letters.charAt(i));
                backtrack(ans, map, digits, index + 1, str);
                str.deleteCharAt(index);
            }
        }
    }

    @Test
    public void test() {
        String digits = "23";
        List<String> strings = letterCombinations(digits);
        System.out.println(strings);
    }
}
