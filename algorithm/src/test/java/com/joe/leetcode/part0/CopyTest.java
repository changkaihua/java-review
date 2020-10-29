package com.joe.leetcode.part0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ckh
 * @create 10/29/20 9:02 PM
 */
public class CopyTest {

    private List<List<String>> res = new LinkedList<>();

    public List<List<String>> solveNQueens(int n) {
        // 初始化棋盘
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        // 回溯法
        backtrack(board, 0);

        return res;
    }

    private void backtrack(char[][] board, int row) {
        // 结束条件: 到最后一行
        if (row == board.length) {
            transferRes(board);
            return;
        }

        int columns = board[row].length;
        // 遍历选择
        for (int column = 0; column < columns; column++) {
            // 不满足条件则跳过
            if (!isValid(board, row, column)) continue;
            // 做选择
            board[row][column] = 'Q';
            // 回溯
            backtrack(board, row + 1);
            // 撤销选择
            board[row][column] = '.';
        }
    }

    private boolean isValid(char[][] board, int row, int column) {
        int n = board.length;
        // 判断同列
        for (int i = 0; i < n; i++) {
            if (board[i][column] == 'Q') return false;
        }

        // 判断右上
        for (int i = row - 1, j = column + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }

        // 判断左上
        for (int i = row - 1, j = column - 1; j >= 0 && i >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    private void transferRes(char[][] board) {
        List<String> part = new ArrayList<>();
        for (char[] chars : board) {
            StringBuilder builder = new StringBuilder();
            for (char aChar : chars) {
                builder.append(aChar);
            }
            part.add(builder.toString());
        }
//        System.out.println("part = " + part);
        res.add(part);
    }
}
