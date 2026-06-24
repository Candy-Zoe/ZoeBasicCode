// ============================================================
// Go 函数 - 函数基础
// 运行方式：go run 01_函数基础.go
// ============================================================

package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println("=== 1. 函数基本用法 ===")
	greet()
	greetUser("张三")
	fmt.Println("add(3, 5) =", add(3, 5))

	fmt.Println("\n=== 2. 多返回值 ===")
	sum, product := calculate(10, 3)
	fmt.Printf("10 和 3: 和 = %d, 积 = %d\n", sum, product)

	// 忽略返回值
	s, _ := calculate(5, 2)
	fmt.Println("只取和:", s)

	fmt.Println("\n=== 3. 命名返回值 ===")
	quotient, remainder := divide(10, 3)
	fmt.Printf("10 / 3: 商 = %d, 余数 = %d\n", quotient, remainder)

	fmt.Println("\n=== 4. 可变参数 ===")
	fmt.Println("sumAll(1,2,3) =", sumAll(1, 2, 3))
	fmt.Println("sumAll(1,2,3,4,5) =", sumAll(1, 2, 3, 4, 5))

	// 传入切片
	nums := []int{1, 2, 3, 4, 5, 6}
	fmt.Println("sumAll(nums...) =", sumAll(nums...))

	fmt.Println("\n=== 5. 递归函数 ===")
	fmt.Println("阶乘:")
	for i := 0; i < 6; i++ {
		fmt.Printf("%d! = %d\n", i, factorial(i))
	}

	fmt.Println("\n=== 6. 函数作为值 ===")
	// 函数变量
	var addFunc func(int, int) int = add
	fmt.Println("函数变量调用:", addFunc(10, 20))

	// 匿名函数
	multiply := func(a, b int) int {
		return a * b
	}
	fmt.Println("匿名函数:", multiply(3, 4))

	fmt.Println("\n=== 7. 闭包 ===")
	counter := makeCounter()
	fmt.Println("闭包计数:", counter()) // 1
	fmt.Println("闭包计数:", counter()) // 2
	fmt.Println("闭包计数:", counter()) // 3

	counter2 := makeCounter()
	fmt.Println("新闭包计数:", counter2()) // 1（独立的计数）

	fmt.Println("\n=== 8. defer 延迟执行 ===")
	fmt.Println("开始")
	defer fmt.Println("延迟执行1")
	defer fmt.Println("延迟执行2")
	fmt.Println("结束")
	// defer 是栈式执行，后声明的先执行

	fmt.Println("\n=== 9. 函数使用示例 ===")
	fmt.Println("斐波那契前 10 项:")
	for i := 0; i < 10; i++ {
		fmt.Printf("%d ", fibonacci(i))
	}
	fmt.Println()

	fmt.Println("\n1 到 50 之间的质数:")
	for num := 1; num <= 50; num++ {
		if isPrime(num) {
			fmt.Printf("%d ", num)
		}
	}
	fmt.Println()
}

// 无参数无返回值
func greet() {
	fmt.Println("Hello, World!")
}

// 带参数
func greetUser(name string) {
	fmt.Printf("你好，%s！\n", name)
}

// 带返回值
func add(a int, b int) int {
	return a + b
}

// 多返回值
func calculate(a, b int) (int, int) {
	return a + b, a * b
}

// 命名返回值
func divide(a, b int) (quotient int, remainder int) {
	quotient = a / b
	remainder = a % b
	return // 自动返回命名的返回值
}

// 可变参数
func sumAll(nums ...int) int {
	total := 0
	for _, n := range nums {
		total += n
	}
	return total
}

// 递归函数
func factorial(n int) int {
	if n == 0 || n == 1 {
		return 1
	}
	return n * factorial(n-1)
}

// 闭包
func makeCounter() func() int {
	count := 0
	return func() int {
		count++
		return count
	}
}

// 斐波那契
func fibonacci(n int) int {
	if n <= 1 {
		return n
	}
	return fibonacci(n-1) + fibonacci(n-2)
}

// 判断质数
func isPrime(n int) bool {
	if n < 2 {
		return false
	}
	for i := 2; i <= int(math.Sqrt(float64(n))); i++ {
		if n%i == 0 {
			return false
		}
	}
	return true
}
