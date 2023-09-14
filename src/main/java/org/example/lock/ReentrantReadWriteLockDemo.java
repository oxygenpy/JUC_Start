package org.example.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-13 19:34
 * @date 1.0
 */
class MyResource{
    Map<String, String> map = new HashMap<String, String>();
    Lock lock = new ReentrantLock();
    ReadWriteLock rwLock = new ReentrantReadWriteLock();
    public void write(String key, String value) {
        rwLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "---开始写入");
            TimeUnit.MILLISECONDS.sleep(500);
            map.put(key, value);

            System.out.println(Thread.currentThread().getName() + "---完成写入");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }
    public void read(String key) {
        rwLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "---开始读取");
            TimeUnit.MILLISECONDS.sleep(2000);
            String s = map.get(key);
            System.out.println(Thread.currentThread().getName() + "---读取完成结果:" + s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }

    }

}

public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        MyResource myResource = new MyResource();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                myResource.write(finalI+"", finalI + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                myResource.read(finalI +"");
            }, String.valueOf(i)).start();
        }

        TimeUnit.SECONDS.sleep(1);

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(()->{
                myResource.write(finalI+"", finalI + "");
            }, "新写线程-" + String.valueOf(i)).start();
        }
    }
}
