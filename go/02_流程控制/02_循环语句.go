// ============================================================
// Go 流程控制 - 循环语句
// 运行方式：go run 02_循环语句.go
// ============================================================

package main

import "fmt"

func main() {
	fmt.Println("=== 1. 基本 for 循环 ===")
	for i := 0; i < 5; i++ {
		fmt.Printf("第 %d 次循环\n", i+1)
	}

	fmt.Println("\n=== 2. 类似 while 的 for 循环 ===")
	count := 1
	for count <= 5 {
		fmt.Printf("count = %d\n", count)
		count++
	}

	fmt.Println("\n=== 3. 无限循环（用 break 退出） ===")
	n := 0
	for {
		if n >= 3 {
			break
		}
		fmt.Printf("无限循环 n = %d\n", n)
		n++
	}

	fmt.Println("\n=== 4. for-range 遍历 ===")

	// 遍历数组/切片
	fmt.Println("--- 遍历切片 ---")
	nums := []int{10, 20, 30, 40, 50}
	for i, v := range nums {
		fmt.Printf("索引 %d: %d\n", i, v)
	}

	// 只取值
	fmt.Println("\n--- 只取值（忽略索引） ---")
	for _, v := range nums {
		fmt.Printf("值: %d\n", v)
	}

	// 遍历字符串
	fmt.Println("\n--- 遍历字符串 ---")
	str := "Hello 世界"
	for i, ch := range str {
		fmt.Printf("索引 %d: %c\n", i, ch)
	}

	// 遍历 map
	fmt.Println("\n--- 遍历 map ---")
	m := map[string]int{"a": 1, "b": 2, "c": 3}
	for k, v := range m {
		fmt.Printf("%s: %d\n", k, v)
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

	fmt.Println("\n=== 6. 嵌套循环 ===")
	fmt.Println("乘法表:")
	for i := 1; i <= 5; i++ {
		for j := 1; j <= i; j++ {
			fmt.Printf("%d*%d=%-2d ", j, i, i*j)
		}
		fmt.Println()
	}

	fmt.Println("\n=== 7. 使用示例：查找质数 ===")
	fmt.Println("1 到 50 之间的质数:")
	for num := 1; num <= 50; num++ {
		if isPrime(num) {
			fmt.Printf("%d ", num)
		}
	}
	fmt.Println()

	fmt.Println("\n=== 8. 使用示例：斐波那契数列 ===")
	fmt.Println("前 10 项:")
	for i := 0; i < 10; i++ {
		fmt.Printf("%d ", fibonacci(i))
	}
	fmt.Println()
}

func isPrime(n int) bool {
	if n < 2 {
		return false
	}
	for i := 2; i*i <= n; i++ {
		if n%i == 0 {
			return false
		}
	}
	return true
}

func fibonacci(n int) int {
	if n <= 1 {
		return n
	}
	a, b := 0, 1
	for i := 2; i <= n; i++ {
		a, b = b, a+b
	}
	return b
}
