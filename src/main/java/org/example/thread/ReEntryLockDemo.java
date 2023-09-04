package org.example.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-03 18:37
 * @date 1.0
 */
public class ReEntryLockDemo {
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " come in m1");
        m2();
        System.out.println(Thread.currentThread().getName() + " end m1");
    }
    public synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + " come in m2");
        m3();
        System.out.println(Thread.currentThread().getName() + " end m2");
    }
    public synchronized void m3() {
        System.out.println(Thread.currentThread().getName() + " come in m3");

        System.out.println(Thread.currentThread().getName() + " end m3");
    }

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
//        ReEntryLockDemo demo = new ReEntryLockDemo();
//        demo.m1();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " come in 外层");
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " come in 中层");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " come in 内层");
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        } finally {
            lock.unlock();
        }
    }

    private static void entryM1() {
        Object o = new Object();
        new Thread(()->{
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + "外层");
                new Thread(()->{
                    synchronized (o) {
                        System.out.println(Thread.currentThread().getName() + "中层");
                        new Thread(()->{
                            synchronized (o) {
                                System.out.println(Thread.currentThread().getName() + "内层");
                            }
                        }).start();
                    }
                }).start();
            }
        }).start();
    }
}
