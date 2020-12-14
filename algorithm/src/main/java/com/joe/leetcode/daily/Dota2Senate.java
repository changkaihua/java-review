package com.joe.leetcode.daily;

import com.sun.org.apache.bcel.internal.generic.DSUB;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 649. Dota2 Senate
 *
 * @author ckh
 * @since 2020/12/11
 */
public class Dota2Senate {

    /**
     * Radiant  Dire
     */
    public String predictPartyVictory(String senate) {
        int n = senate.length();
        Queue<Integer> R = new ArrayDeque<>();
        Queue<Integer> D = new ArrayDeque<>();
        for (int i = 0; i < senate.toCharArray().length; i++) {
            if (senate.charAt(i) == 'R') {
                R.offer(i);
            } else {
                D.offer(i);
            }
        }
        while (!R.isEmpty() && !D.isEmpty()) {
            int radiantIndex = R.poll(), direIndex = D.poll();
            // 在前面的重新入队
            if (radiantIndex < direIndex) {
                R.offer(radiantIndex + n);
            } else {
                D.offer(direIndex + n);
            }
        }
        return R.isEmpty() ? "Dire" : "Radiant";


    }
}
