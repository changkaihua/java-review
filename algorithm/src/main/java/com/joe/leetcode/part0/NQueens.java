package com.joe.leetcode.part0;

import java.util.*;

/**
 * @author ckh
 * @create 10/29/20 8:06 PM
 */
public class NQueens {


    public static void main(String[] args) {
        NQueens queens = new NQueens();
        List<List<String>> solveNQueens = queens.solveNQueens(4);
        System.out.println(solveNQueens);
    }

    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // init the board
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        // back track
        backtrack(board, 0);

        return res;

    }

    private void backtrack(char[][] board, int row) {
        if (row == board.length) {
            List<String> temp = transferRes(board);
            res.add(temp);
            return;
        }
        int columns = board[row].length;
        for (int column = 0; column < columns; column++) {
            // 剪支
            if (!isValid(board, row, column)) continue;

            board[row][column] = 'Q';
            backtrack(board, row + 1);
            board[row][column] = '.';
        }
    }

    private boolean isValid(char[][] board, int row, int column) {
        int n = board.length;
        // 判断每一列
        for (char[] rows : board) {
            if (rows[column] == 'Q') return false;
        }

        // 判断左上
        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        // 判断右上
        for (int i = row - 1, j = column + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    private List<String> transferRes(char[][] board) {
        List<String> t = new ArrayList<>();
        for (char[] chars : board) {
            StringBuilder builder = new StringBuilder();
            for (char aChar : chars) {
                builder.append(aChar);
            }
            t.add(builder.toString());
        }
        return t;
    }
}
