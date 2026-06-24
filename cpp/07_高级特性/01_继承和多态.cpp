// ============================================================
// C++ 高级特性 - 继承、多态、抽象类
// 编译运行：g++ -std=c++17 01_继承和多态.cpp -o 01_继承和多态 && ./01_继承和多态
// ============================================================

#include <iostream>
#include <string>
#include <vector>
using namespace std;

// ============================================================
// 1. 单继承
// ============================================================

class Animal {
protected:
    string name;
    int age;

public:
    Animal(const string& n, int a) : name(n), age(a) {
        cout << "[构造] Animal: " << name << endl;
    }

    virtual ~Animal() {
        cout << "[析构] Animal: " << name << endl;
    }

    virtual void speak() const {
        cout << name << " 发出声音" << endl;
    }

    void eat() const {
        cout << name << " 在吃东西" << endl;
    }

    string getName() const { return name; }
    int getAge() const { return age; }
};

class Dog : public Animal {
private:
    string breed;  // 品种

public:
    Dog(const string& n, int a, const string& b)
        : Animal(n, a), breed(b) {
        cout << "[构造] Dog: " << name << endl;
    }

    ~Dog() override {
        cout << "[析构] Dog: " << name << endl;
    }

    // 重写虚函数
    void speak() const override {
        cout << name << " 汪汪叫" << endl;
    }

    void fetch() const {
        cout << name << " 在接飞盘" << endl;
    }
};

class Cat : public Animal {
public:
    Cat(const string& n, int a) : Animal(n, a) {}

    void speak() const override {
        cout << name << " 喵喵叫" << endl;
    }

    void climb() const {
        cout << name << " 在爬树" << endl;
    }
};

void demo_single_inheritance() {
    cout << "=== 1. 单继承 ===" << endl;

    cout << "\n--- 创建 Dog 对象 ---" << endl;
    {
        Dog dog("旺财", 3, "金毛");
        dog.speak();
        dog.eat();      // 继承自 Animal
        dog.fetch();    // Dog 特有
    }

    cout << "\n--- 创建 Cat 对象 ---" << endl;
    {
        Cat cat("咪咪", 2);
        cat.speak();
        cat.climb();
    }
}

// ============================================================
// 2. 多重继承
// ============================================================

class Flyable {
public:
    virtual void fly() const = 0;
    virtual ~Flyable() {}
};

class Swimmable {
public:
    virtual void swim() const = 0;
    virtual ~Swimmable() {}
};

class Duck : public Animal, public Flyable, public Swimmable {
public:
    Duck(const string& n, int a) : Animal(n, a) {}

    void speak() const override {
        cout << name << " 嘎嘎叫" << endl;
    }

    void fly() const override {
        cout << name << " 在飞翔" << endl;
    }

    void swim() const override {
        cout << name << " 在游泳" << endl;
    }
};

void demo_multiple_inheritance() {
    cout << "\n=== 2. 多重继承 ===" << endl;

    Duck duck("唐老鸭", 5);
    duck.speak();
    duck.fly();
    duck.swim();
    duck.eat();
}

// ============================================================
// 3. 虚函数与多态
// ============================================================

void makeSpeak(const Animal& animal) {
    animal.speak();
}

void demo_polymorphism() {
    cout << "\n=== 3. 虚函数与多态 ===" << endl;

    vector<Animal*> animals;
    animals.push_back(new Dog("旺财", 3, "金毛"));
    animals.push_back(new Cat("咪咪", 2));
    animals.push_back(new Duck("唐老鸭", 5));

    cout << "多态调用 speak():" << endl;
    for (const auto& a : animals) {
        cout << "  ";
        makeSpeak(*a);
    }

    // 虚析构函数的重要性
    for (auto& a : animals) {
        delete a;
    }
    animals.clear();
}

// ============================================================
// 4. 抽象类与接口
// ============================================================

class Shape {
public:
    virtual ~Shape() {}
    virtual double area() const = 0;       // 纯虚函数
    virtual double perimeter() const = 0;  // 纯虚函数

    void printInfo() const {
        cout << "面积: " << area() << ", 周长: " << perimeter() << endl;
    }
};

