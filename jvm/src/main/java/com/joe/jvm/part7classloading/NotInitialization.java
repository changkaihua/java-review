package com.joe.jvm.part7classloading;

import org.omg.PortableInterceptor.SUCCESSFUL;

/**
 * @author ckh
 * @create 10/15/20 10:55 AM
 */
@SuppressWarnings("AlibabaRemoveCommentedCode")
public class NotInitialization {
    public static void main(String[] args) {
        /*
            被动引用 eg1
                通过子类引用父类的静态字段, 不会导致子类初始化

            通过子类引用父类中定义的静态字段,只会触发父类的初始化,不会触发子类的初始化,但是子类会被加载和验证
            通过 -XX:+TraceClassLoading 参数可以看到
                [Loaded com.joe.jvm.part7classloading.SuperClass from file:/home/joe/IdeaProjects/java-review/jvm/target/classes/]
                [Loaded com.joe.jvm.part7classloading.SubClass from file:/home/joe/IdeaProjects/java-review/jvm/target/classes/]
         */
         System.out.println(SubClass.value);

        /*
            被动引用 eg2
                通过数组定义来引用类, 不会触发此类的初始化
            没有初始化 SuperClass 类, 但是触发了另一个 [L...SuperClass 类的初始化,
            是由虚拟机自动生成的, 由 anewarray 指令触发

         */
        SuperClass[] sca = new SuperClass[10];


        /*
            被动引用eg3z
                常量在编译阶段会存入调用类的常量池中,本质上没有直接引用到定义常量的类, 因此不会触发定义常量类的初始化
                甚至都没有触发加载..., 因为是编译阶段就存了, 加载阶段都没关系了,,,
         */
        System.out.println(ConstClass.HELLO);

    }
}
