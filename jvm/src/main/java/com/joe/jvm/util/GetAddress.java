package com.joe.jvm.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author ckh
 * @create 10/13/20 3:49 PM
 */
public class GetAddress {
    static final Unsafe unsafe = getUnsafe();
    static final boolean is64bit = true;

    /**
     * 打印对象的地址，没什么用，因为Java的地址是不断变化的，这里只是为了看看GC后的地址变化
     */
    public static void printAddresses(Object objects) {
        System.out.print("0x");
        long last = 0;
        int offset = unsafe.arrayBaseOffset(objects.getClass());
        int scale = unsafe.arrayIndexScale(objects.getClass());
        switch (scale) {
            case 4:
                long factor = is64bit ? 8 : 1;
                final long i1 = (unsafe.getInt(objects, offset) & 0xFFFFFFFFL) * factor;
                System.out.print(Long.toHexString(i1));
//                last = i1;
//                for (int i = 1; i < objects.length; i++) {
//                    final long i2 = (unsafe.getInt(objects, offset + i * 4) & 0xFFFFFFFFL) * factor;
//                    if (i2 > last) {
//                        System.out.print(", +" + Long.toHexString(i2 - last));
//                    } else {
//                        System.out.print(", -" + Long.toHexString( last - i2));
//                    }
//                    last = i2;
//                }
                break;
            case 8:
                throw new AssertionError("Not supported");
            default:
                break;
        }
        System.out.println();
    }

    private static Unsafe getUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
