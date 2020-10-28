package com.joe.concurrent.part1;

import com.joe.annotations.NotThreadSafe;


/**
 * UnsafeSequence
 *
 * @author Brian Goetz and Tim Peierls
 */
@NotThreadSafe
public class UnsafeSequence {
    private int value;

    /**
     * Returns a unique value.
     */
    public int getNext() {
        // 竟态条件(Race Condition)
        return value++;
    }


}
