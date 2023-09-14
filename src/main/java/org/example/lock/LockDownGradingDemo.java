package org.example.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-13 20:06
 * @date 1.0
 */
public class LockDownGradingDemo {
    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

        // 锁降级
        writeLock.lock();
        System.out.println("---写锁");

        readLock.lock();
        System.out.println("---读锁");

        writeLock.unlock();

        readLock.unlock();
    }
}
