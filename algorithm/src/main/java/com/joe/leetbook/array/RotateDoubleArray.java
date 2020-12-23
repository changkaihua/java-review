package com.joe.leetbook.array;

/**
 * @author ckh
 * @since 2020/12/8
 */
public class RotateDoubleArray {

    /**
     * 暴力
     */
    public void V1(int[][] matrix) {
        int n = matrix.length;
        int[][] copy = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                copy[col][n - 1 - row] = matrix[row][col];
            }
        }

        for (int row = 0; row < n; row++) {
            System.arraycopy(copy[row], 0, matrix[row], 0, n);
        }
    }

    /**
     * 先按主对角线反转, 再按每一行重点翻转
     * too trick
     */
    public void V2(int[][] matrix) {
        int n = matrix.length;
        // 先以对角线（左上-右下）为轴进行翻转
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        // 再对每一行以中点进行翻转
        int mid = n >> 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < mid; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = tmp;
            }
        }
    }
}
