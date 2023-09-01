package org.example.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-31 20:36
 * @date 1.0
 */
public class CompletableFutureAPI1Demo {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newFixedThreadPool(3);
//        CompletableFuture.supplyAsync(()->{
//            System.out.println("111");
//            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {throw new RuntimeException(e);}
//            return 1;
//        }, threadPool).handle((f, e) -> {
//            System.out.println("222");
//            int k = 1/0;
//            return f+2;
//        }).handle((f, e) -> {
//            System.out.println("333");
//            return f+3;
//        }).whenComplete((v, e) -> {
//            System.out.println("计算结果："+v);
//        }).exceptionally(e-> {
//            e.printStackTrace();
//            System.out.println(e.getCause() + "--" + e.getMessage());
//            return null;
//        }) ;
//        threadPool.shutdown();

        System.out.println(CompletableFuture.supplyAsync(()->"aaaaaa").thenRun(()->{}).join());
        System.out.println(CompletableFuture.supplyAsync(()->"aaaaaa").thenAccept((r->{
            System.out.println(r);
        })).join());
        System.out.println(CompletableFuture.supplyAsync(()->"aaaaaa").thenApply(r->r + "hhh").join());

    }

}
