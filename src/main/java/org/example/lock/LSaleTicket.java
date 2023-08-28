package org.example.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-24 16:16
 * @date 1.0
 */
public class LSaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "AA").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "BB").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "CC").start();
    }
}


class Ticket {
    private int num = 30;
    // 可重入锁
    private final ReentrantLock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + " 需要卖出的票：" + (num--) + " 剩余的票：" + num);
            }
        } finally {
            lock.unlock();
        }

    }
}