package org.example.syncup;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-11 14:23
 * @date 1.0
 */
public class SynchronizedUpDemo {
    static Object object = new Object();
    void m1() {
        Object o = new Object();
        synchronized (o) {
            System.out.println("------------m1" + o.hashCode() + "\t" + object.hashCode());
        }
    }
    public static void main(String[] args) throws InterruptedException {
        SynchronizedUpDemo upDemo = new SynchronizedUpDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                upDemo.m1();
            }, String.valueOf(i)).start();
        }
    }
}
