package com.joe.jvm.part8;

/**
 * @author ckh
 * @create 10/19/20 2:59 PM
 */
public class TestLocalVariableTableGc {
    public static void main(String[] args) {
//        test1();
        test2();

//        test3();
    }

    /**
     * gc 回收 了
     */
    private static void test3() {
        {
            byte[] placeHolder = new byte[64 * 1024 * 1024];
        }
        // placeHolder 原本占用的变量槽 被变量a复用, 成功GC
        int a = 0;
        System.gc();
    }

    /**
     * gc 没有回收
     */
    private static void test2() {
        {
            byte[] placeHolder = new byte[64 * 1024 * 1024];
        }
        // 虽然离开了placeHolder 的作用域, 但是并没有对局部变量表的读写操作,
        // placeHolder原本占用的变量槽还没有被其他变量复用, 所以局部变量表仍然保持着对它的关联
        System.gc();
    }

    /**
     * gc 没有回收
     */
    private static void test1() {
        byte[] placeHolder = new byte[64 * 1024 * 1024];
        // placeHolder 还在作用域内
        System.gc();
    }
}
