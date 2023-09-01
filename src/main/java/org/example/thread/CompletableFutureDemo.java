package org.example.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-30 10:05
 * @date 1.0
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(new MyThread());
        // 创建线程
        Thread t1 = new Thread(task, "t1");
        t1.start();

        // 获取异步线程的处理结果
        System.out.println(task.get());
    }
}


class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("---come in call");
        return "hello callable";
    }
}
