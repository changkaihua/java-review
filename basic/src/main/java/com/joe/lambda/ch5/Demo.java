package com.joe.lambda.ch5;

import org.junit.Test;

import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * @author ckh
 * @since 2021/1/11
 */
public class Demo {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    @Test
    public void test1() {
        List<Transaction> transactions = this.transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        System.out.println(transactions);
    }

    @Test
    public void test2() {
        Set<String> cites = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(toSet());

        System.out.println(cites);
    }

    @Test
    public void test3() {
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        System.out.println(traders);
    }

    @Test
    public void test4() {
        String traderStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (a, b) -> a + b);
        System.out.println(traderStr);
        // 上面的代码效率不高, 所有字符串被反复连接, 每次都会创建一个新的String对象

        String trader = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
        // joining 用到了 StringBuilder

        System.out.println("trader = " + trader);
    }

    @Test
    public void test5() {
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Milan"))
                .findAny()
                .ifPresent(System.out::println);
        // solution
        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
    }

    @Test
    public void test6() {
        transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    @Test
    public void test7() {
        Optional<Integer> maxValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
    }

    @Test
    public void test8() {
        Optional<Transaction> smallestTransaction = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

        Optional<Transaction> minTransaction = transactions.stream()
                .min(comparing(Transaction::getValue));
    }

    @Test
    public void test9() {
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

    }

    /**
     * 生成斐波那契
     */
    @Test
    public void test10() {
        // [0,1] 数列中的数字和其后序数字
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        System.out.println("================");
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .mapToInt(t -> t[0])
                .forEach(System.out::println);
        System.out.println("================");
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrev = this.previous;
                int next = this.previous + this.current;
                this.previous = this.current;
                this.current = next;
                return oldPrev;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}
