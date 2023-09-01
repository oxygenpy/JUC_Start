package org.example.thread;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-31 16:47
 * @date 1.0
 */
public class CompletableFutureMallDemo {

    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("tb"),
            new NetMall("pdd"),
            new NetMall("mt"),
            new NetMall("elm")
    );
    public static List<String> getPrice(List<NetMall> list, String productName) {
        return list.stream().map(netMall ->
                String.format(productName + "in %s is price %.2f", netMall.getName(), netMall.price(productName)))
                .collect(Collectors.toList());
    }

    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName) {

        return list.stream().map(netMall -> CompletableFuture.supplyAsync(()->
            String.format(productName + "in %s is price %.2f", netMall.getName(), netMall.price(productName))))
                .collect(Collectors.toList()).stream().map(s -> s.join()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<String> stringList = getPrice(list, "mysql");
        for (String s : stringList) {
            System.out.println(s);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        long start1 = System.currentTimeMillis();
        List<String> stringList1 = getPriceByCompletableFuture(list, "mysql");
        for (String s : stringList1) {
            System.out.println(s);
        }
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);
    }


}

class NetMall {
    @Getter
    private String name;
    public NetMall(String name) {
        this.name = name;
    }
    public double price(String mallName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + mallName.charAt(0);
    }
}