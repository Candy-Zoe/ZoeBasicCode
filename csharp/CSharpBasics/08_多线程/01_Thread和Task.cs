// ============================================================
// C# 多线程编程
// 运行方式：将此文件内容替换 Program.cs 后运行 dotnet run
// ============================================================

using System;
using System.Threading;
using System.Threading.Tasks;
using System.Collections.Generic;
using System.Collections.Concurrent;
using System.Linq;

namespace CSharpBasics
{
    class ThreadDemo
    {
        static void Main(string[] args)
        {
            Console.WriteLine("=== 1. Thread 基础 ===");
            DemoThread();

            Console.WriteLine("\n=== 2. ThreadPool ===");
            DemoThreadPool();

            Console.WriteLine("\n=== 3. Task 和 async/await ===");
            DemoTask();

            Console.WriteLine("\n=== 4. 锁与同步 ===");
            DemoLock();

            Console.WriteLine("\n=== 5. 并发集合 ===");
            DemoConcurrent();

            Console.WriteLine("\n=== 6. 并行编程 (PLINQ) ===");
            DemoParallel();

            Console.WriteLine("\n=== 7. Channel 生产者消费者 ===");
            DemoChannel();

            Console.WriteLine("\n运行完成");
        }

        // ============================================================
        // 1. Thread 基础
        // ============================================================
        static void DemoThread()
        {
            // 使用 Thread
            Thread t1 = new Thread(() =>
            {
                for (int i = 1; i <= 3; i++)
                {
                    Console.WriteLine($"[Thread1] 第 {i} 次");
                    Thread.Sleep(100);
                }
            });
            t1.Start();

            // 带参数的 Thread
            Thread t2 = new Thread((obj) =>
            {
                string name = (string)obj;
                for (int i = 1; i <= 3; i++)
                {
                    Console.WriteLine($"[{name}] 第 {i} 次");
                    Thread.Sleep(80);
                }
            });
            t2.Start("Thread2");

            t1.Join();
            t2.Join();
        }

        // ============================================================
        // 2. ThreadPool
        // ============================================================
        static void DemoThreadPool()
        {
            for (int i = 1; i <= 5; i++)
            {
                int taskId = i;
                ThreadPool.QueueUserWorkItem(state =>
                {
                    Console.WriteLine($"[线程池任务 {taskId}] 线程ID: {Thread.CurrentThread.ManagedThreadId}");
                    Thread.Sleep(50);
                });
            }

            Thread.Sleep(500);  // 等待任务完成
        }

        // ============================================================
        // 3. Task 和 async/await
        // ============================================================
        static async Task DemoTask()
        {
            // Task.Run
            Task<int> task1 = Task.Run(() =>
            {
                Thread.Sleep(200);
                return 42;
            });

            Console.WriteLine($"task1.Result = {task1.Result}");

            // async/await
            int result = await ComputeAsync(5);
            Console.WriteLine($"ComputeAsync(5) = {result}");

            // 并行执行多个任务
            Task<int> t1 = ComputeAsync(10);
            Task<int> t2 = ComputeAsync(20);
            Task<int> t3 = ComputeAsync(30);

            int[] results = await Task.WhenAll(t1, t2, t3);
            Console.WriteLine($"并行结果: [{string.Join(", ", results)}]");

            // Task.WhenAny
            Task<string> first = await Task.WhenAny(
                Task.Delay(300).ContinueWith(_ => "任务1"),
                Task.Delay(100).ContinueWith(_ => "任务2")
            );
            Console.WriteLine($"先完成的任务: {first.Result}");
        }

        static async Task<int> ComputeAsync(int x)
        {
            await Task.Delay(100);
            return x * x;
        }

        // ============================================================
        // 4. 锁与同步
        // ============================================================
        static int sharedCounter = 0;
        static readonly object lockObj = new object();
        static int atomicCounter = 0;

