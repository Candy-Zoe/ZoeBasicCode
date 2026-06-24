// ============================================================
// Rust 基础语法 - 变量、数据类型、函数
// 编译运行：rustc 01_变量和数据类型.rs -o 01_变量和数据类型 && ./01_变量和数据类型
// 或使用 cargo
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

    // ============================================================
    // 数据类型
    // ============================================================

    println!("\n=== 2. 数据类型 ===");

    // 整型
    let guess: u32 = "42".parse().expect("不是数字");
    println!("u32: guess = {}", guess);

    // 浮点数
    let x = 2.0;       // f64
    let y: f32 = 3.0;  // f32
    println!("f64: x = {}, f32: y = {}", x, y);

    // 数值运算
    let sum = 5 + 10;
    let difference = 95.5 - 4.3;
    let product = 4 * 30;
    let quotient = 56.7 / 32.2;
    let remainder = 43 % 5;
    println!("加: {}, 减: {}, 乘: {}, 除: {:.2}, 模: {}", sum, difference, product, quotient, remainder);

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

    // ============================================================
    // 函数
    // ============================================================

    println!("\n=== 3. 函数 ===");

    println!("add(5, 3) = {}", add(5, 3));
    println!("add(10, 20) = {}", add(10, 20));

    let five = five();
    println!("five() = {}", five);

    let x = plus_one(5);
    println!("plus_one(5) = {}", x);

    // 函数作为表达式
    let y = {
        let x = 3;
        x + 1  // 注意没有分号，这是表达式
    };
    println!("y = {}", y);

    // ============================================================
    // 注释
    // ============================================================

    println!("\n=== 4. 注释 ===");
    println!("// 单行注释");
    println!("/* ... */ 多行注释");
    println!("/// 文档注释");
    println!("//! 模块级文档注释");

    // ============================================================
    // 控制流
    // ============================================================

    println!("\n=== 5. 控制流 ===");

    // if 表达式
    let number = 3;
    if number < 5 {
        println!("条件为真");
    } else {
        println!("条件为假");
    }

    // if let
    let number = 6;
    if number % 4 == 0 {
        println!("能被 4 整除");
    } else if number % 3 == 0 {
        println!("能被 3 整除");
    } else if number % 2 == 0 {
        println!("能被 2 整除");
    } else {
        println!("不能被 4, 3, 2 整除");
    }

    // if 作为表达式
    let condition = true;
    let number = if condition { 5 } else { 6 };
    println!("number = {}", number);

    // loop 循环
    println!("\n--- loop 循环 ---");
    let mut counter = 0;
    let result = loop {
        counter += 1;
        if counter == 10 {
            break counter * 2;  // break 可以返回值
        }
    };
    println!("循环结果: {}", result);

    // while 循环
    println!("\n--- while 循环 ---");
    let mut number = 3;
    while number != 0 {
        println!("{}!", number);
        number -= 1;
    }
    println!("发射！");

    // for 循环
    println!("\n--- for 循环 ---");
    let a = [10, 20, 30, 40, 50];
    for element in a.iter() {
        println!("值为: {}", element);
    }

    // Range
    println!("\n--- Range ---");
    for number in 1..4 {  // 1..4 是 1, 2, 3
        println!("{}", number);
    }

    for number in (1..4).rev() {  // 反转
        println!("{}!", number);
    }
    println!("出发！");

    println!("\n=== 运行完成 ===");
}

fn add(a: i32, b: i32) -> i32 {
    a + b  // 表达式，没有分号
}

fn five() -> i32 {
    5
}

fn plus_one(x: i32) -> i32 {
    x + 1
}