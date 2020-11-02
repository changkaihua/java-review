package com.joe.concurrent.part5;

import com.joe.annotations.ThreadSafe;
import com.joe.concurrent.utils.Computable;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

/**
 * Factorizer
 * <p/>
 * Factorizing servlet that caches results using Memoizer
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class Factorizer extends GenericServlet implements Servlet {
    private final Computable<BigInteger, BigInteger[]> c = this::factor;

    private final Computable<BigInteger, BigInteger[]> cache = new Memoizer<>(c);

    public void service(ServletRequest req, ServletResponse resp) {

        try {
            BigInteger i = extractFromRequest(req);
            encodeIntoResponse(resp, cache.compute(i));
        } catch (InterruptedException e) {
            encodeError(resp, "factorization interrupted");
        }
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    void encodeError(ServletResponse resp, String errorString) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }
}