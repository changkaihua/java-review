package com.joe.leetbook.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author ckh
 * @since 2020/12/9
 */
public class ZeroArray {

    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[] flag = new boolean[col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (flag[j]) {
                    matrix[i][j] = 0;
                    continue;
                }
                if (matrix[i][j] == 0) {
                    flag[j] = true;
                    for (int t = 0; t < col; t++) {
                        matrix[i][t] = 0;
                    }
                    break;
                }
            }
        }

        for (int i = 0; i < flag.length; i++) {
            if (flag[i]){
                for (int j = 0; j < row; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }

    @Test
    public void test(){
        int[][] matrix = {
                //[[1,1,1],[1,0,1],[1,1,1]]
                {0,1,2,0},
                {3,4,5,6},
                {1,3,1,5}
        };
        setZeroes(matrix);

        System.out.println(Arrays.deepToString(matrix));
    }
}
