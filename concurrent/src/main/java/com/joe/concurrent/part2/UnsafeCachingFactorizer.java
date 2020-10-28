package com.joe.concurrent.part2;

import com.joe.annotations.NotThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * UnsafeCachingFactorizer
 * <p>
 * Servlet that attempts to cache its last result without adequate atomicity
 *
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
public class UnsafeCachingFactorizer extends GenericServlet implements Servlet {

    private final AtomicReference<BigInteger> lastNumber
            = new AtomicReference<>();

    private final AtomicReference<BigInteger[]> lastFactors
            = new AtomicReference<>();

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        // 竟态条件, get 是原子的, 但是 i.equals() 的 i 就不一定了
        if (i.equals(lastNumber.get())) {
            encodeIntoResponse(resp, lastFactors.get());
        } else {
            BigInteger[] factors = factor(i);
            // 竟态条件, 虽然每个操作是原子的, 每个set是安全的, 但是合起来就不一定了, 可能只更新了一个值然后被中断
            lastNumber.set(i);
            lastFactors.set(factors);
            encodeIntoResponse(resp, factors);
        }
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    /**
     * mock compute result
     */
    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }
}
