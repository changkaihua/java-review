package com.joe.jvm.part7classloading;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ckh
 * @create 10/16/20 2:45 PM
 */
@SuppressWarnings("ALL")
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    // bug记录: 第一次写的时候, ".class" 漏了 "." 导致 is 一直为null,一直使用父类的加载器,也就一直返回 true
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
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


        // 使用ClassLoaderTest的类加载器加载本类
        Object obj1 = ClassLoaderTest.class.getClassLoader().loadClass("com.joe.jvm.part7classloading.ClassLoaderTest")
                .newInstance();
        System.out.println(obj1.getClass());
        // true
        System.out.println(obj1 instanceof com.joe.jvm.part7classloading.ClassLoaderTest  );

        // 使用自定义类加载器加载本类
        Object obj2 = myLoader.loadClass("com.joe.jvm.part7classloading.ClassLoaderTest")
                .newInstance();

        System.out.println(obj2.getClass());
        // false
        System.out.println(obj2 instanceof com.joe.jvm.part7classloading.ClassLoaderTest);
    }
}
