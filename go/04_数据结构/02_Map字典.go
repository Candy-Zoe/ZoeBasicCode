// ============================================================
// Go 数据结构 - Map 字典
// 运行方式：go run 02_Map字典.go
// ============================================================

package main

import "fmt"

func main() {
	fmt.Println("=== 1. Map 基本操作 ===")

	// make 创建 map
	m := make(map[string]int)
	m["苹果"] = 5
	m["香蕉"] = 3
	m["橙子"] = 8
	fmt.Println("map:", m)
	fmt.Println("苹果:", m["苹果"])

	// 字面量创建
	m2 := map[string]int{
		"a": 1,
		"b": 2,
		"c": 3,
	}
	fmt.Println("字面量 map:", m2)

	fmt.Println("\n=== 2. 判断键是否存在 ===")
	value, ok := m["葡萄"]
	if ok {
		fmt.Println("葡萄:", value)
	} else {
		fmt.Println("葡萄不存在")
	}

	value, ok = m["苹果"]
	if ok {
		fmt.Println("苹果存在，值为:", value)
	}

	fmt.Println("\n=== 3. 删除元素 ===")
	fmt.Println("删除前:", m)
	delete(m, "香蕉")
	fmt.Println("删除香蕉后:", m)

	fmt.Println("\n=== 4. 遍历 map ===")
	fmt.Println("遍历 map:")
	for k, v := range m {
		fmt.Printf("  %s: %d\n", k, v)
	}

	// 只遍历键
	fmt.Println("\n只遍历键:")
	for k := range m {
		fmt.Println("  键:", k)
	}

	fmt.Println("\n=== 5. map 的长度 ===")
	fmt.Println("map 长度:", len(m))

	fmt.Println("\n=== 6. map 是引用类型 ===")
	m3 := map[string]int{"x": 10, "y": 20}
	m4 := m3
	m4["x"] = 100
	fmt.Println("原 map:", m3)
	fmt.Println("赋值后修改:", m4)

	fmt.Println("\n=== 7. 嵌套 map ===")
	students := map[string]map[string]int{
		"张三": {
			"语文": 85,
			"数学": 92,
		},
		"李四": {
			"语文": 78,
			"数学": 88,
		},
	}
	fmt.Println("学生成绩:", students)
	fmt.Println("张三的数学:", students["张三"]["数学"])

	fmt.Println("\n=== 8. 使用示例：统计字符出现次数 ===")
	str := "hello world"
	counts := make(map[rune]int)
	for _, ch := range str {
		counts[ch]++
	}
	for ch, count := range counts {
		fmt.Printf("  '%c': %d\n", ch, count)
	}

	fmt.Println("\n=== 9. 使用示例：集合（Set） ===")
	// Go 没有内置 Set，可以用 map 实现
	set := make(map[string]bool)
	items := []string{"apple", "banana", "apple", "orange", "banana"}
	for _, item := range items {
		set[item] = true
	}
	fmt.Println("去重后的元素:")
	for k := range set {
		fmt.Println(" ", k)
	}
}
