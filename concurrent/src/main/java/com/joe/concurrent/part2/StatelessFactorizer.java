package com.joe.concurrent.part2;

import com.joe.annotations.ThreadSafe;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import javax.servlet.*;


/**
 * StatelessFactorizer
 * <p>
 * A stateless servlet
 * stateless: 即不包含任何域, 也不包含任何对其他类中域的引用
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class StatelessFactorizer extends GenericServlet implements Servlet {

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        System.out.println(Arrays.toString(factors));
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
        try {
            resp.getWriter().print(Arrays.toString(factors));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }
}
