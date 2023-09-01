package org.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-30 10:19
 * @date 1.0
 */
public class FutureThreadPoolDemo {

    public static void main(String[] args) throws Exception {
        // 串行执行1140毫秒
        // t1();
        // 采用异步线程池一共105毫秒，加上结果收集一共593毫秒
        long startTime = System.currentTimeMillis();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        FutureTask<String> task1 = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return "task1 over";
        });
        FutureTask<String> task2 = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return "task2 over";
        });
        FutureTask<String> task3 = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return "task3 over";
        });
        threadPool.submit(task1);
        threadPool.submit(task2);
        threadPool.submit(task3);
        threadPool.shutdown();
        System.out.println(task1.get());
        System.out.println(task2.get());
        System.out.println(task3.get());
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " 一共耗时：" + (endTime - startTime));
    }

    private static void t1() throws InterruptedException {
        long startTime = System.currentTimeMillis();

        TimeUnit.MILLISECONDS.sleep(500);
        TimeUnit.MILLISECONDS.sleep(300);
        TimeUnit.MILLISECONDS.sleep(300);

        long endTime = System.currentTimeMillis();
        System.out.println("-------cost time:" + (endTime - startTime));
        System.out.println(Thread.currentThread().getName() + "--over");
    }
}

