// ============================================================
// C++ 多线程编程 - std::thread
// 编译运行：g++ -std=c++17 01_多线程.cpp -o 01_多线程 -lpthread && ./01_多线程
// ============================================================

#include <iostream>
#include <thread>
#include <mutex>
#include <condition_variable>
#include <atomic>
#include <vector>
#include <string>
#include <chrono>
#include <future>
using namespace std;

// ============================================================
// 1. 基础线程
// ============================================================

void worker(int id) {
    cout << "[线程 " << id << "] 开始" << endl;

    for (int i = 0; i < 3; i++) {
        cout << "[线程 " << id << "] 第 " << i+1 << " 次输出" << endl;
        this_thread::sleep_for(chrono::milliseconds(100));
    }

    cout << "[线程 " << id << "] 结束" << endl;
}

void demo_basic_thread() {
    cout << "=== 1. 基础线程 ===" << endl;

    vector<thread> threads;

    for (int i = 1; i <= 3; i++) {
        threads.emplace_back(worker, i);
    }

    for (auto& t : threads) {
        t.join();
    }

    cout << "所有线程已完成" << endl;
}

// ============================================================
// 2. 线程传参
// ============================================================

struct Task {
    int id;
    string name;
    int iterations;
};

void run_task(Task t) {
    cout << "[" << t.name << "] 启动, ID=" << t.id << endl;
    for (int i = 0; i < t.iterations; i++) {
        cout << "[" << t.name << "] 工作中... " << i+1 << "/" << t.iterations << endl;
        this_thread::sleep_for(chrono::milliseconds(50));
    }
    cout << "[" << t.name << "] 完成" << endl;
}

void demo_thread_args() {
    cout << "\n=== 2. 线程传参 ===" << endl;

    Task tasks[] = {
        {1, "任务A", 3},
        {2, "任务B", 2},
        {3, "任务C", 4}
    };

    vector<thread> threads;
    for (auto& task : tasks) {
        threads.emplace_back(run_task, task);
    }

    for (auto& t : threads) {
        t.join();
    }
}

// ============================================================
// 3. 互斥锁
// ============================================================

mutex mtx;
int shared_counter = 0;

void increment_counter() {
    for (int i = 0; i < 1000; i++) {
        lock_guard<mutex> lock(mtx);  // RAII 自动加锁解锁
        shared_counter++;
    }
}

void demo_mutex() {
    cout << "\n=== 3. 互斥锁 ===" << endl;

    shared_counter = 0;
    vector<thread> threads;

    for (int i = 0; i < 5; i++) {
        threads.emplace_back(increment_counter);
    }

    for (auto& t : threads) {
        t.join();
    }

    cout << "5个线程各加1000次，最终值: " << shared_counter << endl;
    cout << "理论值: 5000" << endl;
}

// ============================================================
// 4. 原子操作
// ============================================================

atomic<int> atomic_counter(0);
atomic<bool> ready(false);

void atomic_increment() {
    while (!ready) {
        this_thread::yield();
    }

    for (int i = 0; i < 1000; i++) {
        atomic_counter++;
    }
}

void demo_atomic() {
    cout << "\n=== 4. 原子操作 ===" << endl;

    atomic_counter = 0;
    ready = false;

    vector<thread> threads;
    for (int i = 0; i < 5; i++) {
        threads.emplace_back(atomic_increment);
    }

    this_thread::sleep_for(chrono::milliseconds(100));
    ready = true;  // 通知所有线程开始

    for (auto& t : threads) {
        t.join();
    }

    cout << "原子计数: " << atomic_counter << endl;
}

// ============================================================
// 5. 条件变量 - 生产者消费者
// ============================================================

const int BUFFER_SIZE = 5;
vector<int> buffer;
mutex buffer_mtx;
condition_variable buffer_not_full;
condition_variable buffer_not_empty;

void producer(int id) {
    for (int i = 0; i < 5; i++) {
        int item = id * 10 + i;
        {
            unique_lock<mutex> lock(buffer_mtx);
            buffer_not_full.wait(lock, []{ return buffer.size() < BUFFER_SIZE; });

            buffer.push_back(item);
            cout << "[生产者" << id << "] 生产: " << item
                 << ", 缓冲区: " << buffer.size() << "/" << BUFFER_SIZE << endl;
        }
        buffer_not_empty.notify_one();
        this_thread::sleep_for(chrono::milliseconds(100));
    }
}

void consumer(int id) {
    for (int i = 0; i < 5; i++) {
        int item;
        {
            unique_lock<mutex> lock(buffer_mtx);
            buffer_not_empty.wait(lock, []{ return !buffer.empty(); });

            item = buffer.front();
            buffer.erase(buffer.begin());
            cout << "[消费者" << id << "] 消费: " << item
                 << ", 缓冲区: " << buffer.size() << "/" << BUFFER_SIZE << endl;
        }
        buffer_not_full.notify_one();
        this_thread::sleep_for(chrono::milliseconds(150));
    }
}

void demo_condition_variable() {
    cout << "\n=== 5. 条件变量 - 生产者消费者 ===" << endl;

    buffer.clear();
    vector<thread> threads;
    threads.emplace_back(producer, 1);
    threads.emplace_back(producer, 2);
    threads.emplace_back(consumer, 1);
    threads.emplace_back(consumer, 2);

    for (auto& t : threads) {
        t.join();
    }
    cout << "生产者消费者任务完成" << endl;
}

// ============================================================
// 6. async 和 future
// ============================================================

int compute(int x) {
    this_thread::sleep_for(chrono::milliseconds(200));
    return x * x;
}

void demo_async() {
    cout << "\n=== 6. async 和 future ===" << endl;

    // 异步执行
    future<int> result1 = async(launch::async, compute, 5);
    future<int> result2 = async(launch::async, compute, 10);
    future<int> result3 = async(launch::async, compute, 15);

    cout << "5^2 = " << result1.get() << endl;
    cout << "10^2 = " << result2.get() << endl;
    cout << "15^2 = " << result3.get() << endl;
}

// ============================================================
// 7. promise 和 packaged_task
// ============================================================

void demo_promise() {
    cout << "\n=== 7. promise ===" << endl;

    promise<string> prom;
    future<string> fut = prom.get_future();

    thread t([&prom]() {
        this_thread::sleep_for(chrono::milliseconds(100));
        prom.set_value("来自子线程的问候");
    });

    cout << "收到: " << fut.get() << endl;
    t.join();
}

int main() {
    demo_basic_thread();
    demo_thread_args();
    demo_mutex();
    demo_atomic();
    demo_condition_variable();
    demo_async();
    demo_promise();

    cout << "\n=== 运行完成 ===" << endl;
    return 0;
}