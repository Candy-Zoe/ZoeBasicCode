// ============================================================
// Go 常用操作 - 包和模块
// 运行方式：go run 02_包和模块.go
// ============================================================

package main

import (
	"encoding/json"
	"fmt"
	"strings"
	"time"
)

// 定义结构体用于 JSON 演示
type PersonJSON struct {
	Name string `json:"name"`
	Age  int    `json:"age"`
	City string `json:"city"`
}

func main() {
	fmt.Println("=== 1. 标准库常用包 ===")

	fmt.Println("\n--- strings 包（字符串操作） ---")
	str := "Hello, Go 语言"
	fmt.Println("原字符串:", str)
	fmt.Println("长度:", len(str))
	fmt.Println("包含 'Go':", strings.Contains(str, "Go"))
	fmt.Println("前缀 'Hello':", strings.HasPrefix(str, "Hello"))
	fmt.Println("后缀 '语言':", strings.HasSuffix(str, "语言"))
	fmt.Println("索引 'Go':", strings.Index(str, "Go"))
	fmt.Println("替换:", strings.Replace(str, "Go", "Golang", 1))
	fmt.Println("大写:", strings.ToUpper(str))
	fmt.Println("小写:", strings.ToLower("HELLO"))
	fmt.Println("分割:", strings.Split("a,b,c", ","))
	fmt.Println("连接:", strings.Join([]string{"a", "b", "c"}, "-"))
	fmt.Println("去空格:", strings.TrimSpace("  hello  "))

	fmt.Println("\n--- time 包（时间操作） ---")
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

	// 时间解析
	layout := "2006-01-02"
	t, err := time.Parse(layout, "2024-01-01")
	if err == nil {
		fmt.Println("解析时间:", t.Format("2006-01-02"))
	}

	fmt.Println("\n--- encoding/json 包（JSON 序列化） ---")
	p := PersonJSON{Name: "张三", Age: 25, City: "北京"}
	jsonData, err := json.Marshal(p)
	if err != nil {
		fmt.Println("序列化失败:", err)
		return
	}
	fmt.Println("JSON:", string(jsonData))

	// 格式化输出
	prettyJSON, _ := json.MarshalIndent(p, "", "  ")
	fmt.Println("格式化 JSON:")
	fmt.Println(string(prettyJSON))

	// 反序列化
	var p2 PersonJSON
	err = json.Unmarshal(jsonData, &p2)
	if err != nil {
		fmt.Println("反序列化失败:", err)
		return
	}
	fmt.Printf("解析后: %+v\n", p2)

	fmt.Println("\n=== 2. 包的导入 ===")
	fmt.Println("// 导入单个包")
	fmt.Println(`import "fmt"`)
	fmt.Println("\n// 导入多个包")
	fmt.Println(`import (
    "fmt"
    "strings"
)`)

	fmt.Println("\n=== 3. 包的可见性 ===")
	fmt.Println("// 大写字母开头的函数/变量是导出的（公开的）")
	fmt.Println("// 小写字母开头的函数/变量是未导出的（私有的）")
	fmt.Println("// 例如: fmt.Println 是公开的，可以从其他包访问")

	fmt.Println("\n=== 4. Go Modules ===")
	fmt.Println("// 初始化模块: go mod init 模块名")
	fmt.Println("// 添加依赖: go get 包名")
	fmt.Println("// 下载依赖: go mod download")
	fmt.Println("// 整理依赖: go mod tidy")
	fmt.Println("// 查看依赖: go list -m all")

	fmt.Println("\n=== 5. 文件操作（ioutil/os 包） ===")
	fmt.Println("// 写入文件: ioutil.WriteFile")
	fmt.Println("// 读取文件: ioutil.ReadFile")
	fmt.Println("// 打开文件: os.Open")
	fmt.Println("// 创建文件: os.Create")

	fmt.Println("\n运行完成！")
}
