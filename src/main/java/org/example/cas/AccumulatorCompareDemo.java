package org.example.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-07 9:42
 * @date 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class ClickNumber{
    int number = 0;
    public synchronized void addBySynchronized() {
        number++;
    }
    AtomicLong atomicLong = new AtomicLong(0);
    public void addByAtomicLong() {
        atomicLong.getAndIncrement();
    }
    LongAdder longAdder = new LongAdder();
    public void addByLongAdder() {
        longAdder.increment();
    }
    LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 0);
    public void addByLongAccumulator() {
        longAccumulator.accumulate(1);
    }
}


public class AccumulatorCompareDemo {
    private final static int SIZE = 50;
    private final static int _W = 10000;
    public static void main(String[] args) throws InterruptedException {
        ClickNumber clickNumber = new ClickNumber();

        CountDownLatch countDownLatch1 = new CountDownLatch(SIZE);
        CountDownLatch countDownLatch2 = new CountDownLatch(SIZE);
        CountDownLatch countDownLatch3 = new CountDownLatch(SIZE);
        CountDownLatch countDownLatch4 = new CountDownLatch(SIZE);
        long start, end;
        start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j < 100 * _W; j++) {
                        clickNumber.addBySynchronized();
                    }
                } finally {
                    countDownLatch1.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch1.await();
        end = System.currentTimeMillis();
        System.out.println("synchronized:" + (end - start) + "  计算结果：" + clickNumber.getNumber());


        start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j < 100 * _W; j++) {
                        clickNumber.addByAtomicLong();
                    }
                } finally {
                    countDownLatch2.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch2.await();
        end = System.currentTimeMillis();
        //
        System.out.println("atomicLong:" + (end - start) + "  计算结果：" + clickNumber.getNumber());

        start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j < 100 * _W; j++) {
                        clickNumber.addByLongAdder();
                    }
                } finally {
                    countDownLatch3.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch3.await();
        end = System.currentTimeMillis();
        // synchronized:1531  计算结果：50000000
        System.out.println("longAdder:" + (end - start) + "  计算结果：" + clickNumber.getNumber());

        start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j < 100 * _W; j++) {
                        clickNumber.addByLongAccumulator();
                    }
                } finally {
                    countDownLatch4.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch4.await();
        end = System.currentTimeMillis();
        // synchronized:1531  计算结果：50000000
        System.out.println("longAccumulator:" + (end - start) + "  计算结果：" + clickNumber.getNumber());
    }

}
