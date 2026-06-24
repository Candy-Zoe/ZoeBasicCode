// ============================================================
// Go 高级特性 - 并发和 goroutine
// 运行方式：go run 01_并发和goroutine.go
// ============================================================

package main

import (
	"fmt"
	"time"
)

// 计算斐波那契
func fibonacci(n int) int {
	if n <= 1 {
		return n
	}
	return fibonacci(n-1) + fibonacci(n-2)
}

// 模拟一个耗时任务
func worker(id int, ch chan<- string) {
	fmt.Printf("Worker %d 开始工作...\n", id)
	time.Sleep(time.Millisecond * time.Duration(100*id))
	result := fmt.Sprintf("Worker %d 完成，结果: %d", id, fibonacci(id+5))
	ch <- result
}

// 通道同步示例
func say(s string) {
	for i := 0; i < 3; i++ {
		time.Sleep(100 * time.Millisecond)
		fmt.Println(s)
	}
}

func main() {
	fmt.Println("=== 1. goroutine 轻量级线程 ===")
	fmt.Println("Go 语言的并发特色：goroutine + channel")
	fmt.Println("使用 go 关键字启动 goroutine")

	// 启动多个 goroutine
	fmt.Println("\n--- 启动多个 goroutine ---")
	go say("世界")
	say("你好")

	fmt.Println("\n=== 2. Channel 通道 ===")
	fmt.Println("Channel 用于 goroutine 之间通信")

	// 创建通道
	ch := make(chan string)

	// 启动 worker goroutine
	go worker(1, ch)

	// 从通道接收数据
	result := <-ch
	fmt.Println("接收到:", result)

	fmt.Println("\n=== 3. 缓冲通道 ===")
	bufferedCh := make(chan int, 3) // 容量为3的缓冲通道

	bufferedCh <- 1
	bufferedCh <- 2
	bufferedCh <- 3

	fmt.Println("缓冲通道值 1:", <-bufferedCh)
	fmt.Println("缓冲通道值 2:", <-bufferedCh)
	fmt.Println("缓冲通道值 3:", <-bufferedCh)

	fmt.Println("\n=== 4. select 多路复用 ===")
	ch1 := make(chan string)
	ch2 := make(chan string)

	go func() {
		time.Sleep(200 * time.Millisecond)
		ch1 <- "来自通道1"
	}()

	go func() {
		time.Sleep(100 * time.Millisecond)
		ch2 <- "来自通道2"
	}()

	for i := 0; i < 2; i++ {
		select {
		case msg1 := <-ch1:
			fmt.Println("收到:", msg1)
		case msg2 := <-ch2:
			fmt.Println("收到:", msg2)
		}
	}

	fmt.Println("\n=== 5. range 和 close ===")
	numCh := make(chan int, 5)

	go func() {
		for i := 1; i <= 5; i++ {
			numCh <- i
		}
		close(numCh) // 关闭通道
	}()

	fmt.Println("遍历通道:")
	for num := range numCh {
		fmt.Printf("  %d\n", num)
	}

	fmt.Println("\n=== 6. 并发计算示例 ===")

	// 启动多个 worker
	jobs := 5
	results := make(chan string, jobs)

	for i := 1; i <= jobs; i++ {
		go worker(i, results)
	}

	fmt.Println("等待所有 worker 完成...")
	for i := 1; i <= jobs; i++ {
		fmt.Println(<-results)
	}

	fmt.Println("\n=== 7. sync.WaitGroup（简单实现） ===")
	fmt.Println("// sync.WaitGroup 用于等待一组 goroutine 完成")
	fmt.Println("// import \"sync\"")
	fmt.Println("// var wg sync.WaitGroup")
	fmt.Println("// wg.Add(n) - 设置计数器")
	fmt.Println("// wg.Done() - 计数器减1")
	fmt.Println("// wg.Wait() - 等待计数器为0")

	fmt.Println("\n=== 8. Mutex 互斥锁（概念） ===")
	fmt.Println("// sync.Mutex 用于保护共享资源")
	fmt.Println("// var mu sync.Mutex")
	fmt.Println("// mu.Lock() - 加锁")
	fmt.Println("// mu.Unlock() - 解锁")

	fmt.Println("\n=== 并发总结 ===")
	fmt.Println("- goroutine: 轻量级线程，用 go 关键字启动")
	fmt.Println("- channel: goroutine 之间的通信管道")
	fmt.Println("- select: 多路复用，同时监听多个通道")
	fmt.Println("- sync.WaitGroup: 等待一组 goroutine 完成")
	fmt.Println("- sync.Mutex: 互斥锁，保护共享资源")
}
