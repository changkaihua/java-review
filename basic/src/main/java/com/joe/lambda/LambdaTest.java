package com.joe.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author ckh
 * @since 2021/1/11
 */
public class LambdaTest {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red")
        );

        inventory.sort(Comparator
                .comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor)
        );

        Predicate<Apple> redApple = a -> "red".equals(a.getColor());

        Predicate<Apple> notRedApple = redApple.negate();
        Predicate<Apple> redAndHeavyApple = redApple.and(a -> a.getWeight() > 150);
    }


}
