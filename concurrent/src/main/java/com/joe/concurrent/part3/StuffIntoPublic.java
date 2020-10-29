package com.joe.concurrent.part3;

/**
 * StuffIntoPublic
 * <p/>
 * Unsafe publication
 * <p>
 * 解释见: https://stackoverflow.com/questions/1621435/not-thread-safe-object-publishing 和测试代码
 *
 * @author Brian Goetz and Tim Peierls
 */
public class StuffIntoPublic {
    public Holder holder;

    public void initialize() {
        holder = new Holder(42);
    }
}
