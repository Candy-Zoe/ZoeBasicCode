// ============================================================
// C++ 高级特性 - 模板、STL、智能指针
// 编译运行：g++ -std=c++17 02_模板和STL.cpp -o 02_模板和STL && ./02_模板和STL
// ============================================================

#include <iostream>
#include <string>
#include <vector>
#include <list>
#include <map>
#include <set>
#include <algorithm>
#include <memory>
#include <functional>
using namespace std;

// ============================================================
// 1. 函数模板
// ============================================================

template <typename T>
T maximum(T a, T b) {
    return (a > b) ? a : b;
}

template <typename T>
void swap_values(T& a, T& b) {
    T temp = a;
    a = b;
    b = temp;
}

template <typename T, typename U>
auto add(T a, U b) -> decltype(a + b) {
    return a + b;
}

void demo_function_template() {
    cout << "=== 1. 函数模板 ===" << endl;

    cout << "maximum(3, 5) = " << maximum(3, 5) << endl;
    cout << "maximum(3.14, 2.71) = " << maximum(3.14, 2.71) << endl;
    cout << "maximum('a', 'z') = " << maximum('a', 'z') << endl;

    string s1 = "hello", s2 = "world";
    cout << "maximum('hello', 'world') = " << maximum(s1, s2) << endl;

    int x = 10, y = 20;
    cout << "交换前: x=" << x << ", y=" << y << endl;
    swap_values(x, y);
    cout << "交换后: x=" << x << ", y=" << y << endl;

    cout << "add(3, 4.5) = " << add(3, 4.5) << endl;
}

// ============================================================
// 2. 类模板
// ============================================================

template <typename T>
class Stack {
private:
    vector<T> elements;

public:
    void push(const T& elem) {
        elements.push_back(elem);
    }

    T pop() {
        T top = elements.back();
        elements.pop_back();
        return top;
    }

    T top() const {
        return elements.back();
    }

    bool empty() const {
        return elements.empty();
    }

    size_t size() const {
        return elements.size();
    }
};

template <typename T, size_t N>
class FixedArray {
private:
    T data[N];

public:
    T& operator[](size_t index) {
        return data[index];
    }

    const T& operator[](size_t index) const {
        return data[index];
    }

    size_t size() const { return N; }
};

