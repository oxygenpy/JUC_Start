package org.example.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-28 11:53
 * @date 1.0
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    // 从信号量获取一个许可
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到了车位");

                    // 设置随机等待时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                    System.out.println(Thread.currentThread().getName()+"----离开车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    // 释放一个许可，返还给信号量
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }

    }
}
