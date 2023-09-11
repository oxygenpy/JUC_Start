package org.example.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-09 16:08
 * @date 1.0
 */
public class JolDemo {
    public static void main(String[] args) {
//        System.out.println(VM.current().details());
//        System.out.println(VM.current().objectAlignment());

        Object o1 = new Object();
        Customer c1 = new Customer();
        System.out.println(ClassLayout.parseInstance(o1).toPrintable());
        System.out.println(ClassLayout.parseInstance(c1).toPrintable());
    }
}

class Customer{
    int id;
    boolean flag;
}