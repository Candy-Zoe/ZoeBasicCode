# ZoeBasicCode - 多语言编程基础学习项目

让编程小白快速掌握多门语言的基础语法，通过**运行代码**和**阅读代码注释**来加强理解。

## 项目特点

- 📚 覆盖 **9 种**主流编程语言
- 📝 每个文件都有详细的中文注释
- 🏃 可直接运行的代码示例
- 🗂️ 按模块和小节分级组织，循序渐进
- 🎯 每种语言遵循相同的学习路径，便于对比学习

## 目录结构

```
ZoeBasicCode/
├── python/          # Python
├── csharp/          # C#（含完整解决方案）
├── c/               # C 语言
├── cpp/             # C++
├── java/            # Java
├── javascript/      # JavaScript
├── html/            # HTML
├── css/             # CSS
└── go/              # Go 语言
```

## 语言模块说明

### 每种语言的学习模块

| 模块 | 内容 |
|------|------|
| 01_基础语法 | 变量、数据类型、运算符 |
| 02_流程控制 | 条件语句、循环语句 |
| 03_函数 | 函数定义、参数、返回值、递归等 |
| 04_数据结构 | 数组、列表、字典、集合等 |
| 05_面向对象 | 类、对象、继承、多态等 |
| 06_常用操作 | 文件操作、异常处理、模块等 |

> 注：HTML/CSS 等标记语言模块划分略有不同

## 各语言快速开始

### 🐍 Python
```bash
cd python
python 01_基础语法/01_变量和数据类型.py
```

### 🔷 C#
需要安装 [.NET SDK](https://dotnet.microsoft.com/download)
```bash
cd csharp
# 将对应 .cs 文件内容替换到 Program.cs
dotnet run
```
或者直接用 Visual Studio / Rider 打开 `CSharpBasics.sln`

### ⚫ C 语言
需要安装 GCC (Windows: MinGW, Linux/macOS: 系统自带)
```bash
cd c
gcc 01_基础语法/01_变量和数据类型.c -o demo
./demo
```

### ➕ C++
需要安装 G++ (Windows: MinGW, Linux/macOS: 系统自带)
```bash
cd cpp
g++ -std=c++17 01_基础语法/01_变量和数据类型.cpp -o demo
./demo
```

### ☕ Java
需要安装 JDK 8+
```bash
cd java/01_基础语法
javac VariablesAndTypes.java
java VariablesAndTypes
```

### 🟨 JavaScript
**Node.js 环境：**
```bash
cd javascript
node 01_基础语法/01_变量和数据类型.js
```

**浏览器环境：** 创建 HTML 文件通过 `<script>` 标签引入

### 🌐 HTML
直接用浏览器打开 `.html` 文件即可
```bash
# 双击打开，或
start html/01_基础标签/index.html
```

### 🎨 CSS
直接用浏览器打开 `.html` 文件即可（CSS 在 HTML 文件内）
```bash
start css/01_基础语法/index.html
```

### 🐹 Go 语言
需要安装 Go 1.16+
```bash
cd go
go run 01_基础语法/01_变量和数据类型.go
```

## 学习建议

### 初学者路径
1. 从 **Python** 入门，理解编程基本概念
2. 再学 **C 语言**，理解底层原理
3. 然后根据兴趣选择 **JavaScript/Web** 或 **Java/C#** 深入

### 横向对比学习
学完一门语言后，可以对比其他语言的相同概念：
- 变量声明方式有什么不同？
- 循环语法有什么区别？
- 面向对象的实现方式差异？

## 学习资源

- 每个语言目录下都有各自的 `README.md`，包含详细说明
- 每个代码文件都有详细中文注释，边看边运行
- 修改代码观察运行结果，加深理解

## 环境配置参考

| 语言 | 官方下载 | 推荐版本 |
|------|----------|----------|
| Python | https://www.python.org | 3.10+ |
| C# / .NET | https://dotnet.microsoft.com | .NET 8 |
| C/C++ (MinGW) | https://www.mingw-w64.org | GCC 11+ |
| Java | https://adoptium.net | JDK 17 |
| Node.js | https://nodejs.org | 18 LTS |
| Go | https://go.dev | 1.21+ |

## License

MIT
