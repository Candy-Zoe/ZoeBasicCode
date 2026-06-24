// ============================================================
// Go 流程控制和函数
// 运行方式：go run 02_流程控制和函数.go
// ============================================================

package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println("=== 1. if-else 语句 ===")
	score := 85
	if score >= 90 {
		fmt.Println("优秀")
	} else if score >= 80 {
		fmt.Println("良好")
	} else if score >= 60 {
		fmt.Println("及格")
	} else {
		fmt.Println("不及格")
	}

	// if 带初始化语句（Go 特有）
	if n := 10; n > 5 {
		fmt.Println("n > 5")
	}

	fmt.Println("\n=== 2. for 循环 ===")
	// 基本 for
	for i := 0; i < 5; i++ {
		fmt.Printf("第 %d 次循环\n", i+1)
	}

	// 类似 while 的 for
	count := 1
	for count <= 5 {
		fmt.Printf("count = %d\n", count)
		count++
	}

	// 无限循环（用 break 退出）
	n := 0
	for {
		if n >= 3 {
			break
		}
		fmt.Printf("无限循环 n = %d\n", n)
		n++
	}

	fmt.Println("\n=== 3. for-range 遍历 ===")
	// 遍历数组/切片
	nums := []int{10, 20, 30, 40, 50}
	for i, v := range nums {
		fmt.Printf("索引 %d: %d\n", i, v)
	}

	// 遍历字符串
	str := "Hello 世界"
	for i, ch := range str {
		fmt.Printf("索引 %d: %c\n", i, ch)
	}

	// 遍历 map
	m := map[string]int{"a": 1, "b": 2, "c": 3}
	for k, v := range m {
		fmt.Printf("%s: %d\n", k, v)
	}

	fmt.Println("\n=== 4. switch 语句 ===")
	day := 3
	switch day {
	case 1:
		fmt.Println("星期一")
	case 2:
		fmt.Println("星期二")
	case 3:
		fmt.Println("星期三")
	case 4:
		fmt.Println("星期四")
	case 5:
		fmt.Println("星期五")
	case 6, 7:
		fmt.Println("周末")
	default:
		fmt.Println("无效")
	}

	// switch 带初始化
	switch n := 8; {
	case n < 0:
		fmt.Println("负数")
	case n < 10:
		fmt.Println("个位数")
	default:
		fmt.Println("多位数")
	}

	fmt.Println("\n=== 5. break 和 continue ===")
	fmt.Print("break 示例（到5停止）: ")
	for i := 0; i < 10; i++ {
		if i == 5 {
			break
		}
		fmt.Printf("%d ", i)
	}
	fmt.Println()

	fmt.Print("continue 示例（跳过偶数）: ")
	for i := 0; i < 10; i++ {
		if i%2 == 0 {
			continue
		}
		fmt.Printf("%d ", i)
	}
	fmt.Println()

	fmt.Println("\n=== 6. 函数基本用法 ===")
	greet()
	greetUser("张三")
	fmt.Println("add(3, 5) =", add(3, 5))

	fmt.Println("\n=== 7. 多返回值 ===")
	sum, product := calculate(10, 3)
	fmt.Printf("10 和 3: 和 = %d, 积 = %d\n", sum, product)

	// 忽略返回值
	s, _ := calculate(5, 2)
	fmt.Println("只取和:", s)

	fmt.Println("\n=== 8. 命名返回值 ===")
	fmt.Println("divide(10, 3) =", divide(10, 3))

	fmt.Println("\n=== 9. 可变参数 ===")
	fmt.Println("sumAll(1,2,3) =", sumAll(1, 2, 3))
	fmt.Println("sumAll(1,2,3,4,5) =", sumAll(1, 2, 3, 4, 5))

	fmt.Println("\n=== 10. 递归函数 ===")
	fmt.Println("阶乘:")
	for i := 0; i < 6; i++ {
		fmt.Printf("%d! = %d\n", i, factorial(i))
	}

	fmt.Println("\n=== 11. 函数作为值 ===")
	// 函数变量
	var addFunc func(int, int) int = add
	fmt.Println("函数变量调用:", addFunc(10, 20))

	// 匿名函数
	multiply := func(a, b int) int {
		return a * b
	}
	fmt.Println("匿名函数:", multiply(3, 4))

	// 闭包
	counter := makeCounter()
	fmt.Println("闭包计数:", counter()) // 1
	fmt.Println("闭包计数:", counter()) // 2
	fmt.Println("闭包计数:", counter()) // 3

	fmt.Println("\n=== 12. defer 延迟执行 ===")
	fmt.Println("开始")
	defer fmt.Println("延迟执行1")
	defer fmt.Println("延迟执行2")
	fmt.Println("结束")
	// defer 是栈式执行，后声明的先执行

	fmt.Println("\n=== 13. 使用示例：斐波那契数列 ===")
	fmt.Println("前10项:")
	for i := 0; i < 10; i++ {
		fmt.Printf("%d ", fibonacci(i))
	}
	fmt.Println()

	fmt.Println("\n=== 14. 使用示例：查找质数 ===")
	fmt.Println("1到50之间的质数:")
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
