package com.joe.jvm.part6;

/**
 * @author ckh
 * @create 10/14/20 4:41 PM
 */
public class TestTryCatchClass {


    public int inc() {
        int x = 0;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x++;
        }
    }

    public String incString(){
        String s = "abc";
        try {
            s = "bcd";
            int i = 1/0;
            return s;
        }catch (Exception e){
            s = "asdf";
            return s;
        }finally {
            s = "zzzz";
        }
    }

    public static void main(String[] args) {
        TestTryCatchClass catchClass = new TestTryCatchClass();
        System.out.println(catchClass.incString());

        System.out.println(catchClass.inc());
    }
}
