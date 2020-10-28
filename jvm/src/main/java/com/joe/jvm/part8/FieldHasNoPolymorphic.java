package com.joe.jvm.part8;

import java.util.Random;

/**
 * @author ckh
 * @create 10/19/20 4:40 PM
 */
public class FieldHasNoPolymorphic {
    static class Father {
        public int money = 1;

        public Father() {
            money = 2;
            showMeTheMoney();
        }

        public void showMeTheMoney() {
            System.out.println("Father has " + money);

        }
    }

    static class Son extends Father {
        public int money = 3;

        public Son() {
            money = 4;
            showMeTheMoney();
        }

        @Override
        public void showMeTheMoney() {
            System.out.println("Son has " + money);
        }
    }


    public static void main(String[] args) {
        Father guy = new Son();
        System.out.println("this guy has " + guy.money);
    }
}
