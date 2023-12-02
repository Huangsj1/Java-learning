package day8;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class MyThreadDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 多线程的启动方法
        // 一、定义类继承Thread
        // 1.自定义一个类继承Thread
        // 2.重写run方法
        // 3.创建子类对象，启动线程
        /*System.out.println("---------方式一----------");
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.setName("t1");
        t2.setName("t2");

        t1.start();
        t2.start();*/

        // 二、实现接口Runable
        System.out.println("---------方式二----------");
        // 创建需要多线程执行的任务
        MyThread2 mt = new MyThread2();
        // 创建线程对象
        Thread t3 = new Thread(mt);
        Thread t4 = new Thread(mt);
        t3.setName("t3");
        t4.setName("t4");
        t3.start();
        t4.start();

        // 三、可以获取线程执行结果
        /*System.out.println("---------方式三----------");
        // 1.创建实现了接口Callable和重写了call()的对象
        MyCallable mc = new MyCallable();
        // 2.创建FutureTask对象管理多线程运行结果
        FutureTask<Integer> ft = new FutureTask<>(mc);
        // 3.创建线程对象
        Thread t5 = new Thread(ft);
        t5.start();
        Integer res = ft.get();
        System.out.println(res);*/
        System.out.printf("主线程%s结尾-----", Thread.currentThread().getName());
    }
}


class MyThread extends Thread {
    @Override
    public void run() {
        // 线程需要执行的代码
        for (int i = 0; i < 1000; i++) {
            // getName()是父类的方法，获取当前线程名
            System.out.println(getName() + ": HelloWorld");
        }
    }
}

class MyThread2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            // 获取当前线程对象
            // Thread t = Thread.currentThread();
            System.out.println(Thread.currentThread().getName() + ": Hello");
        }
    }
}

class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}