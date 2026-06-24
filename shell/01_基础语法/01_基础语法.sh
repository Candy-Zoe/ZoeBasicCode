# ============================================================
# Shell / Bash 脚本编程基础
# 运行：bash 01_基础语法.sh
# ============================================================

#!/bin/bash

echo "=== 1. 变量 ==="

# 定义变量
name="张三"
age=25
echo "姓名: $name, 年龄: $age"

# 只读变量
readonly PI=3.14159
echo "圆周率: $PI"
# PI=3.14  # 错误，不能修改

# 删除变量
unset age
# echo $age  # 已删除，无输出

# 变量类型
echo "字符串长度: ${#name}"

# 字符串拼接
first_name="张"
last_name="三"
full_name="${first_name}${last_name}"
echo "全名: $full_name"

# 字符串截取
str="Hello World"
echo "截取: ${str:0:5}"  # Hello
echo "截取: ${str:6}"    # World

# 环境变量
echo "HOME: $HOME"
echo "PATH: $PATH"
echo "USER: $USER"
echo "PWD: $PWD"

# 位置参数
echo "脚本名: $0"
echo "参数1: ${1:-无}"
echo "参数个数: $#"
echo "所有参数: $*"
echo "进程ID: $$"

# ============================================================
# 2. 数组
# ============================================================

echo ""
echo "=== 2. 数组 ==="

# 定义数组
fruits=("苹果" "香蕉" "橙子" "葡萄")

# 访问元素
echo "第一个水果: ${fruits[0]}"
echo "所有元素: ${fruits[@]}"
echo "数组长度: ${#fruits[@]}"

# 修改元素
fruits[0]="红苹果"
echo "修改后第一个: ${fruits[0]}"

# 添加元素
fruits+=("西瓜")
echo "添加后: ${fruits[@]}"

# 遍历数组
echo "遍历数组:"
for fruit in "${fruits[@]}"; do
    echo "  - $fruit"
done

# ============================================================
# 3. 运算符
# ============================================================

echo ""
echo "=== 3. 运算符 ==="

a=10
b=3

# 算术运算
echo "$a + $b = $((a + b))"
echo "$a - $b = $((a - b))"
echo "$a * $b = $((a * b))"
echo "$a / $b = $((a / b))"
echo "$a % $b = $((a % b))"

# 关系运算 - 使用 test 或 [ ]
if [ $a -gt $b ]; then
    echo "$a 大于 $b"
fi

# 字符串比较
str1="hello"
str2="world"
if [ "$str1" \< "$str2" ]; then
    echo "$str1 < $str2"
fi

# 逻辑运算
if [ $a -gt 5 ] && [ $b -lt 10 ]; then
    echo "a > 5 且 b < 10"
fi

# ============================================================
# 4. 流程控制
# ============================================================

echo ""
echo "=== 4. 流程控制 ==="

# if 语句
score=85
if [ $score -ge 90 ]; then
    echo "优秀"
elif [ $score -ge 80 ]; then
    echo "良好"
elif [ $score -ge 60 ]; then
    echo "及格"
else
    echo "不及格"
fi

# case 语句
day="周一"
case $day in
    "周一"|"周二"|"周三"|"周四"|"周五")
        echo "工作日"
        ;;
    "周六"|"周日")
        echo "周末"
        ;;
    *)
        echo "无效日期"
        ;;
esac

# for 循环
echo "for 循环:"
for i in 1 2 3 4 5; do
    echo "  $i"
done

# C 风格 for 循环
echo "C 风格 for:"
for ((i=1; i<=3; i++)); do
    echo "  $i"
done

# while 循环
echo "while 循环:"
count=1
while [ $count -le 3 ]; do
    echo "  count=$count"
    count=$((count + 1))
done

# until 循环
echo "until 循环:"
count=1
until [ $count -gt 3 ]; do
    echo "  count=$count"
    count=$((count + 1))
done

