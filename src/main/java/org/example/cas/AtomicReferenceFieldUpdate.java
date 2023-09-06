package org.example.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-06 21:01
 * @date 1.0
 */
class MyVar {
    public volatile Boolean isInit = Boolean.FALSE;
    AtomicReferenceFieldUpdater<MyVar, Boolean> updater =
            AtomicReferenceFieldUpdater.newUpdater(MyVar.class, Boolean.class, "isInit");
    public void init(MyVar myVar) throws InterruptedException {
        if (updater.compareAndSet(myVar, Boolean.FALSE, Boolean.TRUE)) {
            System.out.println(Thread.currentThread().getName() + "\t" + "init start need 3 second");
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName() + "\t over init");
        }else {
            System.out.println(Thread.currentThread().getName() + "\t已经有线程进行初始化操作");
        }
    }
}
public class AtomicReferenceFieldUpdate {
    public static void main(String[] args) {
        MyVar myVar = new MyVar();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    myVar.init(myVar);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, String.valueOf(i)).start();
        }
    }
}
