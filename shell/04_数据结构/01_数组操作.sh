#!/bin/bash
# ============================================================
# Shell 数据结构 - 数组操作
# 运行：bash 01_数组操作.sh
# ============================================================

echo "=== 1. 数组定义 ==="

# 方式一：直接定义
fruits=("苹果" "香蕉" "橙子" "葡萄" "西瓜")
echo "所有水果: ${fruits[@]}"

# 方式二：逐个赋值
colors[0]="红色"
colors[1]="绿色"
colors[2]="蓝色"
echo "所有颜色: ${colors[@]}"

# 方式三：使用命令输出创建数组
files=($(ls -1 /etc | head -5))
echo "前5个系统文件: ${files[@]}"

echo ""
echo "=== 2. 访问数组元素 ==="

# 访问单个元素
echo "第一个水果: ${fruits[0]}"
echo "第三个水果: ${fruits[2]}"

# 获取所有元素
echo "所有元素(@): ${fruits[@]}"
echo "所有元素(*): ${fruits[*]}"

# 获取数组长度
echo "数组长度: ${#fruits[@]}"
echo "数组长度: ${#fruits[*]}"

# 获取元素的长度
echo "第一个元素的长度: ${#fruits[0]}"

echo ""
echo "=== 3. 修改数组元素 ==="

# 修改元素
echo "修改前: ${fruits[0]}"
fruits[0]="红苹果"
echo "修改后: ${fruits[0]}"

# 添加元素（追加）
fruits+=("草莓")
echo "添加后所有元素: ${fruits[@]}"
echo "添加后长度: ${#fruits[@]}"

echo ""
echo "=== 4. 删除数组元素 ==="

# 删除单个元素
echo "删除前: ${fruits[@]}"
unset 'fruits[1]'
echo "删除索引1后: ${fruits[@]}"
echo "删除后长度: ${#fruits[@]}"

# 注意：删除元素后索引不会重新排列
echo "数组索引: ${!fruits[@]}"

# 重新索引数组
fruits=("${fruits[@]}")
echo "重新索引后: ${fruits[@]}"
echo "新的索引: ${!fruits[@]}"

echo ""
echo "=== 5. 数组遍历 ==="

# 方式一：for in 遍历值
echo "for in 遍历值:"
for fruit in "${fruits[@]}"; do
    echo "  - $fruit"
done

echo ""

# 方式二：通过索引遍历
echo "通过索引遍历:"
for i in "${!fruits[@]}"; do
    echo "  索引$i: ${fruits[$i]}"
done

echo ""

# 方式三：C 风格循环
echo "C 风格循环:"
for ((i=0; i<${#fruits[@]}; i++)); do
    echo "  $i: ${fruits[$i]}"
done

echo ""
echo "=== 6. 数组切片 ==="

nums=(1 2 3 4 5 6 7 8 9 10)
echo "原数组: ${nums[@]}"
echo "从第3个开始取5个: ${nums[@]:2:5}"
echo "从第5个开始到末尾: ${nums[@]:4}"

echo ""
echo "=== 7. 数组排序和去重 ==="

# 排序（借助 sort 命令）
unsorted=(5 2 8 1 9 3)
echo "未排序: ${unsorted[@]}"
sorted=($(echo "${unsorted[@]}" | tr ' ' '\n' | sort -n))
echo "排序后: ${sorted[@]}"

# 去重
with_dups=(1 2 2 3 3 3 4 5 5)
echo "有重复: ${with_dups[@]}"
uniq_arr=($(echo "${with_dups[@]}" | tr ' ' '\n' | sort -u))
echo "去重后: ${uniq_arr[@]}"

echo ""
echo "=== 8. 关联数组（字典/Map） ==="

# 声明关联数组（需要 bash 4+）
declare -A student

# 赋值
student["name"]="张三"
student["age"]=18
student["grade"]="高三"

# 访问
echo "姓名: ${student[name]}"
echo "年龄: ${student[age]}"
echo "所有键: ${!student[@]}"
echo "所有值: ${student[@]}"

echo ""
echo "遍历关联数组:"
for key in "${!student[@]}"; do
    echo "  $key: ${student[$key]}"
done

echo ""
echo "=== 运行完成 ==="
