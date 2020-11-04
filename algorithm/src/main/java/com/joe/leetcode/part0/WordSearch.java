package com.joe.leetcode.part0;

/**
 * 79. Word Search
 * <p>
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 * @author ckh
 * @create 11/4/20 6:30 PM
 */
public class WordSearch {

    public boolean exist(char[][] board, String word) {

        int rows = board.length, columns = board[0].length;
        boolean[][] visited = new boolean[rows][columns];

        char[] words = word.toCharArray();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (dfs(board, words, i, j, 0)) return true;
//                if (backtrack(board, visited, i, j, words, 0)) return true;
            }
        }
        // 最后也没找到答案
        return false;
    }

    private boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) return false;
        if (k == word.length - 1) return true;
        // 做选择
        char temp = word[k];
        board[i][j] = '/';

        boolean res = dfs(board, word, i, j + 1, k + 1) ||
                dfs(board, word, i, j - 1, k + 1) ||
                dfs(board, word, i + 1, j, k + 1) ||
                dfs(board, word, i - 1, j, k + 1);
        // 撤销选择
        board[i][j] = temp;
        return res;

    }

    /**
     * @param board   棋盘
     * @param visited 已经访问过
     * @param i       坐标x
     * @param j       坐标y
     * @param word    单词
     * @param k       单词的第k个字符
     */
    private boolean backtrack(char[][] board, boolean[][] visited, int i, int j, char[] word, int k) {
        if (board[i][j] != word[k]) {
            return false;
        } else if (k == word.length - 1) {
            // 此时字符相同且为最后一个字符, 所以直接返回true
            return true;
        }
        // 下面都是在 board[i][j] == word.charAt(k) 的基础上

        // 做选择
        visited[i][j] = true;

        boolean result = false;
        // left , up, right, down
        int[][] paths = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        for (int[] path : paths) {
            int newI = i + path[0], newJ = j + path[1];
            if (newI >= 0 && newI < board.length && newJ >= 0 && newJ < board[0].length) {
                // 剪支
                if (!visited[newI][newJ]) {
                    boolean flag = backtrack(board, visited, newI, newJ, word, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }

        // 撤销选择
        visited[i][j] = false;

        return result;
    }


    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        String word = "ABCCEf";
        System.out.println(new WordSearch().exist(board, word));
    }
}
