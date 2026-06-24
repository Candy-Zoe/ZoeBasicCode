#!/bin/bash
# ============================================================
# Shell 基础语法 - 变量和数据类型
# 运行：bash 01_变量和数据类型.sh
# ============================================================

echo "=== 1. 变量定义 ==="

# 定义变量（等号两边不能有空格）
name="张三"
age=25
echo "姓名: $name, 年龄: $age"

# 只读变量
readonly PI=3.14159
echo "圆周率: $PI"
# PI=3.14  # 错误，不能修改只读变量

# 删除变量
unset age
# echo $age  # 已删除，无输出

echo ""
echo "=== 2. 字符串操作 ==="

# 字符串长度
str="Hello World"
echo "字符串: $str"
echo "字符串长度: ${#str}"

# 字符串拼接
first_name="张"
last_name="三"
full_name="${first_name}${last_name}"
echo "全名: $full_name"

# 字符串截取
echo "截取前5个字符: ${str:0:5}"  # Hello
echo "从第6个字符开始截取: ${str:6}"    # World

echo ""
echo "=== 3. 环境变量 ==="

echo "HOME: $HOME"
echo "PATH: $PATH"
echo "USER: $USER"
echo "PWD: $PWD"
echo "SHELL: $SHELL"

echo ""
echo "=== 4. 特殊变量（位置参数） ==="

echo "脚本名: $0"
echo "参数1: ${1:-无}"
echo "参数个数: $#"
echo "所有参数(\$*): $*"
echo "所有参数(\$@): $@"
echo "进程ID: $$"
echo "上一个命令的退出状态: $?"

echo ""
echo "=== 5. 变量默认值 ==="

# 如果变量未定义，使用默认值
echo "未定义的变量: ${undefined_var:-默认值}"

# 如果变量未定义或为空，使用默认值并赋值
# echo "${undefined_var:=默认值}"
# echo "赋值后: $undefined_var"

echo ""
echo "=== 运行完成 ==="
