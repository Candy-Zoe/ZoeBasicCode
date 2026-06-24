// ============================================================
// Go 面向对象 - 接口
// 运行方式：go run 02_接口.go
// ============================================================

package main

import (
	"fmt"
	"math"
)

// 1. 接口定义
type Shape interface {
	Area() float64
	Perimeter() float64
}

type Rectangle struct {
	Width  float64
	Height float64
}

func (r Rectangle) Area() float64 {
	return r.Width * r.Height
}

func (r Rectangle) Perimeter() float64 {
	return 2 * (r.Width + r.Height)
}

type Circle struct {
	Radius float64
}

func (c Circle) Area() float64 {
	return math.Pi * c.Radius * c.Radius
}

func (c Circle) Perimeter() float64 {
	return 2 * math.Pi * c.Radius
}

// 2. 空接口
func printAny(v interface{}) {
	fmt.Printf("值: %v, 类型: %T\n", v, v)
}

// 3. 带额外方法的接口
type Writer interface {
	Write(data string) error
}

type FileWriter struct {
	FileName string
}

func (fw FileWriter) Write(data string) error {
	fmt.Printf("向文件 %s 写入: %s\n", fw.FileName, data)
	return nil
}

func main() {
	fmt.Println("=== 1. 接口基本使用 ===")
	shapes := []Shape{
		Rectangle{Width: 4, Height: 5},
		Circle{Radius: 3},
	}
	for _, shape := range shapes {
		fmt.Printf("%T: 面积=%.2f, 周长=%.2f\n", shape, shape.Area(), shape.Perimeter())
	}

	fmt.Println("\n=== 2. 接口变量 ===")
	var s Shape
	s = Rectangle{Width: 6, Height: 8}
	fmt.Println("矩形面积:", s.Area())

	s = Circle{Radius: 5}
	fmt.Println("圆形面积:", s.Area())

	fmt.Println("\n=== 3. 空接口 interface{} ===")
	printAny(42)
	printAny("hello")
	printAny(3.14)
	printAny(true)

	fmt.Println("\n=== 4. 类型断言 ===")
	var i interface{} = "hello"
	s2, ok := i.(string)
	if ok {
		fmt.Println("字符串:", s2)
	}

	// 类型 switch
	var v interface{} = 100
	switch value := v.(type) {
	case int:
		fmt.Println("int 类型:", value)
	case string:
		fmt.Println("string 类型:", value)
	default:
		fmt.Println("其他类型")
	}

	fmt.Println("\n=== 5. 接口组合 ===")
	// Go 支持接口组合（一个接口包含另一个接口）
	type ReadWriter interface {
		Read() string
		Write(string) error
	}

	type SimpleRW struct {
		data string
	}

	func (rw *SimpleRW) Read() string {
		return rw.data
	}

	func (rw *SimpleRW) Write(data string) error {
		rw.data = data
		return nil
	}

	var rw ReadWriter = &SimpleRW{}
	rw.Write("Hello, Go!")
	fmt.Println("读取数据:", rw.Read())

	fmt.Println("\n=== 6. 接口使用示例：排序 ===")
	// 使用接口实现多态排序
	type Person struct {
		Name string
		Age  int
	}

	people := []Person{
		{"张三", 25},
		{"李四", 30},
		{"王五", 20},
	}

	fmt.Println("排序前:")
	for _, p := range people {
		fmt.Printf("  %s: %d岁\n", p.Name, p.Age)
	}

	// 简单的冒泡排序（按年龄）
	for i := 0; i < len(people); i++ {
		for j := i + 1; j < len(people); j++ {
			if people[i].Age > people[j].Age {
				people[i], people[j] = people[j], people[i]
			}
		}
	}

	fmt.Println("按年龄排序后:")
	for _, p := range people {
		fmt.Printf("  %s: %d岁\n", p.Name, p.Age)
	}
}
