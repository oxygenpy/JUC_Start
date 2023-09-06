package org.example.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-06 11:09
 * @date 1.0
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(5);
//        System.out.println(integer.compareAndSet(5, 2022) + "  " + integer.get());
//        System.out.println(integer.compareAndSet(5, 2022) + "  " + integer.get());
        integer.getAndIncrement();
    }
}
