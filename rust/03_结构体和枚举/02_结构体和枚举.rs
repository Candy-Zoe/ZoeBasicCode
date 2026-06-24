// ============================================================
// Rust 结构体、枚举、模式匹配
// 编译运行：rustc 02_结构体和枚举.rs -o 02_结构体和枚举 && ./02_结构体和枚举
// ============================================================

fn main() {
    println!("=== 1. 结构体 ===");

    // 定义结构体
    #[derive(Debug)]
    struct User {
        username: String,
        email: String,
        sign_in_count: u64,
        active: bool,
    }

    // 创建实例
    let user1 = User {
        email: String::from("someone@example.com"),
        username: String::from("someusername123"),
        active: true,
        sign_in_count: 1,
    };
    println!("用户: {}", user1.username);
    println!("邮箱: {}", user1.email);
    println!("激活: {}", user1.active);

    // 可变结构体
    let mut user2 = User {
        email: String::from("another@example.com"),
        username: String::from("anotherusername567"),
        active: true,
        sign_in_count: 1,
    };
    user2.email = String::from("newemail@example.com");
    println!("修改后邮箱: {}", user2.email);

    // 结构体更新语法
    let user3 = User {
        email: String::from("third@example.com"),
        username: String::from("thirdusername"),
        ..user1  // 其余字段从 user1 复制
    };
    println!("user3.sign_in_count = {}", user3.sign_in_count);

    // 元组结构体
    struct Color(i32, i32, i32);
    struct Point(i32, i32, i32);

    let black = Color(0, 0, 0);
    let origin = Point(0, 0, 0);
    println!("黑色: Color({}, {}, {})", black.0, black.1, black.2);
    println!("原点: Point({}, {}, {})", origin.0, origin.1, origin.2);

    // 类单元结构体
    struct AlwaysEqual;
    let subject = AlwaysEqual;
    println!("类单元结构体");

    // Debug 输出
    println!("\n--- Debug 输出 ---");
    println!("user1: {:?}", user1);
    println!("user1: {:#?}", user1);

    // ============================================================
    // 方法
    // ============================================================

    println!("\n=== 2. 方法 ===");

    #[derive(Debug)]
    struct Rectangle {
        width: u32,
        height: u32,
    }

    impl Rectangle {
        // 方法
        fn area(&self) -> u32 {
            self.width * self.height
        }

        fn can_hold(&self, other: &Rectangle) -> bool {
            self.width > other.width && self.height > other.height
        }

        // 关联函数（类似静态方法）
        fn square(size: u32) -> Rectangle {
            Rectangle { width: size, height: size }
        }
    }

    let rect1 = Rectangle { width: 30, height: 50 };
    println!("矩形面积: {}", rect1.area());

    let rect2 = Rectangle { width: 10, height: 40 };
    let rect3 = Rectangle { width: 60, height: 45 };
    println!("rect1 能容纳 rect2: {}", rect1.can_hold(&rect2));
    println!("rect1 能容纳 rect3: {}", rect1.can_hold(&rect3));

    let sq = Rectangle::square(10);
    println!("正方形: {:?}", sq);

    // ============================================================
    // 枚举
    // ============================================================

    println!("\n=== 3. 枚举 ===");

    // 基本枚举
    enum IpAddrKind {
        V4,
        V6,
    }

    let four = IpAddrKind::V4;
    let six = IpAddrKind::V6;

    // 枚举成员可以关联数据
    enum IpAddr {
        V4(u8, u8, u8, u8),
        V6(String),
    }

    let home = IpAddr::V4(127, 0, 0, 1);
    let loopback = IpAddr::V6(String::from("::1"));

    // 更复杂的枚举
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
    println!("Message: {:?}", m3);

    // Option 枚举
    let some_number = Some(5);
    let some_string = Some("a string");
    let absent_number: Option<i32> = None;
    println!("some_number: {:?}", some_number);
    println!("absent_number: {:?}", absent_number);

    // ============================================================
    // 模式匹配
    // ============================================================

    println!("\n=== 4. 模式匹配 ===");

    // match 表达式
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

    // 匹配 Option<T>
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

    // 通配符
    let some_u8_value = 3u8;
    match some_u8_value {
        1 => println!("one"),
        3 => println!("three"),
        5 => println!("five"),
        _ => println!("something else"),
    }

    // if let 简洁控制流
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

    // ============================================================
    // 模块系统
    // ============================================================

    println!("\n=== 5. 模块系统 ===");

    mod front_of_house {
        pub mod hosting {
            pub fn add_to_waitlist() {
                println!("加入等候名单");
            }
        }

        mod serving {
            fn take_order() {}
        }
    }

    // 使用 pub 函数
    front_of_house::hosting::add_to_waitlist();

    // use 关键字
    use front_of_house::hosting;
    hosting::add_to_waitlist();

    println!("\n=== 运行完成 ===");
}