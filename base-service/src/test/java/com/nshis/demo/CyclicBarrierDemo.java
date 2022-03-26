package com.nshis.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    final static int N = 4;

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(N,
                () -> System.out.println("当前线程" + Thread.currentThread().getName()));

        for (int i = 0; i < N; i++) {
            MyThread myThread = new MyThread(barrier);
            new Thread(myThread).start();
            Thread.sleep(1000);
        }
    }

    static class MyThread implements Runnable {

        private CyclicBarrier cyclicBarrier;

        MyThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
            try {
                Thread.sleep(3000); // 以睡眠来模拟写入数据操作
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }

}
