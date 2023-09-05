package org.example.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-05 15:35
 * @date 1.0
 */
public class VolatileDemo {

    static boolean flag = true;
    // static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " come in");
            while (flag) {

            }
            System.out.println(Thread.currentThread().getName() + " flag被设置为false，程序停止");
        }, "t1").start();
        TimeUnit.SECONDS.sleep(2);
        flag = false;
        System.out.println(Thread.currentThread().getName() + " 修改完成");
    }
}
