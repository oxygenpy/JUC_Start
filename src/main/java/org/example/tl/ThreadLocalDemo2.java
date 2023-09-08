package org.example.tl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-07 22:06
 * @date 1.0
 */
class MyData{
    ThreadLocal<Integer> threadLocalField = ThreadLocal.withInitial(()->0);
    public void add() {
        threadLocalField.set(threadLocalField.get() + 1);
    }
}
public class ThreadLocalDemo2 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        MyData myData = new MyData();

        try {
            // 模拟10个线程
            for (int i = 0; i < 10; i++) {
                threadPool.submit(()->{
                    try {
                        Integer start = myData.threadLocalField.get();
                        myData.add();
                        Integer end = myData.threadLocalField.get();
                        System.out.println(Thread.currentThread().getName() + "\t 初值:" + start + "\t 计算后：" + end);
                    } finally {
                        myData.threadLocalField.remove();
                    }
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }
    }
}
