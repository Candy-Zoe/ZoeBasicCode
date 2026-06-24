// ============================================================
// Kotlin 基础语法
// 编译运行：kotlinc 01_基础语法.kt -include-runtime -d basickt.jar && java -jar basickt.jar
// ============================================================

fun main() {
    println("=== 1. 变量和数据类型 ===")

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

    // 字符串模板
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

    // ============================================================
    // 2. 基本类型
    // ============================================================

    println("\n=== 2. 基本类型 ===")

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
    // 3. 数组
    // ============================================================

    println("\n=== 3. 数组 ===")

    val nums = intArrayOf(1, 2, 3, 4, 5)
    println("IntArray: ${nums.joinToString()}")

    val strs = arrayOf("苹果", "香蕉", "橙子")
    println("Array: ${strs.joinToString()}")
    println("第一个元素: ${strs[0]}")
    println("数组大小: ${strs.size}")

    val zeros = IntArray(5)
    println("零数组: ${zeros.joinToString()}")

    val doubles = DoubleArray(3) { it * 1.5 }
    println("DoubleArray: ${doubles.joinToString()}")

    // ============================================================
    // 4. 集合
    // ============================================================

    println("\n=== 4. 集合 ===")

    // List - 只读
    val list = listOf(1, 2, 3, 4, 5)
    println("List: $list")
    println("大小: ${list.size}, 第一个: ${list.first()}")

    // MutableList - 可变
    val mList = mutableListOf("A", "B", "C")
    mList.add("D")
    mList[0] = "AAA"
    println("MutableList: $mList")

    // Set - 无序不重复
    val set = setOf(1, 2, 3, 3, 4, 4, 5)
    println("Set: $set")

    // Map
    val map = mapOf("name" to "张三", "age" to 25, "city" to "北京")
    println("Map: $map")
    println("姓名: ${map["name"]}")

    val mMap = mutableMapOf(1 to "one", 2 to "two")
    mMap[3] = "three"
    println("MutableMap: $mMap")

    // 集合操作
    val numbers = (1..10).toList()
    println("原列表: $numbers")
    println("过滤偶数: ${numbers.filter { it % 2 == 0 }}")
    println("映射平方: ${numbers.map { it * it }}")
    println("排序降序: ${numbers.sortedDescending()}")
    println("总和: ${numbers.sum()}, 平均值: ${numbers.average()}")
    println("分组: ${numbers.groupBy { it % 2 }}")

    // ============================================================
    // 5. 函数
    // ============================================================

    println("\n=== 5. 函数 ===")

    // 基本函数
    fun add(a: Int, b: Int): Int {
        return a + b
    }
    println("add(3, 5) = ${add(3, 5)}")

    // 表达式函数体
    fun multiply(a: Int, b: Int) = a * b
    println("multiply(3, 5) = ${multiply(3, 5)}")

    // 默认参数
    fun greet(name: String = "世界") {
        println("你好, $name!")
    }
    greet()
    greet("张三")

    // 命名参数
    fun printInfo(name: String, age: Int, city: String) {
        println("$name, $age 岁, 来自 $city")
    }
    printInfo(age = 25, name = "李四", city = "上海")

    // 可变参数
    fun sum(vararg numbers: Int): Int {
        return numbers.sum()
    }
    println("sum(1,2,3,4,5) = ${sum(1, 2, 3, 4, 5)}")

    // 中缀函数
    infix fun Int.times(str: String): String {
        return str.repeat(this)
    }
    println("3 times \"A\" = ${3 times "A"}")

    // 扩展函数
    fun String.reverse(): String {
        return this.reversed()
    }
    println("\"hello\".reverse() = ${"hello".reverse()}")

    // 高阶函数
    fun applyOperation(a: Int, b: Int, op: (Int, Int) -> Int): Int {
        return op(a, b)
    }
    val result = applyOperation(10, 5) { x, y -> x - y }
    println("10 - 5 = $result")

    // Lambda 表达式
    val square = { x: Int -> x * x }
    println("square(5) = ${square(5)}")

    // 匿名函数
    val adder = fun(a: Int, b: Int): Int = a + b
    println("adder(3, 4) = ${adder(3, 4)}")

    // ============================================================
    // 6. 控制流
    // ============================================================

    println("\n=== 6. 控制流 ===")

    // if 表达式
    val score = 85
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

    // when 表达式
    val day = "周一"
    val dayType = when (day) {
        "周一", "周二", "周三", "周四", "周五" -> "工作日"
        "周六", "周日" -> "周末"
        else -> "无效日期"
    }
    println("$day 是 $dayType")

    // when 不带参数
    val x2 = 15
    val result2 = when {
        x2 < 10 -> "小于10"
        x2 < 20 -> "小于20"
        else -> "大于等于20"
    }
    println("x = $x2, $result2")

    // for 循环
    println("for 循环:")
    for (i in 1..3) {
        println("  $i")
    }

    println("downTo:")
    for (i in 3 downTo 1) {
        println("  $i")
    }

    println("step:")
    for (i in 1..5 step 2) {
        println("  $i")
    }

    println("until (不包含上限):")
    for (i in 1 until 3) {
        println("  $i")
    }

    // 遍历集合
    val fruits = listOf("苹果", "香蕉", "橙子")
    println("遍历列表:")
    for ((index, fruit) in fruits.withIndex()) {
        println("  $index. $fruit")
    }

    // while 循环
    println("while 循环:")
    var i = 1
    while (i <= 3) {
        println("  $i")
        i++
    }

    // do-while 循环
    println("do-while 循环:")
    i = 1
    do {
        println("  $i")
        i++
    } while (i <= 3)

    // return / break / continue
    println("break/continue:")
    loop@ for (i in 1..3) {
        for (j in 1..3) {
            if (j == 2) continue
            if (i == 3) break@loop
            println("  i=$i, j=$j")
        }
    }

    // ============================================================
    // 7. 类与对象
    // ============================================================

    println("\n=== 7. 类与对象 ===")

    // 基本类
    class Person(val name: String, var age: Int) {
        fun greet() {
            println("你好，我是$name，$age 岁")
        }
    }

    val p = Person("张三", 25)
    p.greet()
    p.age = 26
    println("修改后年龄: ${p.age}")

    // 数据类
    data class User(val id: Int, val name: String, val email: String)

    val user1 = User(1, "李四", "lisi@example.com")
    val user2 = User(1, "李四", "lisi@example.com")
    println("user1: $user1")
    println("user1 == user2: ${user1 == user2}")

    // 解构声明
    val (id, name2, email) = user1
    println("解构: id=$id, name=$name2, email=$email")

    // copy
    val user3 = user1.copy(name = "王五")
    println("copy 后: $user3")

    // 枚举类
    enum class Direction {
        NORTH, SOUTH, EAST, WEST
    }

    val dir = Direction.NORTH
    println("方向: $dir")

    // 带属性的枚举
    enum class Color(val r: Int, val g: Int, val b: Int) {
        RED(255, 0, 0),
        GREEN(0, 255, 0),
        BLUE(0, 0, 255);

        fun toHex(): String {
            return String.format("#%02X%02X%02X", r, g, b)
        }
    }
    println("红色: ${Color.RED}, 16进制: ${Color.RED.toHex()}")

    // 单例对象
    object Singleton {
        val version = "1.0"
        fun info() {
            println("这是单例对象，版本: $version")
        }
    }
    Singleton.info()

    // 伴生对象
    class MyClass {
        companion object {
            fun create(): MyClass = MyClass()
            val TAG = "MyClass"
        }
    }
    val instance = MyClass.create()
    println("伴生对象 TAG: ${MyClass.TAG}")

    // 继承
    open class Animal(val name: String) {
        open fun speak() {
            println("$name 发出声音")
        }
    }

    class Dog(name: String) : Animal(name) {
        override fun speak() {
            println("$name 汪汪叫")
        }

        fun fetch() {
            println("$name 在接飞盘")
        }
    }

    val dog = Dog("旺财")
    dog.speak()
    dog.fetch()

    // 接口
    interface Drawable {
        fun draw()
        fun info() {
            println("可以绘制")
        }
    }

    class Circle(val radius: Double) : Drawable {
        override fun draw() {
            println("画一个半径为 $radius 的圆")
        }
    }

    val circle = Circle(5.0)
    circle.draw()
    circle.info()

    // 密封类
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
        }
    }
    handleResult(Result.Success("数据已加载"))
    handleResult(Result.Error("网络错误"))

    // ============================================================
    // 8. 空安全
    // ============================================================

    println("\n=== 8. 空安全 ===")

    // 可空类型
    var nullableStr: String? = "Hello"
    println("nullableStr = $nullableStr")
    nullableStr = null
    println("nullableStr = $nullableStr")

    // 安全调用 ?.
    val len = nullableStr?.length
    println("长度: $len")

    // 非空断言 !!
    val notNull = "Hello"
    println("长度: ${notNull!!.length}")

    // Elvis 操作符
    val name3: String? = null
    val displayName = name3 ?: "未知"
    println("显示名称: $displayName")

    // 安全转换
    val obj: Any = "Hello"
    val str: String? = obj as? String
    println("安全转换: $str")

    // ============================================================
    // 9. 泛型
    // ============================================================

    println("\n=== 9. 泛型 ===")

    // 泛型函数
    fun <T> identity(value: T): T = value
    println("identity(42) = ${identity(42)}")
    println("identity(\"hello\") = ${identity("hello")}")

    // 泛型类
    class Box<T>(var content: T) {
        fun get(): T = content
        fun set(value: T) { content = value }
        fun isEmpty(): Boolean = content == null
    }

    val intBox = Box(100)
    println("Box content: ${intBox.get()}")

    val strBox = Box("Hello")
    println("Box content: ${strBox.get()}")

    // 泛型约束
    fun <T : Comparable<T>> max(a: T, b: T): T {
        return if (a > b) a else b
    }
    println("max(10, 20) = ${max(10, 20)}")
    println("max(\"a\", \"z\") = ${max("a", "z")}")

    // 型变：out (协变) 和 in (逆变)
    interface Producer<out T> {
        fun produce(): T
    }

    interface Consumer<in T> {
        fun consume(item: T)
    }

    println("泛型协变/逆变: out 表示生产者, in 表示消费者")

    // ============================================================
    // 10. 作用域函数
    // ============================================================

    println("\n=== 10. 作用域函数 ===")

    // let
    val str: String? = "Hello"
    str?.let {
        println("let: 字符串长度 = ${it.length}")
    }

    // apply
    val list2 = mutableListOf<Int>().apply {
        add(1)
        add(2)
        add(3)
    }
    println("apply 创建的列表: $list2")

    // run
    val result3 = StringBuilder().run {
        append("Hello")
        append(" ")
        append("World")
        toString()
    }
    println("run 结果: $result3")

    // with
    val list3 = listOf(1, 2, 3)
    val size = with(list3) {
        println("with: 列表 = $this")
        size
    }
    println("with 返回大小: $size")

    // also
    val numbers2 = mutableListOf<Int>()
    numbers2.also {
        it.add(1)
        it.add(2)
    }.also {
        println("also: $it")
    }

    // takeIf / takeUnless
    val num = 42
    val result4 = num.takeIf { it > 10 }
    println("takeIf { it > 10 }: $result4")

    val result5 = num.takeUnless { it > 100 }
    println("takeUnless { it > 100 }: $result5")

    println("\n=== 运行完成 ===")
}