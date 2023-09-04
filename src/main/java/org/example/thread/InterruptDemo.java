package org.example.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-04 9:43
 * @date 1.0
 */
public class InterruptDemo {
    static volatile boolean isTrue = false;
    static AtomicBoolean aBoolean = new AtomicBoolean(false);
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "线程被通知中断");
                    break;
                }else {
                    System.out.println("hello interrupt api");
                }
            }
        }, "t1");
        t1.start();
        TimeUnit.MILLISECONDS.sleep(20);
        new Thread(()->{
            t1.interrupt();
        }, "t2").start();
    }

    private static void m2() throws InterruptedException {
        new Thread(()->{
            while (true) {
                if (aBoolean.get()) {
                    System.out.println(Thread.currentThread().getName() + "线程被通知中断");
                    break;
                }else {
                    System.out.println("hello AtomicBoolean");
                }
            }
        }, "a").start();
        TimeUnit.MILLISECONDS.sleep(20);
        new Thread(()->{
            aBoolean.set(true);
        }, "b").start();
    }

    private static void m1() throws InterruptedException {
        new Thread(()->{
            while (true) {
                if (isTrue) {
                    System.out.println(Thread.currentThread().getName() + "线程被通知中断");
                    break;
                }else {
                    System.out.println("a1");
                }
            }
        }, "a").start();
        TimeUnit.MILLISECONDS.sleep(20);
        new Thread(()->{
            isTrue = true;
        }, "b").start();
    }
}
