#!/bin/bash
# ============================================================
# Shell 文件操作 - 文件读写
# 运行：bash 01_文件读写.sh
# ============================================================

test_dir="/tmp/shell_file_test"
mkdir -p $test_dir

echo "=== 1. 文件写入 ==="

# 方式一：使用 > 覆盖写入
file1="$test_dir/file1.txt"
echo "这是第一行内容" > $file1
echo "这是第二行内容" > $file1
echo "覆盖写入后文件内容:"
cat $file1

echo ""

# 方式二：使用 >> 追加写入
file2="$test_dir/file2.txt"
echo "第一行" > $file2
echo "第二行" >> $file2
echo "第三行" >> $file2
echo "追加写入后文件内容:"
cat $file2

echo ""

# 方式三：使用 tee 写入（同时输出到屏幕和文件）
file3="$test_dir/file3.txt"
echo "使用 tee 写入:"
echo "Hello Tee" | tee $file3
echo "追加写入:"
echo "Append" | tee -a $file3

echo ""
echo "=== 2. 文件读取 ==="

# 方式一：使用 cat 读取全部
echo "cat 读取全部:"
cat $file2

echo ""

# 方式二：使用 head 读取前几行
echo "head 读取前2行:"
head -2 $file2

echo ""

# 方式三：使用 tail 读取后几行
echo "tail 读取最后1行:"
tail -1 $file2

echo ""

# 方式四：按行读取（while read）
echo "while read 逐行读取:"
line_num=1
while read -r line; do
    echo "  第$line_num行: $line"
    line_num=$((line_num + 1))
done < $file2

echo ""
echo "=== 3. 输入输出重定向 ==="

# 标准输出重定向
echo "标准输出重定向:"
ls $test_dir > $test_dir/ls_output.txt
cat $test_dir/ls_output.txt

echo ""

# 标准错误重定向
echo "标准错误重定向:"
ls /nonexistent_dir 2> $test_dir/error.log
echo "错误日志内容:"
cat $test_dir/error.log

echo ""

# 同时重定向标准输出和错误
echo "同时重定向 stdout 和 stderr:"
ls $test_dir /nonexistent > $test_dir/all.log 2>&1
echo "所有输出:"
cat $test_dir/all.log

echo ""

# 重定向到空设备
echo "丢弃输出:"
ls $test_dir > /dev/null 2>&1
echo "输出已丢弃"

echo ""
echo "=== 4. 管道 Pipe ==="

echo "管道示例:"
echo -e "banana\napple\ncherry\napple" | sort | uniq

echo ""

# 多个管道连接
echo "多管道组合:"
cat $file2 | head -2 | tr 'a-z' 'A-Z'

echo ""
echo "=== 5. Here Document ==="

# Here Document 写入文件
here_file="$test_dir/here.txt"
cat > $here_file << 'EOF'
这是 Here Document 写入的内容
第一行
第二行
第三行
变量不会被替换：$HOME
EOF
echo "Here Document 内容:"
cat $here_file

echo ""

# Here Document 变量替换
here_file2="$test_dir/here2.txt"
cat > $here_file2 << EOF
当前用户: $USER
当前目录: $PWD
EOF
echo "带变量替换的 Here Document:"
cat $here_file2

echo ""
echo "=== 6. Here String ==="

echo "Here String 示例:"
cat <<< "这是 Here String 的内容"

echo ""

# 和其他命令配合
echo "Here String 配合 grep:"
grep "hello" <<< "hello world hello bash"

echo ""
echo "=== 7. 文件描述符 ==="

echo "文件描述符示例:"

# 打开文件描述符 3 用于写入
exec 3> $test_dir/fd3.txt
echo "这是写入fd3的内容" >&3
echo "又一行内容" >&3

# 关闭文件描述符
exec 3>&-

echo "fd3.txt 内容:"
cat $test_dir/fd3.txt

# 清理
rm -rf $test_dir

echo ""
echo "=== 运行完成 ==="
