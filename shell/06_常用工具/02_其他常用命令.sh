#!/bin/bash
# ============================================================
# Shell 常用工具 - 其他常用命令
# 运行：bash 02_其他常用命令.sh
# ============================================================

test_dir="/tmp/shell_tools2_test"
mkdir -p $test_dir

# 创建测试文件
echo -e "banana\napple\ncherry\napple\nbanana\norange" > $test_dir/fruits.txt
echo -e "5\n2\n8\n1\n9\n3\n7" > $test_dir/numbers.txt
echo "hello world hello bash hello shell" > $test_dir/text.txt

echo "=== 1. find 文件查找 ==="

echo "1.1 查找当前目录下的 .txt 文件（测试目录）:"
find $test_dir -name "*.txt" -type f

echo ""

# 按大小查找
echo "1.2 查找小于 1k 的文件:"
find $test_dir -size -1k -type f

echo ""

# 按时间查找
echo "1.3 查找 1 小时内修改过的文件:"
find $test_dir -mmin -60 -type f 2>/dev/null

echo ""

# 查找后执行命令
echo "1.4 查找并显示文件内容:"
find $test_dir -name "fruits.txt" -exec cat {} \;

echo ""
echo "=== 2. sort 排序 ==="

echo "2.1 字典序排序:"
sort $test_dir/fruits.txt

echo ""

# 数值排序
echo "2.2 数值排序:"
sort -n $test_dir/numbers.txt

echo ""

# 逆序排序
echo "2.3 逆序排序:"
sort -rn $test_dir/numbers.txt

echo ""

# 去重排序
echo "2.4 排序并去重:"
sort -u $test_dir/fruits.txt

echo ""
echo "=== 3. uniq 去重 ==="

echo "3.1 去重（需要先排序）:"
sort $test_dir/fruits.txt | uniq

echo ""

# 统计重复次数
echo "3.2 统计重复次数:"
sort $test_dir/fruits.txt | uniq -c

echo ""

# 只显示重复的行
echo "3.3 只显示重复的行:"
sort $test_dir/fruits.txt | uniq -d

echo ""

# 只显示不重复的行
echo "3.4 只显示不重复的行:"
sort $test_dir/fruits.txt | uniq -u

echo ""
echo "=== 4. wc 统计 ==="

echo "4.1 统计行数:"
wc -l $test_dir/fruits.txt

echo ""

# 统计单词数
echo "4.2 统计单词数:"
wc -w $test_dir/text.txt

echo ""

# 统计字符数
echo "4.3 统计字符数:"
wc -c $test_dir/text.txt

echo ""

# 全部统计
echo "4.4 全部统计（行 单词 字符）:"
wc $test_dir/text.txt

echo ""
echo "=== 5. cut 切割 ==="

echo "5.1 按字符切割（第1-3个字符）:"
cut -c 1-3 $test_dir/fruits.txt

echo ""

# 按字段切割（需要 -d 指定分隔符）
echo "5.2 按冒号切割 /etc/passwd（只显示前3行）:"
head -3 /etc/passwd | cut -d: -f1,3,7 2>/dev/null || echo "（演示：cut -d: -f1,3,7 /etc/passwd）"

echo ""
echo "=== 6. tr 字符转换 ==="

echo "6.1 小写转大写:"
echo "hello world" | tr 'a-z' 'A-Z'

echo ""

# 大写转小写
echo "6.2 大写转小写:"
echo "HELLO WORLD" | tr 'A-Z' 'a-z'

echo ""

# 删除字符
echo "6.3 删除所有数字:"
echo "abc123def456" | tr -d '0-9'

echo ""

# 压缩重复字符
echo "6.4 压缩重复空格:"
echo "hello    world" | tr -s ' '

echo ""
echo "=== 7. head 和 tail ==="

echo "7.1 head 显示前3行:"
head -3 $test_dir/numbers.txt

echo ""

# tail 显示后几行
echo "7.2 tail 显示最后2行:"
tail -2 $test_dir/numbers.txt

echo ""

# 实时跟踪文件（这里不演示，会阻塞）
echo "7.3 tail -f 实时跟踪文件（常用日志监控）"
echo "    用法: tail -f /var/log/nginx.log"

echo ""
echo "=== 8. xargs 参数传递 ==="

echo "8.1 将输出作为参数:"
echo "a.txt b.txt c.txt" | xargs touch
ls -l a.txt b.txt c.txt 2>/dev/null
rm -f a.txt b.txt c.txt

echo ""

# 与 find 配合
echo "8.2 find + xargs 批量处理:"
find $test_dir -name "*.txt" | xargs ls -la

echo ""
echo "=== 9. 其他实用命令 ==="

# date 日期
echo "9.1 date 日期命令:"
echo "    当前时间: $(date)"
echo "    格式化: $(date '+%Y-%m-%d %H:%M:%S')"
echo "    时间戳: $(date +%s)"

echo ""

# echo
echo "9.2 echo 高级用法:"
echo -e "    制表符:\t分隔"
echo -e "    换行:\n第一行\n第二行"
echo -n "    不换行输出1"
echo -n " 不换行输出2"
echo ""

echo ""

# printf
echo "9.3 printf 格式化输出:"
printf "    姓名: %-10s 年龄: %3d 分数: %5.2f\n" "张三" 25 95.5
printf "    姓名: %-10s 年龄: %3d 分数: %5.2f\n" "李四" 30 88.0

echo ""

# basename 和 dirname
echo "9.4 basename 和 dirname:"
path="/home/user/docs/file.txt"
echo "    路径: $path"
echo "    文件名: $(basename $path)"
echo "    目录名: $(dirname $path)"

echo ""

# which 查找命令
echo "9.5 which 查找命令位置:"
echo "    bash 位置: $(which bash 2>/dev/null || echo '未找到')"
echo "    ls 位置: $(which ls 2>/dev/null || echo '未找到')"

# 清理
rm -rf $test_dir

echo ""
echo "=== 运行完成 ==="
