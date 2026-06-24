/* ============================================================
 * C 语言多线程编程 - pthread
 * 编译运行：gcc 01_多线程.c -o 01_多线程 -lpthread && ./01_多线程
 * ============================================================ */

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <string.h>

// ============================================================
// 1. 基础线程创建
// ============================================================

// 线程函数必须返回 void*，参数为 void*
void* thread_function(void* arg) {
    int id = *(int*)arg;
    printf("[线程 %d] 开始执行\n", id);

    for (int i = 0; i < 3; i++) {
        printf("[线程 %d] 第 %d 次输出\n", id, i + 1);
        sleep(1);
    }

    printf("[线程 %d] 执行结束\n", id);
    return NULL;
}

void demo_basic_thread() {
    printf("=== 1. 基础线程创建 ===\n");

    pthread_t t1, t2;
    int id1 = 1, id2 = 2;

    // 创建线程
    if (pthread_create(&t1, NULL, thread_function, &id1) != 0) {
        perror("pthread_create");
        return;
    }
    if (pthread_create(&t2, NULL, thread_function, &id2) != 0) {
        perror("pthread_create");
        return;
    }

    // 等待线程结束
    pthread_join(t1, NULL);
    pthread_join(t2, NULL);

    printf("主线程：所有子线程已完成\n");
}

// ============================================================
// 2. 线程参数传递
// ============================================================

typedef struct {
    int id;
    char name[50];
    int count;
} ThreadData;

void* worker(void* arg) {
    ThreadData* data = (ThreadData*)arg;
    printf("[%s] ID=%d, 任务开始\n", data->name, data->id);

    for (int i = 0; i < data->count; i++) {
        printf("[%s] 工作中... %d/%d\n", data->name, i + 1, data->count);
        usleep(200000);  // 200ms
    }

    printf("[%s] 任务完成\n", data->name);
    return NULL;
}

void demo_thread_args() {
    printf("\n=== 2. 线程参数传递 ===\n");

    pthread_t threads[3];
    ThreadData datas[3] = {
        {1, "工人A", 3},
        {2, "工人B", 4},
        {3, "工人C", 2}
    };

    for (int i = 0; i < 3; i++) {
        pthread_create(&threads[i], NULL, worker, &datas[i]);
    }

    for (int i = 0; i < 3; i++) {
        pthread_join(threads[i], NULL);
    }
}

// ============================================================
// 3. 互斥锁 - 解决竞态条件
// ============================================================

int shared_counter = 0;
pthread_mutex_t counter_mutex = PTHREAD_MUTEX_INITIALIZER;

void* increment(void* arg) {
    for (int i = 0; i < 1000; i++) {
        // 加锁
        pthread_mutex_lock(&counter_mutex);
        shared_counter++;
        // 解锁
        pthread_mutex_unlock(&counter_mutex);
    }
    return NULL;
}

void demo_mutex() {
    printf("\n=== 3. 互斥锁 ===\n");

    shared_counter = 0;
    pthread_t threads[5];

    for (int i = 0; i < 5; i++) {
        pthread_create(&threads[i], NULL, increment, NULL);
    }

    for (int i = 0; i < 5; i++) {
        pthread_join(threads[i], NULL);
    }

    printf("5个线程各加1000次，最终值: %d\n", shared_counter);
    printf("理论值: 5000\n");
}

// ============================================================
// 4. 死锁演示（注释掉的错误示例）
// ============================================================

/*
pthread_mutex_t mutex_a = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutex_b = PTHREAD_MUTEX_INITIALIZER;

void* deadlock_thread1(void* arg) {
    pthread_mutex_lock(&mutex_a);
    printf("线程1: 拿到锁A，等待锁B\n");
    sleep(1);
    pthread_mutex_lock(&mutex_b);  // 死锁！
    printf("线程1: 拿到锁B\n");
    pthread_mutex_unlock(&mutex_b);
    pthread_mutex_unlock(&mutex_a);
    return NULL;
}

void* deadlock_thread2(void* arg) {
    pthread_mutex_lock(&mutex_b);
    printf("线程2: 拿到锁B，等待锁A\n");
    sleep(1);
    pthread_mutex_lock(&mutex_a);  // 死锁！
    printf("线程2: 拿到锁A\n");
    pthread_mutex_unlock(&mutex_a);
    pthread_mutex_unlock(&mutex_b);
    return NULL;
}
*/

