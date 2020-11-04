package com.joe.concurrent.part7;

import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author ckh
 * @create 11/4/20 11:40 AM
 */
public class PrimeGeneratorTest {

    @Test
    public void aSecondOfPrimes() throws InterruptedException {
        long now = System.currentTimeMillis();
        List<BigInteger> primes = PrimeGenerator.aSecondOfPrimes();
        System.out.println(primes);

        System.out.println("use time: " +(System.currentTimeMillis() - now)+" ms");
    }
}