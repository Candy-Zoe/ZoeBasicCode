// ============================================================
// Kotlin 流程控制 - 条件语句
// 编译运行：kotlinc 01_条件语句.kt -include-runtime -d out.jar && java -jar out.jar
// ============================================================

fun main() {
    println("=== 1. if 表达式 ===")

    // 基本 if 语句
    val score = 85
    if (score >= 60) {
        println("及格了")
    }

    // if/else 语句
    val score2 = 55
    if (score2 >= 60) {
        println("及格了")
    } else {
        println("不及格")
    }

    // if/else if/else 多分支
    val score3 = 92
    if (score3 >= 90) {
        println("优秀")
    } else if (score3 >= 80) {
        println("良好")
    } else if (score3 >= 60) {
        println("及格")
    } else {
        println("不及格")
    }

    // if 是表达式，可以返回值
    val grade = if (score >= 90) {
        "优秀"
    } else if (score >= 80) {
        "良好"
    } else if (score >= 60) {
        "及格"
    } else {
        "不及格"
    }
    println("成绩等级: $grade")

    // 单行 if 表达式
    val max = if (10 > 5) 10 else 5
    println("最大值: $max")

    // 嵌套 if
    val age = 25
    val hasLicense = true
    if (age >= 18) {
        if (hasLicense) {
            println("可以开车")
        } else {
            println("需要先考驾照")
        }
    } else {
        println("未成年，不能开车")
    }

    // ============================================================
    // 2. when 表达式
    // ============================================================

    println("\n=== 2. when 表达式 ===")

    // 基本 when
    val day = "周一"
    val dayType = when (day) {
        "周一", "周二", "周三", "周四", "周五" -> "工作日"
        "周六", "周日" -> "周末"
        else -> "无效日期"
    }
    println("$day 是 $dayType")

    // when 作为表达式
    val x = 3
    val dayName = when (x) {
        1 -> "星期一"
        2 -> "星期二"
        3 -> "星期三"
        4 -> "星期四"
        5 -> "星期五"
        6 -> "星期六"
        7 -> "星期日"
        else -> "无效日期"
    }
    println("第 $x 天是 $dayName")

    // when 不带参数
    val x2 = 15
    val result = when {
        x2 < 10 -> "小于10"
        x2 < 20 -> "小于20"
        else -> "大于等于20"
    }
    println("x = $x2, $result")

    // when 中使用 in 和 !in
    val num = 7
    val numType = when (num) {
        in 1..10 -> "在 1 到 10 之间"
        !in 1..10 -> "不在 1 到 10 之间"
        else -> "其他"
    }
    println("$num $numType")

    // when 中使用 is 类型检查
    val obj: Any = "Hello"
    val typeInfo = when (obj) {
        is String -> "是字符串，长度: ${obj.length}"
        is Int -> "是整数，值: $obj"
        else -> "其他类型"
    }
    println(typeInfo)

    // 多条件分支
    val month = 2
    val season = when (month) {
        12, 1, 2 -> "冬季"
        3, 4, 5 -> "春季"
        6, 7, 8 -> "夏季"
        9, 10, 11 -> "秋季"
        else -> "无效月份"
    }
    println("$month 月是 $season")

    // when 中使用表达式
    val a = 10
    val b = 20
    val c = when {
        a > b -> "a 大于 b"
        a < b -> "a 小于 b"
        else -> "a 等于 b"
    }
    println("a=$a, b=$b, $c")

    // ============================================================
    // 3. 三元运算符
    // ============================================================

    println("\n=== 3. 三元运算符（if 表达式） ===")

    val age2 = 20
    val isAdult = if (age2 >= 18) true else false
    println("年龄 $age2，是否成年: $isAdult")

    val y = 20
    val maxVal = if (a > y) a else y
    println("a=$a, y=$y，最大值: $maxVal")

    // 嵌套 if 表达式
    val score4 = 88
    val grade2 = if (score4 >= 90) "优秀" else
                if (score4 >= 80) "良好" else
                if (score4 >= 60) "及格" else "不及格"
    println("分数 $score4，等级: $grade2")

    println("\n=== 运行完成 ===")
}
