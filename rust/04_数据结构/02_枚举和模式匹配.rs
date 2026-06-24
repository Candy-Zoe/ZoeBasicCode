// ============================================================
// Rust 数据结构 - 枚举和模式匹配
// 编译运行：rustc 02_枚举和模式匹配.rs -o 02_枚举和模式匹配 && ./02_枚举和模式匹配
// ============================================================

fn main() {
    println!("=== 1. 枚举定义 ===");

    // 基本枚举
    #[derive(Debug)]
    enum IpAddrKind {
        V4,
        V6,
    }

    let four = IpAddrKind::V4;
    let six = IpAddrKind::V6;
    println!("four: {:?}", four);
    println!("six: {:?}", six);

    println!("\n=== 2. 枚举成员关联数据 ===");

    #[derive(Debug)]
    enum IpAddr {
        V4(u8, u8, u8, u8),
        V6(String),
    }

    let home = IpAddr::V4(127, 0, 0, 1);
    let loopback = IpAddr::V6(String::from("::1"));
    println!("home: {:?}", home);
    println!("loopback: {:?}", loopback);

    println!("\n=== 3. 更复杂的枚举 ===");

    #[derive(Debug)]
    enum Message {
        Quit,
        Move { x: i32, y: i32 },
        Write(String),
        ChangeColor(i32, i32, i32),
    }

    let m1 = Message::Quit;
    let m2 = Message::Move { x: 10, y: 20 };
    let m3 = Message::Write(String::from("hello"));
    let m4 = Message::ChangeColor(255, 0, 0);
    println!("Message::Write: {:?}", m3);

    // 枚举方法
    impl Message {
        fn call(&self) {
            match self {
                Message::Quit => println!("退出"),
                Message::Move { x, y } => println!("移动到 ({}, {})", x, y),
                Message::Write(text) => println!("写入: {}", text),
                Message::ChangeColor(r, g, b) => println!("变色: R{}, G{}, B{}", r, g, b),
            }
        }
    }

    m1.call();
    m2.call();
    m3.call();
    m4.call();

    println!("\n=== 4. Option 枚举 ===");

    let some_number = Some(5);
    let some_string = Some("a string");
    let absent_number: Option<i32> = None;
    println!("some_number: {:?}", some_number);
    println!("some_string: {:?}", some_string);
    println!("absent_number: {:?}", absent_number);

    // Option 使用
    let x: i32 = 5;
    let y: Option<i32> = Some(10);
    // println!("x + y = {}", x + y); // 错误！不能直接相加
    println!("x + y = {}", x + y.unwrap_or(0));

    println!("\n=== 5. match 模式匹配 ===");

    enum Coin {
        Penny,
        Nickel,
        Dime,
        Quarter,
    }

    fn value_in_cents(coin: Coin) -> u8 {
        match coin {
            Coin::Penny => {
                println!("幸运便士！");
                1
            }
            Coin::Nickel => 5,
            Coin::Dime => 10,
            Coin::Quarter => 25,
        }
    }

    println!("Penny: {} 分", value_in_cents(Coin::Penny));
    println!("Quarter: {} 分", value_in_cents(Coin::Quarter));

    println!("\n=== 6. 匹配 Option<T> ===");

    fn plus_one(x: Option<i32>) -> Option<i32> {
        match x {
            None => None,
            Some(i) => Some(i + 1),
        }
    }

    let five = Some(5);
    let six = plus_one(five);
    let none = plus_one(None);
    println!("plus_one(Some(5)) = {:?}", six);
    println!("plus_one(None) = {:?}", none);

    println!("\n=== 7. 通配符和 _ ===");

    let some_u8_value = 3u8;
    match some_u8_value {
        1 => println!("one"),
        3 => println!("three"),
        5 => println!("five"),
        _ => println!("something else"),
    }

    println!("\n=== 8. if let 简洁控制流 ===");

    let some_u8_value = Some(3u8);
    if let Some(3) = some_u8_value {
        println!("three");
    }

    let mut count = 0;
    let coin = Coin::Quarter;
    if let Coin::Quarter = coin {
        println!("这是 25 美分");
    } else {
        count += 1;
    }

    println!("\n=== 9. while let ===");

    let mut stack = Vec::new();
    stack.push(1);
    stack.push(2);
    stack.push(3);

    println!("弹出元素:");
    while let Some(top) = stack.pop() {
        println!("  {}", top);
    }

    println!("\n=== 运行完成 ===");
}
