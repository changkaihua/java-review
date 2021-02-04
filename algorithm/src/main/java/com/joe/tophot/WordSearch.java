package com.joe.tophot;

import org.junit.Test;

/**
 * 79. word search
 *
 * @author ckh
 * @since 2021/2/4
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int rows = board.length, columns = board[0].length;
        char[] words = word.toCharArray();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] words, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0
                || words[k] != board[i][j]) return false;
        else if (k == words.length - 1) return true;

        char choose = words[k];
        board[i][j] = '\\';

        boolean ans = dfs(board, words, i, j + 1, k + 1) ||
                dfs(board, words, i + 1, j, k + 1) ||
                dfs(board, words, i - 1, j, k + 1) ||
                dfs(board, words, i, j - 1, k + 1);

        board[i][j] = choose;
        return ans;
    }
}
