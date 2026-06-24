// ============================================================
// Rust 流程控制 - 条件语句
// 编译运行：rustc 01_条件语句.rs -o 01_条件语句 && ./01_条件语句
// ============================================================

fn main() {
    println!("=== 1. if 表达式 ===");
    let number = 3;
    if number < 5 {
        println!("条件为真");
    } else {
        println!("条件为假");
    }

    // else if
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
    println!("\n--- if 作为表达式 ---");
    let condition = true;
    let number = if condition { 5 } else { 6 };
    println!("number = {}", number);

    let score = 85;
    let grade = if score >= 90 {
        "优秀"
    } else if score >= 80 {
        "良好"
    } else if score >= 60 {
        "及格"
    } else {
        "不及格"
    };
    println!("成绩等级: {}", grade);

    println!("\n=== 2. match 模式匹配 ===");

    // 基本 match
    let day = 3;
    match day {
        1 => println!("星期一"),
        2 => println!("星期二"),
        3 => println!("星期三"),
        4 => println!("星期四"),
        5 => println!("星期五"),
        6 | 7 => println!("周末"),
        _ => println!("无效"),
    }

    // match 作为表达式
    let coin = 25;
    let value = match coin {
        1 => "1 分 (Penny)",
        5 => "5 分 (Nickel)",
        10 => "10 分 (Dime)",
        25 => "25 分 (Quarter)",
        _ => "其他硬币",
    };
    println!("硬币: {}", value);

    // match 匹配范围
    let age = 20;
    match age {
        0..=12 => println!("儿童"),
        13..=19 => println!("青少年"),
        20..=59 => println!("成年人"),
        _ => println!("老年人"),
    }

    println!("\n=== 3. if let 简洁控制流 ===");

    // if let 匹配 Some
    let some_value = Some(3);
    if let Some(3) = some_value {
        println!("值为 3");
    }

    // if let 带 else
    let some_value = Some(5);
    if let Some(3) = some_value {
        println!("值为 3");
    } else {
        println!("值不是 3，而是 {:?}", some_value);
    }

    // 匹配 None
    let none_value: Option<i32> = None;
    if let None = none_value {
        println!("值为 None");
    }

    println!("\n=== 4. match 匹配枚举 ===");

    #[derive(Debug)]
    enum Message {
        Quit,
        Move { x: i32, y: i32 },
        Write(String),
        ChangeColor(i32, i32, i32),
    }

    let msg = Message::Write(String::from("hello"));
    match msg {
        Message::Quit => println!("退出"),
        Message::Move { x, y } => println!("移动到 ({}, {})", x, y),
        Message::Write(text) => println!("写入: {}", text),
        Message::ChangeColor(r, g, b) => println!("颜色: R{}, G{}, B{}", r, g, b),
    }

    // 匹配 Option<T>
    println!("\n--- 匹配 Option<T> ---");
    let five = Some(5);
    let six = plus_one(five);
    let none = plus_one(None);
    println!("plus_one(Some(5)) = {:?}", six);
    println!("plus_one(None) = {:?}", none);

    println!("\n=== 运行完成 ===");
}

fn plus_one(x: Option<i32>) -> Option<i32> {
    match x {
        None => None,
        Some(i) => Some(i + 1),
    }
}
