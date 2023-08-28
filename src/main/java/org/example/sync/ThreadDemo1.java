package org.example.sync;

/**
 * @author LiJinLong
 * @description 交替执行 通知
 * @create 2023-08-24 16:39
 * @date 1.0
 */

class Share {

    private int num = 0;

    public synchronized void incr() throws InterruptedException {
        // 判断 干活 通知
        while (num != 0) {
            // wait在哪里等待，唤醒后继续向下执行，所以要写在while里，避免虚假唤醒
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + ":" + num);

        this.notifyAll();
    }

    public synchronized void decr() throws InterruptedException {
        while (num != 1) {
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + ":" + num);

        this.notifyAll();
    }

}

public class ThreadDemo1 {
    public static void main(String[] args) {
        Share share = new Share();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "AA").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "BB").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "CC").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "DD").start();
    }
}
