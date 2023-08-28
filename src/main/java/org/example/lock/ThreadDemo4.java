package org.example.lock;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-24 20:03
 * @date 1.0
 */
public class ThreadDemo4 {
    public static void main(String[] args) {

        // List<String> list = new ArrayList<>();
        // List<String> list = new Vector<>();
        // List<String> list = Collections.synchronizedList(new ArrayList<>());
        // List<String> list = new CopyOnWriteArrayList<>();
        // Set<String> set = new HashSet<>();
//        Set<String> set = new CopyOnWriteArraySet<>();
//
//        for (int i = 0; i < 40; i++) {
//            new Thread(()->{
//                set.add(UUID.randomUUID().toString().substring(0, 5));
//                System.out.println(set);
//            }).start();
//        }

        // Map<String, String> map = new HashMap<>();
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 40; i++) {
            String key = String.valueOf(i);
            new Thread(()->{
                map.put(key, UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }).start();
        }

    }


}


