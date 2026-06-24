// ============================================================
// Kotlin 面向对象 - 数据类和密封类
// 编译运行：kotlinc 02_数据类和密封类.kt -include-runtime -d out.jar && java -jar out.jar
// ============================================================

fun main() {
    println("=== 1. 数据类 ===")

    // 数据类：自动生成 equals/hashCode/toString/copy/componentN
    data class User(val id: Int, val name: String, val email: String)

    val user1 = User(1, "李四", "lisi@example.com")
    val user2 = User(1, "李四", "lisi@example.com")

    println("user1: $user1")
    println("user2: $user2")
    println("user1 == user2: ${user1 == user2}")
    println("user1 === user2: ${user1 === user2}")

    // ============================================================
    // 2. 数据类的 copy
    // ============================================================

    println("\n=== 2. 数据类的 copy ===")

    val user3 = user1.copy(name = "王五")
    println("user1: $user1")
    println("user3 (copy 后): $user3")

    val user4 = user1.copy(id = 2, email = "wangwu@example.com")
    println("user4 (copy 多个属性): $user4")

    // ============================================================
    // 3. 解构声明
    // ============================================================

    println("\n=== 3. 解构声明 ===")

    val (id, name, email) = user1
    println("解构: id=$id, name=$name, email=$email")

    // 只解构需要的属性
    val (_, nameOnly, _) = user1
    println("只取 name: $nameOnly")

    // 数据类在循环中的解构
    val users = listOf(
        User(1, "张三", "zhang@example.com"),
        User(2, "李四", "li@example.com"),
        User(3, "王五", "wang@example.com")
    )

    println("用户列表:")
    for ((uid, uname, uemail) in users) {
        println("  $uid: $uname <$uemail>")
    }

    // ============================================================
    // 4. Pair 和 Triple
    // ============================================================

    println("\n=== 4. Pair 和 Triple ===")

    val pair = Pair("key", 42)
    println("Pair: $pair")
    println("first: ${pair.first}, second: ${pair.second}")

    val (key, value) = pair
    println("解构 Pair: key=$key, value=$value")

    // to 中缀函数创建 Pair
    val mapEntry = "name" to "张三"
    println("to 中缀: $mapEntry")

    val triple = Triple("张三", 25, "北京")
    println("Triple: $triple")
    val (tName, tAge, tCity) = triple
    println("解构 Triple: $tName, $tAge, $tCity")

    // ============================================================
    // 5. 密封类 sealed class
    // ============================================================

    println("\n=== 5. 密封类 sealed class ===")

    // 密封类表示受限的类继承结构
    sealed class Result {
        data class Success(val data: String) : Result()
        data class Error(val message: String) : Result()
        object Loading : Result()
    }

    fun handleResult(result: Result) {
        when (result) {
            is Result.Success -> println("成功: ${result.data}")
            is Result.Error -> println("错误: ${result.message}")
            is Result.Loading -> println("加载中...")
            // 不需要 else，因为密封类的子类是有限的
        }
    }

    handleResult(Result.Success("数据已加载"))
    handleResult(Result.Error("网络错误"))
    handleResult(Result.Loading)

    // 更复杂的密封类例子
    sealed class Expr {
        data class Num(val value: Int) : Expr()
        data class Add(val left: Expr, val right: Expr) : Expr()
        data class Multiply(val left: Expr, val right: Expr) : Expr()
    }

    fun eval(expr: Expr): Int = when (expr) {
        is Expr.Num -> expr.value
        is Expr.Add -> eval(expr.left) + eval(expr.right)
        is Expr.Multiply -> eval(expr.left) * eval(expr.right)
    }

    val expr = Expr.Add(
        Expr.Num(1),
        Expr.Multiply(Expr.Num(2), Expr.Num(3))
    )
    println("表达式计算: 1 + 2 × 3 = ${eval(expr)}")

    // ============================================================
    // 6. 密封接口 sealed interface
    // ============================================================

    println("\n=== 6. 密封接口 sealed interface ===")

    sealed interface Shape {
        fun area(): Double
    }

    data class Circle(val radius: Double) : Shape {
        override fun area() = Math.PI * radius * radius
    }

    data class Rectangle(val width: Double, val height: Double) : Shape {
        override fun area() = width * height
    }

    data class Square(val side: Double) : Shape {
        override fun area() = side * side
    }

    fun totalArea(shapes: List<Shape>): Double {
        return shapes.sumOf { it.area() }
    }

    val shapes = listOf(
        Circle(5.0),
        Rectangle(4.0, 3.0),
        Square(2.0)
    )
    println("总面积: ${totalArea(shapes)}")

    // ============================================================
    // 7. 枚举类 vs 密封类
    // ============================================================

    println("\n=== 7. 枚举类 vs 密封类 ===")

    // 枚举类：每个枚举常量是单例
    enum class Direction {
        NORTH, SOUTH, EAST, WEST
    }

    println("枚举类: Direction.NORTH = ${Direction.NORTH}")

    // 密封类：每个子类可以有多个实例，且可以有不同的状态
    println("密封类: 每个子类可以有不同的属性和多个实例")

    println("\n=== 运行完成 ===")
}
