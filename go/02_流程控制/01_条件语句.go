// ============================================================
// Go 流程控制 - 条件语句
// 运行方式：go run 01_条件语句.go
// ============================================================

package main

import "fmt"

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
	fmt.Println("\n--- if 带初始化语句 ---")
	if n := 10; n > 5 {
		fmt.Println("n > 5")
	}

	if age := 20; age >= 18 {
		fmt.Println("成年人")
	} else {
		fmt.Println("未成年人")
	}

	fmt.Println("\n=== 2. switch 语句 ===")
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
	fmt.Println("\n--- switch 带初始化 ---")
	switch n := 8; {
	case n < 0:
		fmt.Println("负数")
	case n < 10:
		fmt.Println("个位数")
	default:
		fmt.Println("多位数")
	}

	// switch 多个条件值
	fmt.Println("\n--- switch 多个条件值 ---")
	month := 2
	switch month {
	case 1, 3, 5, 7, 8, 10, 12:
		fmt.Println("31 天")
	case 4, 6, 9, 11:
		fmt.Println("30 天")
	case 2:
		fmt.Println("28 或 29 天")
	default:
		fmt.Println("无效月份")
	}

	// switch 类型断言（type switch）
	fmt.Println("\n--- type switch 类型断言 ---")
	var v interface{} = 100
	switch value := v.(type) {
	case int:
		fmt.Println("int 类型:", value)
	case string:
		fmt.Println("string 类型:", value)
	case float64:
		fmt.Println("float64 类型:", value)
	default:
		fmt.Println("其他类型")
	}
}
