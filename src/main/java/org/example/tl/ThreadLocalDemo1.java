package org.example.tl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-07 21:47
 * @date 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class House {
    int saleNum = 0;
    public synchronized void addSale() {
        saleNum++;
    }

    ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(()->0);
    public void assSaleVolumeByThreadLocal() {
        saleVolume.set(saleVolume.get() + 1);
    }
}

public class ThreadLocalDemo1 {
    public static void main(String[] args) throws InterruptedException {
        House house = new House();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    int size = new Random().nextInt(5) + 1;
                    for (int j = 0; j < size; j++) {
                        house.addSale();
                        house.assSaleVolumeByThreadLocal();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t卖出：" + house.saleVolume.get());
                } finally {
                    house.saleVolume.remove();
                }
            }, String.valueOf(i)).start();
        }
        TimeUnit.MILLISECONDS.sleep(300);
        System.out.println(Thread.currentThread().getName() + "\t" + house.getSaleNum());
    }
}
