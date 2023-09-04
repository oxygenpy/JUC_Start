package org.example.thread;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-04 10:58
 * @date 1.0
 */
public class InterruptDemo4 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println("-----1");
        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println("-----2");
    }
}
