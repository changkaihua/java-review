package com.joe.spring;

/**
 * @author ckh
 * @create 9/16/20 10:58 AM
 */
public class IocTest {

    public static void main(String[] args) {
        try {
//            Person person = new Person();
            Class.forName("Person.class");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
