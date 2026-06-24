#!/bin/bash
# ============================================================
# Shell 文件操作 - 文件测试
# 运行：bash 02_文件测试.sh
# ============================================================

test_dir="/tmp/shell_file_test2"
mkdir -p $test_dir

# 创建测试文件和目录
echo "测试内容" > $test_dir/file.txt
chmod 644 $test_dir/file.txt
chmod +x $test_dir/file.txt 2>/dev/null || true
mkdir -p $test_dir/subdir
ln -sf $test_dir/file.txt $test_dir/link.txt 2>/dev/null || true

echo "=== 1. 文件类型测试 ==="

echo "测试路径: $test_dir"

# -f 普通文件
if [ -f "$test_dir/file.txt" ]; then
    echo "  file.txt 是普通文件"
fi

# -d 目录
if [ -d "$test_dir/subdir" ]; then
    echo "  subdir 是目录"
fi

# -L 符号链接
if [ -L "$test_dir/link.txt" ]; then
    echo "  link.txt 是符号链接"
fi

# -b 块设备文件
# -c 字符设备文件
# -p 管道文件
# -S 套接字文件

echo ""
echo "=== 2. 文件权限测试 ==="

test_file="$test_dir/file.txt"

# -r 可读
if [ -r "$test_file" ]; then
    echo "  $test_file 可读"
fi

# -w 可写
if [ -w "$test_file" ]; then
    echo "  $test_file 可写"
fi

# -x 可执行
if [ -x "$test_file" ]; then
    echo "  $test_file 可执行"
else
    echo "  $test_file 不可执行"
fi

echo ""
echo "=== 3. 文件大小和时间测试 ==="

# -s 文件非空
if [ -s "$test_file" ]; then
    echo "  $test_file 非空"
fi

# -e 文件存在
if [ -e "$test_file" ]; then
    echo "  $test_file 存在"
fi

# -O 文件所有者是当前用户
if [ -O "$test_file" ]; then
    echo "  $test_file 所有者是当前用户"
fi

# -G 文件所属组是当前用户组
if [ -G "$test_file" ]; then
    echo "  $test_file 所属组是当前用户组"
fi

echo ""
echo "=== 4. 两个文件比较 ==="

file_a="$test_dir/file.txt"
file_b="$test_dir/link.txt"

# -ef 两个文件是同一个文件（inode 相同）
if [ "$file_a" -ef "$file_b" ]; then
    echo "  file_a 和 file_b 是同一个文件"
fi

# 创建两个文件比较时间
file_new="$test_dir/new.txt"
file_old="$test_dir/old.txt"
echo "new" > $file_new
sleep 1
echo "old" > $file_old

# -nt 前者比后者新
if [ $file_new -nt $file_old ]; then
    echo "  new.txt 比 old.txt 新"
fi

# -ot 前者比后者旧
if [ $file_old -ot $file_new ]; then
    echo "  old.txt 比 new.txt 旧"
fi

echo ""
echo "=== 5. 综合示例：检查文件状态 ==="

check_file() {
    local file=$1
    echo "检查文件: $file"

    if [ ! -e "$file" ]; then
        echo "  文件不存在"
        return
    fi

    if [ -f "$file" ]; then
        echo "  类型: 普通文件"
    elif [ -d "$file" ]; then
        echo "  类型: 目录"
    elif [ -L "$file" ]; then
        echo "  类型: 符号链接"
    fi

    if [ -r "$file" ]; then
        echo "  权限: 可读"
    fi
    if [ -w "$file" ]; then
        echo "  权限: 可写"
    fi
    if [ -x "$file" ]; then
        echo "  权限: 可执行"
    fi

    if [ -s "$file" ]; then
        echo "  大小: 非空"
    else
        echo "  大小: 空文件"
    fi
}

check_file "$test_file"
echo ""
check_file "$test_dir/subdir"

echo ""
echo "=== 6. test 命令和 [ ] 以及 [[ ]] ==="

# test 命令
echo "test 命令:"
test -f "$test_file" && echo "  test -f: 是普通文件"

# [ ] 是 test 的语法糖
echo "[ ] 写法:"
[ -f "$test_file" ] && echo "  [ -f ]: 是普通文件"

# [[ ]] 是 bash 增强版，支持更多特性
echo "[[ ]] 写法:"
[[ -f "$test_file" && -r "$test_file" ]] && echo "  [[ ]]: 是可读的普通文件"

# 清理
rm -rf $test_dir

echo ""
echo "=== 运行完成 ==="
