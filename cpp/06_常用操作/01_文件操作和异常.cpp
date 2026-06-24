// ============================================================
// C++ 文件操作和异常处理
// 编译运行：g++ 01_文件操作和异常.cpp -o 01_文件操作和异常 && ./01_文件操作和异常
// ============================================================

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <stdexcept>
#include <filesystem>

using namespace std;
namespace fs = std::filesystem;

int main() {
    cout << "=== 1. 写入文件 ===" << endl;
    ofstream outFile("test.txt");
    if (outFile.is_open()) {
        outFile << "第一行" << endl;
        outFile << "第二行" << endl;
        outFile << "第三行" << endl;
        outFile.close();
        cout << "已写入 test.txt" << endl;
    }

    cout << "\n=== 2. 读取文件 ===" << endl;
    ifstream inFile("test.txt");
    if (inFile.is_open()) {
        string line;
        cout << "文件内容:" << endl;
        while (getline(inFile, line)) {
            cout << "  " << line << endl;
        }
        inFile.close();
    }

    cout << "\n=== 3. 追加写入 ===" << endl;
    ofstream appendFile("test.txt", ios::app);
    if (appendFile.is_open()) {
        appendFile << "第四行（追加）" << endl;
        appendFile.close();
        cout << "已追加" << endl;
    }

    cout << "\n=== 4. 二进制文件读写 ===" << endl;
    struct Student {
        char name[50];
        int age;
        double score;
    };

    Student stu = {"张三", 20, 95.5};
    ofstream binFile("student.dat", ios::binary);
    binFile.write(reinterpret_cast<char*>(&stu), sizeof(stu));
    binFile.close();
    cout << "已写入二进制文件" << endl;

    Student readStu;
    ifstream readBin("student.dat", ios::binary);
    readBin.read(reinterpret_cast<char*>(&readStu), sizeof(readStu));
    readBin.close();
    cout << "读取: " << readStu.name << ", " << readStu.age << "岁, " << readStu.score << "分" << endl;

    cout << "\n=== 5. 文件流状态检查 ===" << endl;
    ifstream testFile("nonexistent.txt");
    if (!testFile) {
        cout << "文件打开失败（文件不存在）" << endl;
    }
    if (testFile.fail()) {
        cout << "fail() 返回 true" << endl;
    }
    if (testFile.bad()) {
        cout << "bad() 返回 true" << endl;
    }
    if (testFile.eof()) {
        cout << "eof() 返回 true" << endl;
    }

    cout << "\n=== 6. 异常处理 try-catch ===" << endl;
    try {
        int result = 10 / 0;
    } catch (const runtime_error& e) {
        cout << "运行时错误: " << e.what() << endl;
    } catch (...) {
        cout << "捕获到异常（整数除零在C++中是未定义行为）" << endl;
    }

    cout "\n=== 7. 标准异常类型 ===" << endl;
    try {
        vector<int> vec(5);
        vec.at(10);  // 越界访问，抛出 out_of_range
    } catch (const out_of_range& e) {
        cout << "越界异常: " << e.what() << endl;
    }

    try {
        int* p = new int[1000000000000];  // 内存不足
        delete[] p;
    } catch (const bad_alloc& e) {
        cout << "内存分配失败: " << e.what() << endl;
    }

    cout << "\n=== 8. 自定义异常 ===" << endl;
    class AgeException : public exception {
    private:
        string message;
    public:
        AgeException(const string& msg) : message(msg) {}
        const char* what() const throw() {
            return message.c_str();
        }
    };

    try {
        int age = 200;
        if (age < 0 || age > 150) {
            throw AgeException("年龄无效，必须在0-150之间");
        }
    } catch (const AgeException& e) {
        cout << "自定义异常: " << e.what() << endl;
    }

    cout << "\n=== 9. throw 重新抛出 ===" << endl;
    try {
        try {
            throw runtime_error("内部异常");
        } catch (const runtime_error& e) {
            cout << "捕获内部异常: " << e.what() << endl;
            throw;  // 重新抛出
        }
    } catch (const runtime_error& e) {
        cout << "外层捕获: " << e.what() << endl;
    }

    cout << "\n=== 10. noexcept ===" << endl;
    cout << "noexcept 表示函数不会抛出异常" << endl;
    auto noThrowFunc = []() noexcept {
        cout << "这个函数不会抛出异常" << endl;
    };
    noThrowFunc();

    cout << "\n=== 11. 文件系统操作 (C++17) ===" << endl;
    fs::path p = "test.txt";
    cout << "文件存在: " << fs::exists(p) << endl;
    cout << "文件大小: " << fs::file_size(p) << " 字节" << endl;
    cout << "是普通文件: " << fs::is_regular_file(p) << endl;

    // 列出当前目录文件
    cout << "\n当前目录文件:" << endl;
    for (const auto& entry : fs::directory_iterator(".")) {
        cout << "  " << entry.path().filename() << endl;
    }

    cout << "\n=== 清理测试文件 ===" << endl;
    fs::remove("test.txt");
    fs::remove("student.dat");
    cout << "已清理测试文件" << endl;

    return 0;
}