class Rectangle : public Shape {
private:
    double width, height;

public:
    Rectangle(double w, double h) : width(w), height(h) {}

    double area() const override {
        return width * height;
    }

    double perimeter() const override {
        return 2 * (width + height);
    }
};

class Circle : public Shape {
private:
    double radius;

public:
    Circle(double r) : radius(r) {}

    double area() const override {
        return 3.14159 * radius * radius;
    }

    double perimeter() const override {
        return 2 * 3.14159 * radius;
    }
};

class Triangle : public Shape {
private:
    double a, b, c;

public:
    Triangle(double x, double y, double z) : a(x), b(y), c(z) {}

    double area() const override {
        double p = (a + b + c) / 2;
        return p * (p - a) * (p - b) * (p - c);  // 简化海伦公式
    }

    double perimeter() const override {
        return a + b + c;
    }
};

void demo_abstract_class() {
    cout << "\n=== 4. 抽象类与接口 ===" << endl;

    // Shape s;  // 错误！不能实例化抽象类

    vector<Shape*> shapes;
    shapes.push_back(new Rectangle(4, 5));
    shapes.push_back(new Circle(3));
    shapes.push_back(new Triangle(3, 4, 5));

    cout << "各种图形的面积和周长:" << endl;
    for (const auto& s : shapes) {
        cout << "  ";
        s->printInfo();
    }

    for (auto& s : shapes) {
        delete s;
    }
}

// ============================================================
// 5. 访问控制：protected、private、public 继承
// ============================================================

class Base {
public:
    int pub = 1;
protected:
    int prot = 2;
private:
    int priv = 3;

public:
    int getPriv() const { return priv; }
};

class PublicDerived : public Base {
public:
    void show() {
        cout << "pub: " << pub << endl;      // public 继承: 保持 public
        cout << "prot: " << prot << endl;    // protected 继承: 保持 protected
        // cout << priv;  // 错误！private 不可访问
        cout << "priv (通过getPriv): " << getPriv() << endl;
    }
};

class ProtectedDerived : protected Base {
public:
    void show() {
        cout << "pub (变成protected): " << pub << endl;
        cout << "prot: " << prot << endl;
    }
};

class PrivateDerived : private Base {
public:
    void show() {
        cout << "pub (变成private): " << pub << endl;
        cout << "prot (变成private): " << prot << endl;
    }
};

void demo_access_control() {
    cout << "\n=== 5. 访问控制 ===" << endl;

    cout << "--- public 继承 ---" << endl;
    PublicDerived pd;
    pd.show();
    cout << "外部访问 pub: " << pd.pub << endl;
    // cout << pd.prot;  // 错误！

    cout << "\n--- protected 继承 ---" << endl;
    ProtectedDerived prd;
    prd.show();
    // cout << prd.pub;  // 错误！变成 protected 了

    cout << "\n--- private 继承 ---" << endl;
    PrivateDerived pvd;
    pvd.show();
    // cout << pvd.pub;  // 错误！变成 private 了
}

// ============================================================
// 6. 构造函数与析构函数顺序
// ============================================================

class A {
public:
    A() { cout << "  A 构造" << endl; }
    ~A() { cout << "  A 析构" << endl; }
};

class B : public A {
public:
    B() { cout << "  B 构造" << endl; }
    ~B() { cout << "  B 析构" << endl; }
};

class C : public B {
public:
    C() { cout << "  C 构造" << endl; }
    ~C() { cout << "  C 析构" << endl; }
};

void demo_ctor_dtor_order() {
    cout << "\n=== 6. 构造/析构顺序 ===" << endl;
    cout << "创建 C 对象:" << endl;
    {
        C c;
        cout << "  (对象正在使用)" << endl;
    }
    cout << "对象已销毁" << endl;
    cout << "构造顺序: 基类 → 派生类" << endl;
    cout << "析构顺序: 派生类 → 基类" << endl;
}

int main() {
    demo_single_inheritance();
    demo_multiple_inheritance();
    demo_polymorphism();
    demo_abstract_class();
    demo_access_control();
    demo_ctor_dtor_order();

    cout << "\n=== 运行完成 ===" << endl;
    return 0;
}