// ============================================================
// Go 面向对象和常用操作
// 运行方式：go run 03_面向对象和常用操作.go
// ============================================================

package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"os"
	"time"
)

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
	Person // 匿名字段，相当于继承
	StudentID string
	Score     float64
}

func (s Student) Study() {
	fmt.Printf("%s 在学习，成绩: %.1f\n", s.Name, s.Score)
}

// 4. 接口
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
	return 3.14159 * c.Radius * c.Radius
}

func (c Circle) Perimeter() float64 {
	return 2 * 3.14159 * c.Radius
}

// 5. 空接口
func printAny(v interface{}) {
	fmt.Printf("值: %v, 类型: %T\n", v, v)
}

func main() {
	fmt.Println("=== 1. 结构体和方法 ===")
	p1 := Person{Name: "张三", Age: 25, City: "北京"}
	p1.Greet()

	// 值接收者 vs 指针接收者
	fmt.Println("\n值接收者（不修改）:")
	p2 := Person{Name: "李四", Age: 30, City: "上海"}
	fmt.Println("调用前 Age:", p2.Age)
	p2.BirthdayValue()
	fmt.Println("调用后 Age:", p2.Age, "(没变)")

	fmt.Println("\n指针接收者（修改）:")
	p3 := Person{Name: "王五", Age: 28, City: "广州"}
	fmt.Println("调用前 Age:", p3.Age)
	p3.BirthdayPointer()
	fmt.Println("调用后 Age:", p3.Age, "(变了)")

	fmt.Println("\n=== 2. 结构体嵌套（组合） ===")
	s := Student{
		Person:    Person{Name: "小明", Age: 18, City: "深圳"},
		StudentID: "2023001",
		Score:     95.5,
	}
	s.Greet()   // 继承自 Person
	s.Study()

	fmt.Println("\n=== 3. 接口 ===")
	shapes := []Shape{
		Rectangle{Width: 4, Height: 5},
		Circle{Radius: 3},
	}
	for _, shape := range shapes {
		fmt.Printf("%T: 面积=%.2f, 周长=%.2f\n", shape, shape.Area(), shape.Perimeter())
	}

	fmt.Println("\n=== 4. 空接口 interface{} ===")
	printAny(42)
	printAny("hello")
	printAny(3.14)
	printAny(true)

	fmt.Println("\n=== 5. 类型断言 ===")
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

	fmt.Println("\n=== 6. 错误处理 ===")
	// Go 用多返回值处理错误，没有 try-catch
	result, err := divideSafe(10, 0)
	if err != nil {
		fmt.Println("错误:", err)
	} else {
		fmt.Println("结果:", result)
	}

	result, err = divideSafe(10, 3)
	if err != nil {
		fmt.Println("错误:", err)
	} else {
		fmt.Println("10/3 =", result)
	}

	fmt.Println("\n=== 7. defer 和 recover 捕获 panic ===")
	func() {
		defer func() {
			if r := recover(); r != nil {
				fmt.Println("捕获到 panic:", r)
			}
		}()
		fmt.Println("可能出错的代码前")
		// panic("出错了！") // 取消注释测试
		fmt.Println("可能出错的代码后")
	}()

	fmt.Println("\n=== 8. 文件操作 ===")
	// 写入文件
	content := []byte("第一行\n第二行\n第三行\n")
	err = ioutil.WriteFile("test.txt", content, 0644)
	if err != nil {
		fmt.Println("写入失败:", err)
		return
	}
	fmt.Println("已写入 test.txt")

	// 读取文件
	data, err := ioutil.ReadFile("test.txt")
	if err != nil {
		fmt.Println("读取失败:", err)
		return
	}
	fmt.Println("文件内容:")
	fmt.Println(string(data))

	// 使用 os 包
	file, err := os.Open("test.txt")
	if err != nil {
		fmt.Println("打开失败:", err)
	}
	defer file.Close()
	fmt.Println("文件打开成功")

	fmt.Println("\n=== 9. JSON 序列化/反序列化 ===")
	type PersonJSON struct {
		Name string `json:"name"`
		Age  int    `json:"age"`
		City string `json:"city"`
	}

	p := PersonJSON{Name: "张三", Age: 25, City: "北京"}
	jsonData, err := json.Marshal(p)
	if err != nil {
		fmt.Println("序列化失败:", err)
		return
	}
	fmt.Println("JSON:", string(jsonData))

	// 反序列化
	var p4 PersonJSON
	err = json.Unmarshal(jsonData, &p4)
	if err != nil {
		fmt.Println("反序列化失败:", err)
		return
	}
	fmt.Printf("解析后: %+v\n", p4)

	fmt.Println("\n=== 10. 时间操作 ===")
	now := time.Now()
	fmt.Println("当前时间:", now)
	fmt.Println("格式化:", now.Format("2006-01-02 15:04:05"))
	fmt.Println("年:", now.Year(), "月:", int(now.Month()), "日:", now.Day())
	fmt.Println("时:", now.Hour(), "分:", now.Minute(), "秒:", now.Second())

	// 时间计算
	tomorrow := now.Add(24 * time.Hour)
	fmt.Println("明天:", tomorrow.Format("2006-01-02"))

	// 时间戳
	fmt.Println("时间戳:", now.Unix())
	fmt.Println("纳秒时间戳:", now.UnixNano())

	fmt.Println("\n=== 11. 字符串操作 ===")
	str := "Hello, Go 语言"
	fmt.Println("原字符串:", str)
	fmt.Println("长度:", len(str))
	fmt.Println("包含 'Go':", contains(str, "Go"))
	fmt.Println("前缀 'Hello':", hasPrefix(str, "Hello"))
	fmt.Println("后缀 '语言':", hasSuffix(str, "语言"))
	fmt.Println("索引 'Go':", indexOf(str, "Go"))
	fmt.Println("替换:", replace(str, "Go", "Golang"))
	fmt.Println("大写:", toUpper(str))
	fmt.Println("小写:", toLower("HELLO"))

	fmt.Println("\n=== 12. goroutine 轻量级线程 ===")
	fmt.Println("Go 语言的并发特色：goroutine + channel")
	fmt.Println("使用 go 关键字启动 goroutine")

	fmt.Println("\n=== 清理测试文件 ===")
	os.Remove("test.txt")
	fmt.Println("已删除 test.txt")
}

