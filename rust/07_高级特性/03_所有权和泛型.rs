// ============================================================
// Rust 高级特性 - 所有权、借用、生命周期、泛型、trait
// 编译运行：rustc 03_所有权和泛型.rs -o 03_所有权和泛型 && ./03_所有权和泛型
// ============================================================

fn main() {
    println!("=== 1. 所有权系统 ===");

    // String 的所有权
    let s1 = String::from("hello");
    let s2 = s1;  // s1 的所有权转移给 s2，s1 失效
    println!("s2 = {}", s2);
    // println!("{}", s1);  // 错误！s1 已失效

    // clone 深拷贝
    let s1 = String::from("hello");
    let s2 = s1.clone();
    println!("s1 = {}, s2 = {}", s1, s2);

    // 基本类型实现了 Copy trait
    let x = 5;
    let y = x;
    println!("x = {}, y = {}", x, y);

    // 函数与所有权
    let s = String::from("hello");
    takes_ownership(s);
    // println!("{}", s);  // 错误！s 已被移走

    let x = 5;
    makes_copy(x);
    println!("x = {} (Copy 类型，不转移)", x);

    // 返回值与所有权
    let s1 = gives_ownership();
    println!("gives_ownership 返回: {}", s1);

    let s2 = String::from("hello");
    let s3 = takes_and_gives_back(s2);
    println!("takes_and_gives_back 返回: {}", s3);

    // ============================================================
    // 引用与借用
    // ============================================================

    println!("\n=== 2. 引用与借用 ===");

    let s1 = String::from("hello");
    let len = calculate_length(&s1);
    println!("'{}' 的长度是 {}", s1, len);

    // 可变引用
    let mut s = String::from("hello");
    change(&mut s);
    println!("修改后: {}", s);

    // 不可变引用和可变引用不能同时存在
    let mut s = String::from("hello");
    let r1 = &s;
    let r2 = &s;  // 多个不可变引用可以
    println!("{} and {}", r1, r2);
    // r1 和 r2 之后不再使用，可以创建可变引用
    let r3 = &mut s;
    println!("可变引用: {}", r3);

    // 悬垂引用 - Rust 编译器会防止
    // let reference_to_nothing = dangle();  // 编译错误

    // ============================================================
    // 切片
    // ============================================================

    println!("\n=== 3. 切片 Slice ===");

    let s = String::from("hello world");

    let hello = &s[0..5];
    let world = &s[6..11];
    println!("hello = {}, world = {}", hello, world);

    let hello = &s[..5];    // 等同于 0..5
    let world = &s[6..];    // 等同于 6..s.len()
    let entire = &s[..];    // 整个字符串
    println!("entire = {}", entire);

    // 数组切片
    let a = [1, 2, 3, 4, 5];
    let slice = &a[1..3];
    println!("数组切片: [{}, {}]", slice[0], slice[1]);

    // ============================================================
    // 泛型
    // ============================================================

    println!("\n=== 4. 泛型 ===");

    // 泛型函数
    let number_list = vec![34, 50, 25, 100, 65];
    let result = largest_i32(&number_list);
    println!("最大数字: {}", result);

    let char_list = vec!['y', 'm', 'a', 'q'];
    let result = largest_char(&char_list);
    println!("最大字符: {}", result);

    // 使用泛型的 largest 函数（需要 PartialOrd trait）
    let number_list = vec![34, 50, 25, 100, 65];
    println!("泛型 largest: {}", largest(&number_list));

    // 泛型结构体
    let p1 = Point { x: 5, y: 10 };
    let p2 = Point { x: 1.0, y: 4.0 };
    println!("整数点: ({}, {})", p1.x, p1.y);
    println!("浮点点点: ({}, {})", p2.x, p2.y);

    let p = Point { x: 5, y: 10.4 };
    println!("混合点: ({}, {})", p.x, p.y);

    // 泛型方法
    let p = Point { x: 5, y: 10 };
    println!("p.x() = {}", p.x());

    // ============================================================
    // Trait
    // ============================================================

    println!("\n=== 5. Trait ===");

    let tweet = Tweet {
        username: String::from("horse_ebooks"),
        content: String::from("of course, as you probably already know, people"),
        reply: false,
        retweet: false,
    };

    println!("1 条新推文: {}", tweet.summarize());

    let article = NewsArticle {
        headline: String::from("Penguins win the Stanley Cup Championship!"),
        location: String::from("Pittsburgh, PA, USA"),
        author: String::from("Iceburgh"),
        content: String::from("The Pittsburgh Penguins once again are the best hockey team in the NHL."),
    };

    println!("新文章发布: {}", article.summarize());

    // trait 作为参数
    notify(&tweet);
    notify(&article);

    // 返回实现了 trait 的类型
    let s = returns_summarizable();
    println!("返回的 Summary: {}", s.summarize());

    // ============================================================
    // 生命周期
    // ============================================================

    println!("\n=== 6. 生命周期 ===");

    let string1 = String::from("abcd");
    let string2 = "xyz";

    let result = longest(string1.as_str(), string2);
    println!("最长字符串是: {}", result);

    // 结构体中的生命周期
    let novel = String::from("Call me Ishmael. Some years ago...");
    let first_sentence = novel.split('.').next().expect("没有找到 '.'");
    let i = ImportantExcerpt { part: first_sentence };
    println!("重要片段: {}", i.part);

    // 生命周期省略规则
    let s = first_word("hello world");
    println!("第一个单词: {}", s);

    // ============================================================
    // 错误处理
    // ============================================================

    println!("\n=== 7. 错误处理 ===");

    // panic! 宏
    // panic!("crash and burn");  // 会导致程序崩溃

    // Result 枚举
    use std::fs::File;
    use std::io::ErrorKind;

    let f = File::open("hello.txt");
    let f = match f {
        Ok(file) => {
            println!("文件打开成功");
            file
        }
        Err(error) => match error.kind() {
            ErrorKind::NotFound => {
                println!("文件不存在，创建中...");
                match File::create("hello.txt") {
                    Ok(fc) => fc,
                    Err(e) => panic!("创建文件失败: {:?}", e),
                }
            }
            other_error => panic!("打开文件失败: {:?}", other_error),
        },
    };

    // ? 传播错误
    match read_username_from_file() {
        Ok(username) => println!("用户名: {}", username),
        Err(e) => println!("读取错误: {:?}", e),
    }

    // ============================================================
    // 闭包和迭代器
    // ============================================================

    println!("\n=== 8. 闭包和迭代器 ===");

    // 闭包
    let add_one = |x: i32| x + 1;
    println!("add_one(5) = {}", add_one(5));

    let sum = |a, b| a + b;
    println!("sum(3, 4) = {}", sum(3, 4));

    // 捕获环境
    let x = 4;
    let equal_to_x = |z| z == x;
    println!("4 == x: {}", equal_to_x(4));

    // 迭代器
    let v1 = vec![1, 2, 3];
    let v1_iter = v1.iter();

    println!("迭代器:");
    for val in v1_iter {
        println!("  {}", val);
    }

    // 迭代器适配器
    let v1: Vec<i32> = vec![1, 2, 3];
    let v2: Vec<_> = v1.iter().map(|x| x + 1).collect();
    println!("map 后: {:?}", v2);

    // filter
    let numbers: Vec<i32> = (1..=10).collect();
    let evens: Vec<_> = numbers.iter().filter(|x| *x % 2 == 0).collect();
    println!("偶数: {:?}", evens);

    // fold
    let sum: i32 = (1..=10).fold(0, |acc, x| acc + x);
    println!("1 到 10 的和: {}", sum);

    println!("\n=== 运行完成 ===");
}

// 所有权函数
fn takes_ownership(some_string: String) {
    println!("takes_ownership: {}", some_string);
}

fn makes_copy(some_integer: i32) {
    println!("makes_copy: {}", some_integer);
}

fn gives_ownership() -> String {
    let some_string = String::from("hello");
    some_string
}

fn takes_and_gives_back(a_string: String) -> String {
    a_string
}

// 借用函数
fn calculate_length(s: &String) -> usize {
    s.len()
}

fn change(some_string: &mut String) {
    some_string.push_str(", world");
}

// fn dangle() -> &String {
//     let s = String::from("hello");
//     &s  // 返回局部变量的引用，编译错误
// }

// 泛型函数
fn largest_i32(list: &[i32]) -> i32 {
    let mut largest = list[0];
    for &item in list {
        if item > largest {
            largest = item;
        }
    }
    largest
}

fn largest_char(list: &[char]) -> char {
    let mut largest = list[0];
    for &item in list {
        if item > largest {
            largest = item;
        }
    }
    largest
}

fn largest<T: PartialOrd>(list: &[T]) -> &T {
    let mut largest = &list[0];
    for item in list {
        if item > largest {
            largest = item;
        }
    }
    largest
}

// 泛型结构体
struct Point<T, U> {
    x: T,
    y: U,
}

impl<T, U> Point<T, U> {
    fn x(&self) -> &T {
        &self.x
    }
}

// Trait 定义
pub trait Summary {
    fn summarize(&self) -> String;

    fn summarize_author(&self) -> String {
        String::from("(Read more...)")  // 默认实现
    }
}

struct NewsArticle {
    headline: String,
    location: String,
    author: String,
    content: String,
}

impl Summary for NewsArticle {
    fn summarize(&self) -> String {
        format!("{}, by {} ({})", self.headline, self.author, self.location)
    }
}

struct Tweet {
    username: String,
    content: String,
    reply: bool,
    retweet: bool,
}

impl Summary for Tweet {
    fn summarize(&self) -> String {
        format!("{}: {}", self.username, self.content)
    }
}

// trait 作为参数
fn notify(item: &impl Summary) {
    println!("突发新闻! {}", item.summarize());
}

// 返回 trait
fn returns_summarizable() -> impl Summary {
    Tweet {
        username: String::from("horse_ebooks"),
        content: String::from("of course, as you probably already know, people"),
        reply: false,
        retweet: false,
    }
}

// 生命周期
fn longest<'a>(x: &'a str, y: &'a str) -> &'a str {
    if x.len() > y.len() {
        x
    } else {
        y
    }
}

struct ImportantExcerpt<'a> {
    part: &'a str,
}

// 生命周期省略规则
fn first_word(s: &str) -> &str {
    let bytes = s.as_bytes();
    for (i, &item) in bytes.iter().enumerate() {
        if item == b' ' {
            return &s[0..i];
        }
    }
    &s[..]
}

// 错误处理 - ? 运算符
use std::io;
use std::io::Read;

fn read_username_from_file() -> Result<String, io::Error> {
    let mut s = String::new();
    // 简化：返回一个示例
    Ok(String::from("test_user"))
}