        static void DemoLock()
        {
            // lock 关键字
            Thread[] threads = new Thread[5];
            for (int i = 0; i < 5; i++)
            {
                threads[i] = new Thread(() =>
                {
                    for (int j = 0; j < 1000; j++)
                    {
                        lock (lockObj)
                        {
                            sharedCounter++;
                        }
                    }
                });
                threads[i].Start();
            }
            foreach (var t in threads) t.Join();
            Console.WriteLine($"lock 计数器: {sharedCounter}");

            // Interlocked 原子操作
            atomicCounter = 0;
            threads = new Thread[5];
            for (int i = 0; i < 5; i++)
            {
                threads[i] = new Thread(() =>
                {
                    for (int j = 0; j < 1000; j++)
                    {
                        Interlocked.Increment(ref atomicCounter);
                    }
                });
                threads[i].Start();
            }
            foreach (var t in threads) t.Join();
            Console.WriteLine($"Interlocked 计数器: {atomicCounter}");

            // Semaphore 信号量
            using (SemaphoreSlim semaphore = new SemaphoreSlim(2))
            {
                Task[] tasks = new Task[5];
                for (int i = 0; i < 5; i++)
                {
                    int id = i;
                    tasks[i] = Task.Run(async () =>
                    {
                        await semaphore.WaitAsync();
                        try
                        {
                            Console.WriteLine($"  [信号量任务{id}] 开始");
                            await Task.Delay(200);
                            Console.WriteLine($"  [信号量任务{id}] 结束");
                        }
                        finally
                        {
                            semaphore.Release();
                        }
                    });
                }
                Task.WaitAll(tasks);
            }
        }

        // ============================================================
        // 5. 并发集合
        // ============================================================
        static void DemoConcurrent()
        {
            // ConcurrentBag
            ConcurrentBag<int> bag = new ConcurrentBag<int>();
            Parallel.For(0, 10, i => bag.Add(i));
            Console.WriteLine($"ConcurrentBag 元素数: {bag.Count}");

            // ConcurrentDictionary
            ConcurrentDictionary<string, int> dict = new ConcurrentDictionary<string, int>();
            dict.TryAdd("apple", 1);
            dict.AddOrUpdate("banana", 1, (key, oldValue) => oldValue + 1);
            Console.WriteLine($"ConcurrentDictionary: apple={dict["apple"]}, banana={dict["banana"]}");

            // ConcurrentQueue
            ConcurrentQueue<string> queue = new ConcurrentQueue<string>();
            Parallel.ForEach(new[] { "A", "B", "C", "D" }, item => queue.Enqueue(item));

            Console.Write("ConcurrentQueue 元素: ");
            while (queue.TryDequeue(out string item))
            {
                Console.Write(item + " ");
            }
            Console.WriteLine();
        }

        // ============================================================
        // 6. 并行编程 PLINQ
        // ============================================================
        static void DemoParallel()
        {
            int[] numbers = Enumerable.Range(1, 10).ToArray();

            // Parallel.For
            Console.Write("Parallel.For: ");
            Parallel.For(0, numbers.Length, i =>
            {
                Console.Write($"{numbers[i] * numbers[i]} ");
            });
            Console.WriteLine();

            // Parallel.ForEach
            Console.Write("Parallel.ForEach: ");
            var result = new List<int>();
            Parallel.ForEach(numbers, n =>
            {
                result.Add(n * 2);
            });
            result.Sort();
            Console.WriteLine(string.Join(" ", result));

            // PLINQ
            var squares = numbers
                .AsParallel()
                .WithDegreeOfParallelism(4)
                .Where(n => n % 2 == 0)
                .Select(n => n * n)
                .ToList();

            Console.WriteLine($"PLINQ 偶数平方: [{string.Join(", ", squares)}]");

            // Parallel.Invoke
            Parallel.Invoke(
                () => Console.WriteLine("[并行任务1] 执行"),
                () => Console.WriteLine("[并行任务2] 执行"),
                () => Console.WriteLine("[并行任务3] 执行")
            );
        }

        // ============================================================
        // 7. Channel 生产者消费者
        // ============================================================
        static async Task DemoChannel()
        {
            // 简单的生产者消费者实现
            BlockingCollection<int> queue = new BlockingCollection<int>(boundedCapacity: 5);

            // 生产者
            Task producer1 = Task.Run(() =>
            {
                for (int i = 0; i < 5; i++)
                {
                    queue.Add(i);
                    Console.WriteLine($"[生产者1] 生产: {i}");
                    Thread.Sleep(50);
                }
            });

            Task producer2 = Task.Run(() =>
            {
                for (int i = 10; i < 15; i++)
                {
                    queue.Add(i);
                    Console.WriteLine($"[生产者2] 生产: {i}");
                    Thread.Sleep(60);
                }
            });

            // 消费者
            Task consumer1 = Task.Run(() =>
            {
                foreach (int item in queue.GetConsumingEnumerable())
                {
                    Console.WriteLine($"[消费者1] 消费: {item}");
                    Thread.Sleep(80);
                }
            });

            Task consumer2 = Task.Run(() =>
            {
                foreach (int item in queue.GetConsumingEnumerable())
                {
                    Console.WriteLine($"[消费者2] 消费: {item}");
                    Thread.Sleep(100);
                }
            });

            await Task.WhenAll(producer1, producer2);
            queue.CompleteAdding();
            await Task.WhenAll(consumer1, consumer2);
        }
    }
}