// 安全的除法，返回错误
func divideSafe(a, b int) (int, error) {
	if b == 0 {
		return 0, fmt.Errorf("不能除以零")
	}
	return a / b, nil
}

// 简化的字符串函数（实际中用 strings 包）
func contains(s, substr string) bool {
	for i := 0; i <= len(s)-len(substr); i++ {
		if s[i:i+len(substr)] == substr {
			return true
		}
	}
	return false
}

func hasPrefix(s, prefix string) bool {
	if len(prefix) > len(s) {
		return false
	}
	return s[:len(prefix)] == prefix
}

func hasSuffix(s, suffix string) bool {
	if len(suffix) > len(s) {
		return false
	}
	return s[len(s)-len(suffix):] == suffix
}

func indexOf(s, substr string) int {
	for i := 0; i <= len(s)-len(substr); i++ {
		if s[i:i+len(substr)] == substr {
			return i
		}
	}
	return -1
}

func replace(s, old, new string) string {
	idx := indexOf(s, old)
	if idx == -1 {
		return s
	}
	return s[:idx] + new + s[idx+len(old):]
}

func toUpper(s string) string {
	result := make([]byte, len(s))
	for i := 0; i < len(s); i++ {
		c := s[i]
		if c >= 'a' && c <= 'z' {
			result[i] = c - 32
		} else {
			result[i] = c
		}
	}
	return string(result)
}

func toLower(s string) string {
	result := make([]byte, len(s))
	for i := 0; i < len(s); i++ {
		c := s[i]
		if c >= 'A' && c <= 'Z' {
			result[i] = c + 32
		} else {
			result[i] = c
		}
	}
	return string(result)
}
