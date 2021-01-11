package com.joe.tophot;

import org.junit.Test;

import java.util.*;

/**
 * @author ckh
 * @since 2021/1/8
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();

        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (char c : chars) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty())
                    return false;
                if (stack.peek() == map.get(c)) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        return stack.isEmpty();


    }

    public boolean isValidV2(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '[') stack.push(']');
            else if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (stack.isEmpty() || c != stack.pop()) return false;
        }
        return stack.isEmpty();
    }

    public boolean v3(String s) {
        char[] chars = s.toCharArray();

        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        for (char c : chars) {
            if (map.containsKey(c)) {
                stack.push(map.get(c));
            } else {
                if (stack.isEmpty() || stack.peek() != c) {
                    return false;
                }
                stack.pop();

            }
        }
        return stack.isEmpty();
    }

    @Test
    public void test() {
//        System.out.println(isValid("(])"));
//        System.out.println(isValidV2("]]"));
        System.out.println(v3("()"));
    }
}
