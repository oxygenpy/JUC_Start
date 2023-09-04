package org.example.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-03 13:22
 * @date 1.0
 */

class Ticket{
    private int num = 50;
    private ReentrantLock lock = new ReentrantLock(true);
    public void sale() {
        lock.lock();
        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + " 当前量:" + num-- + " 余量:" + num);
            }
        } finally {
            lock.unlock();
        }
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        }, "aa").start();
        new Thread(()->{
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        }, "bb").start();
        new Thread(()->{
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        }, "cc").start();
    }
}
