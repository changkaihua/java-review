package com.joe.lambda.ch6;

import org.hamcrest.core.Is;
import org.junit.Test;
import sun.rmi.log.LogOutputStream;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 将前n个整数按照质数分区
 *
 * @author ckh
 * @since 2021/1/12
 */
public class PrePartitionPrimes {

    /**
     * 对前n个整数进行质数分区
     *
     * @version 1.0
     */
    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(Collectors.partitioningBy(PrePartitionPrimes::isPrime));
    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return takeWhile(primes, i -> i <= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p == 0);
    }

    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            // 检查列表中当前项目是否满足谓词
            if (!p.test(item)) {
                // 不满足则返回该项目之前的前缀子列表
                return list.subList(0, i);
            }
            i++;
        }
        // 全部都满足
        return list;
    }

    // 优化: 仅看被测试数是否能被质数整除

    public Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimesCollector());
    }

    @Test
    public void test() {
        int i = 100;
        System.out.println(partitionPrimes(i));
        System.out.println("=======================");
        System.out.println(partitionPrimesWithCustomCollector(i));
    }

    @Test
    public void testPerformance() {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            // 470 450 460 ~ 460
            partitionPrimes(1000000);
            // 323 292 352 ~ 322
//            partitionPrimesWithCustomCollector(1000000);
            long duration = (System.nanoTime() - start) / 1000000;
            if (duration < fastest) fastest = duration;
        }
        // 自定义性能提升了 30%
        System.out.println(
                "Fastest execution done in " + fastest + " msecs"
        );

    }
}
