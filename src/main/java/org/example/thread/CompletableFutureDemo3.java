package org.example.thread;

import java.util.concurrent.*;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-31 15:24
 * @date 1.0
 */
public class CompletableFutureDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture.supplyAsync(()->{
                System.out.println(Thread.currentThread().getName() + "----start");
                try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {throw new RuntimeException(e);}
                int i = ThreadLocalRandom.current().nextInt(10);
                System.out.println("任务经过1秒钟执行完成" + "---" + i);
                int t = i/0;
                return i;
            }, threadPool).whenComplete((v, e) -> {
                if (e == null) {
                    System.out.println("计算返回:" + v);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println("出现异常：" + e.getCause() + "\t" + e.getMessage());
                return null;
            });
            System.out.println(Thread.currentThread().getName() + "--主线程");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }

    }

    private static void future1() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "----start");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int i = ThreadLocalRandom.current().nextInt(10);
            System.out.println("任务经过1秒钟执行完成");
            return i;
        });
        System.out.println(Thread.currentThread().getName() + "--主线程");
        System.out.println(completableFuture.get());
    }
}
