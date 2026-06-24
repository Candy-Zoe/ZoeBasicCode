#!/bin/bash
# ============================================================
# Shell 流程控制 - 条件语句
# 包含：if, case
# 运行：bash 01_条件语句.sh
# ============================================================

echo "=== 1. if 语句 ==="

# 基本 if-else
score=85
echo "分数: $score"
if [ $score -ge 60 ]; then
    echo "及格"
else
    echo "不及格"
fi

echo ""

# if-elif-else 多分支
if [ $score -ge 90 ]; then
    echo "优秀"
elif [ $score -ge 80 ]; then
    echo "良好"
elif [ $score -ge 60 ]; then
    echo "及格"
else
    echo "不及格"
fi

echo ""
echo "=== 2. 嵌套 if ==="

age=20
has_id=true

if [ $age -ge 18 ]; then
    echo "已成年"
    if [ "$has_id" = true ]; then
        echo "有身份证，可以办理业务"
    else
        echo "没有身份证，无法办理业务"
    fi
else
    echo "未成年"
fi

echo ""
echo "=== 3. case 语句 ==="

# 基本 case 语句
day="周一"
echo "今天是: $day"
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

echo ""

# 使用通配符的 case
fruit="apple"
echo "水果: $fruit"
case $fruit in
    apple|banana)
        echo "常见水果"
        ;;
    orange|grape)
        echo "浆果类"
        ;;
    *)
        echo "其他水果"
        ;;
esac

echo ""

# 数字匹配
num=3
echo "数字: $num"
case $num in
    [1-3])
        echo "小数"
        ;;
    [4-6])
        echo "中数"
        ;;
    [7-9])
        echo "大数"
        ;;
    *)
        echo "不在1-9范围内"
        ;;
esac

echo ""
echo "=== 4. 条件表达式简写 ==="

# 简写的 if-else（三元运算符风格）
[ $score -ge 60 ] && echo "及格" || echo "不及格"

# 命令执行成功后执行下一条
mkdir -p /tmp/test_dir && echo "目录创建成功"
rmdir /tmp/test_dir

echo ""
echo "=== 运行完成 ==="
