package org.example.thread;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-30 15:42
 * @date 1.0
 */
public class FutureAPIDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        // 创建一个异步任务
        FutureTask<String> task = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(5);
            return "task over";
        });

        Thread t1 = new Thread(task);
        t1.start();

        System.out.println(Thread.currentThread().getName() + "---忙其他任务了");

        // System.out.println(task.get());
        // System.out.println(task.get(2, TimeUnit.SECONDS));

        while (true) {
            if (task.isDone()) {
                System.out.println(task.get());
                break;
            }else {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("程序正在执行");
            }
        }

    }


    @Test
    public void t1() {

    }
}
