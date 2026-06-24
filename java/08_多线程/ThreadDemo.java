// ============================================================
// Java 多线程编程
// 编译运行：javac ThreadDemo.java && java ThreadDemo
// ============================================================

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class ThreadDemo {

    // ============================================================
    // 1. 继承 Thread 类
    // ============================================================

    static class MyThread extends Thread {
        private String name;

        public MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("[" + name + "] 线程开始");
            for (int i = 1; i <= 3; i++) {
                System.out.println("[" + name + "] 第 " + i + " 次输出");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("[" + name + "] 线程结束");
        }
    }

    static void demoExtendThread() {
        System.out.println("=== 1. 继承 Thread 类 ===");

        MyThread t1 = new MyThread("线程A");
        MyThread t2 = new MyThread("线程B");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("所有线程完成\n");
    }

    // ============================================================
    // 2. 实现 Runnable 接口
    // ============================================================

    static class MyRunnable implements Runnable {
        private String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                System.out.println("[" + name + "] Runnable 执行 " + i);
                try { Thread.sleep(50); } catch (InterruptedException e) {}
            }
        }
    }

    static void demoRunnable() {
        System.out.println("=== 2. 实现 Runnable 接口 ===");

        Thread t1 = new Thread(new MyRunnable("任务1"));
        Thread t2 = new Thread(new MyRunnable("任务2"));
        Thread t3 = new Thread(() -> {
            // Lambda 实现
            for (int i = 1; i <= 3; i++) {
                System.out.println("[Lambda] 执行 " + i);
                try { Thread.sleep(50); } catch (InterruptedException e) {}
            }
        });

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join(); t2.join(); t3.join();
        } catch (InterruptedException e) {}

        System.out.println();
    }

    // ============================================================
    // 3. 线程同步 - synchronized
    // ============================================================

    static class Counter {
        private int count = 0;

        // 同步方法
        public synchronized void increment() {
            count++;
        }

        public synchronized int getCount() {
            return count;
        }
    }

    static void demoSynchronized() {
        System.out.println("=== 3. 线程同步 ===");

        Counter counter = new Counter();
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            try { t.join(); } catch (InterruptedException e) {}
        }

        System.out.println("5个线程各加1000次: " + counter.getCount());
        System.out.println();
    }

    // ============================================================
    // 4. Lock 和 ReentrantLock
    // ============================================================

    static void demoLock() throws InterruptedException {
        System.out.println("=== 4. Lock 锁 ===");

        ReentrantLock lock = new ReentrantLock();
        AtomicInteger counter = new AtomicInteger(0);

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                lock.lock();
                try {
                    counter.incrementAndGet();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }

        for (Thread t : threads) t.join();
        System.out.println("使用 Lock 的计数: " + counter.get());
        System.out.println();
    }

    // ============================================================
    // 5. 线程池
    // ============================================================

    static void demoThreadPool() throws InterruptedException {
        System.out.println("=== 5. 线程池 ===");

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 6; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("[线程池] 任务 " + taskId + " 开始 - " + Thread.currentThread().getName());
                try { Thread.sleep(200); } catch (InterruptedException e) {}
                System.out.println("[线程池] 任务 " + taskId + " 完成");
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("所有任务完成\n");
    }

    // ============================================================
    // 6. Callable 和 Future
    // ============================================================

    static void demoCallable() throws Exception {
        System.out.println("=== 6. Callable 和 Future ===");

        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            Future<Integer> future = executor.submit(() -> {
                Thread.sleep(100);
                return num * num;
            });
            futures.add(future);
        }

        System.out.println("平方数结果:");
        for (int i = 0; i < futures.size(); i++) {
            System.out.println("  " + (i+1) + "^2 = " + futures.get(i).get());
        }

        executor.shutdown();
        System.out.println();
    }

    // ============================================================
    // 7. 生产者消费者
    // ============================================================

    static class ProducerConsumer {
        private final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        public void produce(int id) {
            for (int i = 0; i < 5; i++) {
                int item = id * 10 + i;
                try {
                    queue.put(item);
                    System.out.println("[生产者" + id + "] 生产: " + item + ", 队列大小: " + queue.size());
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void consume(int id) {
            for (int i = 0; i < 5; i++) {
                try {
                    int item = queue.take();
                    System.out.println("[消费者" + id + "] 消费: " + item + ", 队列大小: " + queue.size());
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void demoProducerConsumer() throws InterruptedException {
        System.out.println("=== 7. 生产者消费者 ===");

        ProducerConsumer pc = new ProducerConsumer();

        Thread p1 = new Thread(() -> pc.produce(1));
        Thread p2 = new Thread(() -> pc.produce(2));
        Thread c1 = new Thread(() -> pc.consume(1));
        Thread c2 = new Thread(() -> pc.consume(2));

        p1.start(); p2.start(); c1.start(); c2.start();
        p1.join(); p2.join(); c1.join(); c2.join();

        System.out.println();
    }

    // ============================================================
    // 8. 原子类
    // ============================================================

    static void demoAtomic() throws InterruptedException {
        System.out.println("=== 8. 原子类 ===");

        AtomicInteger atomicCounter = new AtomicInteger(0);
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    atomicCounter.incrementAndGet();
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) t.join();
        System.out.println("原子计数: " + atomicCounter.get());
        System.out.println();
    }

    // ============================================================
    // 9. CompletableFuture (Java 8+)
    // ============================================================

    static void demoCompletableFuture() throws Exception {
        System.out.println("=== 9. CompletableFuture ===");

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(200); } catch (InterruptedException e) {}
            return 42;
        });

        future.thenApply(result -> result * 2)
              .thenAccept(result -> System.out.println("最终结果: " + result))
              .get();

        // 并行执行
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> 20);
        CompletableFuture<Integer> f3 = CompletableFuture.supplyAsync(() -> 30);

        int sum = CompletableFuture.allOf(f1, f2, f3)
                .thenApply(v -> f1.join() + f2.join() + f3.join())
                .get();
        System.out.println("并行求和: " + sum);
    }

    public static void main(String[] args) throws Exception {
        demoExtendThread();
        demoRunnable();
        demoSynchronized();
        demoLock();
        demoThreadPool();
        demoCallable();
        demoProducerConsumer();
        demoAtomic();
        demoCompletableFuture();

        System.out.println("\n=== 运行完成 ===");
    }
}