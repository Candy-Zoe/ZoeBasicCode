// ============================================================
// Kotlin 面向对象 - 枚举和接口
// 编译运行：kotlinc 03_枚举和接口.kt -include-runtime -d out.jar && java -jar out.jar
// ============================================================

fun main() {
    println("=== 1. 枚举类 ===")

    // 基本枚举类
    enum class Direction {
        NORTH, SOUTH, EAST, WEST
    }

    val dir = Direction.NORTH
    println("方向: $dir")
    println("方向名称: ${dir.name}")
    println("方向序数: ${dir.ordinal}")

    // 遍历枚举
    println("所有方向:")
    for (d in Direction.values()) {
        println("  ${d.ordinal}: ${d.name}")
    }

    // valueOf
    val east = Direction.valueOf("EAST")
    println("valueOf(\"EAST\"): $east")

    // ============================================================
    // 2. 带属性的枚举
    // ============================================================

    println("\n=== 2. 带属性的枚举 ===")

    enum class Color(val r: Int, val g: Int, val b: Int) {
        RED(255, 0, 0),
        GREEN(0, 255, 0),
        BLUE(0, 0, 255),
        YELLOW(255, 255, 0),
        BLACK(0, 0, 0);

        fun toHex(): String {
            return String.format("#%02X%02X%02X", r, g, b)
        }

        fun isBright(): Boolean {
            return (r + g + b) / 3 > 128
        }
    }

    println("红色: ${Color.RED}, 16进制: ${Color.RED.toHex()}")
    println("绿色: ${Color.GREEN}, 16进制: ${Color.GREEN.toHex()}")
    println("蓝色: ${Color.BLUE}, 16进制: ${Color.BLUE.toHex()}")
    println("黄色是亮色: ${Color.YELLOW.isBright()}")
    println("黑色是亮色: ${Color.BLACK.isBright()}")

    // ============================================================
    // 3. 枚举类实现接口
    // ============================================================

    println("\n=== 3. 枚举类实现接口 ===")

    interface Describable {
        fun describe(): String
    }

    enum class WeekDay : Describable {
        MONDAY {
            override fun describe() = "周一，工作日的开始"
        },
        FRIDAY {
            override fun describe() = "周五，周末就要来了"
        },
        SATURDAY {
            override fun describe() = "周六，快乐周末"
        },
        SUNDAY {
            override fun describe() = "周日，明天要上班了"
        }
    }

    for (day in WeekDay.values()) {
        println("  $day: ${day.describe()}")
    }

    // ============================================================
    // 4. 接口
    // ============================================================

    println("\n=== 4. 接口 ===")

    // 基本接口
    interface Drawable {
        fun draw()
    }

    class Circle0(val radius: Double) : Drawable {
        override fun draw() {
            println("画一个半径为 $radius 的圆")
        }
    }

    class Rectangle0(val width: Double, val height: Double) : Drawable {
        override fun draw() {
            println("画一个 ${width}x${height} 的矩形")
        }
    }

    fun drawShape(shape: Drawable) {
        shape.draw()
    }

    drawShape(Circle0(5.0))
    drawShape(Rectangle0(4.0, 3.0))

    // 接口中的默认实现
    println("\n接口中的默认实现:")

    interface Drawable2 {
        fun draw()
        fun info() {
            println("这是一个可绘制的对象")
        }
    }

    class Circle2(val radius: Double) : Drawable2 {
        override fun draw() {
            println("画圆，半径: $radius")
        }
        // info 可以不重写，使用默认实现
    }

    class Rectangle2(val width: Double, val height: Double) : Drawable2 {
        override fun draw() {
            println("画矩形: ${width}x${height}")
        }
        override fun info() {
            println("这是一个矩形，宽: $width, 高: $height")
        }
    }

    val c2 = Circle2(5.0)
    val r2 = Rectangle2(4.0, 3.0)
    c2.info()
    r2.info()

    // ============================================================
    // 5. 接口中的属性
    // ============================================================

    println("\n=== 5. 接口中的属性 ===")

    interface Named {
        val name: String
        val fullName: String
            get() = "Full: $name"
    }

    class Person2(override val name: String) : Named {
        // name 属性必须重写
    }

    class Company(val brand: String) : Named {
        override val name: String
            get() = brand

        override val fullName: String
            get() = "Company: $brand"
    }

    val person = Person2("张三")
    val company = Company("Google")
    println("Person: name=${person.name}, fullName=${person.fullName}")
    println("Company: name=${company.name}, fullName=${company.fullName}")

    // ============================================================
    // 6. 实现多个接口
    // ============================================================

    println("\n=== 6. 实现多个接口 ===")

    interface Clickable {
        fun click()
        fun showOff() {
            println("我是可点击的")
        }
    }

    interface Focusable {
        fun setFocus(b: Boolean)
        fun showOff() {
            println("我是可聚焦的")
        }
    }

    class Button : Clickable, Focusable {
        override fun click() {
            println("按钮被点击了")
        }

        override fun setFocus(b: Boolean) {
            println("按钮${if (b) "获得" else "失去"}焦点")
        }

        // 两个接口都有 showOff，必须重写
        override fun showOff() {
            super<Clickable>.showOff()
            super<Focusable>.showOff()
        }
    }

    val button = Button()
    button.click()
    button.setFocus(true)
    button.showOff()

    // ============================================================
    // 7. 接口继承
    // ============================================================

    println("\n=== 7. 接口继承 ===")

    interface Animal3 {
        val name: String
        fun eat()
    }

    interface FlyingAnimal : Animal3 {
        fun fly()
    }

    class Bird(override val name: String) : FlyingAnimal {
        override fun eat() {
            println("$name 在啄食")
        }

        override fun fly() {
            println("$name 在飞翔")
        }
    }

    val bird = Bird("麻雀")
    bird.eat()
    bird.fly()

    // ============================================================
    // 8. 函数式接口 SAM
    // ============================================================

    println("\n=== 8. 函数式接口 (SAM) ===")

    fun interface IntPredicate {
        fun accept(i: Int): Boolean
    }

    val isEven = IntPredicate { it % 2 == 0 }
    println("4 是偶数: ${isEven.accept(4)}")
    println("5 是偶数: ${isEven.accept(5)}")

    // 自定义 SAM 接口
    fun interface StringTransformer {
        fun transform(s: String): String
    }

    fun transformString(s: String, transformer: StringTransformer): String {
        return transformer.transform(s)
    }

    val upper = transformString("hello") { it.uppercase() }
    val reversed = transformString("hello") { it.reversed() }
    println("大写: $upper")
    println("反转: $reversed")

    println("\n=== 运行完成 ===")
}
