package com.joe.jvm.part2oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ckh
 * @create 10/12/20 4:16 PM
 */
public class HeapOOM {

    static class OOMObject {

    }

    /**
     * VM args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }

    }

}
