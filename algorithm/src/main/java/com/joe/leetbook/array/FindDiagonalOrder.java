package com.joe.leetbook.array;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author ckh
 * @since 2020/12/10
 */
@SuppressWarnings("UnusedReturnValue")
public class FindDiagonalOrder {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }

        int rows = matrix.length;
        int columns = matrix[0].length;
        int[] ans = new int[rows * columns];
        int diagonalSize = rows + columns - 1;
        ArrayList<Integer> temp = new ArrayList<>();
        int idx = 0;

        for (int i = 0; i < diagonalSize; i++) {
            int row = i < columns ? 0 : i - columns + 1;
            int col = i < columns ? i : columns - 1;

            temp.clear();
            while (row < rows && col > -1) {
                temp.add(matrix[row][col]);
                row++;
                col--;
            }

            if (i % 2 == 0) {
                Collections.reverse(temp);
            }
            for (Integer val : temp) {
                ans[idx++] = val;
            }
        }
        return ans;
    }

    @Test
    public void test() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        findDiagonalOrder(matrix);
        System.out.println("matrix = " + Arrays.deepToString(matrix));
    }
}
