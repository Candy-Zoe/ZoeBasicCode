// ============================================================
// TypeScript 高级特性 - 类和继承
// 运行：ts-node 02_类和继承.ts
// ============================================================

console.log("=== 1. 类的基础 ===");

// 基本类
class Animal {
    name: string;

    constructor(name: string) {
        this.name = name;
    }

    move(distanceInMeters: number = 0): void {
        console.log(`${this.name} 移动了 ${distanceInMeters} 米`);
    }
}

let animal = new Animal("狗");
animal.move(10);

// ============================================================
// 2. 继承
// ============================================================

console.log("\n=== 2. 继承 ===");

class Dog extends Animal {
    breed: string;

    constructor(name: string, breed: string) {
        super(name);
        this.breed = breed;
    }

    bark(): void {
        console.log(`${this.name} 汪汪叫`);
    }

    move(distanceInMeters: number = 0): void {
        console.log(`${this.name} 跑了 ${distanceInMeters} 米`);
        super.move(distanceInMeters);
    }
}

let dog = new Dog("旺财", "金毛");
dog.bark();
dog.move(20);

// ============================================================
// 3. 访问修饰符
// ============================================================

console.log("\n=== 3. 访问修饰符 ===");

class Car {
    public brand: string;        // 公开，默认
    private price: number;       // 私有，只能在类内部访问
    protected color: string;     // 受保护，子类可访问

    constructor(brand: string, price: number, color: string) {
        this.brand = brand;
        this.price = price;
        this.color = color;
    }

    public getPrice(): number {
        return this.price;
    }

    protected getColor(): string {
        return this.color;
    }
}

let car = new Car("Tesla", 500000, "white");
console.log(`汽车品牌: ${car.brand}, 价格: ${car.getPrice()}`);
// console.log(car.price);  // 错误：私有属性不能直接访问
// console.log(car.color);  // 错误：受保护属性不能直接访问

// 子类访问 protected
class ElectricCar extends Car {
    battery: number;

    constructor(brand: string, price: number, color: string, battery: number) {
        super(brand, price, color);
        this.battery = battery;
    }

    info(): void {
        console.log(`品牌: ${this.brand}, 颜色: ${this.getColor()}, 电池: ${this.battery}kWh`);
    }
}

let tesla = new ElectricCar("Tesla", 500000, "red", 100);
tesla.info();

// ============================================================
// 4. 存取器 getter/setter
// ============================================================

console.log("\n=== 4. 存取器 getter/setter ===");

class Employee {
    private _fullName: string = "";

    get fullName(): string {
        return this._fullName;
    }

    set fullName(newName: string) {
        if (newName.length > 2) {
            this._fullName = newName;
        } else {
            console.log("名字太短了");
        }
    }
}

let emp = new Employee();
emp.fullName = "张三";
console.log(`员工: ${emp.fullName}`);
emp.fullName = "李";
console.log(`员工: ${emp.fullName}`);

// ============================================================
// 5. 静态属性和方法
// ============================================================

console.log("\n=== 5. 静态属性和方法 ===");

class Grid {
    static origin = { x: 0, y: 0 };

    static calculateDistance(point: { x: number; y: number }): number {
        let xDist = point.x - Grid.origin.x;
        let yDist = point.y - Grid.origin.y;
        return Math.sqrt(xDist * xDist + yDist * yDist);
    }
}

console.log(`原点距离 (3,4): ${Grid.calculateDistance({ x: 3, y: 4 })}`);
console.log(`原点坐标: (${Grid.origin.x}, ${Grid.origin.y})`);

// ============================================================
// 6. 抽象类
// ============================================================

console.log("\n=== 6. 抽象类 ===");

abstract class Animal2 {
    abstract makeSound(): void;

    move(): void {
        console.log("移动中...");
    }
}

class Cat extends Animal2 {
    makeSound(): void {
        console.log("喵喵叫");
    }
}

let cat = new Cat();
cat.makeSound();
cat.move();

// 抽象类不能实例化
// let animal2 = new Animal2();  // 错误

// ============================================================
// 7. 接口与类
// ============================================================

console.log("\n=== 7. 接口与类 ===");

interface ClockInterface {
    currentTime: Date;
    setTime(d: Date): void;
}

class Clock implements ClockInterface {
    currentTime: Date = new Date();
    setTime(d: Date): void {
        this.currentTime = d;
    }
}

let clock = new Clock();
console.log(`时钟时间: ${clock.currentTime.toLocaleString()}`);

// 实现多个接口
interface Flyable {
    fly(): void;
}

interface Swimmable {
    swim(): void;
}

class Duck implements Flyable, Swimmable {
    fly(): void {
        console.log("鸭子在飞");
    }
    swim(): void {
        console.log("鸭子在游泳");
    }
}

let duck = new Duck();
duck.fly();
duck.swim();

// ============================================================
// 8. 接口继承类
// ============================================================

console.log("\n=== 8. 接口继承类 ===");

class Point {
    x: number;
    y: number;
    constructor(x: number, y: number) {
        this.x = x;
        this.y = y;
    }
}

interface Point3d extends Point {
    z: number;
}

let point3d: Point3d = { x: 1, y: 2, z: 3 };
console.log(`3D 点: (${point3d.x}, ${point3d.y}, ${point3d.z})`);

// ============================================================
// 9. 类的表达式
// ============================================================

console.log("\n=== 9. 类的表达式 ===");

let Greeter = class {
    greeting: string;
    constructor(message: string) {
        this.greeting = message;
    }
    greet() {
        return "Hello, " + this.greeting;
    }
};

let greeter = new Greeter("world");
console.log(greeter.greet());

console.log("\n=== 运行完成 ===");
