package com.joe.jvm.part3gc;

import java.util.concurrent.TimeUnit;

/**
 * 1. 对象在GC 时可以自我拯救
 * 2. 这种自救的机会只有一次，因为一个对象的 finalize 方法最多只会被系统自动调用一次
 *
 * @author ckh
 * @create 10/13/20 9:22 AM
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOCK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed");
        FinalizeEscapeGC.SAVE_HOCK = this;
    }

    public static void main(String[] args) {
        SAVE_HOCK = new FinalizeEscapeGC();

        // 对象第一次成功拯救自己
        SAVE_HOCK = null;
        System.gc();

        // finalize 方法优先级很低，暂停1s等待它
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (SAVE_HOCK != null) {
            SAVE_HOCK.isAlive();
        }else {
            System.out.println("no, i am dead");
        }

        // 下面是第二次尝试拯救，但是失败
        SAVE_HOCK = null;
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (SAVE_HOCK != null) {
            SAVE_HOCK.isAlive();
        }else {
            System.out.println("no, i am dead");
        }




    }
}
