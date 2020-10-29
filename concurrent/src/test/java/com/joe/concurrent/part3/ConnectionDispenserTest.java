package com.joe.concurrent.part3;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static com.joe.concurrent.part3.ConnectionDispenser.DB_URL;
import static org.junit.Assert.*;

/**
 * @author ckh
 * @create 10/29/20 2:54 PM
 */
public class ConnectionDispenserTest {

    @Test
    public void getConnection() {
        ConnectionDispenser dispenser = new ConnectionDispenser();

//        System.out.println(dispenser.getConnection().hashCode());
//
//        System.out.println(dispenser.getConnection().hashCode());
//        System.out.println(dispenser.getConnection().hashCode());


        new Thread(() ->
        {
            System.out.println(Thread.currentThread().getName() + ": " + dispenser.getConnection().hashCode());


            try {
                Connection connection = DriverManager.getConnection(DB_URL, "root", "root");
                System.out.println(Thread.currentThread().getName() + ": " +connection.hashCode());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }, "test1").start();

        new Thread(() ->
        {
            System.out.println(Thread.currentThread().getName() + ": " + dispenser.getConnection().hashCode());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "test2").start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(dispenser.getConnection().hashCode());
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}