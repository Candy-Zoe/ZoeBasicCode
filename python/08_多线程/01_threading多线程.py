# ============================================================
# Python 多线程与多进程
# ============================================================

import time
import threading
import multiprocessing
from concurrent.futures import ThreadPoolExecutor, ProcessPoolExecutor, as_completed
from queue import Queue

print("=== 1. threading 基础 ===")


def worker(name, delay):
    print(f"[线程 {name}] 开始")
    time.sleep(delay)
    print(f"[线程 {name}] 完成")


# 创建线程
threads = []
for i in range(3):
    t = threading.Thread(target=worker, args=(f"Worker-{i+1}", 0.5))
    threads.append(t)
    t.start()

# 等待所有线程
for t in threads:
    t.join()

print("所有线程完成\n")


print("=== 2. 守护线程与线程属性 ===")


def background_task():
    while True:
        print("[守护线程] 工作中...")
        time.sleep(0.5)


# 守护线程：主线程结束时自动终止
daemon = threading.Thread(target=background_task, daemon=True)
daemon.start()

print(f"线程名: {daemon.name}")
print(f"线程是否存活: {daemon.is_alive()}")
print(f"是否守护: {daemon.daemon}")

time.sleep(1.5)
print("主线程结束（守护线程也会结束）\n")


print("=== 3. 线程同步 - Lock ===")

counter = 0
lock = threading.Lock()


def increment():
    global counter
    for _ in range(1000):
        with lock:  # 自动加锁解锁
            counter += 1


threads = [threading.Thread(target=increment) for _ in range(5)]
for t in threads: t.start()
for t in threads: t.join()

print(f"5个线程各加1000次: {counter}")
print("理论值: 5000\n")


print("=== 4. RLock 可重入锁 ===")

rlock = threading.RLock()


def recursive_lock(n):
    with rlock:
        if n > 0:
            print(f"递归调用, n={n}")
            recursive_lock(n - 1)


recursive_lock(3)
print()


print("=== 5. 线程间通信 - Queue ===")

q = Queue(maxsize=5)


def producer():
    for i in range(5):
        q.put(f"消息{i}")
        print(f"[生产者] 放入: 消息{i}")
        time.sleep(0.1)


def consumer():
    while True:
        item = q.get()
        print(f"[消费者] 取出: {item}")
        q.task_done()
        if item == "消息4":
            break


t1 = threading.Thread(target=producer)
t2 = threading.Thread(target=consumer)

t1.start()
t2.start()

t1.join()
q.join()  # 等待队列处理完毕
print("生产者消费者完成\n")


print("=== 6. 线程池 ThreadPoolExecutor ===")

with ThreadPoolExecutor(max_workers=3) as executor:
    def task(n):
        time.sleep(0.2)
        return f"任务{n}结果"


    # 提交单个任务
    future1 = executor.submit(task, 1)
    print(future1.result())

    # 批量提交
    futures = [executor.submit(task, i) for i in range(1, 6)]

    for future in as_completed(futures):
        print(future.result())

print()


print("=== 7. 同步原语 - Event ===")

event = threading.Event()


def waiter():
    print("[等待者] 等待事件...")
    event.wait()
    print("[等待者] 收到事件，继续执行")


def setter():
    time.sleep(1)
    print("[设置者] 触发事件")
    event.set()


t1 = threading.Thread(target=waiter)
t2 = threading.Thread(target=setter)
t1.start()
t2.start()
t1.join()
t2.join()
print()


print("=== 8. 多进程 multiprocessing ===")


def square(n):
    return n * n


if __name__ == "__main__":
    numbers = list(range(1, 6))

    # 进程池
    with multiprocessing.Pool(processes=2) as pool:
        results = pool.map(square, numbers)
        print(f"进程池结果: {results}")

    # 进程池执行器
    with ProcessPoolExecutor(max_workers=2) as executor:
        futures = [executor.submit(square, n) for n in numbers]
        for future in as_completed(futures):
            print(f"  {future.result()}")

print()


print("=== 9. 异步编程 asyncio ===")

import asyncio


async def async_task(name, delay):
    print(f"[异步 {name}] 开始")
    await asyncio.sleep(delay)
    print(f"[异步 {name}] 完成")
    return f"{name} 的结果"


async def main_async():
    # 顺序执行
    r1 = await async_task("A", 0.2)
    print(r1)

    # 并发执行
    tasks = [
        async_task("B", 0.3),
        async_task("C", 0.1),
        async_task("D", 0.2)
    ]
    results = await asyncio.gather(*tasks)
    print(f"并发结果: {results}")


asyncio.run(main_async())


print("\n=== 10. 多线程 vs 多进程 总结 ===")
print("多线程 (threading):")
print("  优点: 共享内存, 通信简单, 创建开销小")
print("  缺点: 受 GIL 限制, 不适合 CPU 密集任务")
print("  适用: I/O 密集型任务 (网络请求, 文件读写)")
print()
print("多进程 (multiprocessing):")
print("  优点: 真正并行, 充分利用多核")
print("  缺点: 进程间通信复杂, 内存开销大")
print("  适用: CPU 密集型任务 (计算, 数据处理)")


print("\n=== 运行完成 ===")