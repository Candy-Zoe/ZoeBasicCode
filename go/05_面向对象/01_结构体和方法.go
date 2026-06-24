// ============================================================
// Go 面向对象 - 结构体和方法
// 运行方式：go run 01_结构体和方法.go
// ============================================================

package main

import "fmt"

// 1. 结构体（Go 没有 class，用 struct）
type Person struct {
	Name string
	Age  int
	City string
}

// 2. 方法（给结构体添加方法）
func (p Person) Greet() {
	fmt.Printf("你好，我是%s，今年%d岁，来自%s\n", p.Name, p.Age, p.City)
}

// 值接收者（不修改原对象）
func (p Person) BirthdayValue() {
	p.Age++
}

// 指针接收者（修改原对象）
func (p *Person) BirthdayPointer() {
	p.Age++
}

// 3. 结构体嵌套
type Student struct {
	Person    // 匿名字段，相当于继承
	StudentID string
	Score     float64
}

func (s Student) Study() {
	fmt.Printf("%s 在学习，成绩: %.1f\n", s.Name, s.Score)
}

func main() {
	fmt.Println("=== 1. 结构体基本使用 ===")
	p1 := Person{Name: "张三", Age: 25, City: "北京"}
	fmt.Println("结构体:", p1)
	fmt.Println("姓名:", p1.Name)
	fmt.Println("年龄:", p1.Age)

	p2 := Person{"李四", 30, "上海"}
	fmt.Println("简写方式:", p2)

	p3 := Person{Name: "王五"}
	fmt.Println("部分字段初始化:", p3)

	fmt.Println("\n=== 2. 指针结构体 ===")
	p4 := &Person{Name: "赵六", Age: 28, City: "深圳"}
	fmt.Println("指针结构体:", *p4)
	fmt.Println("姓名:", p4.Name) // 自动解引用

	fmt.Println("\n=== 3. 方法 ===")
	p1.Greet()

	// 值接收者 vs 指针接收者
	fmt.Println("\n--- 值接收者（不修改） ---")
	p5 := Person{Name: "钱七", Age: 30, City: "广州"}
	fmt.Println("调用前 Age:", p5.Age)
	p5.BirthdayValue()
	fmt.Println("调用后 Age:", p5.Age, "(没变)")

	fmt.Println("\n--- 指针接收者（修改） ---")
	p6 := Person{Name: "孙八", Age: 28, City: "杭州"}
	fmt.Println("调用前 Age:", p6.Age)
	p6.BirthdayPointer()
	fmt.Println("调用后 Age:", p6.Age, "(变了)")

	fmt.Println("\n=== 4. 结构体嵌套（组合） ===")
	s := Student{
		Person:    Person{Name: "小明", Age: 18, City: "成都"},
		StudentID: "2023001",
		Score:     95.5,
	}
	s.Greet()   // 继承自 Person
	s.Study()

	fmt.Println("\n=== 5. 结构体作为函数参数 ===")
	printPerson(p1)

	fmt.Println("\n=== 6. 结构体切片 ===")
	people := []Person{
		{Name: "张三", Age: 25, City: "北京"},
		{Name: "李四", Age: 30, City: "上海"},
		{Name: "王五", Age: 28, City: "广州"},
	}
	fmt.Println("人员列表:")
	for _, p := range people {
		fmt.Printf("  %s, %d岁, %s\n", p.Name, p.Age, p.City)
	}
}

func printPerson(p Person) {
	fmt.Printf("姓名: %s, 年龄: %d, 城市: %s\n", p.Name, p.Age, p.City)
}
