package org.example.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-06 20:09
 * @date 1.0
 */
public class AtomicMarkableReferenceDemo {

    public static void main(String[] args) {
        AtomicMarkableReference<Integer> markableReference = new AtomicMarkableReference<>(100, false);
        new Thread(()->{
            boolean marked = markableReference.isMarked();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            markableReference.compareAndSet(100, 1000, marked, !marked);
        }, "t1").start();

        new Thread(()->{
            boolean marked = markableReference.isMarked();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean b = markableReference.compareAndSet(100, 2000, marked, !marked);
            System.out.println(Thread.currentThread().getName() + " 结果:" + b + " \t" + markableReference.isMarked() + "\t" + markableReference.getReference());
        }, "t2").start();
    }

}
