package org.example.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-25 10:29
 * @date 1.0
 */
public class Lock8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(()->{
            try {
                Phone.sendSMS();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "AA").start();

        Thread.sleep(100);

        new Thread(()->{
            try {
                // Phone.sendEmail();
                Phone.sendEmail();
                // phone2.sendEmail();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, "BB").start();
    }


}


class  Phone {

    public static synchronized void sendSMS() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("-----------sendSMS");
    }

    public static synchronized void sendEmail() throws Exception {
        System.out.println("-----------sendEmail");
    }

    public void getHello() {
        System.out.println("---------getHello");
    }

}
