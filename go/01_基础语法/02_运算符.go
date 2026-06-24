// ============================================================
// Go 基础语法 - 运算符
// 运行方式：go run 02_运算符.go
// ============================================================

package main

import "fmt"

func main() {
	fmt.Println("=== 1. 算术运算符 ===")
	a, b := 10, 3

	fmt.Println("a =", a, ", b =", b)
	fmt.Println("a + b =", a+b)   // 加法
	fmt.Println("a - b =", a-b)   // 减法
	fmt.Println("a * b =", a*b)   // 乘法
	fmt.Println("a / b =", a/b)   // 除法（整数除法）
	fmt.Println("a % b =", a%b)   // 取模（余数）

	// 自增、自减
	a++
	fmt.Println("a++ 后 a =", a)
	b--
	fmt.Println("b-- 后 b =", b)

	fmt.Println("\n=== 2. 关系运算符 ===")
	x, y := 5, 10

	fmt.Println("x =", x, ", y =", y)
	fmt.Println("x == y :", x == y)   // 等于
	fmt.Println("x != y :", x != y)   // 不等于
	fmt.Println("x > y  :", x > y)    // 大于
	fmt.Println("x < y  :", x < y)    // 小于
	fmt.Println("x >= y :", x >= y)   // 大于等于
	fmt.Println("x <= y :", x <= y)   // 小于等于

	fmt.Println("\n=== 3. 逻辑运算符 ===")
	t, f := true, false

	fmt.Println("t =", t, ", f =", f)
	fmt.Println("t && f :", t && f)   // 逻辑与（AND）
	fmt.Println("t || f :", t || f)   // 逻辑或（OR）
	fmt.Println("!t     :", !t)       // 逻辑非（NOT）
	fmt.Println("!f     :", !f)

	// 短路求值
	fmt.Println("\n短路求值示例：")
	n := 0
	if false && (n == 0) {
		fmt.Println("不会执行到这里")
	}
	fmt.Println("短路后 n =", n, "（n 没有被求值）")

	fmt.Println("\n=== 4. 位运算符 ===")
	m, n2 := 60, 13 // 60 = 0011 1100, 13 = 0000 1101

	fmt.Printf("m = %d (二进制: %08b)\n", m, m)
	fmt.Printf("n = %d (二进制: %08b)\n", n2, n2)
	fmt.Printf("m & n  = %d (二进制: %08b)  按位与\n", m&n2, m&n2)
	fmt.Printf("m | n  = %d (二进制: %08b)  按位或\n", m|n2, m|n2)
	fmt.Printf("m ^ n  = %d (二进制: %08b)  按位异或\n", m^n2, m^n2)
	fmt.Printf("m << 2 = %d (二进制: %08b)  左移\n", m<<2, m<<2)
	fmt.Printf("m >> 2 = %d (二进制: %08b)  右移\n", m>>2, m>>2)

	fmt.Println("\n=== 5. 赋值运算符 ===")
	var c int = 10
	fmt.Println("初始 c =", c)

	c += 5
	fmt.Println("c += 5 后 c =", c)

	c -= 3
	fmt.Println("c -= 3 后 c =", c)

	c *= 2
	fmt.Println("c *= 2 后 c =", c)

	c /= 4
	fmt.Println("c /= 4 后 c =", c)

	c %= 3
	fmt.Println("c %= 3 后 c =", c)

	fmt.Println("\n=== 6. 其他运算符 ===")
	d := 42
	p := &d
	fmt.Println("d 的地址:", p)
	fmt.Println("p 指向的值:", *p)

	*p = 100
	fmt.Println("通过指针修改后 d =", d)

	fmt.Println("\n=== 7. 运算符优先级示例 ===")
	result := 5 + 3*2
	fmt.Println("5 + 3 * 2 =", result, "（先乘后加）")

	result = (5 + 3) * 2
	fmt.Println("(5 + 3) * 2 =", result, "（括号优先）")
}
