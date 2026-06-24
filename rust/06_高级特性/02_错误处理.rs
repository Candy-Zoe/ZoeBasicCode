// ============================================================
// Rust 高级特性 - 错误处理
// 编译运行：rustc 02_错误处理.rs -o 02_错误处理 && ./02_错误处理
// ============================================================

use std::fs::File;
use std::io::{self, Read};
use std::num::ParseIntError;

fn main() {
    println!("=== 1. panic! 宏 ===");
    println!("panic! 会导致程序崩溃并展开栈");
    // panic!("crash and burn");  // 取消注释测试

    println!("\n=== 2. Result 枚举 ===");

    // Result<T, E> 枚举有两个成员：Ok(T) 和 Err(E)
    let result: Result<i32, String> = Ok(42);
    match result {
        Ok(value) => println!("成功: {}", value),
        Err(e) => println!("失败: {}", e),
    }

    let result: Result<i32, String> = Err(String::from("出错了"));
    match result {
        Ok(value) => println!("成功: {}", value),
        Err(e) => println!("失败: {}", e),
    }

    println!("\n=== 3. 文件操作的 Result ===");

    let f = File::open("hello_test.txt");
    let f = match f {
        Ok(file) => {
            println!("文件打开成功");
            file
        }
        Err(error) => {
            println!("文件打开失败: {:?}", error.kind());
            println!("尝试创建文件...");
            match File::create("hello_test.txt") {
                Ok(fc) => {
                    println!("文件创建成功");
                    fc
                }
                Err(e) => {
                    println!("创建文件失败: {:?}", e);
                    return;
                }
            }
        }
    };
    println!("{:?}", f);

    println!("\n=== 4. unwrap 和 expect ===");

    // unwrap: 如果是 Ok 返回值，否则 panic
    // let f = File::open("nonexistent.txt").unwrap(); // 会 panic

    // expect: 类似 unwrap，但可以指定错误信息
    // let f = File::open("nonexistent.txt").expect("文件不存在");

    let f = File::open("hello_test.txt").expect("打开文件失败");
    println!("文件打开成功: {:?}", f);

    println!("\n=== 5. ? 运算符传播错误 ===");

    match read_username_from_file() {
        Ok(username) => println!("用户名: {}", username),
        Err(e) => println!("读取错误: {:?}", e),
    }

    println!("\n=== 6. 自定义错误类型 ===");

    #[derive(Debug)]
    enum MyError {
        ParseError(ParseIntError),
        IoError(io::Error),
    }

    impl std::fmt::Display for MyError {
        fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
            match self {
                MyError::ParseError(e) => write!(f, "解析错误: {}", e),
                MyError::IoError(e) => write!(f, "IO 错误: {}", e),
            }
        }
    }

    impl std::error::Error for MyError {}

    // From trait 用于错误转换
    impl From<io::Error> for MyError {
        fn from(err: io::Error) -> MyError {
            MyError::IoError(err)
        }
    }

    println!("\n=== 7. 错误处理示例：解析数字 ===");

    fn parse_number(s: &str) -> Result<i32, ParseIntError> {
        s.parse::<i32>()
    }

    match parse_number("42") {
        Ok(n) => println!("解析成功: {}", n),
        Err(e) => println!("解析失败: {}", e),
    }

    match parse_number("abc") {
        Ok(n) => println!("解析成功: {}", n),
        Err(e) => println!("解析失败: {}", e),
    }

    println!("\n=== 8. 错误处理最佳实践 ===");

    // 使用 match 处理错误
    println!("--- match 处理 ---");
    match divide(10, 2) {
        Ok(result) => println!("10 / 2 = {}", result),
        Err(e) => println!("错误: {}", e),
    }

    // 使用 if let
    println!("\n--- if let 处理 ---");
    if let Ok(result) = divide(10, 0) {
        println!("结果: {}", result);
    } else {
        println!("除以零错误");
    }

    // 使用 ? 运算符（在函数中）
    println!("\n--- ? 运算符传播 ---");
    match calculate(10, 2, 0) {
        Ok(result) => println!("计算结果: {}", result),
        Err(e) => println!("计算错误: {}", e),
    }

    // 清理测试文件
    let _ = std::fs::remove_file("hello_test.txt");
    println!("\n清理测试文件完成");

    println!("\n=== 运行完成 ===");
}

fn divide(a: i32, b: i32) -> Result<i32, String> {
    if b == 0 {
        Err(String::from("不能除以零"))
    } else {
        Ok(a / b)
    }
}

fn calculate(a: i32, b: i32, c: i32) -> Result<i32, String> {
    let d = divide(a, b)?;
    let result = divide(d, c)?;
    Ok(result)
}

fn read_username_from_file() -> Result<String, io::Error> {
    // 简化：返回一个示例
    Ok(String::from("test_user"))
}
