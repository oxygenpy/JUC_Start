package org.example.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-25 17:39
 * @date 1.0
 */

class MyThread1 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "thread1");
    }
}

class MyThread2 implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "进入call睡眠");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + "睡眠结束线程返回");
        return System.currentTimeMillis();
    }
}

public class Demo1 {
    public static void main(String[] args) throws Exception {


//        FutureTask<Long> futureTask = new FutureTask<Long>(new MyThread2());
//
//        new Thread(futureTask, "bb").start();
//        for (int i = 0; i < 10; i++) {
//            Long integer = futureTask.get();
//            System.out.println(integer);
//        }
//        new Thread(new MyThread1(), "aa").start();

        FutureTask<Integer> task1 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + "come call");
            return 200;
        });
        new Thread(task1, "lucy").start();
        while (!task1.isDone()) {
            System.out.println("wait...");
        }
        System.out.println(task1.get());
        System.out.println(Thread.currentThread().getName() + "over");
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }


