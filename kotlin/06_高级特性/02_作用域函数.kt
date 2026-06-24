// ============================================================
// Kotlin 高级特性 - 作用域函数
// 编译运行：kotlinc 02_作用域函数.kt -include-runtime -d out.jar && java -jar out.jar
// ============================================================

fun main() {
    println("=== 1. let 函数 ===")

    // let: 上下文对象是 it，返回值是 lambda 结果
    val str: String? = "Hello"
    val result = str?.let {
        println("let: 字符串 = $it")
        println("let: 长度 = ${it.length}")
        it.uppercase()
    }
    println("let 返回值: $result")

    // let 用于非空检查
    val name: String? = "张三"
    name?.let {
        println("姓名: $it")
    }

    // let 链式调用
    val numbers = listOf(1, 2, 3, 4, 5)
    val result2 = numbers
        .filter { it % 2 == 0 }
        .let {
            println("偶数: $it")
            it.sum()
        }
    println("偶数和: $result2")

    // ============================================================
    // 2. apply 函数
    // ============================================================

    println("\n=== 2. apply 函数 ===")

    // apply: 上下文对象是 this，返回值是对象本身
    data class Person(var name: String = "", var age: Int = 0, var city: String = "")

    val person = Person().apply {
        name = "李四"
        age = 25
        city = "北京"
    }
    println("apply 创建的对象: $person")

    // apply 用于初始化对象
    val list = mutableListOf<Int>().apply {
        add(1)
        add(2)
        add(3)
        add(4)
        add(5)
    }
    println("apply 创建的列表: $list")

    // apply 用于配置对象
    val sb = StringBuilder().apply {
        append("Hello")
        append(" ")
        append("World")
        append("!")
    }
    println("apply 构建字符串: $sb")

    // ============================================================
    // 3. run 函数
    // ============================================================

    println("\n=== 3. run 函数 ===")

    // run: 上下文对象是 this，返回值是 lambda 结果
    val str2 = "Hello"
    val result3 = str2.run {
        println("run: 字符串 = $this")
        println("run: 长度 = ${this.length}")
        this.uppercase()
    }
    println("run 返回值: $result3")

    // run 作为非扩展函数
    val result4 = run {
        val a = 10
        val b = 20
        a + b
    }
    println("非扩展 run: $result4")

    // run 用于对象配置并计算结果
    data class Circle(var radius: Double = 0.0) {
        val area: Double
            get() = Math.PI * radius * radius
    }

    val area = Circle().run {
        radius = 5.0
        area
    }
    println("圆的面积: $area")

    // ============================================================
    // 4. with 函数
    // ============================================================

    println("\n=== 4. with 函数 ===")

    // with: 上下文对象是 this（作为参数传入），返回值是 lambda 结果
    val list2 = listOf(1, 2, 3, 4, 5)
    val stats = with(list2) {
        println("with: 列表 = $this")
        println("with: 大小 = $size")
        "总和: ${sum()}, 平均值: ${average()}"
    }
    println("with 返回值: $stats")

    // with 用于多次调用同一个对象的方法
    val sb2 = StringBuilder()
    with(sb2) {
        append("Hello")
        append(", ")
        append("Kotlin")
        append("!")
    }
    println("with 构建字符串: $sb2")

    // ============================================================
    // 5. also 函数
    // ============================================================

    println("\n=== 5. also 函数 ===")

    // also: 上下文对象是 it，返回值是对象本身
    val numbers2 = mutableListOf<Int>()
    numbers2.also {
        it.add(1)
        it.add(2)
    }.also {
        println("also: 列表 = $it")
    }.also {
        it.add(3)
    }
    println("also 最终列表: $numbers2")

    // also 用于调试
    val result5 = (1..10)
        .filter { it % 2 == 0 }
        .also { println("过滤后: $it") }
        .map { it * it }
        .also { println("映射后: $it") }
        .sum()
    println("最终结果: $result5")

    // also 用于初始化后额外操作
    data class User(val name: String) {
        init {
            println("User $name 创建")
        }
    }

    val user = User("王五").also {
        println("对 ${it.name} 做额外操作")
    }

    // ============================================================
    // 6. takeIf / takeUnless
    // ============================================================

    println("\n=== 6. takeIf / takeUnless ===")

    val num = 42

    val result6 = num.takeIf { it > 10 }
    println("takeIf { it > 10 }: $result6")

    val result7 = num.takeIf { it > 100 }
    println("takeIf { it > 100 }: $result7")

    val result8 = num.takeUnless { it > 100 }
    println("takeUnless { it > 100 }: $result8")

    val result9 = num.takeUnless { it > 10 }
    println("takeUnless { it > 10 }: $result9")

    // takeIf 链式调用
    val str3 = "Hello World"
    val result10 = str3
        .takeIf { it.length > 5 }
        ?.uppercase()
        ?.also { println("处理结果: $it") }
    println("链式调用结果: $result10")

    // ============================================================
    // 7. 作用域函数对比
    // ============================================================

    println("\n=== 7. 作用域函数对比 ===")

    println("""
        |函数        |上下文对象|返回值       |是扩展函数
        |let         |it        |Lambda 结果 |是
        |run         |this      |Lambda 结果 |是
        |run         |-         |Lambda 结果 |否（非扩展）
        |with        |this      |Lambda 结果 |否（传参）
        |apply       |this      |对象本身     |是
        |also        |it        |对象本身     |是
        |takeIf      |it        |对象或null   |是
        |takeUnless  |it        |对象或null   |是
    """.trimMargin())

    println("\n=== 运行完成 ===")
}
