package org.example.pool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-28 21:11
 * @date 1.0
 */
public class ThreadPoolDemo1 {

    public static void main(String[] args) {
        // 一池n线程
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
        ExecutorService threadPool3 = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                threadPool3.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool3.shutdown();
        }

    }




}
