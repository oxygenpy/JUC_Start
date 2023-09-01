package org.example.thread;

import java.util.concurrent.*;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-31 15:15
 * @date 1.0
 */
public class CompletableFutureDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//            System.out.println(Thread.currentThread().getName() + "--start");
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }, threadPool);
//        System.out.println(future.get());

        CompletableFuture<String> async = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "start");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hello supplyAsync";
        });

        System.out.println(async.get());

        threadPool.shutdown();
    }

}
