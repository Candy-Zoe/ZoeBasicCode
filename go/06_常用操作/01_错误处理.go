// ============================================================
// Go 常用操作 - 错误处理
// 运行方式：go run 01_错误处理.go
// ============================================================

package main

import (
	"errors"
	"fmt"
)

// 自定义错误
var ErrDivideByZero = errors.New("不能除以零")

// 安全的除法，返回错误
func divideSafe(a, b int) (int, error) {
	if b == 0 {
		return 0, ErrDivideByZero
	}
	return a / b, nil
}

// 更复杂的自定义错误类型
type MyError struct {
	Code    int
	Message string
}

func (e *MyError) Error() string {
	return fmt.Sprintf("错误 %d: %s", e.Code, e.Message)
}

func doSomething(flag bool) error {
	if !flag {
		return &MyError{Code: 404, Message: "资源未找到"}
	}
	return nil
}

func main() {
	fmt.Println("=== 1. Go 错误处理基本方式 ===")
	fmt.Println("Go 用多返回值处理错误，没有 try-catch")

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

	fmt.Println("\n=== 2. errors.New 创建错误 ===")
	err = errors.New("这是一个自定义错误")
	fmt.Println("错误信息:", err.Error())

	fmt.Println("\n=== 3. fmt.Errorf 格式化错误 ===")
	name := "test.txt"
	err = fmt.Errorf("无法打开文件 %s", name)
	fmt.Println("错误信息:", err)

	fmt.Println("\n=== 4. 自定义错误类型 ===")
	err = doSomething(false)
	if err != nil {
		fmt.Println("发生错误:", err)

		// 类型断言获取具体错误
		if myErr, ok := err.(*MyError); ok {
			fmt.Println("错误代码:", myErr.Code)
			fmt.Println("错误消息:", myErr.Message)
		}
	}

	err = doSomething(true)
	if err == nil {
		fmt.Println("执行成功")
	}

	fmt.Println("\n=== 5. defer 和 recover 捕获 panic ===")
	func() {
		defer func() {
			if r := recover(); r != nil {
				fmt.Println("捕获到 panic:", r)
			}
		}()
		fmt.Println("可能出错的代码前")
		panic("出错了！")
		fmt.Println("可能出错的代码后") // 不会执行
	}()

	fmt.Println("程序继续运行...")

	fmt.Println("\n=== 6. errors.Is 和 errors.As (Go 1.13+) ===")
	// 检查特定错误
	err = ErrDivideByZero
	if errors.Is(err, ErrDivideByZero) {
		fmt.Println("是除以零的错误")
	}

	// 提取特定类型的错误
	err = &MyError{Code: 500, Message: "服务器内部错误"}
	var myErr *MyError
	if errors.As(err, &myErr) {
		fmt.Println("MyError 类型，Code:", myErr.Code)
	}

	fmt.Println("\n=== 7. 错误处理最佳实践 ===")

	// 方式1：立即处理错误
	fmt.Println("--- 立即处理错误 ---")
	f, err := divideSafe(10, 2)
	if err != nil {
		fmt.Println("处理错误:", err)
		return
	}
	fmt.Println("结果:", f)

	// 方式2：错误传播
	fmt.Println("\n--- 错误传播 ---")
	result, err = calculateSomething(10, 0)
	if err != nil {
		fmt.Println("计算失败:", err)
	} else {
		fmt.Println("计算结果:", result)
	}
}

func calculateSomething(a, b int) (int, error) {
	result, err := divideSafe(a, b)
	if err != nil {
		return 0, fmt.Errorf("计算失败: %w", err) // 包装错误
	}
	return result * 2, nil
}
