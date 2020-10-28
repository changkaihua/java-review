package com.joe.jvm.part8.invoke;

import java.lang.invoke.*;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * @author ckh
 * @create 10/19/20 8:46 PM
 */
@SuppressWarnings("all")
public class InvokeDynamicTest {
    public static void testMethod(String s) {
        System.out.println("hello String:" + s);
    }

    public static CallSite BootstrapMethod(MethodHandles.Lookup lookup, String name, MethodType mt) throws Throwable {
        return new ConstantCallSite(lookup.findStatic(InvokeDynamicTest.class, name, mt));
    }

    private static MethodType MT_BootStrapMethod() {
        return MethodType
                .fromMethodDescriptorString(
                        "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;" +
                                "Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;", null);
    }

    private static MethodHandle MH_BootstrapMethod() throws Throwable {
        return lookup().findStatic(InvokeDynamicTest.class,
                "BootstrapMethod", MT_BootStrapMethod());
    }

    private static MethodHandle INDY_BootstrapMethod() throws Throwable {
        CallSite cs = (CallSite) MH_BootstrapMethod().invokeWithArguments(
                lookup(), "testMethod",
                MethodType.fromMethodDescriptorString(
                        "(Ljava/lang/String;)V", null));
        return cs.dynamicInvoker();
    }

    public static void main(String[] args) throws Throwable {
        INDY_BootstrapMethod().invokeExact("hello world");
    }
}
