// ============================================================
// Kotlin 面向对象 - 类和对象
// 编译运行：kotlinc 01_类和对象.kt -include-runtime -d out.jar && java -jar out.jar
// ============================================================

fun main() {
    println("=== 1. 类的基础 ===")

    // 基本类
    class Person(val name: String, var age: Int) {
        fun greet() {
            println("你好，我是$name，$age 岁")
        }

        fun haveBirthday() {
            age++
            println("$name 过生日了，现在 $age 岁")
        }
    }

    val p = Person("张三", 25)
    p.greet()
    p.haveBirthday()
    println("修改后年龄: ${p.age}")

    // ============================================================
    // 2. 构造函数
    // ============================================================

    println("\n=== 2. 构造函数 ===")

    // 主构造函数 + init 块
    class User(val name: String, val age: Int) {
        val isAdult: Boolean

        init {
            isAdult = age >= 18
            println("用户 $name 创建完成")
        }

        fun info() {
            println("姓名: $name, 年龄: $age, 成年: $isAdult")
        }
    }

    val user = User("李四", 20)
    user.info()

    // 次构造函数
    class Student {
        val name: String
        val age: Int
        val school: String

        constructor(name: String, age: Int) {
            this.name = name
            this.age = age
            this.school = "未知学校"
        }

        constructor(name: String, age: Int, school: String) {
            this.name = name
            this.age = age
            this.school = school
        }

        fun info() {
            println("$name, $age 岁, $school")
        }
    }

    val s1 = Student("王五", 18)
    val s2 = Student("赵六", 20, "清华大学")
    s1.info()
    s2.info()

    // ============================================================
    // 3. 继承
    // ============================================================

    println("\n=== 3. 继承 ===")

    // open 表示可以被继承
    open class Animal(val name: String) {
        open fun speak() {
            println("$name 发出声音")
        }

        fun eat() {
            println("$name 在吃东西")
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

    class Cat(name: String) : Animal(name) {
        override fun speak() {
            println("$name 喵喵叫")
        }

        fun catchMouse() {
            println("$name 在抓老鼠")
        }
    }

    val dog = Dog("旺财")
    dog.speak()
    dog.fetch()
    dog.eat()

    val cat = Cat("咪咪")
    cat.speak()
    cat.catchMouse()

    // ============================================================
    // 4. 可见性修饰符
    // ============================================================

    println("\n=== 4. 可见性修饰符 ===")

    open class Base {
        public val publicProp = "public"
        protected val protectedProp = "protected"
        private val privateProp = "private"
        internal val internalProp = "internal"

        fun test() {
            println("类内部可访问:")
            println("  public: $publicProp")
            println("  protected: $protectedProp")
            println("  private: $privateProp")
            println("  internal: $internalProp")
        }
    }

    class Derived : Base() {
        fun access() {
            println("子类可访问:")
            println("  public: $publicProp")
            println("  protected: $protectedProp")
            println("  internal: $internalProp")
            // println("  private: $privateProp") // 错误，子类不能访问
        }
    }

    val base = Base()
    base.test()
    val derived = Derived()
    derived.access()
    println("外部可访问:")
    println("  public: ${base.publicProp}")
    println("  internal: ${base.internalProp}")
    // println("  protected: ${base.protectedProp}") // 错误
    // println("  private: ${base.privateProp}") // 错误

    // ============================================================
    // 5. 抽象类
    // ============================================================

    println("\n=== 5. 抽象类 ===")

    abstract class Shape {
        abstract fun area(): Double
        abstract fun perimeter(): Double

        fun info() {
            println("面积: ${area()}, 周长: ${perimeter()}")
        }
    }

    class Circle(val radius: Double) : Shape() {
        override fun area(): Double = Math.PI * radius * radius
        override fun perimeter(): Double = 2 * Math.PI * radius
    }

    class Rectangle(val width: Double, val height: Double) : Shape() {
        override fun area(): Double = width * height
        override fun perimeter(): Double = 2 * (width + height)
    }

    val circle = Circle(5.0)
    println("圆形:")
    circle.info()

    val rectangle = Rectangle(4.0, 3.0)
    println("矩形:")
    rectangle.info()

    // ============================================================
    // 6. 单例对象 object
    // ============================================================

    println("\n=== 6. 单例对象 object ===")

    object Singleton {
        val version = "1.0"
        fun info() {
            println("这是单例对象，版本: $version")
        }
    }

    Singleton.info()
    println("Singleton.version = ${Singleton.version}")

    // ============================================================
    // 7. 伴生对象
    // ============================================================

    println("\n=== 7. 伴生对象 ===")

    class MyClass {
        companion object {
            fun create(): MyClass = MyClass()
            val TAG = "MyClass"

            fun fromValue(value: String): MyClass {
                println("从 $value 创建 MyClass")
                return MyClass()
            }
        }

        fun hello() {
            println("Hello from MyClass")
        }
    }

    val instance = MyClass.create()
    println("伴生对象 TAG: ${MyClass.TAG}")
    val instance2 = MyClass.fromValue("test")
    instance.hello()

    // ============================================================
    // 8. 嵌套类和内部类
    // ============================================================

    println("\n=== 8. 嵌套类和内部类 ===")

    class Outer {
        private val bar = 1

        class Nested {
            fun foo() = 2
        }

        inner class Inner {
            fun foo() = bar
        }
    }

    val nested = Outer.Nested()
    println("嵌套类: ${nested.foo()}")

    val inner = Outer().Inner()
    println("内部类: ${inner.foo()}")

    println("\n=== 运行完成 ===")
}
