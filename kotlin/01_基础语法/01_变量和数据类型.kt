// ============================================================
// Kotlin 基础语法 - 变量和数据类型
// 编译运行：kotlinc 01_变量和数据类型.kt -include-runtime -d out.jar && java -jar out.jar
// ============================================================

fun main() {
    println("=== 1. 变量声明 ===")

    // 可变变量
    var x = 10
    x = 20
    println("var x = $x")

    // 不可变变量（推荐）
    val y = 10
    // y = 20  // 错误，val 不可变
    println("val y = $y")

    // 显式类型
    val a: Int = 100
    val b: String = "Hello"
    val c: Double = 3.14
    val d: Boolean = true
    val e: Char = 'A'
    println("Int: $a, String: $b, Double: $c, Boolean: $d, Char: $e")

    // 类型推断
    val f = "Type Inference"  // 自动推断为 String
    println("类型推断: $f")

    // ============================================================
    // 2. 字符串
    // ============================================================

    println("\n=== 2. 字符串 ===")

    val name = "张三"
    val age = 25
    println("姓名: $name, 年龄: $age")
    println("字符串长度: ${name.length}")
    println("5 年后: ${age + 5} 岁")

    // 多行字符串
    val multiLine = """
        第一行
        第二行
        第三行
    """.trimIndent()
    println("多行字符串:\n$multiLine")

    // 字符串方法
    val str = "Hello World"
    println("大写: ${str.uppercase()}")
    println("小写: ${str.lowercase()}")
    println("替换: ${str.replace("World", "Kotlin")")
    println("截取: ${str.substring(0, 5)}")
    println("是否以 He 开头: ${str.startsWith("He")}")

    // ============================================================
    // 3. 基本类型
    // ============================================================

    println("\n=== 3. 基本类型 ===")

    // 整数类型
    val byteVal: Byte = 127
    val shortVal: Short = 32767
    val intVal: Int = 2147483647
    val longVal: Long = 9223372036854775807L
    println("Byte: $byteVal, Short: $shortVal, Int: $intVal, Long: $longVal")

    // 浮点类型
    val floatVal: Float = 3.14F
    val doubleVal: Double = 3.141592653589793
    println("Float: $floatVal, Double: $doubleVal")

    // 类型转换
    val i: Int = 100
    val l: Long = i.toLong()
    val d2: Double = i.toDouble()
    println("类型转换: Int->Long: $l, Int->Double: $d2")

    // ============================================================
    // 4. 布尔类型
    // ============================================================

    println("\n=== 4. 布尔类型 ===")

    val isTrue = true
    val isFalse = false
    println("true: $isTrue, false: $isFalse")
    println("!true: ${!isTrue}")
    println("true && false: ${isTrue && isFalse}")
    println("true || false: ${isTrue || isFalse}")

    // ============================================================
    // 5. 字符类型
    // ============================================================

    println("\n=== 5. 字符类型 ===")

    val ch: Char = 'A'
    println("字符: $ch")
    println("ASCII 码: ${ch.code}")
    println("下一个字符: ${ch + 1}")
    println("是否字母: ${ch.isLetter()}")
    println("是否数字: ${ch.isDigit()}")

    // ============================================================
    // 6. 类型别名
    // ============================================================

    println("\n=== 6. 类型别名 ===")

    typealias UserName = String
    val userName: UserName = "李四"
    println("类型别名 UserName: $userName")

    println("\n=== 运行完成 ===")
}
