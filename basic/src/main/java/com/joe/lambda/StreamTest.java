package com.joe.lambda;

import com.joe.test.A;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static java.util.stream.Collectors.toList;
import static com.joe.lambda.Dish.menu;

/**
 * @author ckh
 * @since 2021/1/11
 */
public class StreamTest {
    public static void main(String[] args) {
//        test1();
//        test2();
        // 返回所有的数对
        // 给定 2 个列表 [1,2,3] 和 [3,4]
        // 返回 [1,3],[1,4],[2,3],[2,4],[3,3],[3,4]
        List<Integer> integers1 = Arrays.asList(1, 2, 3);
        List<Integer> integers2 = Arrays.asList(3, 4);

        List<int[]> pairs = integers1.stream()
                .flatMap(i -> integers2.stream()
                        .map(j -> new int[]{i, j}))
                .collect(toList());

        for (int[] pair : pairs) {
            System.out.println(Arrays.toString(pair));
        }

        System.out.println("====================");
        List<int[]> onlyThreePairs = integers1.stream()
                .flatMap(i -> integers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(toList());
        for (int[] threePair : onlyThreePairs) {
            System.out.println(Arrays.toString(threePair));
        }


    }

    private static void test2() {
        List<String> words = Arrays.asList("Hello", "World");

        List<String[]> word = words.stream()
                .map(s -> s.split(""))
                .distinct()
                .collect(toList());
        System.out.println(word);

        String[] arrayOfWords = {"hello", "world"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
        streamOfWords.forEach(System.out::println);

        List<Stream<String>> list = words.stream()
                .map(s -> s.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(toList());


        List<String> collect = words.stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct().collect(toList());

        System.out.println(collect);
    }

    private static void test1() {
        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());

        System.out.println(threeHighCaloricDishNames);
    }
}
