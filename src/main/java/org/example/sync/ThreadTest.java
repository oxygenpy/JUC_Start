package org.example.sync;

/**
 * @author LiJinLong
 * @description
 * @create ${YEAR}-${MONTH}-${DAY} ${TIME}
 * @date 1.0
 */
class SyncTest {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "aa").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "bb").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "cc").start();
    }
}



class Ticket{
    private Integer num = 30;
    public synchronized void sale() {
        if (num > 0) {
            System.out.println(Thread.currentThread().getName() + " 卖出的票：" + (num--) + " 剩余的票：" + num);
        }
    }
}