#!/bin/bash
# ============================================================
# Shell 常用工具 - grep, sed, awk
# 运行：bash 01_grep_sed_awk.sh
# ============================================================

test_dir="/tmp/shell_tools_test"
mkdir -p $test_dir

# 创建测试文件
cat > $test_dir/data.txt << 'EOF'
1 张三 90 数学
2 李四 85 英语
3 王五 92 数学
4 赵六 78 物理
5 钱七 88 英语
6 孙八 95 数学
7 周九 82 物理
EOF

echo "=== 1. grep 文本搜索 ==="

echo "测试文件内容:"
cat $test_dir/data.txt

echo ""

# 基本搜索
echo "1.1 搜索包含'数学'的行:"
grep "数学" $test_dir/data.txt

echo ""

# 显示行号
echo "1.2 显示行号搜索:"
grep -n "英语" $test_dir/data.txt

echo ""

# 反向匹配
echo "1.3 不包含'数学'的行:"
grep -v "数学" $test_dir/data.txt

echo ""

# 忽略大小写
echo "1.4 忽略大小写搜索:"
echo "Hello World" | grep -i "hello"

echo ""

# 正则表达式
echo "1.5 正则表达式搜索（以数字开头）:"
grep "^[0-9]" $test_dir/data.txt

echo ""

# 统计匹配行数
echo "1.6 统计包含'数学'的行数:"
grep -c "数学" $test_dir/data.txt

echo ""

# 只显示匹配的部分
echo "1.7 只显示匹配的分数（两位数字）:"
grep -o "[0-9]\{2\}" $test_dir/data.txt | head -5

echo ""
echo "=== 2. sed 流编辑器 ==="

echo "2.1 替换文本（将'数学'替换为'Math'）:"
sed 's/数学/Math/' $test_dir/data.txt

echo ""

# 全局替换
echo "2.2 全局替换（所有数字替换为*）:"
sed 's/[0-9]/*/g' $test_dir/data.txt

echo ""

# 删除行
echo "2.3 删除第3行:"
sed '3d' $test_dir/data.txt

echo ""

# 删除匹配行
echo "2.4 删除包含'物理'的行:"
sed '/物理/d' $test_dir/data.txt

echo ""

# 插入行
echo "2.5 在第2行后插入新行:"
sed '2a\-------' $test_dir/data.txt

echo ""

# 替换特定行
echo "2.6 替换第4行:"
sed '4c\这是替换后的行' $test_dir/data.txt

echo ""

# 原地修改文件（-i）
echo "2.7 原地修改文件示例:"
cp $test_dir/data.txt $test_dir/data_bak.txt
sed -i 's/张三/张三丰/' $test_dir/data_bak.txt
cat $test_dir/data_bak.txt
rm $test_dir/data_bak.txt

echo ""
echo "=== 3. awk 文本处理 ==="

echo "3.1 打印指定列（第2列-姓名）:"
awk '{print $2}' $test_dir/data.txt

echo ""

# 打印多列
echo "3.2 打印姓名和科目（第2列和第4列）:"
awk '{print $2 " - " $4}' $test_dir/data.txt

echo ""

# 条件过滤
echo "3.3 分数大于85分的学生:"
awk '$3 > 85 {print $2, $3}' $test_dir/data.txt

echo ""

# 内置变量
echo "3.4 显示行号和字段数:"
awk '{print "行"NR": "$0" (共"NF"列)"}' $test_dir/data.txt

echo ""

# BEGIN 和 END 块
echo "3.5 计算总分和平均分:"
awk 'BEGIN {sum=0; count=0}
     {sum += $3; count++}
     END {
         print "总人数:", count
         print "总分:", sum
         print "平均分:", sum/count
     }' $test_dir/data.txt

echo ""

# 分组统计
echo "3.6 按科目统计平均分:"
awk '{
    score[$4] += $3
    count[$4]++
}
END {
    for (subj in score) {
        printf "%s: 平均分=%.2f, 人数=%d\n", subj, score[subj]/count[subj], count[subj]
    }
}' $test_dir/data.txt

echo ""

# 自定义分隔符
echo "3.7 自定义分隔符（冒号分隔）:"
echo "root:x:0:0:root:/root:/bin/bash" | awk -F: '{print "用户名:"$1, "UID:"$3, "Shell:"$7}'

echo ""

# awk 数组和循环
echo "3.8 awk 数组和循环示例:"
awk '{
    names[NR] = $2
}
END {
    print "倒序输出:"
    for (i = NR; i >= 1; i--) {
        print "  " i ": " names[i]
    }
}' $test_dir/data.txt

# 清理
rm -rf $test_dir

echo ""
echo "=== 运行完成 ==="
