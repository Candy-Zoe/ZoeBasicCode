// ============================================================
// Go 数据结构 - 数组和切片
// 运行方式：go run 01_数组和切片.go
// ============================================================

package main

import "fmt"

func main() {
	fmt.Println("=== 1. 数组 ===")

	// 声明数组
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

	// 数组是值类型
	fmt.Println("\n--- 数组是值类型 ---")
	arr4 := [3]int{1, 2, 3}
	arr5 := arr4
	arr5[0] = 100
	fmt.Println("原数组:", arr4)
	fmt.Println("复制后修改:", arr5)

	fmt.Println("\n=== 2. 切片 slice（动态数组） ===")

	// 字面量创建
	slice := []int{1, 2, 3, 4, 5}
	fmt.Println("切片:", slice)
	fmt.Println("长度:", len(slice))
	fmt.Println("容量:", cap(slice))

	// append 添加元素
	slice = append(slice, 6, 7)
	fmt.Println("append 后:", slice)
	fmt.Println("长度:", len(slice), "容量:", cap(slice))

	// 切片操作
	fmt.Println("\n--- 切片操作 ---")
	fmt.Println("slice[1:3] =", slice[1:3])
	fmt.Println("slice[:3] =", slice[:3])
	fmt.Println("slice[2:] =", slice[2:])

	// make 创建切片
	fmt.Println("\n--- make 创建切片 ---")
	slice2 := make([]int, 5, 10) // 长度5，容量10
	fmt.Println("make 切片:", slice2, "len:", len(slice2), "cap:", cap(slice2))

	slice3 := make([]int, 5) // 长度和容量都是5
	fmt.Println("make 切片:", slice3, "len:", len(slice3), "cap:", cap(slice3))

	fmt.Println("\n=== 3. 切片的本质 ===")

	// 切片共享底层数组
	fmt.Println("--- 切片共享底层数组 ---")
	arr6 := [5]int{1, 2, 3, 4, 5}
	s1 := arr6[1:3]
	s2 := arr6[2:4]
	fmt.Println("原数组:", arr6)
	fmt.Println("s1 =", s1)
	fmt.Println("s2 =", s2)

	s1[1] = 100 // 修改 s1 的第二个元素（即 arr6[2]）
	fmt.Println("修改 s1 后:")
	fmt.Println("原数组:", arr6)
	fmt.Println("s1 =", s1)
	fmt.Println("s2 =", s2)

	fmt.Println("\n=== 4. 遍历切片 ===")

	nums := []int{10, 20, 30, 40, 50}
	for i, v := range nums {
		fmt.Printf("索引 %d: %d\n", i, v)
	}

	fmt.Println("\n=== 5. copy 函数 ===")

	src := []int{1, 2, 3, 4, 5}
	dst := make([]int, 3)
	n := copy(dst, src)
	fmt.Println("src:", src)
	fmt.Println("dst:", dst)
	fmt.Println("复制了", n, "个元素")

	fmt.Println("\n=== 6. 切片追加多个元素 ---")
	s := []int{1, 2, 3}
	s = append(s, 4, 5, 6)
	fmt.Println("追加后:", s)

	// 追加另一个切片
	s2 := []int{7, 8, 9}
	s = append(s, s2...)
	fmt.Println("追加切片后:", s)
}
