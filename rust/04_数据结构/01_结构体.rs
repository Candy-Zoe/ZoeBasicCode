// ============================================================
// Rust 数据结构 - 结构体
// 编译运行：rustc 01_结构体.rs -o 01_结构体 && ./01_结构体
// ============================================================

fn main() {
    println!("=== 1. 结构体定义和实例化 ===");

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

    println!("\n=== 2. 元组结构体 ===");
    struct Color(i32, i32, i32);
    struct Point(i32, i32, i32);

    let black = Color(0, 0, 0);
    let origin = Point(0, 0, 0);
    println!("黑色: Color({}, {}, {})", black.0, black.1, black.2);
    println!("原点: Point({}, {}, {})", origin.0, origin.1, origin.2);

    println!("\n=== 3. 类单元结构体 ===");
    struct AlwaysEqual;
    let _subject = AlwaysEqual;
    println!("类单元结构体 AlwaysEqual");

    println!("\n=== 4. Debug 输出 ===");
    println!("Debug 输出: {:?}", user1);
    println!("格式化 Debug: {:#?}", user1);

    println!("\n=== 5. 方法 ===");

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

    println!("\n=== 6. 结构体示例：几何图形 ===");
    struct Circle {
        radius: f64,
    }

    impl Circle {
        fn area(&self) -> f64 {
            std::f64::consts::PI * self.radius * self.radius
        }

        fn perimeter(&self) -> f64 {
            2.0 * std::f64::consts::PI * self.radius
        }
    }

    let c = Circle { radius: 3.0 };
    println!("圆形半径: {}", c.radius);
    println!("面积: {:.2}", c.area());
    println!("周长: {:.2}", c.perimeter());

    println!("\n=== 运行完成 ===");
}
