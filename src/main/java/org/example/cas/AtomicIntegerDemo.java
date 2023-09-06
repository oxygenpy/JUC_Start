package org.example.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-06 19:35
 * @date 1.0
 */
class MyNumber{
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addPlusPlus() {
        atomicInteger.getAndIncrement();
    }
}
public class AtomicIntegerDemo {
    public final static int SIZE = 50;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        MyNumber myNumber = new MyNumber();
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                try {
                    for (int j = 1; j <= 1000; j++) {
                        myNumber.addPlusPlus();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t" + myNumber.atomicInteger.get());
    }

}
