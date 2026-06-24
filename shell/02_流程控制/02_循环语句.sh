#!/bin/bash
# ============================================================
# Shell 流程控制 - 循环语句
# 包含：for, while, until, break, continue
# 运行：bash 02_循环语句.sh
# ============================================================

echo "=== 1. for 循环 ==="

# 基本 for 循环（列表遍历）
echo "for 循环 - 数字列表:"
for i in 1 2 3 4 5; do
    echo "  $i"
done

echo ""

# for 循环 - 字符串列表
echo "for 循环 - 字符串列表:"
for fruit in 苹果 香蕉 橙子; do
    echo "  $fruit"
done

echo ""

# for 循环 - 命令结果
echo "for 循环 - 命令结果（当前目录文件）:"
count=0
for file in *; do
    if [ -f "$file" ]; then
        echo "  $file"
        count=$((count + 1))
        if [ $count -ge 3 ]; then
            echo "  ...（只显示前3个）"
            break
        fi
    fi
done

echo ""

# C 风格 for 循环
echo "C 风格 for 循环:"
for ((i=1; i<=5; i++)); do
    echo "  i = $i"
done

echo ""
echo "=== 2. while 循环 ==="

# 基本 while 循环
echo "while 循环:"
count=1
while [ $count -le 5 ]; do
    echo "  count = $count"
    count=$((count + 1))
done

echo ""

# while 循环读取文件内容
test_file="/tmp/test_while.txt"
echo -e "第一行\n第二行\n第三行" > $test_file
echo "while 读取文件:"
while read -r line; do
    echo "  $line"
done < $test_file
rm -f $test_file

echo ""
echo "=== 3. until 循环 ==="

# until 循环（条件为假时循环）
echo "until 循环:"
count=1
until [ $count -gt 5 ]; do
    echo "  count = $count"
    count=$((count + 1))
done

echo ""
echo "=== 4. break 和 continue ==="

# continue：跳过本次循环
echo "continue 示例（跳过3）:"
for i in 1 2 3 4 5; do
    if [ $i -eq 3 ]; then
        continue
    fi
    echo "  $i"
done

echo ""

# break：跳出循环
echo "break 示例（遇到5停止）:"
for i in 1 2 3 4 5 6 7; do
    if [ $i -eq 5 ]; then
        break
    fi
    echo "  $i"
done

echo ""

# 多层循环的 break 和 continue
echo "多层循环示例:"
for i in 1 2 3; do
    for j in 1 2 3; do
        if [ $j -eq 2 ]; then
            continue 2  # 跳过外层循环的本次迭代
        fi
        echo "  i=$i, j=$j"
    done
done

echo ""
echo "=== 5. 循环与数组配合 ==="

fruits=("苹果" "香蕉" "橙子" "葡萄")
echo "遍历数组:"
for fruit in "${fruits[@]}"; do
    echo "  - $fruit"
done

echo ""
echo "=== 运行完成 ==="
