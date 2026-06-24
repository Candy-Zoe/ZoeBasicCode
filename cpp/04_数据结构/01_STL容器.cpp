// ============================================================
// C++ STL 容器
// 编译运行：g++ 01_STL容器.cpp -o 01_STL容器 && ./01_STL容器
// ============================================================

#include <iostream>
#include <vector>
#include <array>
#include <list>
#include <deque>
#include <map>
#include <unordered_map>
#include <set>
#include <unordered_set>
#include <queue>
#include <stack>
#include <string>
#include <algorithm>

using namespace std;

int main() {
    cout << "=== 1. vector 动态数组 ===" << endl;
    vector<int> vec = {1, 2, 3, 4, 5};
    cout << "初始: ";
    for (int n : vec) cout << n << " ";
    cout << endl;

    vec.push_back(6);
    cout << "push_back(6) 后: ";
    for (int n : vec) cout << n << " ";
    cout << endl;

    cout << "大小: " << vec.size() << ", 容量: " << vec.capacity() << endl;
    cout << "第一个元素: " << vec.front() << endl;
    cout << "最后一个元素: " << vec.back() << endl;

    cout << "\n=== 2. array 固定大小数组 (C++11) ===" << endl;
    array<int, 5> arr = {1, 2, 3, 4, 5};
    cout << "array 大小: " << arr.size() << endl;
    for (int n : arr) cout << n << " ";
    cout << endl;

    cout << "\n=== 3. string 字符串 ===" << endl;
    string str = "Hello C++";
    cout << "str = " << str << endl;
    cout << "长度: " << str.length() << endl;
    cout << "子串(0,5): " << str.substr(0, 5) << endl;
    cout << "查找 'C++': " << str.find("C++") << endl;

    cout << "\n=== 4. map 有序字典 ===" << endl;
    map<string, int> scores;
    scores["张三"] = 95;
    scores["李四"] = 85;
    scores["王五"] = 90;

    cout << "遍历 map:" << endl;
    for (auto &pair : scores) {
        cout << "  " << pair.first << ": " << pair.second << endl;
    }

    cout << "\n=== 5. unordered_map 哈希字典 ===" << endl;
    unordered_map<string, int> dict;
    dict["apple"] = 5;
    dict["banana"] = 3;
    cout << "apple = " << dict["apple"] << endl;

    cout << "\n=== 6. set 有序集合 ===" << endl;
    set<int> s = {3, 1, 4, 1, 5, 9, 2, 6};
    cout << "set 元素（自动排序去重）: ";
    for (int n : s) cout << n << " ";
    cout << endl;
    cout << "包含 5: " << (s.count(5) ? "是" : "否") << endl;

    cout << "\n=== 7. unordered_set 哈希集合 ===" << endl;
    unordered_set<int> us = {3, 1, 4, 1, 5};
    cout << "unordered_set 大小: " << us.size() << endl;

    cout << "\n=== 8. list 双向链表 ===" << endl;
    list<int> lst = {1, 2, 3};
    lst.push_front(0);
    lst.push_back(4);
    cout << "list: ";
    for (int n : lst) cout << n << " ";
    cout << endl;

    cout << "\n=== 9. deque 双端队列 ===" << endl;
    deque<int> dq = {2, 3};
    dq.push_front(1);
    dq.push_back(4);
    cout << "deque: ";
    for (int n : dq) cout << n << " ";
    cout << endl;

    cout << "\n=== 10. queue 队列（FIFO） ===" << endl;
    queue<string> q;
    q.push("第一个");
    q.push("第二个");
    q.push("第三个");
    cout << "队列大小: " << q.size() << endl;
    cout << "队首: " << q.front() << endl;
    q.pop();
    cout << "出队后队首: " << q.front() << endl;

    cout << "\n=== 11. stack 栈（LIFO） ===" << endl;
    stack<int> stk;
    stk.push(1);
    stk.push(2);
    stk.push(3);
    cout << "栈顶: " << stk.top() << endl;
    stk.pop();
    cout << "出栈后栈顶: " << stk.top() << endl;

    cout << "\n=== 12. STL 算法 ===" << endl;
    vector<int> nums = {3, 1, 4, 1, 5, 9, 2, 6};
    cout << "原数组: ";
    for (int n : nums) cout << n << " ";
    cout << endl;

    // sort 排序
    sort(nums.begin(), nums.end());
    cout << "排序后: ";
    for (int n : nums) cout << n << " ";
    cout << endl;

    // reverse 反转
    reverse(nums.begin(), nums.end());
    cout << "反转后: ";
    for (int n : nums) cout << n << " ";
    cout << endl;

    // find 查找
    auto it = find(nums.begin(), nums.end(), 5);
    if (it != nums.end()) {
        cout << "找到 5，位置索引: " << distance(nums.begin(), it) << endl;
    }

    // count 计数
    int cnt = count(nums.begin(), nums.end(), 1);
    cout << "1 出现的次数: " << cnt << endl;

    // min/max
    cout << "最小值: " << *min_element(nums.begin(), nums.end()) << endl;
    cout << "最大值: " << *max_element(nums.begin(), nums.end()) << endl;

    // accumulate 求和
    int sum = 0;
    for (int n : nums) sum += n;
    cout << "总和: " << sum << endl;

    return 0;
}
