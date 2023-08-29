package org.example.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author LiJinLong
 * @description
 * @create 2023-08-29 10:18
 * @date 1.0
 */

class MyTask extends RecursiveTask<Integer> {

    private static final Integer VALUE = 10;
    private int begin;
    private int end;
    private int result;
    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - begin <= VALUE) {
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        } else {
            int mid = (begin + end) / 2;
            MyTask task1 = new MyTask(begin, mid);
            MyTask task2 = new MyTask(mid + 1, end);
            task1.fork();
            task2.fork();
            result = task1.join() + task2.join();
        }
        return result;
    }
}

public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(0, 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myTask);
        Integer integer = submit.get();
        System.out.println(integer);

        forkJoinPool.shutdown();
    }

}
