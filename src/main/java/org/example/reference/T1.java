package org.example.reference;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-08 11:44
 * @date 1.0
 */
public class T1 {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("jjjjjj");
        System.out.println(threadLocal.get());
    }
}
