// ============================================================
// Java 方法
// 编译运行：javac Methods.java && java Methods
// ============================================================

public class Methods {
    public static void main(String[] args) {
        System.out.println("=== 1. 基本方法 ===");
        greet();
        greetUser("张三");
        System.out.println("3 + 5 = " + add(3, 5));

        System.out.println("\n=== 2. 方法重载 ===");
        System.out.println("add(3, 5) = " + add(3, 5));
        System.out.println("add(3.5, 2.5) = " + add(3.5, 2.5));
        System.out.println("add(\"Hello\", \" World\") = " + add("Hello", " World"));

        System.out.println("\n=== 3. 可变参数 ===");
        System.out.println("sum(1,2,3) = " + sum(1, 2, 3));
        System.out.println("sum(1,2,3,4,5) = " + sum(1, 2, 3, 4, 5));

        System.out.println("\n=== 4. 递归方法 ===");
        System.out.println("阶乘:");
        for (int i = 0; i < 6; i++) {
            System.out.println(i + "! = " + factorial(i));
        }

        System.out.println("\n=== 5. 静态方法 vs 实例方法 ===");
        // 静态方法直接调用
        System.out.println("静态方法调用: " + staticMethod());
        
        // 实例方法需要先创建对象
        Methods obj = new Methods();
        System.out.println("实例方法调用: " + obj.instanceMethod());

        System.out.println("\n=== 6. 方法的参数传递 ===");
        // 基本类型：值传递
        int x = 10;
        modifyInt(x);
        System.out.println("基本类型传递后 x = " + x + " (不改变)");

        // 引用类型：引用传递
        int[] arr = {1, 2, 3};
        modifyArray(arr);
        System.out.print("数组传递后: ");
        for (int n : arr) System.out.print(n + " ");
        System.out.println(" (改变了)");

        System.out.println("\n=== 7. 方法重写（在继承中） ===");
        Animal dog = new Dog();
        dog.speak();  // 调用子类重写的方法

        System.out.println("\n=== 8. super 关键字 ===");
        Dog dog2 = new Dog();
        dog2.makeSound();

        System.out.println("\n=== 9. final 方法 ===");
        System.out.println("final 方法不能被子类重写");

        System.out.println("\n=== 10. 抽象方法 ===");
        Shape rect = new Rectangle(4, 5);
        System.out.println("矩形面积: " + rect.area());
    }

    // 无参数无返回值
    static void greet() {
        System.out.println("Hello, World!");
    }

    // 带参数的方法
    static void greetUser(String name) {
        System.out.println("你好，" + name + "！");
    }

    // 带返回值的方法
    static int add(int a, int b) {
        return a + b;
    }

    // 方法重载：参数类型不同
    static double add(double a, double b) {
        return a + b;
    }

    // 方法重载：参数类型不同
    static String add(String a, String b) {
        return a + b;
    }

    // 可变参数
    static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }

    // 递归方法
    static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    // 静态方法
    static String staticMethod() {
        return "我是静态方法";
    }

    // 实例方法
    String instanceMethod() {
        return "我是实例方法";
    }

    // 修改基本类型（值传递）
    static void modifyInt(int n) {
        n = 100;
    }

    // 修改数组（引用传递）
    static void modifyArray(int[] a) {
        a[0] = 100;
    }
}

class Animal {
    void speak() {
        System.out.println("动物发出声音");
    }
}

class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("汪汪汪");
    }

    void makeSound() {
        super.speak();  // 调用父类方法
        speak();        // 调用本类方法
    }
}

abstract class Shape {
    abstract double area();
}

class Rectangle extends Shape {
    double width, height;
    Rectangle(double w, double h) {
        width = w;
        height = h;
    }
    @Override
    double area() {
        return width * height;
    }
}
