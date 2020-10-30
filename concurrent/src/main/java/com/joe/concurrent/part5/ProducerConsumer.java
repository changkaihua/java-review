package com.joe.concurrent.part5;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ProducerConsumer
 * <p/>
 * Producer and consumer tasks in a desktop search application
 *
 * @author Brian Goetz and Tim Peierls
 */
public class ProducerConsumer {

    static class FileCrawler implements Runnable {
        private final BlockingQueue<File> fileQueue;
        private final FileFilter fileFilter;
        private final File root;

        public FileCrawler(BlockingQueue<File> fileQueue,
                           final FileFilter fileFilter,
                           File root) {
            this.fileQueue = fileQueue;
            this.root = root;
            this.fileFilter = new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || fileFilter.accept(f);
                }
            };
        }

        private boolean alreadyIndexed(File f) {
            return false;
        }

        @Override
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void crawl(File root) throws InterruptedException {
            //
            File[] entries = root.listFiles(fileFilter);


            if (entries != null) {
                if (entries.length > 0) System.out.println("files " + Arrays.toString(entries));
                System.out.println("===========");
                for (File entry : entries)
                    if (entry.isDirectory())
                        crawl(entry);
                    else if (!alreadyIndexed(entry))
                        fileQueue.put(entry);
            }
        }
    }

    static class Indexer implements Runnable {
        private final BlockingQueue<File> queue;

        public Indexer(BlockingQueue<File> queue) {
            this.queue = queue;
        }

        public void run() {
            try {
                while (true)
                    indexFile(queue.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public void indexFile(File file) {
            // Index the file...
//            System.out.println(Thread.currentThread().getName() + " -> fileName = " + file.getName());
        }

    }

    private static final int BOUND = 10;
    private static final int N_CONSUMERS = Runtime.getRuntime().availableProcessors();

    public static void startIndexing(File root) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<File>(BOUND);
        // 只接受 M 开头的文件
        FileFilter filter = file -> file.getName().startsWith("M");

        new Thread(new FileCrawler(queue, filter, root)).start();

        try {
            new Indexer(queue).indexFile(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        for (int i = 0; i < N_CONSUMERS; i++)
//            new Thread(new Indexer(queue)).start();


    }
}
