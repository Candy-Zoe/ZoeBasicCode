#!/bin/bash
# ============================================================
# Shell 基础语法 - 运算符
# 运行：bash 02_运算符.sh
# ============================================================

echo "=== 1. 算术运算符 ==="

a=10
b=3

# 使用 $(( )) 进行算术运算
echo "$a + $b = $((a + b))"
echo "$a - $b = $((a - b))"
echo "$a * $b = $((a * b))"
echo "$a / $b = $((a / b))"
echo "$a % $b = $((a % b))"

# 自增自减
c=5
echo "c = $c"
((c++))
echo "c++ 后: $c"
((c--))
echo "c-- 后: $c"

echo ""
echo "=== 2. 关系运算符 ==="

# 使用 test 或 [ ] 进行比较
# -eq 等于, -ne 不等于, -gt 大于, -lt 小于, -ge 大于等于, -le 小于等于

if [ $a -gt $b ]; then
    echo "$a 大于 $b"
fi

if [ $a -ne $b ]; then
    echo "$a 不等于 $b"
fi

if [ $b -le $a ]; then
    echo "$b 小于等于 $a"
fi

echo ""
echo "=== 3. 字符串运算符 ==="

str1="hello"
str2="world"
str3=""

# = 相等, != 不相等, -z 长度为0, -n 长度不为0
if [ "$str1" != "$str2" ]; then
    echo "$str1 不等于 $str2"
fi

if [ -z "$str3" ]; then
    echo "str3 是空字符串"
fi

if [ -n "$str1" ]; then
    echo "str1 不是空字符串"
fi

# 字符串比较（按字典序）
if [[ "$str1" < "$str2" ]]; then
    echo "$str1 < $str2 (字典序)"
fi

echo ""
echo "=== 4. 逻辑运算符 ==="

x=5
y=10

# && 与, || 或, ! 非
if [ $x -gt 0 ] && [ $y -lt 20 ]; then
    echo "x > 0 且 y < 20"
fi

if [ $x -gt 10 ] || [ $y -gt 5 ]; then
    echo "x > 10 或 y > 5"
fi

if ! [ $x -gt 10 ]; then
    echo "x 不大于 10"
fi

# 使用 [[ ]] 更简洁的写法
if [[ $x -gt 0 && $y -lt 20 ]]; then
    echo "[[ ]] 写法: x > 0 且 y < 20"
fi

echo ""
echo "=== 5. 文件测试运算符 ==="

test_file="/tmp/test_operator.txt"
echo "测试文件" > $test_file

# -f 普通文件, -d 目录, -r 可读, -w 可写, -x 可执行, -s 文件非空
if [ -f $test_file ]; then
    echo "$test_file 是普通文件"
fi

if [ -r $test_file ]; then
    echo "$test_file 可读"
fi

if [ -w $test_file ]; then
    echo "$test_file 可写"
fi

if [ -s $test_file ]; then
    echo "$test_file 非空"
fi

rm -f $test_file

echo ""
echo "=== 运行完成 ==="
