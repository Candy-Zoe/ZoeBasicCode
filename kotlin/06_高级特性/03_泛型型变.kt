// ============================================================
// Kotlin 高级特性 - 泛型型变
// 编译运行：kotlinc 03_泛型型变.kt -include-runtime -d out.jar && java -jar out.jar
// ============================================================

fun main() {
    println("=== 1. 泛型基础 ===")

    // 泛型函数
    fun <T> identity(value: T): T = value
    println("identity(42) = ${identity(42)}")
    println("identity(\"hello\") = ${identity("hello")}")

    // 泛型类
    class Box<T>(var content: T) {
        fun get(): T = content
        fun set(value: T) { content = value }
        override fun toString(): String = "Box($content)"
    }

    val intBox = Box(100)
    println("Int Box: $intBox, value = ${intBox.get()}")

    val strBox = Box("Hello")
    println("String Box: $strBox, value = ${strBox.get()}")

    // 多个泛型参数
    class Pair<A, B>(val first: A, val second: B) {
        override fun toString(): String = "($first, $second)"
    }

    val pair = Pair("answer", 42)
    println("Pair: $pair")

    // ============================================================
    // 2. 泛型约束
    // ============================================================

    println("\n=== 2. 泛型约束 ===")

    // 上界约束
    fun <T : Comparable<T>> max(a: T, b: T): T {
        return if (a > b) a else b
    }

    println("max(10, 20) = ${max(10, 20)}")
    println("max(\"a\", \"z\") = ${max("a", "z")}")

    // 多个约束（where 子句）
    fun <T> getLength(item: T): Int where T : CharSequence, T : Comparable<T> {
        return item.length
    }

    println("getLength(\"hello\") = ${getLength("hello")}")

    // ============================================================
    // 3. 型变基础
    // ============================================================

    println("\n=== 3. 型变基础 ===")

    open class Animal(val name: String) {
        open fun speak() = println("$name 发出声音")
    }

    class Dog(name: String) : Animal(name) {
        override fun speak() = println("$name 汪汪叫")
    }

    class Cat(name: String) : Animal(name) {
        override fun speak() = println("$name 喵喵叫")
    }

    // 在 Java 中，List<String> 不是 List<Object> 的子类型（不变）
    // Kotlin 中泛型默认也是不变的
    // val animals: List<Animal> = listOf(Dog("旺财")) // 这是可以的，因为 List 是协变的

    // ============================================================
    // 4. 协变 out
    // ============================================================

    println("\n=== 4. 协变 out (生产者) ===")

    // out: 只能生产（读取），不能消费（写入）
    interface Producer<out T> {
        fun produce(): T
    }

    class AnimalProducer : Producer<Animal> {
        override fun produce(): Animal = Animal("动物")
    }

    class DogProducer : Producer<Dog> {
        override fun produce(): Dog = Dog("旺财")
    }

    // Producer<Dog> 可以赋值给 Producer<Animal>（因为 Dog 是 Animal 的子类型，且 out 协变）
    val producer: Producer<Animal> = DogProducer()
    val animal = producer.produce()
    println("协变: producer.produce() = ${animal.name}")

    // List 在 Kotlin 中是协变的（因为它是只读的）
    val dogs: List<Dog> = listOf(Dog("旺财"), Dog("大黄"))
    val animals: List<Animal> = dogs  // 可以，List 协变
    println("List 协变: animals 大小 = ${animals.size}")

    // ============================================================
    // 5. 逆变 in
    // ============================================================

    println("\n=== 5. 逆变 in (消费者) ===")

    // in: 只能消费（写入），不能生产（读取）
    interface Consumer<in T> {
        fun consume(item: T)
    }

    class AnimalConsumer : Consumer<Animal> {
        override fun consume(item: Animal) {
            println("消费动物: ${item.name}")
        }
    }

    class DogConsumer : Consumer<Dog> {
        override fun consume(item: Dog) {
            println("消费狗: ${item.name}")
        }
    }

    // Consumer<Animal> 可以赋值给 Consumer<Dog>（逆变）
    val consumer: Consumer<Dog> = AnimalConsumer()
    consumer.consume(Dog("旺财"))
    println("逆变: consumer 可以消费 Dog")

    // Comparable 是逆变的
    val anyComparator = Comparator<Any> { a, b ->
        a.hashCode() - b.hashCode()
    }
    val stringComparator: Comparator<String> = anyComparator  // 可以，Comparator 逆变
    println("Comparator 逆变: 可以用 Any 的比较器比较 String")

    // ============================================================
    // 6. 不变（默认）
    // ============================================================

    println("\n=== 6. 不变（默认） ===")

    // MutableList 是不变的，因为它既能读又能写
    val mutableDogs: MutableList<Dog> = mutableListOf(Dog("旺财"))
    // val mutableAnimals: MutableList<Animal> = mutableDogs  // 错误，不变

    // 为什么 MutableList 不能协变？
    // 如果可以，我们就能向 mutableAnimals 添加 Cat，
    // 但 mutableDogs 实际上是 Dog 列表，添加 Cat 会导致类型错误

    // ============================================================
    // 7. 使用处型变（类型投影）
    // ============================================================

    println("\n=== 7. 使用处型变（类型投影） ===")

    // out 投影：只能读，不能写
    fun printList(list: MutableList<out Animal>) {
        for (animal in list) {
            println("  ${animal.name}")
        }
        // list.add(Dog(""))  // 错误，out 投影不能添加
    }

    val dogList: MutableList<Dog> = mutableListOf(Dog("旺财"), Dog("大黄"))
    println("out 投影 - 打印 Dog 列表:")
    printList(dogList)

    // in 投影：只能写，读出来是 Any?
    fun addAnimals(list: MutableList<in Dog>) {
        list.add(Dog("新狗狗"))
        // list.add(Cat("猫咪"))  // 错误，只能添加 Dog 或其子类
        val item = list[0]  // 读出来是 Any?
        println("  第一个元素: $item")
    }

    val animalList: MutableList<Animal> = mutableListOf()
    println("in 投影 - 向 Animal 列表添加 Dog:")
    addAnimals(animalList)
    println("  列表大小: ${animalList.size}")

    // ============================================================
    // 8. 星投影
    // ============================================================

    println("\n=== 8. 星投影 ===")

    // * 投影：不知道具体类型，但安全访问
    fun printFirst(list: List<*>) {
        val first = list.firstOrNull()
        println("第一个元素: $first")
    }

    printFirst(listOf(1, 2, 3))
    printFirst(listOf("a", "b", "c"))
    printFirst(listOf(Dog("旺财")))

    // ============================================================
    // 9. 具体化类型参数 reified
    // ============================================================

    println("\n=== 9. 具体化类型参数 reified ===")

    // inline + reified 可以在运行时访问类型参数
    inline fun <reified T> isInstance(value: Any): Boolean {
        return value is T
    }

    println("isInstance<String>(\"hello\") = ${isInstance<String>("hello")}")
    println("isInstance<Int>(\"hello\") = ${isInstance<Int>("hello")}")

    // 实用例子：过滤指定类型
    inline fun <reified T> filterType(list: List<Any>): List<T> {
        return list.filterIsInstance<T>()
    }

    val mixedList: List<Any> = listOf(1, "a", 2, "b", 3, "c")
    val ints = filterType<Int>(mixedList)
    val strings = filterType<String>(mixedList)
    println("整数: $ints")
    println("字符串: $strings")

    println("\n=== 运行完成 ===")
}
