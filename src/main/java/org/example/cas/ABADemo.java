package org.example.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-06 17:19
 * @date 1.0
 */
public class ABADemo {
    static AtomicInteger integer = new AtomicInteger(100);
    static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100, 1);
    public static void main(String[] args) {

        new Thread(()->{
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " 首次版本号：" + stamp);

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            stampedReference.compareAndSet(100, 101, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " 第二次版本号：" + stampedReference.getStamp());
            stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " 第三次版本号：" + stampedReference.getStamp());
        }, "t3").start();

        new Thread(()->{
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " 首次版本号：" + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean b = stampedReference.compareAndSet(100, 2022, stamp, stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " 第二次版本号：" + stampedReference.getStamp() + " \t" + b + " \t" +stampedReference.getReference());
        }, "t4").start();
    }

    private static void abaHappen() {
        new Thread(()->{
          integer.compareAndSet(100, 101);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            integer.compareAndSet(101, 100);
        }, "A").start();

        new Thread(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(integer.compareAndSet(100, 2022) + "\t" + integer.get());
        }, "B").start();
    }
}
