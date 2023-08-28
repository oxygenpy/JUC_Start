package org.example.lock;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间定制化通信
 * @author LiJinLong
 * @description
 * @create 2023-08-24 19:22
 * @date 1.0
 */

class ShareResource {
    private int flag = 1;
    /**
     * 可重入锁,condition替代了object类中的wait和通知，
     */
    private final Lock lock = new ReentrantLock();
    private final Condition c1 = lock.newCondition();
    private final Condition c2 = lock.newCondition();
    private final Condition c3 = lock.newCondition();

    public void print5(int loop) throws InterruptedException {
        lock.lock();

        try {
            while (flag != 1) {
                c1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i + ":" +loop);
            }
            flag = 2;
            c2.signal();
        } finally {
            lock.unlock();
        }

    }

    public void print10(int loop) throws InterruptedException {
        lock.lock();

        try {
            while (flag != 2) {
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i + ":" +loop);
            }
            flag = 3;
            c3.signal();
        } finally {
            lock.unlock();
        }

    }

    public void print15(int loop) throws InterruptedException {
        lock.lock();

        try {
            while (flag != 3) {
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i + ": 轮数：" +loop);
            }
            flag = 1;
            c1.signal();
        } finally {
            lock.unlock();
        }

    }
}
public class ThreadDemo3 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "AA").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "BB").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "CC").start();
    }
}