void demo_class_template() {
    cout << "\n=== 2. 类模板 ===" << endl;

    cout << "--- Stack<int> ---" << endl;
    Stack<int> int_stack;
    int_stack.push(10);
    int_stack.push(20);
    int_stack.push(30);
    cout << "栈大小: " << int_stack.size() << endl;
    cout << "弹出: " << int_stack.pop() << endl;
    cout << "栈顶: " << int_stack.top() << endl;

    cout << "\n--- Stack<string> ---" << endl;
    Stack<string> str_stack;
    str_stack.push("Hello");
    str_stack.push("World");
    cout << "栈大小: " << str_stack.size() << endl;
    cout << "弹出: " << str_stack.pop() << endl;

    cout << "\n--- FixedArray ---" << endl;
    FixedArray<int, 5> arr;
    for (int i = 0; i < 5; i++) {
        arr[i] = i * 10;
    }
    cout << "数组大小: " << arr.size() << endl;
    cout << "元素: ";
    for (int i = 0; i < 5; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;
}

// ============================================================
// 3. STL 常用容器
// ============================================================

void demo_stl_containers() {
    cout << "\n=== 3. STL 常用容器 ===" << endl;

    // vector
    cout << "--- vector ---" << endl;
    vector<int> vec = {5, 2, 8, 1, 9};
    vec.push_back(3);
    cout << "大小: " << vec.size() << endl;
    cout << "元素: ";
    for (int n : vec) cout << n << " ";
    cout << endl;

    // list
    cout << "\n--- list ---" << endl;
    list<string> str_list = {"apple", "banana", "cherry"};
    str_list.push_front("apricot");
    str_list.push_back("date");
    cout << "元素: ";
    for (const auto& s : str_list) cout << s << " ";
    cout << endl;

    // map
    cout << "\n--- map ---" << endl;
    map<string, int> scores;
    scores["张三"] = 95;
    scores["李四"] = 88;
    scores["王五"] = 92;
    for (const auto& pair : scores) {
        cout << "  " << pair.first << ": " << pair.second << endl;
    }

    // set
    cout << "\n--- set ---" << endl;
    set<int> num_set = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
    cout << "大小(自动去重): " << num_set.size() << endl;
    cout << "元素(自动排序): ";
    for (int n : num_set) cout << n << " ";
    cout << endl;

    if (num_set.count(5)) {
        cout << "5 在集合中" << endl;
    }
}

// ============================================================
// 4. STL 算法
// ============================================================

void demo_stl_algorithms() {
    cout << "\n=== 4. STL 算法 ===" << endl;

    vector<int> v = {5, 2, 8, 1, 9, 3, 7, 4, 6};
    cout << "原始: ";
    for (int n : v) cout << n << " ";
    cout << endl;

    // 排序
    sort(v.begin(), v.end());
    cout << "升序: ";
    for (int n : v) cout << n << " ";
    cout << endl;

    // 降序
    sort(v.begin(), v.end(), greater<int>());
    cout << "降序: ";
    for (int n : v) cout << n << " ";
    cout << endl;

    // 查找
    auto it = find(v.begin(), v.end(), 7);
    if (it != v.end()) {
        cout << "找到 7, 位置: " << (it - v.begin()) << endl;
    }

    // 计数
    int count_even = count_if(v.begin(), v.end(), [](int x) { return x % 2 == 0; });
    cout << "偶数个数: " << count_even << endl;

    // 累加
    int sum = 0;
    for_each(v.begin(), v.end(), [&sum](int x) { sum += x; });
    cout << "总和: " << sum << endl;

    // 转换
    vector<int> squares(v.size());
    transform(v.begin(), v.end(), squares.begin(), [](int x) { return x * x; });
    cout << "平方: ";
    for (int n : squares) cout << n << " ";
    cout << endl;

    // 去重
    sort(v.begin(), v.end());
    auto last = unique(v.begin(), v.end());
    v.erase(last, v.end());
    cout << "去重后: ";
    for (int n : v) cout << n << " ";
    cout << endl;
}

// ============================================================
// 5. 智能指针
// ============================================================

class TestClass {
public:
    string name;
    TestClass(const string& n) : name(n) {
        cout << "[构造] " << name << endl;
    }
    ~TestClass() {
        cout << "[析构] " << name << endl;
    }
    void hello() const {
        cout << "Hello from " << name << endl;
    }
};

void demo_smart_pointers() {
    cout << "\n=== 5. 智能指针 ===" << endl;

    // unique_ptr
    cout << "--- unique_ptr ---" << endl;
    unique_ptr<TestClass> p1 = make_unique<TestClass>("obj1");
    p1->hello();

    unique_ptr<TestClass> p2 = move(p1);  // 转移所有权
    p2->hello();
    // p1->hello();  // 错误！p1 已为空

    // shared_ptr
    cout << "\n--- shared_ptr ---" << endl;
    shared_ptr<TestClass> sp1 = make_shared<TestClass>("shared_obj");
    cout << "引用计数: " << sp1.use_count() << endl;

    {
        shared_ptr<TestClass> sp2 = sp1;
        shared_ptr<TestClass> sp3 = sp1;
        cout << "引用计数: " << sp1.use_count() << endl;
    }
    cout << "引用计数(出作用域后): " << sp1.use_count() << endl;

    // weak_ptr
    cout << "\n--- weak_ptr ---" << endl;
    shared_ptr<TestClass> sp = make_shared<TestClass>("weak_obj");
    weak_ptr<TestClass> wp = sp;

    cout << "wp 引用计数: " << wp.use_count() << endl;
    if (auto locked = wp.lock()) {
        locked->hello();
        cout << "锁定成功, 引用计数: " << sp.use_count() << endl;
    }
}

// ============================================================
// 6. Lambda 表达式
// ============================================================

void demo_lambda() {
    cout << "\n=== 6. Lambda 表达式 ===" << endl;

    // 基本 lambda
    auto add = [](int a, int b) { return a + b; };
    cout << "add(3, 4) = " << add(3, 4) << endl;

    // 值捕获
    int x = 10, y = 20;
    auto sum = [x, y]() { return x + y; };
    cout << "sum = " << sum() << endl;

    // 引用捕获
    auto increment = [&x]() { x++; };
    increment();
    cout << "x = " << x << endl;

    // 通用捕获
    auto add_suffix = [=](string s) { return s + "!"; };
    cout << add_suffix("hello") << endl;

    // mutable lambda
    auto counter = [count = 0]() mutable {
        return count++;
    };
    cout << "counter: " << counter() << endl;
    cout << "counter: " << counter() << endl;
    cout << "counter: " << counter() << endl;

    // lambda 作为参数
    vector<int> nums = {1, 2, 3, 4, 5};
    int total = 0;
    for_each(nums.begin(), nums.end(), [&total](int n) { total += n; });
    cout << "和为: " << total << endl;
}

int main() {
    demo_function_template();
    demo_class_template();
    demo_stl_containers();
    demo_stl_algorithms();
    demo_smart_pointers();
    demo_lambda();

    cout << "\n=== 运行完成 ===" << endl;
    return 0;
}