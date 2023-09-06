package org.example.cas;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-06 19:59
 * @date 1.0
 */
public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
        AtomicIntegerArray array = new AtomicIntegerArray(new int[5]);
        for (int i = 0; i < array.length(); i++) {
            System.out.println(array.get(i));
        }
        array.getAndSet(0, 10);
        System.out.println(array.get(0));
        array.getAndIncrement(0);
        System.out.println(array.get(0));
    }
}