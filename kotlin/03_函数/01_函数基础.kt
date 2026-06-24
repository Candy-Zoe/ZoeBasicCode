// ============================================================
// Kotlin 函数 - 函数基础
// 编译运行：kotlinc 01_函数基础.kt -include-runtime -d out.jar && java -jar out.jar
// ============================================================

fun main() {
    println("=== 1. 函数定义 ===")

    // 基本函数
    fun add(a: Int, b: Int): Int {
        return a + b
    }
    println("add(3, 5) = ${add(3, 5)}")

    // 表达式函数体（单表达式函数）
    fun multiply(a: Int, b: Int) = a * b
    println("multiply(3, 5) = ${multiply(3, 5)}")

    // 无返回值函数（Unit 可省略）
    fun greet(name: String): Unit {
        println("你好, $name!")
    }
    greet("张三")

    // ============================================================
    // 2. 默认参数
    // ============================================================

    println("\n=== 2. 默认参数 ===")

    fun greet2(name: String = "世界") {
        println("你好, $name!")
    }
    greet2()
    greet2("张三")

    // 多个默认参数
    fun printInfo(name: String, age: Int = 18, city: String = "北京") {
        println("$name, $age 岁, 来自 $city")
    }
    printInfo("李四")
    printInfo("王五", 25)
    printInfo("赵六", 30, "上海")

    // ============================================================
    // 3. 命名参数
    // ============================================================

    println("\n=== 3. 命名参数 ===")

    fun printInfo2(name: String, age: Int, city: String) {
        println("$name, $age 岁, 来自 $city")
    }
    printInfo2(age = 25, name = "李四", city = "上海")

    // 命名参数与默认参数结合
    fun createUser(name: String, age: Int = 18, isActive: Boolean = true) {
        println("用户: $name, 年龄: $age, 活跃: $isActive")
    }
    createUser("小明", isActive = false)
    createUser("小红", age = 20)

    // ============================================================
    // 4. 可变参数
    // ============================================================

    println("\n=== 4. 可变参数 ===")

    fun sum(vararg numbers: Int): Int {
        return numbers.sum()
    }
    println("sum(1,2,3,4,5) = ${sum(1, 2, 3, 4, 5)}")
    println("sum(10, 20) = ${sum(10, 20)}")

    // 使用展开运算符 *
    val nums = intArrayOf(1, 2, 3, 4, 5)
    println("sum(*nums) = ${sum(*nums)}")

    // 多个参数，vararg 放在最后
    fun join(separator: String, vararg parts: String): String {
        return parts.joinToString(separator)
    }
    println("join(\"-\", \"a\", \"b\", \"c\") = ${join("-", "a", "b", "c")}")

    // ============================================================
    // 5. 局部函数
    // ============================================================

    println("\n=== 5. 局部函数 ===")

    fun outerFunction(x: Int) {
        fun innerFunction(y: Int): Int {
            return y * y
        }
        val result = innerFunction(x)
        println("局部函数计算: $x 的平方 = $result")
    }
    outerFunction(5)

    // ============================================================
    // 6. 高阶函数
    // ============================================================

    println("\n=== 6. 高阶函数 ===")

    // 函数作为参数
    fun applyOperation(a: Int, b: Int, op: (Int, Int) -> Int): Int {
        return op(a, b)
    }

    val result1 = applyOperation(10, 5) { x, y -> x - y }
    println("10 - 5 = $result1")

    val result2 = applyOperation(10, 5) { x, y -> x * y }
    println("10 × 5 = $result2")

    // 函数作为返回值
    fun multiplyBy(factor: Int): (Int) -> Int {
        return { x -> x * factor }
    }

    val double = multiplyBy(2)
    val triple = multiplyBy(3)
    println("double(5) = ${double(5)}")
    println("triple(5) = ${triple(5)}")

    // ============================================================
    // 7. Lambda 表达式
    // ============================================================

    println("\n=== 7. Lambda 表达式 ===")

    // 基本 Lambda
    val square = { x: Int -> x * x }
    println("square(5) = ${square(5)}")

    // 带参数类型推断
    val addLambda: (Int, Int) -> Int = { a, b -> a + b }
    println("addLambda(3, 4) = ${addLambda(3, 4)}")

    // it: 单个参数的隐式名称
    val numbers = listOf(1, 2, 3, 4, 5)
    val doubled = numbers.map { it * 2 }
    println("doubled: $doubled")

    // ============================================================
    // 8. 匿名函数
    // ============================================================

    println("\n=== 8. 匿名函数 ===")

    val adder = fun(a: Int, b: Int): Int = a + b
    println("adder(3, 4) = ${adder(3, 4)}")

    // 带 return 的匿名函数
    val filter = fun(x: Int): Boolean {
        return x > 3
    }
    val filtered = numbers.filter(filter)
    println("filtered (>3): $filtered")

    // ============================================================
    // 9. 中缀函数
    // ============================================================

    println("\n=== 9. 中缀函数 ===")

    infix fun Int.times(str: String): String {
        return str.repeat(this)
    }
    println("3 times \"A\" = ${3 times "A"}")

    // to 就是一个中缀函数
    val pair = "key" to "value"
    println("pair: $pair")

    // ============================================================
    // 10. 扩展函数
    // ============================================================

    println("\n=== 10. 扩展函数 ===")

    fun String.reverse(): String {
        return this.reversed()
    }
    println("\"hello\".reverse() = ${"hello".reverse()}")

    fun Int.isEven(): Boolean {
        return this % 2 == 0
    }
    println("4.isEven() = ${4.isEven()}")
    println("5.isEven() = ${5.isEven()}")

    println("\n=== 运行完成 ===")
}