// ============================================================
// 5. 条件变量 - 生产者消费者
// ============================================================

#define BUFFER_SIZE 5
int buffer[BUFFER_SIZE];
int in_index = 0, out_index = 0;
int item_count = 0;
pthread_mutex_t buffer_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t buffer_not_full = PTHREAD_COND_INITIALIZER;
pthread_cond_t buffer_not_empty = PTHREAD_COND_INITIALIZER;

void* producer(void* arg) {
    int id = *(int*)arg;
    for (int i = 0; i < 5; i++) {
        int item = id * 10 + i;

        pthread_mutex_lock(&buffer_mutex);
        while (item_count == BUFFER_SIZE) {
            printf("[生产者%d] 缓冲区满，等待...\n", id);
            pthread_cond_wait(&buffer_not_full, &buffer_mutex);
        }

        buffer[in_index] = item;
        in_index = (in_index + 1) % BUFFER_SIZE;
        item_count++;
        printf("[生产者%d] 生产: %d, 缓冲区: %d/%d\n", id, item, item_count, BUFFER_SIZE);

        pthread_cond_signal(&buffer_not_empty);
        pthread_mutex_unlock(&buffer_mutex);

        usleep(100000);
    }
    return NULL;
}

void* consumer(void* arg) {
    int id = *(int*)arg;
    for (int i = 0; i < 5; i++) {
        pthread_mutex_lock(&buffer_mutex);
        while (item_count == 0) {
            printf("[消费者%d] 缓冲区空，等待...\n", id);
            pthread_cond_wait(&buffer_not_empty, &buffer_mutex);
        }

        int item = buffer[out_index];
        out_index = (out_index + 1) % BUFFER_SIZE;
        item_count--;
        printf("[消费者%d] 消费: %d, 缓冲区: %d/%d\n", id, item, item_count, BUFFER_SIZE);

        pthread_cond_signal(&buffer_not_full);
        pthread_mutex_unlock(&buffer_mutex);

        usleep(150000);
    }
    return NULL;
}

void demo_condition_variable() {
    printf("\n=== 4. 条件变量 - 生产者消费者 ===\n");

    pthread_t prod1, prod2, cons1, cons2;
    int p1 = 1, p2 = 2, c1 = 1, c2 = 2;

    pthread_create(&prod1, NULL, producer, &p1);
    pthread_create(&prod2, NULL, producer, &p2);
    pthread_create(&cons1, NULL, consumer, &c1);
    pthread_create(&cons2, NULL, consumer, &c2);

    pthread_join(prod1, NULL);
    pthread_join(prod2, NULL);
    pthread_join(cons1, NULL);
    pthread_join(cons2, NULL);

    printf("生产者消费者任务完成\n");
}

// ============================================================
// 6. 线程返回值
// ============================================================

void* calculator(void* arg) {
    int* nums = (int*)arg;
    int* result = (int*)malloc(sizeof(int));
    *result = nums[0] * nums[1] + nums[2];
    return result;
}

void demo_thread_return() {
    printf("\n=== 5. 线程返回值 ===\n");

    pthread_t thread;
    int nums[3] = {3, 4, 5};  // 3*4+5=17

    pthread_create(&thread, NULL, calculator, nums);

    int* result;
    pthread_join(thread, (void**)&result);

    printf("3*4+5 = %d\n", *result);
    free(result);
}

int main() {
    demo_basic_thread();
    demo_thread_args();
    demo_mutex();
    demo_thread_return();
    demo_condition_variable();

    printf("\n=== 运行完成 ===\n");
    return 0;
}