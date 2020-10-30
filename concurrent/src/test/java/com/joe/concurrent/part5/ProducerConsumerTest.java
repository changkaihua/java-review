package com.joe.concurrent.part5;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * @author ckh
 * @create 10/30/20 4:49 PM
 */
public class ProducerConsumerTest {

    @Test
    public void startIndexing() {
        File file = new File("/home/joe/IdeaProjects/java-review/concurrent/src/main/java/com/joe/concurrent");
        File file1 = new File("src/main/java/com/joe/concurrent");
//        File[] files = {file1};
        ProducerConsumer.startIndexing(file1);
    }
}