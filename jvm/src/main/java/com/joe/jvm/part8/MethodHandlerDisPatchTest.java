package com.joe.jvm.part8;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * @author ckh
 * @create 10/20/20 7:52 PM
 */
@SuppressWarnings("all")
public class MethodHandlerDisPatchTest {
    class GrandFather {
        void thinking() {
            System.out.println("GrandFather.thinking");
        }
    }

    class Father extends GrandFather {
        void thinking() {
            System.out.println("Father.thinking");
        }
    }


    class Son extends Father {
        // 如何实现调用 GrandFather 的 thinking 方法?
        /*void thinking() {
            // son 类无法获取到实际类型是 GrandFather 的对象引用, 而 invokevirtual 指令的分派逻辑是固定的, 根据实际类型分派

            // 1. 通过MethodHandle来处理
            try {
                MethodType mt = MethodType.methodType(void.class);
                MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class,
                        "thinking", mt, getClass());
                mh.invoke(this);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }*/

        void thinking() {
            try {
                MethodType mt = MethodType.methodType(void.class);
                // 通过反射修改访问保护参数
                Field lookupImpl = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                lookupImpl.setAccessible(true);
                MethodHandle mh = ((MethodHandles.Lookup) lookupImpl.get(null))
                        .findSpecial(GrandFather.class, "thinking", mt, GrandFather.class);
                mh.invoke(this);

            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Father.thinking
        // 在 jdk7 update 9 之前上面代码是可以获取到 grandfather 的, 但是后面被修正了
        // 保证 findSpecial() 查找方法时收到访问约束, 只能访问父类的方法
        new MethodHandlerDisPatchTest().new Son().thinking();
    }

}
