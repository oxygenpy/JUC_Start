package org.example.lock;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-25 17:12
 * @date 1.0
 */
public class ThreadDemo6 {
    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        new Thread(()->{
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + " 持有锁a，尝试获取锁b");
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + "获取到锁b");
                }
            }
        }, "A").start();
        new Thread(()->{
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + " 持有锁b，尝试获取锁a");
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName() + "获取到锁a");
                }
            }
        }, "B").start();
    }
}
