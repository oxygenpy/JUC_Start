package org.example.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-01 11:12
 * @date 1.0
 */

class Phone {
    public static synchronized void sendEmail()  {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("-----sendEmail");
    }
    public synchronized void sendSMS() {
        System.out.println("-----sendSMS");
    }

    public void hello() {
        System.out.println("hello");
    }
}

public class Thread8Demo {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            phone.sendEmail();
        }, "a").start();
        TimeUnit.MILLISECONDS.sleep(200);
        new Thread(()->{
            phone2.sendSMS();
            // phone.hello();
        }, "b").start();
    }
}
