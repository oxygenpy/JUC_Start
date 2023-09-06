package org.example.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-06 16:29
 * @date 1.0
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "  come in");
        while (!atomicReference.compareAndSet(null, thread)){

        }
    }
    public void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "  unlock over");
    }
    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo lock = new SpinLockDemo();
        new Thread(()->{
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lock.unlock();
        }, "A").start();
        TimeUnit.MILLISECONDS.sleep(500);
        new Thread(()->{
            lock.lock();

            lock.unlock();
        }, "B").start();
    }
}
