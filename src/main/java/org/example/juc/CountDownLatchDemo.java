package org.example.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-28 11:09
 * @date 1.0
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"号同学离开了教室");

                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        // 计数器非0，主线程阻塞
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"班长锁门了");
    }
}
