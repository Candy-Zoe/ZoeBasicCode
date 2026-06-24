#!/bin/bash
# ============================================================
# Shell 函数 - 函数基础
# 运行：bash 01_函数基础.sh
# ============================================================

echo "=== 1. 函数定义和调用 ==="

# 定义函数（两种方式）
say_hello() {
    echo "Hello, World!"
}

function say_hi {
    echo "Hi, there!"
}

# 调用函数
say_hello
say_hi

echo ""
echo "=== 2. 带参数的函数 ==="

# 函数参数通过 $1, $2... 获取
greet() {
    echo "你好, $1! 你今年 $2 岁了。"
}

greet "张三" 25
greet "李四" 30

echo ""

# 获取函数参数个数和所有参数
show_args() {
    echo "参数个数: $#"
    echo "所有参数(\$*): $*"
    echo "所有参数(\$@): $@"
    echo "第一个参数: $1"
    echo "第二个参数: $2"
}

show_args "a" "b" "c" "d"

echo ""
echo "=== 3. 函数返回值 ==="

# return 只能返回 0-255 的整数（通常用于表示成功/失败）
add() {
    local sum=$(($1 + $2))
    return $sum
}

add 5 3
echo "5 + 3 = $?"

echo ""

# 通过 echo 返回值（可以返回任意字符串）
multiply() {
    echo $(($1 * $2))
}

result=$(multiply 4 5)
echo "4 * 5 = $result"

echo ""
echo "=== 4. 局部变量 ==="

# 使用 local 声明局部变量
global_var="我是全局变量"

test_local() {
    local local_var="我是局部变量"
    echo "函数内 - 全局变量: $global_var"
    echo "函数内 - 局部变量: $local_var"
}

test_local
echo "函数外 - 全局变量: $global_var"
# echo "函数外 - 局部变量: $local_var"  # 错误，局部变量在函数外不可见

echo ""
echo "=== 5. 递归函数 ==="

# 阶乘函数
factorial() {
    if [ $1 -le 1 ]; then
        echo 1
    else
        local prev=$(factorial $(( $1 - 1 )))
        echo $(( $1 * prev ))
    fi
}

echo "5! = $(factorial 5)"
echo "3! = $(factorial 3)"

echo ""
echo "=== 6. 函数库（source 引入） ==="

# 创建一个函数库文件
lib_file="/tmp/math_lib.sh"
cat > $lib_file << 'EOF'
add_num() {
    echo $(($1 + $2))
}

sub_num() {
    echo $(($1 - $2))
}
EOF

# 引入函数库
source $lib_file
# 或者使用 . 命令
# . $lib_file

echo "使用函数库:"
echo "10 + 5 = $(add_num 10 5)"
echo "10 - 5 = $(sub_num 10 5)"

rm -f $lib_file

echo ""
echo "=== 运行完成 ==="
