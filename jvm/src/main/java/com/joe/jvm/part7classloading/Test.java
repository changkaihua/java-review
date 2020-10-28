package com.joe.jvm.part7classloading;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ckh
 * @create 10/16/20 11:40 AM
 */
public class Test {

    static {
        i = 0;
        // illegal forward reference
        // System.out.println(i);
    }

    static int i = 1;

    public static void main(String[] args) throws Exception{
        System.out.println("i = " + i);

        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + "class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);

                } catch (IOException e) {
                    throw new ClassNotFoundException();
                }
            }
        };

        Object obj = myLoader.loadClass("com.joe.jvm.part7classloading.ClassLoaderTest").newInstance();

        System.out.println(obj.getClass());
        System.out.println(obj instanceof ClassLoaderTest);
    }
}
