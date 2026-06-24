// ============================================================
// Rust 基础语法 - 变量和数据类型
// 编译运行：rustc 01_变量和数据类型.rs -o 01_变量和数据类型 && ./01_变量和数据类型
// ============================================================

fn main() {
    println!("=== 1. 变量与可变性 ===");

    // 不可变变量（默认）
    let x = 5;
    println!("x = {}", x);
    // x = 6;  // 错误！默认不可变

    // 可变变量
    let mut y = 5;
    println!("y = {}", y);
    y = 6;
    println!("修改后 y = {}", y);

    // 常量
    const MAX_POINTS: u32 = 100_000;
    println!("常量 MAX_POINTS = {}", MAX_POINTS);

    // 变量遮蔽 (Shadowing)
    let spaces = "   ";
    let spaces = spaces.len();
    println!("spaces = {} (字符串的长度)", spaces);

    println!("\n=== 2. 数据类型 ===");

    // 整型
    let guess: u32 = "42".parse().expect("不是数字");
    println!("u32: guess = {}", guess);

    // 浮点数
    let x = 2.0;       // f64
    let y: f32 = 3.0;  // f32
    println!("f64: x = {}, f32: y = {}", x, y);

    // 布尔类型
    let t = true;
    let f: bool = false;
    println!("布尔: t={}, f={}", t, f);

    // 字符类型
    let c = 'z';
    let z = 'ℤ';
    let heart_eyed_cat = '😻';
    println!("字符: {}, {}, {}", c, z, heart_eyed_cat);

    // 字符串
    let s1 = String::from("Hello");
    let s2 = "World";
    println!("字符串: {} {}", s1, s2);

    // 元组
    let tup: (i32, f64, u8) = (500, 6.4, 1);
    let (x, y, z) = tup;
    println!("元组: ({}, {}, {})", x, y, z);
    println!("元组第一个元素: {}", tup.0);

    // 数组
    let a = [1, 2, 3, 4, 5];
    let b: [i32; 5] = [1, 2, 3, 4, 5];
    let c = [3; 5];  // [3, 3, 3, 3, 3]
    println!("数组: [{}, {}, {}, {}, {}]", a[0], a[1], a[2], a[3], a[4]);
    println!("c = [{}, {}, {}, {}, {}]", c[0], c[1], c[2], c[3], c[4]);

    println!("\n=== 3. 类型转换 ===");
    let x: i32 = 5;
    let y: f64 = x as f64;
    println!("i32 -> f64: {} -> {}", x, y);

    let f = 3.99;
    let i: i32 = f as i32;
    println!("f64 -> i32: {} -> {}", f, i);

    println!("\n=== 4. 注释 ===");
    println!("// 单行注释");
    println!("/* ... */ 多行注释");
    println!("/// 文档注释");
    println!("//! 模块级文档注释");

    println!("\n=== 运行完成 ===");
}
