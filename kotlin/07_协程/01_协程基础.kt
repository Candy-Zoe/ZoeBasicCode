// ============================================================
// Kotlin 协程 - 协程基础
// 编译运行：需要添加 kotlinx-coroutines-core 依赖
// 使用 Gradle: implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3'
// 或运行时使用：kotlinc -cp kotlinx-coroutines-core.jar 01_协程基础.kt
// ============================================================

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("=== 1. 第一个协程 ===")

    // launch: 启动一个新协程，不返回结果
    launch {
        delay(1000)
        println("来自协程的问候!")
    }

    println("Hello,")
    delay(2000)
    println("World!")

    // ============================================================
    // 2. runBlocking
    // ============================================================

    println("\n=== 2. runBlocking ===")

    println("runBlocking 会阻塞当前线程，直到协程完成")

    // ============================================================
    // 3. 挂起函数 suspend
    // ============================================================

    println("\n=== 3. 挂起函数 suspend ===")

    suspend fun doSomething() {
        println("开始做事...")
        delay(500)
        println("做事完成!")
    }

    launch {
        doSomething()
    }

    // ============================================================
    // 4. async 和 await
    // ============================================================

    println("\n=== 4. async 和 await ===")

    // async: 启动协程，返回 Deferred<T>，可以用 await 获取结果
    val deferred1: Deferred<Int> = async {
        delay(500)
        println("计算 1...")
        10
    }

    val deferred2: Deferred<Int> = async {
        delay(300)
        println("计算 2...")
        20
    }

    val result = deferred1.await() + deferred2.await()
    println("async 结果: $result")

    // ============================================================
    // 5. 协程取消
    // ============================================================

    println("\n=== 5. 协程取消 ===")

    val job = launch {
        repeat(10) { i ->
            println("协程运行中: $i")
            delay(200)
        }
    }

    delay(500)
    println("取消协程...")
    job.cancel()
    job.join()
    println("协程已取消")

    // ============================================================
    // 6. 协程超时
    // ============================================================

    println("\n=== 6. 协程超时 ===")

    try {
        withTimeout(500) {
            repeat(100) { i ->
                println("超时测试: $i")
                delay(100)
            }
        }
    } catch (e: TimeoutCancellationException) {
        println("超时了!")
    }

    // withTimeoutOrNull: 超时返回 null
    val result2 = withTimeoutOrNull(300) {
        delay(500)
        "完成"
    }
    println("withTimeoutOrNull 结果: $result2")

    // ============================================================
    // 7. 协程调度器
    // ============================================================

    println("\n=== 7. 协程调度器 ===")

    launch {
        println("无参 launch: ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Default) {
        println("Dispatchers.Default: ${Thread.currentThread().name}")
    }

    launch(Dispatchers.IO) {
        println("Dispatchers.IO: ${Thread.currentThread().name}")
    }

    launch(newSingleThreadContext("MyThread")) {
        println("newSingleThreadContext: ${Thread.currentThread().name}")
    }

    // ============================================================
    // 8. 协程上下文和调度器切换
    // ============================================================

    println("\n=== 8. 协程上下文和调度器切换 ===")

    launch(Dispatchers.Default) {
        println("Default 线程: ${Thread.currentThread().name}")

        withContext(Dispatchers.IO) {
            println("IO 线程: ${Thread.currentThread().name}")
            delay(100)
        }

        println("回到 Default: ${Thread.currentThread().name}")
    }

    // ============================================================
    // 9. 多个协程并行
    // ============================================================

    println("\n=== 9. 多个协程并行 ===")

    val time = measureTimeMillis {
        val one = async { doWork1() }
        val two = async { doWork2() }
        println("结果: ${one.await() + two.await()}")
    }
    println("耗时: ${time}ms (并行执行)")

    // ============================================================
    // 10. 结构化并发
    // ============================================================

    println("\n=== 10. 结构化并发 ===")

    // 父协程会等待所有子协程完成
    coroutineScope {
        launch {
            delay(200)
            println("子协程 1 完成")
        }
        launch {
            delay(100)
            println("子协程 2 完成")
        }
    }
    println("父协程完成（所有子协程都已完成）")

    // ============================================================
    // 11. 协程作用域
    // ============================================================

    println("\n=== 11. 协程作用域 ===")

    // GlobalScope: 全局作用域，生命周期与应用程序相同
    GlobalScope.launch {
        println("GlobalScope 协程")
    }

    // 自定义作用域
    val scope = CoroutineScope(Dispatchers.Default)
    scope.launch {
        println("自定义作用域协程")
    }
    scope.cancel()  // 取消作用域中的所有协程

    delay(500)
    println("\n=== 运行完成 ===")
}

suspend fun doWork1(): Int {
    delay(500)
    println("doWork1 完成")
    return 10
}

suspend fun doWork2(): Int {
    delay(500)
    println("doWork2 完成")
    return 20
}
