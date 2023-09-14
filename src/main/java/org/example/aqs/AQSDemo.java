package org.example.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-13 9:31
 * @date 1.0
 */
public class AQSDemo {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();

        try {

        } finally {
            lock.unlock();
        }
    }

}
