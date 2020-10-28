package com.joe.enmulearn;

/**
 * Color
 *
 * @author ckh
 * @create 9/16/20 8:54 AM
 */
public enum Color {
    /**
     * Color
     */
    RED("red color", 21), GREEN("123", 32), BLUE("2345", 54);

    private String name;
    private int index;

    Color(String name, int index) {
        this.name = name;
        this.index = index;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    // 不能重写 equals 方法，通过重写 toString 方法改变行为
//    boolean equals(Object other){
//        return true;
//    }

    @Override
    public String toString() {
        return this.index + "_" + this.name;
    }
}
