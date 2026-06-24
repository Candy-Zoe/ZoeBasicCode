// ============================================================
// C++ 面向对象
// 编译运行：g++ 01_面向对象.cpp -o 01_面向对象 && ./01_面向对象
// ============================================================

#include <iostream>
#include <string>

using namespace std;

// 1. 类的定义
class Person {
private:
    string name;
    int age;

public:
    // 构造函数
    Person(string n, int a) : name(n), age(a) {
        cout << "Person 构造函数被调用" << endl;
    }

    // 析构函数
    ~Person() {
        cout << "Person 析构函数被调用: " << name << endl;
    }

    // 成员函数
    void greet() {
        cout << "你好，我是" << name << "，今年" << age << "岁" << endl;
    }

    // Getter / Setter
    string getName() { return name; }
    int getAge() { return age; }
    void setAge(int a) {
        if (a > 0) age = a;
    }

    // 静态成员
    static int count;
    static void showCount() {
        cout << "Person 实例数量: " << count << endl;
    }
};

int Person::count = 0;

// 2. 继承
class Student : public Person {
private:
    string studentId;
    double score;

public:
    Student(string n, int a, string id, double s) 
        : Person(n, a), studentId(id), score(s) {
        cout << "Student 构造函数被调用" << endl;
    }

    ~Student() {
        cout << "Student 析构函数被调用" << endl;
    }

    void study() {
        cout << getName() << " 在学习，成绩: " << score << endl;
    }
};

// 3. 多态 - 虚函数
class Animal {
public:
    virtual void speak() {
        cout << "动物发出声音" << endl;
    }
    virtual ~Animal() {}  // 虚析构函数
};

class Dog : public Animal {
public:
    void speak() override {
        cout << "汪汪汪" << endl;
    }
};

class Cat : public Animal {
public:
    void speak() override {
        cout << "喵喵喵" << endl;
    }
};

// 4. 抽象类
class Shape {
public:
    virtual double area() = 0;      // 纯虚函数
    virtual double perimeter() = 0;
    virtual ~Shape() {}
};

class Rectangle : public Shape {
private:
    double width, height;
public:
    Rectangle(double w, double h) : width(w), height(h) {}
    double area() override { return width * height; }
    double perimeter() override { return 2 * (width + height); }
};

class Circle : public Shape {
private:
    double radius;
public:
    Circle(double r) : radius(r) {}
    double area() override { return 3.14159 * radius * radius; }
    double perimeter() override { return 2 * 3.14159 * radius; }
};

int main() {
    cout << "=== 1. 类和对象 ===" << endl;
    Person p1("张三", 25);
    p1.greet();
    cout << "姓名: " << p1.getName() << endl;
    p1.setAge(26);
    cout << "年龄: " << p1.getAge() << endl;

    cout << "\n=== 2. 继承 ===" << endl;
    Student s1("小明", 18, "2023001", 95.5);
    s1.greet();
    s1.study();

    cout << "\n=== 3. 多态 ===" << endl;
    Animal* animals[] = { new Dog(), new Cat() };
    for (Animal* a : animals) {
        a->speak();
    }
    delete animals[0];
    delete animals[1];

    cout << "\n=== 4. 抽象类 ===" << endl;
    Shape* shapes[] = {
        new Rectangle(4, 5),
        new Circle(3)
    };
    for (Shape* s : shapes) {
        cout << "面积: " << s->area() << ", 周长: " << s->perimeter() << endl;
    }
    delete shapes[0];
    delete shapes[1];

    cout << "\n=== 5. 构造/析构顺序 ===" << endl;
    {
        Student s2("小红", 20, "2023002", 88.0);
        // 离开作用域时析构
    }

    cout << "\n=== 6. 友元（简单说明） ===" << endl;
    cout << "友元函数可以访问类的私有成员" << endl;
    cout << "使用 friend 关键字声明" << endl;

    cout << "\n=== 7. 运算符重载 ===" << endl;
    class Point {
    public:
        int x, y;
        Point(int a = 0, int b = 0) : x(a), y(b) {}
        Point operator+(const Point& other) {
            return Point(x + other.x, y + other.y);
        }
        void print() { cout << "(" << x << ", " << y << ")" << endl; }
    };

    Point p2(1, 2), p3(3, 4);
    Point p4 = p2 + p3;
    cout << "p1 + p2 = ";
    p4.print();

    return 0;
}
