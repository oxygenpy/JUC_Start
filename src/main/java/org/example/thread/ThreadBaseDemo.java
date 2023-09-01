package org.example.thread;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-29 14:32
 * @date 1.0
 */
public class ThreadBaseDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {

        }, "t1");
        t1.start();

        Object o = new Object();
        synchronized (o) {
            new Thread(()->{

            }).start();
        }

    }
}
