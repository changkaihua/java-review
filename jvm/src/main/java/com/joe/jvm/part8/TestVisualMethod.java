package com.joe.jvm.part8;

/**
 * @author ckh
 * @create 10/19/20 8:13 PM
 */
public class TestVisualMethod {
    public static class Base {
        String str = "Base string";
        protected void show( ){
            System.out.println(str);
            init();
        }
        protected void init(){
            System.out.println("Base init");
            System.out.println(str);
        }
    }

    public static class Sub extends Base {
        String str = "Sub string";

        @Override
        protected void init(){
            System.out.println("Sub init");
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.show();
    }
}
