package com.joe.concurrent.part5;


import com.joe.concurrent.utils.DataLoadException;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import static com.joe.concurrent.utils.LaunderThrowable.launderThrowable;

/**
 * Preloader
 * <p>
 * Using FutureTask to preload data that is needed later
 *
 * @author Brian Goetz and Tim Peierls
 */

public class Preloader {
    ProductInfo loadProductInfo() throws DataLoadException {
        System.out.println("loading...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("loading success");
        return null;
    }

    private final FutureTask<ProductInfo> future =
            new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
                public ProductInfo call() throws DataLoadException {
                    return loadProductInfo();
                }
            });
    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
        System.out.println(1);
    }

    public ProductInfo get()
            throws DataLoadException, InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException)
                throw (DataLoadException) cause;
            else
                throw launderThrowable(cause);
        }
    }

    interface ProductInfo {
    }

    @Test
    public void test() {
        start();
        System.out.println(2);
        try {
            System.out.println(3);
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(4);
            ProductInfo productInfo = get();
            System.out.println(productInfo);
            System.out.println(5);
        } catch (DataLoadException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}

