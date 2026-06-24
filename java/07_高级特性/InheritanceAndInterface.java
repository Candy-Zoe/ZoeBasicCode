// ============================================================
// Java 高级特性 - 继承、接口、多态
// 编译运行：javac InheritanceAndInterface.java && java InheritanceAndInterface
// ============================================================

import java.util.*;

// ============================================================
// 1. 类的继承
// ============================================================

class Animal {
    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("[构造] Animal: " + name);
    }

    public void speak() {
        System.out.println(name + " 发出声音");
    }

    public void eat() {
        System.out.println(name + " 在吃东西");
    }

    public String getName() { return name; }
    public int getAge() { return age; }
}

class Dog extends Animal {
    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);  // 调用父类构造方法
        this.breed = breed;
        System.out.println("[构造] Dog: " + name);
    }

    @Override
    public void speak() {
        System.out.println(name + " 汪汪叫");
    }

    public void fetch() {
        System.out.println(name + " 在接飞盘");
    }

    public String getBreed() { return breed; }
}

class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public void speak() {
        System.out.println(name + " 喵喵叫");
    }

    public void climb() {
        System.out.println(name + " 在爬树");
    }
}

public class InheritanceAndInterface {

    static void demoInheritance() {
        System.out.println("=== 1. 类的继承 ===");

        System.out.println("\n--- 创建 Dog ---");
        Dog dog = new Dog("旺财", 3, "金毛");
        dog.speak();
        dog.eat();      // 继承自 Animal
        dog.fetch();    // Dog 特有
        System.out.println("品种: " + dog.getBreed());

        System.out.println("\n--- 创建 Cat ---");
        Cat cat = new Cat("咪咪", 2);
        cat.speak();
        cat.climb();
    }

    // ============================================================
    // 2. 接口
    // ============================================================

    interface Flyable {
        void fly();

        default void takeOff() {
            System.out.println("起飞！");
        }

        static void info() {
            System.out.println("这是 Flyable 接口");
        }
    }

    interface Swimmable {
        void swim();
    }

    interface Runnable {
        void run();
    }

    static class Duck extends Animal implements Flyable, Swimmable, Runnable {
        public Duck(String name, int age) {
            super(name, age);
        }

        @Override
        public void speak() {
            System.out.println(name + " 嘎嘎叫");
        }

        @Override
        public void fly() {
            System.out.println(name + " 在飞翔");
        }

        @Override
        public void swim() {
            System.out.println(name + " 在游泳");
        }

        @Override
        public void run() {
            System.out.println(name + " 在奔跑");
        }
    }

    static void demoInterface() {
        System.out.println("\n=== 2. 接口 ===");

        Duck duck = new Duck("唐老鸭", 5);
        duck.speak();
        duck.fly();
        duck.swim();
        duck.run();
        duck.takeOff();  // 默认方法

        Flyable.info();  // 静态方法
    }

    // ============================================================
    // 3. 多态
    // ============================================================

    static void makeSpeak(Animal animal) {
        animal.speak();
    }

    static void demoPolymorphism() {
        System.out.println("\n=== 3. 多态 ===");

        Animal[] animals = {
            new Dog("旺财", 3, "金毛"),
            new Cat("咪咪", 2),
            new Duck("唐老鸭", 5)
        };

        System.out.println("多态调用 speak():");
        for (Animal animal : animals) {
            System.out.print("  ");
            makeSpeak(animal);
        }

        // instanceof 类型检查
        System.out.println("\ninstanceof 类型检查:");
        for (Animal animal : animals) {
            if (animal instanceof Dog) {
                System.out.println("  " + animal.getName() + " 是狗");
            } else if (animal instanceof Cat) {
                System.out.println("  " + animal.getName() + " 是猫");
            } else {
                System.out.println("  " + animal.getName() + " 是鸭子");
            }
        }

        // 向下转型
        System.out.println("\n向下转型:");
        Animal a = new Dog("小白", 2, "萨摩耶");
        if (a instanceof Dog) {
            Dog d = (Dog) a;
            d.fetch();
        }
    }

    // ============================================================
    // 4. 抽象类
    // ============================================================

    static abstract class Shape {
        protected String color;

        public Shape(String color) {
            this.color = color;
        }

        public abstract double area();
        public abstract double perimeter();

        public void printInfo() {
            System.out.println("颜色: " + color + ", 面积: " + area() + ", 周长: " + perimeter());
        }
    }

    static class Rectangle extends Shape {
        private double width, height;

        public Rectangle(String color, double w, double h) {
            super(color);
            this.width = w;
            this.height = h;
        }

        @Override
        public double area() {
            return width * height;
        }

        @Override
        public double perimeter() {
            return 2 * (width + height);
        }
    }

    static class Circle extends Shape {
        private double radius;

        public Circle(String color, double r) {
            super(color);
            this.radius = r;
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }

        @Override
        public double perimeter() {
            return 2 * Math.PI * radius;
        }
    }

    static void demoAbstractClass() {
        System.out.println("\n=== 4. 抽象类 ===");

        // Shape s = new Shape("red");  // 错误！不能实例化抽象类

        Shape[] shapes = {
            new Rectangle("红色", 4, 5),
            new Circle("蓝色", 3),
            new Rectangle("绿色", 6, 7)
        };

        System.out.println("各种图形:");
        for (Shape shape : shapes) {
            System.out.print("  ");
            shape.printInfo();
        }
    }

    // ============================================================
    // 5. 封装
    // ============================================================

    static class Person {
        private String name;
        private int age;
        private String idCard;

        public Person(String name, int age, String idCard) {
            this.name = name;
            setAge(age);
            this.idCard = idCard;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            if (age < 0 || age > 150) {
                throw new IllegalArgumentException("年龄无效: " + age);
            }
            this.age = age;
        }

        // 身份证号只读
        public String getIdCard() {
            return idCard.substring(0, 6) + "********" + idCard.substring(14);
        }
    }

    static void demoEncapsulation() {
        System.out.println("\n=== 5. 封装 ===");

        Person person = new Person("张三", 25, "110101199001011234");
        System.out.println("姓名: " + person.getName());
        System.out.println("年龄: " + person.getAge());
        System.out.println("身份证: " + person.getIdCard());

        person.setAge(26);
        System.out.println("修改后年龄: " + person.getAge());

        try {
            person.setAge(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("捕获异常: " + e.getMessage());
        }
    }

    // ============================================================
    // 6. 构造/析构顺序
    // ============================================================

    static class A {
        public A() { System.out.println("  A 构造"); }
        protected void finalize() { System.out.println("  A 析构"); }
    }

    static class B extends A {
        public B() { System.out.println("  B 构造"); }
    }

    static class C extends B {
        public C() { System.out.println("  C 构造"); }
    }

    static void demoCtorOrder() {
        System.out.println("\n=== 6. 构造顺序 ===");
        System.out.println("创建 C 对象:");
        new C();
        System.out.println("构造顺序: 基类 → 派生类");
    }

    public static void main(String[] args) {
        demoInheritance();
        demoInterface();
        demoPolymorphism();
        demoAbstractClass();
        demoEncapsulation();
        demoCtorOrder();

        System.out.println("\n=== 运行完成 ===");
    }
}