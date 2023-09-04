package org.example.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-04 10:24
 * @date 1.0
 */
public class InterruptDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                System.out.println("----- " + i);
            }
            System.out.println("当前t1中断表示位：" + Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();
        System.out.println(t1.isInterrupted());
        TimeUnit.MILLISECONDS.sleep(2);
        t1.interrupt();
        System.out.println(t1.isInterrupted());

        TimeUnit.MILLISECONDS.sleep(2000);
        System.out.println(t1.isInterrupted());
    }
}
