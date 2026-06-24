// ============================================================
// Java 面向对象
// 编译运行：javac OOPDemo.java && java OOPDemo
// ============================================================

public class OOPDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. 类和对象 ===");
        Person person1 = new Person("张三", 25);
        Person person2 = new Person("李四", 30);
        person1.greet();
        person2.greet();

        System.out.println("\n=== 2. 封装（访问修饰符） ===");
        BankAccount account = new BankAccount(1000);
        System.out.println("余额: " + account.getBalance());
        account.deposit(500);
        account.withdraw(300);
        System.out.println("当前余额: " + account.getBalance());

        System.out.println("\n=== 3. 继承 ===");
        Dog dog = new Dog("旺财");
        dog.speak();
        dog.fetch();

        System.out.println("\n=== 4. 多态 ===");
        Animal[] animals = {new Dog("旺财"), new Cat("咪咪"), new Dog("大黄")};
        for (Animal animal : animals) {
            animal.speak();
        }

        System.out.println("\n=== 5. 抽象类 ===");
        Shape[] shapes = {new RectangleShape(4, 5), new CircleShape(3)};
        for (Shape shape : shapes) {
            System.out.println(shape.getName() + ": 面积=" + shape.area() + ", 周长=" + shape.perimeter());
        }

        System.out.println("\n=== 6. 接口 ===");
        Movable car = new Car();
        Movable plane = new Plane();
        car.move();
        plane.move();

        System.out.println("\n=== 7. 静态成员 ===");
        System.out.println("学生总数: " + Student.getCount());
        Student s1 = new Student("小明");
        Student s2 = new Student("小红");
        System.out.println("学生总数: " + Student.getCount());

        System.out.println("\n=== 8. 内部类 ===");
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.show();

        System.out.println("\n=== 9. 枚举 ===");
        Color color = Color.RED;
        System.out.println("color = " + color);
        System.out.println("color 值 = " + color.getValue());
        System.out.println("所有颜色:");
        for (Color c : Color.values()) {
            System.out.println("  " + c + " = " + c.getValue());
        }

        System.out.println("\n=== 10. 接口的默认方法和静态方法 ===");
        MyInterface impl = new MyClass();
        impl.abstractMethod();
        impl.defaultMethod();
        MyInterface.staticMethod();

        System.out.println("\n=== 11. 记录类 (Java 16+) ===");
        // record Point(int x, int y) {}
        // Point p = new Point(1, 2);
        // System.out.println("记录类: " + p);
        System.out.println("Java 16+ 支持 record 关键字定义不可变数据类");
    }
}

// 1. 基本类
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void greet() {
        System.out.println("你好，我是" + name + "，今年" + age + "岁");
    }

    public String getName() { return name; }
    public int getAge() { return age; }
}

// 2. 封装
class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("存入 " + amount + " 元");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("取出 " + amount + " 元");
        } else {
            System.out.println("取款金额无效");
        }
    }

    public double getBalance() {
        return balance;
    }
}

// 3. 继承
class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void speak() {
        System.out.println(name + " 发出声音");
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void speak() {
        System.out.println(name + " 汪汪叫");
    }

    public void fetch() {
        System.out.println(name + " 去捡球了");
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void speak() {
        System.out.println(name + " 喵喵叫");
    }
}

// 5. 抽象类
abstract class Shape {
    abstract double area();
    abstract double perimeter();
    abstract String getName();
}

class RectangleShape extends Shape {
    private double width, height;
    public RectangleShape(double w, double h) { width = w; height = h; }
    @Override double area() { return width * height; }
    @Override double perimeter() { return 2 * (width + height); }
    @Override String getName() { return "矩形"; }
}

class CircleShape extends Shape {
    private double radius;
    public CircleShape(double r) { radius = r; }
    @Override double area() { return Math.PI * radius * radius; }
    @Override double perimeter() { return 2 * Math.PI * radius; }
    @Override String getName() { return "圆形"; }
}

// 6. 接口
interface Movable {
    void move();
}

class Car implements Movable {
    @Override
    public void move() {
        System.out.println("汽车在公路上行驶");
    }
}

class Plane implements Movable {
    @Override
    public void move() {
        System.out.println("飞机在天空中飞行");
    }
}

// 7. 静态成员
class Student {
    private static int count = 0;
    private String name;

    public Student(String name) {
        this.name = name;
        count++;
    }

    public static int getCount() {
        return count;
    }
}

// 8. 内部类
class Outer {
    private int num = 100;

    class Inner {
        void show() {
            System.out.println("内部类访问外部类的 num = " + num);
        }
    }
}

// 9. 枚举
enum Color {
    RED(1),
    GREEN(2),
    BLUE(3);

    private final int value;

    Color(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

// 10. 接口的默认方法和静态方法
interface MyInterface {
    void abstractMethod();

    default void defaultMethod() {
        System.out.println("这是接口的默认方法");
    }

    static void staticMethod() {
        System.out.println("这是接口的静态方法");
    }
}

class MyClass implements MyInterface {
    @Override
    public void abstractMethod() {
        System.out.println("实现抽象方法");
    }
}
