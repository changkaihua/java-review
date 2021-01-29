package com.joe.tophot;

/**
 * @author ckh
 * @since 2021/1/29
 */
public class HammingDistance {
    public int hammingDistance(int x, int y) {
        int xor = x ^ y, distance = 0;
        while (xor != 0) {
            distance += 1;
            // remove the rightmost bit of '1'
            xor = xor & (xor - 1);
        }
        return distance;
    }

    public int hammingDistanceV2(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
