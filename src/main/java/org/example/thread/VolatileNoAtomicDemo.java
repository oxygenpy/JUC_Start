package org.example.thread;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-05 19:56
 * @date 1.0
 */
class MyNumber{

    volatile int number;
    public void addPlusPlus() {
        number++;
    }
}

public class VolatileNoAtomicDemo {

    public static void main(String[] args) throws InterruptedException {
        MyNumber number = new MyNumber();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000; j++) {
                    number.addPlusPlus();
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread().getName() + number.number);
    }


public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

}
