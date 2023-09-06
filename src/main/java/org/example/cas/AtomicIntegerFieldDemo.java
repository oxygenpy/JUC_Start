package org.example.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-06 20:34
 * @date 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class BankAccount{
    String bankName = "CCB";
    public volatile int money = 0;
    public void add() {
        money++;
    }
    AtomicIntegerFieldUpdater<BankAccount> updater = AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");
    public void transMoney(BankAccount bankAccount) {
        updater.getAndIncrement(bankAccount);
    }
}

public class AtomicIntegerFieldDemo {
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    for (int j = 1; j <= 1000; j++) {
                        // bankAccount.add();
                        bankAccount.transMoney(bankAccount);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(bankAccount.getMoney());
    }
}
