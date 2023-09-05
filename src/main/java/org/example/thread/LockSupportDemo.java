package org.example.thread;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-04 11:34
 * @date 1.0
 */
public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " 被唤醒");
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + " 唤醒t1后执行");
        }, "t2").start();
    }

    private static void lockAwaitSignal() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " 被唤醒");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        }, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + " 唤醒t1后执行");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }

    private static void syncWaitNotify() throws InterruptedException {
        Object o = new Object();
        new Thread(()->{
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + " come in");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 被唤醒");
            }
        }, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            synchronized (o) {
                o.notify();
                System.out.println(Thread.currentThread().getName() + " 唤醒t1后执行");
            }
        }, "t2").start();
    }
}
