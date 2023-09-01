package org.example.thread;

import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-29 15:05
 * @date 1.0
 */
public class DaemonDemo {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() +
                    (Thread.currentThread().isDaemon() ? "守护线程":"用户线程"));
            while (true) {

            }
        });
        t.setDaemon(true);
        t.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().isDaemon());
        System.out.println(Thread.currentThread().getName() + "--end 主线程");

    }

}
