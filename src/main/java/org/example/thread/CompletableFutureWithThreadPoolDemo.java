package org.example.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-01 9:44
 * @date 1.0
 */
public class CompletableFutureWithThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("1号任务:" + Thread.currentThread().getName());
            return "ggggg";
        }).thenRun(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("2号任务:" + Thread.currentThread().getName());
        }).thenRun(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("3号任务:" + Thread.currentThread().getName());
        }).thenRun(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("4号任务:" + Thread.currentThread().getName());
        });

        System.out.println(completableFuture.get(2L, TimeUnit.SECONDS));
    }

}
