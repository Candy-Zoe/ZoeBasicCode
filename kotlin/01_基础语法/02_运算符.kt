// ============================================================
// Kotlin 基础语法 - 运算符
// 编译运行：kotlinc 02_运算符.kt -include-runtime -d out.jar && java -jar out.jar
// ============================================================

fun main() {
    println("=== 1. 算术运算符 ===")

    val a = 10
    val b = 3

    println("a = $a, b = $b")
    println("加法: a + b = ${a + b}")
    println("减法: a - b = ${a - b}")
    println("乘法: a * b = ${a * b}")
    println("除法: a / b = ${a / b}")
    println("取余: a % b = ${a % b}")

    // 自增自减
    var x = 5
    println("\nx = $x")
    println("x++ = ${x++} (后置，先返回后自增)")
    println("现在 x = $x")
    println("++x = ${++x} (前置，先自增后返回)")
    println("x-- = ${x--} (后置)")
    println("现在 x = $x")

    // ============================================================
    // 2. 赋值运算符
    // ============================================================

    println("\n=== 2. 赋值运算符 ===")

    var c = 10
    println("c = $c")
    c += 5
    println("c += 5 → $c")
    c -= 3
    println("c -= 3 → $c")
    c *= 2
    println("c *= 2 → $c")
    c /= 4
    println("c /= 4 → $c")
    c %= 3
    println("c %= 3 → $c")

    // ============================================================
    // 3. 比较运算符
    // ============================================================

    println("\n=== 3. 比较运算符 ===")

    val m = 5
    val n = 10

    println("m = $m, n = $n")
    println("等于: m == n → ${m == n}")
    println("不等于: m != n → ${m != n}")
    println("大于: m > n → ${m > n}")
    println("小于: m < n → ${m < n}")
    println("大于等于: m >= n → ${m >= n}")
    println("小于等于: m <= n → ${m <= n}")

    // === 和 == 的区别
    val str1 = "hello"
    val str2 = String(charArrayOf('h', 'e', 'l', 'l', 'o'))
    println("\n字符串比较:")
    println("str1 == str2 (内容相等): ${str1 == str2}")
    println("str1 === str2 (引用相等): ${str1 === str2}")

    // ============================================================
    // 4. 逻辑运算符
    // ============================================================

    println("\n=== 4. 逻辑运算符 ===")

    val t = true
    val f = false

    println("t = $t, f = $f")
    println("逻辑与 AND: t && f → ${t && f}")
    println("逻辑或 OR: t || f → ${t || f}")
    println("逻辑非 NOT: !t → ${!t}")

    // 短路求值
    println("\n短路求值:")
    val result1 = runCatching { null!!.let { println("不会执行") } }
    println("逻辑与短路: 左边为假时右边不执行")
    println("逻辑或短路: 左边为真时右边不执行")

    // ============================================================
    // 5. 位运算符
    // ============================================================

    println("\n=== 5. 位运算符 ===")

    val p = 5   // 0101
    val q = 3   // 0011

    println("p = $p (二进制: ${p.toString(2)})")
    println("q = $q (二进制: ${q.toString(2)})")
    println("按位与 AND: p and q = ${p and q} (${(p and q).toString(2)})")
    println("按位或 OR: p or q = ${p or q} (${(p or q).toString(2)})")
    println("按位异或 XOR: p xor q = ${p xor q} (${(p xor q).toString(2)})")
    println("按位取反 INV: p.inv() = ${p.inv()}")
    println("左移: p shl 1 = ${p shl 1} (${(p shl 1).toString(2)})")
    println("右移: p shr 1 = ${p shr 1} (${(p shr 1).toString(2)})")
    println("无符号右移: p ushr 1 = ${p ushr 1}")

    // ============================================================
    // 6. 区间运算符
    // ============================================================

    println("\n=== 6. 区间运算符 ===")

    // 闭区间 [1, 5]
    println("闭区间 1..5:")
    for (i in 1..5) {
        print("$i ")
    }
    println()

    // 半开区间 [1, 5)
    println("半开区间 1 until 5:")
    for (i in 1 until 5) {
        print("$i ")
    }
    println()

    // 降序区间
    println("降序区间 5 downTo 1:")
    for (i in 5 downTo 1) {
        print("$i ")
    }
    println()

    // 步长
    println("步长 2: 1..10 step 2:")
    for (i in 1..10 step 2) {
        print("$i ")
    }
    println()

    // in 运算符
    println("\nin 运算符:")
    println("3 in 1..5: ${3 in 1..5}")
    println("6 in 1..5: ${6 in 1..5}")
    println("'c" in "a".."z": ${"c" in "a".."z"}")

    // ============================================================
    // 7. Elvis 操作符和安全调用
    // ============================================================

    println("\n=== 7. 安全运算符 ===")

    val str: String? = "Hello"
    println("安全调用 ?.: ${str?.length}")

    val nullStr: String? = null
    println("安全调用 null: ${nullStr?.length}")

    val name: String? = null
    val displayName = name ?: "未知"
    println("Elvis 操作符 ?: $displayName")

    println("\n=== 运行完成 ===")
}
