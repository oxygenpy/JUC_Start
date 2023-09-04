package org.example.thread;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-01 15:48
 * @date 1.0
 */
public class LockSyncDemo {

    public Object object = new Object();
//    public void mi() {
//        synchronized (object) {
//            System.out.println("------------------------obj");
//        }
//    }
    public synchronized void m1() {
        System.out.println("----------------------------obj");
    }
    public static void main(String[] args) {

    }

}
