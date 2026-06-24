// ============================================================
// Kotlin 流程控制 - 循环语句
// 编译运行：kotlinc 02_循环语句.kt -include-runtime -d out.jar && java -jar out.jar
// ============================================================

fun main() {
    println("=== 1. for 循环 ===")

    // 基本 for 循环（区间）
    println("基本 for 循环 1..5:")
    for (i in 1..5) {
        println("  第 $i 次循环")
    }

    // until 半开区间
    println("until (不包含上限):")
    for (i in 1 until 3) {
        println("  $i")
    }

    // downTo 降序
    println("downTo 降序:")
    for (i in 3 downTo 1) {
        println("  $i")
    }

    // step 步长
    println("step 步长:")
    for (i in 1..5 step 2) {
        println("  $i")
    }

    // 遍历数组
    println("\n遍历数组:")
    val nums = intArrayOf(1, 2, 3, 4, 5)
    for (num in nums) {
        println("  $num")
    }

    // 带索引遍历
    println("带索引遍历:")
    val fruits = listOf("苹果", "香蕉", "橙子")
    for ((index, fruit) in fruits.withIndex()) {
        println("  $index. $fruit")
    }

    // 遍历字符串
    println("遍历字符串:")
    val str = "Hello"
    for (char in str) {
        println("  $char")
    }

    // 嵌套 for 循环 - 九九乘法表
    println("\n九九乘法表:")
    for (i in 1..9) {
        for (j in 1..i) {
            print("$j×$i=${i * j}\t")
        }
        println()
    }

    // ============================================================
    // 2. while 循环
    // ============================================================

    println("\n=== 2. while 循环 ===")

    var count = 1
    while (count <= 5) {
        println("  while 循环第 $count 次")
        count++
    }

    // while 循环求阶乘
    val n = 5
    var factorial = 1
    var i = 1
    while (i <= n) {
        factorial *= i
        i++
    }
    println("$n! = $factorial")

    // ============================================================
    // 3. do...while 循环
    // ============================================================

    println("\n=== 3. do...while 循环 ===")

    var num = 1
    do {
        println("  do...while 第 $num 次")
        num++
    } while (num <= 3)

    // do...while 至少执行一次
    var x = 10
    do {
        println("  即使条件不满足也会执行一次: x = $x")
        x++
    } while (x < 5)

    // ============================================================
    // 4. repeat 函数
    // ============================================================

    println("\n=== 4. repeat 函数 ===")

    repeat(3) {
        println("  repeat 第 ${it + 1} 次")
    }

    // ============================================================
    // 5. break 和 continue
    // ============================================================

    println("\n=== 5. break 和 continue ===")

    // break: 跳出循环
    println("break 示例 (找到 3 就停止):")
    for (i in 1..5) {
        if (i == 3) {
            break
        }
        println("  $i")
    }

    // continue: 跳过当前循环
    println("continue 示例 (跳过偶数):")
    for (i in 1..5) {
        if (i % 2 == 0) {
            continue
        }
        println("  $i")
    }

    // 带标签的 break
    println("带标签的 break:")
    loop@ for (i in 1..3) {
        for (j in 1..3) {
            if (j == 2) continue
            if (i == 3) break@loop
            println("  i=$i, j=$j")
        }
    }

    // ============================================================
    // 6. 遍历集合
    // ============================================================

    println("\n=== 6. 遍历集合 ===")

    // 遍历 List
    val list = listOf("A", "B", "C")
    println("遍历 List:")
    for (item in list) {
        println("  $item")
    }

    // 遍历 Map
    val map = mapOf("name" to "张三", "age" to 25, "city" to "北京")
    println("遍历 Map:")
    for ((key, value) in map) {
        println("  $key: $value")
    }

    // forEach
    println("forEach:")
    list.forEach { println("  $it") }

    // forEachIndexed
    println("forEachIndexed:")
    list.forEachIndexed { index, item ->
        println("  $index: $item")
    }

    println("\n=== 运行完成 ===")
}
