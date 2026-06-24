// ============================================================
// Go 基础语法 - 变量和数据类型
// 运行方式：go run 01_变量和数据类型.go
// ============================================================

package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println("=== 1. 变量声明 ===")

	// var 声明
	var name string = "张三"
	var age int = 25
	fmt.Println("姓名:", name)
	fmt.Println("年龄:", age)

	// 类型推断
	var height = 1.75
	fmt.Println("身高:", height)

	// 短变量声明（最常用，只能在函数内）
	score := 95.5
	isStudent := true
	fmt.Println("成绩:", score)
	fmt.Println("是否学生:", isStudent)

	// 多变量声明
	var a, b, c int = 1, 2, 3
	fmt.Println("a =", a, "b =", b, "c =", c)

	x, y, z := 10, 20, 30
	fmt.Println("x =", x, "y =", y, "z =", z)

	fmt.Println("\n=== 2. 基本数据类型 ===")

	// 整数类型
	var i8 int8 = 127
	var i16 int16 = 32767
	var i32 int32 = 2147483647
	var i64 int64 = 9223372036854775807
	var ui uint = 100

	fmt.Println("int8:", i8)
	fmt.Println("int16:", i16)
	fmt.Println("int32:", i32)
	fmt.Println("int64:", i64)
	fmt.Println("uint:", ui)

	// 浮点类型
	var f32 float32 = 3.14
	var f64 float64 = 3.1415926535
	fmt.Println("float32:", f32)
	fmt.Println("float64:", f64, "(默认)")

	// 复数类型
	var c64 complex64 = complex(1, 2)
	fmt.Println("复数:", c64)
	fmt.Println("实部:", real(c64), "虚部:", imag(c64))

	// 布尔类型
	var boolTrue bool = true
	var boolFalse bool = false
	fmt.Println("布尔值:", boolTrue, boolFalse)

	// 字符类型（rune = int32，Unicode码点）
	var ch byte = 'A'       // byte = uint8，ASCII
	var r rune = '中'       // rune = int32，Unicode
	fmt.Println("字符:", ch, string(ch))
	fmt.Println("中文字符:", r, string(r))

	fmt.Println("\n=== 3. 字符串 ===")
	str1 := "Hello, Go!"
	fmt.Println("字符串:", str1)
	fmt.Println("长度:", len(str1))

	// 字符串拼接
	str2 := "你好，" + "世界！"
	fmt.Println("拼接:", str2)

	// 字符串索引（返回 byte）
	fmt.Println("第一个字符:", str1[0], string(str1[0]))

	// 多行字符串
	multiLine := `第一行
第二行
第三行`
	fmt.Println("多行字符串:")
	fmt.Println(multiLine)

	fmt.Println("\n=== 4. 常量 ===")
	const Pi = 3.1415926
	const MaxSize = 1024
	fmt.Println("Pi =", Pi)
	fmt.Println("MaxSize =", MaxSize)

	// iota 常量生成器
	const (
		Sunday = iota // 0
		Monday        // 1
		Tuesday       // 2
		Wednesday     // 3
	)
	fmt.Println("Sunday =", Sunday)
	fmt.Println("Monday =", Monday)
	fmt.Println("Tuesday =", Tuesday)

	fmt.Println("\n=== 5. 类型转换 ===")
	var i int = 10
	var f float64 = float64(i)
	fmt.Println("int -> float64:", i, "->", f)

	var f2 float64 = 9.99
	var i2 int = int(f2)
	fmt.Println("float64 -> int:", f2, "->", i2)

	// 字符串转数字
	// 注意：需要 strconv 包，这里简单演示
	fmt.Println("字符串转数字需要 strconv 包")

	fmt.Println("\n=== 6. 指针 ===")
	var p *int
	i3 := 42
	p = &i3
	fmt.Println("i3 的地址:", p)
	fmt.Println("p 指向的值:", *p)

	// 修改指针指向的值
	*p = 100
	fmt.Println("修改后 i3 =", i3)

	fmt.Println("\n=== 7. 数组 ===")
	var arr [5]int
	arr[0] = 1
	arr[1] = 2
	arr[2] = 3
	fmt.Println("数组:", arr)
	fmt.Println("长度:", len(arr))

	// 初始化
	arr2 := [5]int{1, 2, 3, 4, 5}
	fmt.Println("初始化数组:", arr2)

	// 不定长度
	arr3 := [...]int{10, 20, 30}
	fmt.Println("不定长度:", arr3)

	fmt.Println("\n=== 8. 切片 slice（动态数组） ===")
	slice := []int{1, 2, 3, 4, 5}
	fmt.Println("切片:", slice)
	fmt.Println("长度:", len(slice))
	fmt.Println("容量:", cap(slice))

	// append 添加元素
	slice = append(slice, 6, 7)
	fmt.Println("append 后:", slice)

	// 切片操作
	fmt.Println("slice[1:3] =", slice[1:3])
	fmt.Println("slice[:3] =", slice[:3])
	fmt.Println("slice[2:] =", slice[2:])

	// make 创建切片
	slice2 := make([]int, 5, 10) // 长度5，容量10
	fmt.Println("make 切片:", slice2, "len:", len(slice2), "cap:", cap(slice2))

	fmt.Println("\n=== 9. map 字典 ===")
	m := make(map[string]int)
	m["苹果"] = 5
	m["香蕉"] = 3
	m["橙子"] = 8
	fmt.Println("map:", m)
	fmt.Println("苹果:", m["苹果"])

	// 判断键是否存在
	value, ok := m["葡萄"]
	if ok {
		fmt.Println("葡萄:", value)
	} else {
		fmt.Println("葡萄不存在")
	}

	// 删除
	delete(m, "香蕉")
	fmt.Println("删除香蕉后:", m)

	// 遍历
	fmt.Println("遍历 map:")
	for k, v := range m {
		fmt.Printf("  %s: %d\n", k, v)
	}

	fmt.Println("\n=== 10. 结构体 ===")
	type Person struct {
		Name string
		Age  int
	}

	p1 := Person{Name: "张三", Age: 25}
	fmt.Println("结构体:", p1)
	fmt.Println("姓名:", p1.Name)

	p2 := Person{"李四", 30}
	fmt.Println("简写:", p2)

	fmt.Println("\n=== 11. 数学计算 ===")
	fmt.Println("math.Pi =", math.Pi)
	fmt.Println("math.Sqrt(16) =", math.Sqrt(16))
	fmt.Println("math.Pow(2, 10) =", math.Pow(2, 10))
	fmt.Println("math.Abs(-5) =", math.Abs(-5))
}