# break 和 continue
echo "break/continue:"
for i in 1 2 3 4 5; do
    if [ $i -eq 3 ]; then
        continue
    fi
    if [ $i -eq 5 ]; then
        break
    fi
    echo "  $i"
done

# ============================================================
# 5. 函数
# ============================================================

echo ""
echo "=== 5. 函数 ==="

# 定义函数
say_hello() {
    echo "Hello, World!"
}

# 带参数的函数
greet() {
    echo "你好, $1!"
}

# 返回值（只能返回 0-255 的整数）
add() {
    return $(($1 + $2))
}

say_hello
greet "张三"

add 5 3
echo "5 + 3 = $?"

# 局部变量
test_local() {
    local var="局部变量"
    echo "函数内: $var"
}
test_local
# echo "函数外: $var"  # 错误，var 是局部变量

# 递归函数
factorial() {
    if [ $1 -le 1 ]; then
        echo 1
    else
        local prev=$(factorial $(( $1 - 1 )))
        echo $(( $1 * prev ))
    fi
}

echo "5! = $(factorial 5)"

# ============================================================
# 6. 输入输出
# ============================================================

echo ""
echo "=== 6. 输入输出 ==="

# 读取输入（注释掉，避免阻塞）
# echo -n "请输入你的名字: "
# read user_name
# echo "你好, $user_name!"

# 重定向
echo "写入文件" > /tmp/test_shell.txt
echo "追加内容" >> /tmp/test_shell.txt
cat /tmp/test_shell.txt

# 管道
echo "abcdef" | tr 'a-z' 'A-Z'

# Here Document
cat << EOF
这是多行文本
第一行
第二行
第三行
EOF

# ============================================================
# 7. 文件操作
# ============================================================

echo ""
echo "=== 7. 文件操作 ==="

test_file="/tmp/test_file.txt"
echo "测试内容" > $test_file

# 判断文件是否存在
if [ -f $test_file ]; then
    echo "$test_file 存在"
fi

# 判断文件可读
if [ -r $test_file ]; then
    echo "文件可读"
fi

# 按行读取文件
echo "读取文件内容:"
while read -r line; do
    echo "  $line"
done < $test_file

# ============================================================
# 8. 常用命令
# ============================================================

echo ""
echo "=== 8. 常用命令 ==="

# grep 查找
echo "grep 示例:"
echo -e "apple\nbanana\ncherry" | grep "an"

# sed 替换
echo "sed 替换:"
echo "hello world" | sed 's/world/China/'

# awk 处理
echo "awk 示例:"
echo "1 张三 90
2 李四 85
3 王五 92" | awk '{print $2 " - " $3 "分"}'

# find 查找
echo "find 查找 /tmp 下的 .txt 文件:"
find /tmp -name "*.txt" -maxdepth 1 2>/dev/null | head -3

# sort 排序
echo "sort 排序:"
echo -e "3\n1\n2" | sort -n

# uniq 去重
echo "uniq 去重:"
echo -e "1\n1\n2\n2\n3" | uniq

# wc 统计
echo "wc 统计:"
echo "hello world" | wc -c

# ============================================================
# 9. 进程和信号
# ============================================================

echo ""
echo "=== 9. 进程和信号 ==="

# 后台运行
sleep 1 &
echo "后台进程 PID: $!"

# 等待进程完成
wait
echo "后台进程已完成"

# trap 捕获信号
trap 'echo "收到 SIGINT"; exit' SIGINT
echo "按 Ctrl+C 测试信号捕获（1秒后自动退出）"
sleep 1

# ============================================================
# 10. 脚本调试
# ============================================================

echo ""
echo "=== 10. 脚本调试 ==="
echo "set -x  开启调试"
echo "set -e  遇到错误立即退出"
echo "set -u  使用未定义变量时报错"
echo "bash -x script.sh  调试运行"

rm -f /tmp/test_shell.txt /tmp/test_file.txt

echo ""
echo "=== 运行完成 ==="