package com.joe.lambda.ch6;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 自定义收集器
 *
 * @author ckh
 * @since 2021/1/12
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {


    /**
     * 创建新的结果容器
     *
     * @return 创建一个空的累加器实例, 供数据收集过程使用
     */
    @Override
    public Supplier<List<T>> supplier() {
//        return () -> new ArrayList<T>(); 等价下面的写法
        return ArrayList::new;
    }

    /**
     * 将元素添加到结果容器
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(
                EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT)
        );
    }
}
