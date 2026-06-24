// ============================================================
// Rust 所有权系统 - 所有权和借用
// 编译运行：rustc 01_所有权和借用.rs -o 01_所有权和借用 && ./01_所有权和借用
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

    println!("\n=== 2. 函数与所有权 ===");

    let s = String::from("hello");
    takes_ownership(s);
    // println!("{}", s);  // 错误！s 已被移走

    let x = 5;
    makes_copy(x);
    println!("x = {} (Copy 类型，不转移)", x);

    println!("\n=== 3. 返回值与所有权 ===");

    let s1 = gives_ownership();
    println!("gives_ownership 返回: {}", s1);

    let s2 = String::from("hello");
    let s3 = takes_and_gives_back(s2);
    println!("takes_and_gives_back 返回: {}", s3);

    println!("\n=== 4. 引用与借用 ===");

    let s1 = String::from("hello");
    let len = calculate_length(&s1);
    println!("'{}' 的长度是 {}", s1, len);

    // 可变引用
    println!("\n--- 可变引用 ---");
    let mut s = String::from("hello");
    change(&mut s);
    println!("修改后: {}", s);

    println!("\n=== 5. 引用规则 ===");

    // 规则1：在任意给定时间，要么只能有一个可变引用，
    //        要么只能有多个不可变引用。
    // 规则2：引用必须总是有效的（不能有悬垂引用）。

    let mut s = String::from("hello");

    // 多个不可变引用可以
    let r1 = &s;
    let r2 = &s;
    println!("{} and {}", r1, r2);

    // r1 和 r2 之后不再使用，可以创建可变引用
    let r3 = &mut s;
    println!("可变引用: {}", r3);

    println!("\n=== 6. 切片 Slice ===");

    let s = String::from("hello world");

    let hello = &s[0..5];
    let world = &s[6..11];
    println!("hello = {}, world = {}", hello, world);

    let hello = &s[..5];    // 等同于 0..5
    let world = &s[6..];    // 等同于 6..s.len()
    let entire = &s[..];    // 整个字符串
    println!("entire = {}", entire);

    // 数组切片
    println!("\n--- 数组切片 ---");
    let a = [1, 2, 3, 4, 5];
    let slice = &a[1..3];
    println!("数组切片: [{}, {}]", slice[0], slice[1]);

    println!("\n=== 7. 字符串切片 ===");

    let s = String::from("hello world");
    let word = first_word(&s);
    println!("第一个单词: {}", word);

    println!("\n=== 8. 所有权总结 ===");
    println!("- 每个值都有一个所有者");
    println!("- 值在任意时刻只能有一个所有者");
    println!("- 当所有者离开作用域，值将被丢弃");
    println!("- 引用不获取所有权（借用）");
    println!("- 不可变引用可以有多个");
    println!("- 可变引用同时只能有一个");

    println!("\n=== 运行完成 ===");
}

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

fn calculate_length(s: &String) -> usize {
    s.len()
}

fn change(some_string: &mut String) {
    some_string.push_str(", world");
}

fn first_word(s: &str) -> &str {
    let bytes = s.as_bytes();
    for (i, &item) in bytes.iter().enumerate() {
        if item == b' ' {
            return &s[0..i];
        }
    }
    &s[..]
}
