package org.example.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-28 14:46
 * @date 1.0
 */

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    public void put(String key, Object value)  {
        // 加写锁
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在写操作" + key);
            TimeUnit.MICROSECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写完成" + key);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放锁
            rwLock.writeLock().unlock();
        }
    }
    public Object get(String key) {
        // 加读锁
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在读操作" + key);
            TimeUnit.MICROSECONDS.sleep(300);
            Object result = null;
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读完成" + key);
            return result;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            rwLock.readLock().unlock();
        }
    }

}

public class ReadWriteLockDemo {

    public static void main(String[] args) {

        // 没加锁之前，写完之前就读了这是读不到的
        MyCache myCache = new MyCache();
        // 写缓存
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(()->{
                myCache.put(num + "", num + "");
            }, String.valueOf(i)).start();
        }

        // 读缓存
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(()->{
                Object o = myCache.get(num+"");
                System.out.println(o);
            }, String.valueOf(i)).start();
        }
    }

}
