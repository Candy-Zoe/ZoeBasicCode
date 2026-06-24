// ============================================================
// Kotlin 高级特性 - 空安全
// 编译运行：kotlinc 01_空安全.kt -include-runtime -d out.jar && java -jar out.jar
// ============================================================

fun main() {
    println("=== 1. 可空类型和非空类型 ===")

    // 非空类型（默认）：不能为 null
    var nonNullStr: String = "Hello"
    // nonNullStr = null  // 编译错误
    println("非空字符串: $nonNullStr")
    println("长度: ${nonNullStr.length}")

    // 可空类型：用 ? 标记，可以为 null
    var nullableStr: String? = "Hello"
    println("可空字符串: $nullableStr")
    nullableStr = null
    println("可空字符串设为 null: $nullableStr")

    // ============================================================
    // 2. 安全调用 ?.
    // ============================================================

    println("\n=== 2. 安全调用 ?. ===")

    val str: String? = "Hello"
    println("str?.length = ${str?.length}")

    val nullStr: String? = null
    println("nullStr?.length = ${nullStr?.length}")

    // 链式安全调用
    data class Person(val name: String?, val address: Address?)
    data class Address(val city: String?, val street: String?)

    val person = Person("张三", Address("北京", "长安街"))
    val city = person.address?.city
    println("城市: $city")

    val person2 = Person("李四", null)
    val city2 = person2.address?.city
    println("城市2: $city2")

    // ============================================================
    // 3. Elvis 操作符 ?:
    // ============================================================

    println("\n=== 3. Elvis 操作符 ?: ===")

    val name: String? = null
    val displayName = name ?: "未知"
    println("显示名称: $displayName")

    val name2: String? = "张三"
    val displayName2 = name2 ?: "未知"
    println("显示名称2: $displayName2")

    // Elvis 操作符与 return/throw 结合
    fun getLength(str: String?): Int {
        val s = str ?: return 0
        return s.length
    }
    println("getLength(\"hello\") = ${getLength("hello")}")
    println("getLength(null) = ${getLength(null)}")

    fun requireNotNull(str: String?): String {
        return str ?: throw IllegalArgumentException("字符串不能为 null")
    }
    try {
        requireNotNull(null)
    } catch (e: IllegalArgumentException) {
        println("捕获异常: ${e.message}")
    }

    // ============================================================
    // 4. 非空断言 !!
    // ============================================================

    println("\n=== 4. 非空断言 !! ===")

    val notNull: String? = "Hello"
    val length = notNull!!.length
    println("notNull!!.length = $length")

    // !! 在确定不为 null 时使用，否则会抛出 NullPointerException
    val maybeNull: String? = "test"
    if (maybeNull != null) {
        println("maybeNull!!.length = ${maybeNull!!.length}")
    }

    // 注意：!! 可能导致 NullPointerException
    // val nullStr2: String? = null
    // nullStr2!!.length  // 会抛出 NullPointerException

    // ============================================================
    // 5. 安全转换 as?
    // ============================================================

    println("\n=== 5. 安全转换 as? ===")

    val obj: Any = "Hello"
    val str2: String? = obj as? String
    println("安全转换 String: $str2")

    val obj2: Any = 123
    val str3: String? = obj2 as? String
    println("安全转换 String (失败): $str3")

    // 传统的 is 检查 + 智能转换
    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            // 智能转换：obj 自动转换为 String
            return obj.length
        }
        return null
    }
    println("getStringLength(\"hello\") = ${getStringLength("hello")}")
    println("getStringLength(123) = ${getStringLength(123)}")

    // ============================================================
    // 6. 可空类型的集合
    // ============================================================

    println("\n=== 6. 可空类型的集合 ===")

    // 可空元素的列表
    val listWithNulls: List<String?> = listOf("A", null, "B", null, "C")
    println("含 null 的列表: $listWithNulls")

    // 过滤掉 null
    val listWithoutNulls: List<String> = listWithNulls.filterNotNull()
    println("过滤 null 后: $listWithoutNulls")

    // 可空的列表
    val nullableList: List<String>? = listOf("a", "b", "c")
    println("可空列表的大小: ${nullableList?.size}")

    // 安全调用 + 函数式操作
    val result = nullableList?.filter { it.length > 0 }?.map { it.uppercase() }
    println("结果: $result")

    // ============================================================
    // 7. let 函数与空安全
    // ============================================================

    println("\n=== 7. let 函数与空安全 ===")

    val email: String? = "test@example.com"

    email?.let {
        println("邮箱地址: $it")
        println("邮箱长度: ${it.length}")
    }

    val nullEmail: String? = null
    nullEmail?.let {
        println("这行不会执行")
    }

    // let 返回值
    val result2 = email?.let {
        "邮箱: $it"
    } ?: "没有邮箱"
    println(result2)

    // ============================================================
    // 8. 延迟初始化 lateinit
    // ============================================================

    println("\n=== 8. 延迟初始化 lateinit ===")

    class LateInitExample {
        lateinit var name: String

        fun initName(n: String) {
            name = n
        }

        fun printName() {
            if (::name.isInitialized) {
                println("name = $name")
            } else {
                println("name 还未初始化")
            }
        }
    }

    val example = LateInitExample()
    example.printName()
    example.initName("张三")
    example.printName()

    // ============================================================
    // 9. lazy 懒加载
    // ============================================================

    println("\n=== 9. lazy 懒加载 ===")

    val lazyValue: String by lazy {
        println("正在初始化 lazyValue...")
        "Hello, Lazy!"
    }

    println("第一次访问:")
    println(lazyValue)
    println("第二次访问:")
    println(lazyValue)

    println("\n=== 运行完成 ===")
}
