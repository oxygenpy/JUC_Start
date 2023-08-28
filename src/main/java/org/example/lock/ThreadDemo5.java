package org.example.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-25 15:11
 * @date 1.0
 */
public class ThreadDemo5 {
    public static void main(String[] args) {
//        Object o = new Object();
//        new Thread(()->{
//            synchronized (o) {
//                System.out.println(Thread.currentThread().getName()+"外层");
//                synchronized (o) {
//                    System.out.println(Thread.currentThread().getName()+"中层");
//                    synchronized (o) {
//                        System.out.println(Thread.currentThread().getName()+"内层");
//                    }
//                }
//            }
//        }, "aaa").start();

        ReentrantLock lock = new ReentrantLock();
        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"外层");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName()+"内层");

                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }

        }, "kkkk").start();
    }
}
