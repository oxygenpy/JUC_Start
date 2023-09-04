package org.example.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-03 19:03
 * @date 1.0
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        new Thread(()->{
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + " 持有锁o1,争抢锁o2");
                try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {throw new RuntimeException(e);}
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + "抢到锁o2");
                }
            }
        }, "A").start();
        new Thread(()->{
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + " 持有锁o2,争抢锁o1");
                try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {throw new RuntimeException(e);}
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "抢到锁o1");
                }
            }
        }, "B").start();
    }

}
