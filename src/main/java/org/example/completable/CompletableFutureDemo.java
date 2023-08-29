package org.example.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-29 10:45
 * @date 1.0
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception{
        // 无返回值异步调用
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName() + "future1");
        });
        future1.get();

        // 有返回值异步调用
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + "future2");
            return 1024;
        });
        future2.whenComplete((t, u) -> {
            System.out.println("future2:t:" + t);   // 返回值
            System.out.println("future2:u:" + u);   // 异常信息
        });
        Integer integer = future2.get();
        System.out.println(integer);
    }

}
