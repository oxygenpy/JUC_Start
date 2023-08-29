package org.example.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-28 20:20
 * @date 1.0
 */
public class RWDemo1 {

    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

        readLock.lock();
        System.out.println("读锁");

        writeLock.lock();
        System.out.println("写锁");

        writeLock.unlock();
        readLock.unlock();
    }
}
