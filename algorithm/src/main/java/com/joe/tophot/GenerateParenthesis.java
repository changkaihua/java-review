package com.joe.tophot;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ckh
 * @since 2021/1/11
 */
public class GenerateParenthesis {
    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return res;
        }
        getParenthesis("", n, n);
        return res;
    }

    /**
     * @param str   当前递归的结果
     * @param left  左括号数
     * @param right 右括号数
     */
    private void getParenthesis(String str, int left, int right) {
        if (left == 0 && right == 0) {
            res.add(str);
            return;
        }
        if (left == right) {
            //剩余左右括号数相等，下一个只能用左括号
            getParenthesis(str + "(", left - 1, right);
        } else if (left < right) {
            //剩余左括号小于右括号，下一个可以用左括号也可以用右括号
            if (left > 0) {
                getParenthesis(str + "(", left - 1, right);
            }
            getParenthesis(str + ")", left, right - 1);
        }
    }

    public List<String> generateparenthesisV2(int n) {
        if (n == 0) return Collections.emptyList();
        dfs("", n, n);
        return res;
    }

    /**
     * @param str   当前递归的结果
     * @param left  左括号数
     * @param right 右括号数
     */
    private void dfs(String str, int left, int right) {
        if (left == 0 && right == 0) {
            res.add(str);
            return;
        }
        if (left > right) return;
        if (left > 0) dfs(str + "(", left - 1, right);
        dfs(str + ")", left, right - 1);
    }

    @Test
    public void test() {
        List<String> strings = generateparenthesisV2(2);
//        List<String> strings2 = generateParenthesis(2);
        System.out.println(strings);
    }
}
