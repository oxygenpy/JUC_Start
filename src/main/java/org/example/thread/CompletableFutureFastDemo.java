package org.example.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-01 9:56
 * @date 1.0
 */
public class CompletableFutureFastDemo {
    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("a");
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {throw new RuntimeException(e);}
            return "aaaa";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("b");
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {throw new RuntimeException(e);}
            return "bbbb";
        });

        CompletableFuture<String> combine = future1.thenCombine(future2, (x, y) -> {
            System.out.println("-----------开始结果合并");
            return x + y;
        });
        System.out.println(combine.join());
    }
}
