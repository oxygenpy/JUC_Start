package org.example.reference;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-08 9:51
 * @date 1.0
 */
class MyObject{

    @Override
    protected void finalize() throws Throwable {
        // finalize是在对象被不可撤销的丢弃之前执行清理工作
        System.out.println("-------------- invoke finalize method !!!");
    }
}
public class ReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(new MyObject(), referenceQueue);

        List<byte[]> list = new ArrayList<>();
        new Thread(()->{
            while (true) {
                list.add(new byte[1 * 1024 * 1024]);
                try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println(phantomReference.get() + "\t byte[] add to list");
            }
        }, "t1").start();

        new Thread(()->{
            while (true) {
                Reference<? extends MyObject> reference = referenceQueue.poll();
                if (reference != null) {
                    System.out.println("------------虚引用对象被回收，加入引用队列...");
                    break;
                }
            }
        }, "t2").start();
    }

    private static void wakeReference() throws InterruptedException {
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.out.println("gc before 内存够用：" + weakReference.get());
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("gc after 内存够用：" + weakReference.get());
    }

    private static void softReference() throws InterruptedException {
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        System.out.println(softReference.get());

        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("gc after 内存够用 " + softReference.get());

        try {
            byte[] temp = new byte[20 * 1024 * 1024];
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("gc after 内存不用 " + softReference.get());
        }
    }

    private static void strongReference() throws InterruptedException {
        MyObject myObject = new MyObject();
        System.out.println("gc before " + myObject);

        myObject = null;
        System.gc();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("gc after " + myObject);
    }
}
