// ============================================================
// TypeScript 函数、接口、类
// ============================================================

console.log("=== 1. 函数 ===");

// 1. 函数类型
function add(x: number, y: number): number {
    return x + y;
}
console.log(`add(1, 2) = ${add(1, 2)}`);

// 函数表达式
let myAdd = function (x: number, y: number): number { return x + y; };

// 2. 可选参数和默认参数
function buildName(firstName: string, lastName?: string): string {
    if (lastName) {
        return firstName + " " + lastName;
    }
    return firstName;
}
console.log(`buildName("张三") = ${buildName("张三")}`);
console.log(`buildName("张", "三") = ${buildName("张", "三")}`);

// 默认参数
function buildName2(firstName: string, lastName = "Smith"): string {
    return firstName + " " + lastName;
}
console.log(`buildName2("Bob") = ${buildName2("Bob")}`);

// 3. 剩余参数
function buildFullName(firstName: string, ...restOfName: string[]): string {
    return firstName + " " + restOfName.join(" ");
}
console.log(`全名: ${buildFullName("Joseph", "Samuel", "Lucas", "MacKinzie")}`);

// 4. 重载
function reverse(x: number): number;
function reverse(x: string): string;
function reverse(x: number | string): number | string {
    if (typeof x === "number") {
        return Number(x.toString().split("").reverse().join(""));
    } else {
        return x.split("").reverse().join("");
    }
}
console.log(`reverse(123) = ${reverse(123)}`);
console.log(`reverse("hello") = ${reverse("hello")}`);

// ============================================================
// 2. 接口 Interface
// ============================================================

console.log("\n=== 2. 接口 Interface ===");

// 基本接口
interface Person {
    firstName: string;
    lastName: string;
    age?: number;           // 可选属性
    readonly id: number;    // 只读属性
}

function greeter(person: Person): string {
    return `Hello, ${person.firstName} ${person.lastName}`;
}

let user: Person = { firstName: "Jane", lastName: "User", id: 1 };
console.log(greeter(user));

// 函数类型接口
interface SearchFunc {
    (source: string, subString: string): boolean;
}

let mySearch: SearchFunc = function (source: string, subString: string): boolean {
    let result = source.search(subString);
    return result > -1;
};
console.log(`mySearch("hello world", "world") = ${mySearch("hello world", "world")}`);

// 可索引的类型
interface StringArray {
    [index: number]: string;
}

let myArray: StringArray = ["Bob", "Fred"];
console.log(`可索引类型: myArray[0] = ${myArray[0]}`);

// 类类型接口
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

// 继承接口
interface Shape {
    color: string;
}

interface Square extends Shape {
    sideLength: number;
}

let square: Square = { color: "blue", sideLength: 10 };
console.log(`正方形: color=${square.color}, sideLength=${square.sideLength}`);

// ============================================================
// 3. 类 Class
// ============================================================

console.log("\n=== 3. 类 Class ===");

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

// 继承
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

// public / private / protected
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
}

let car = new Car("Tesla", 500000, "white");
console.log(`汽车品牌: ${car.brand}, 价格: ${car.getPrice()}`);

// 存取器 getter/setter
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

// 静态属性
class Grid {
    static origin = { x: 0, y: 0 };

    static calculateDistance(point: { x: number; y: number }): number {
        let xDist = point.x - Grid.origin.x;
        let yDist = point.y - Grid.origin.y;
        return Math.sqrt(xDist * xDist + yDist * yDist);
    }
}

console.log(`原点距离 (3,4): ${Grid.calculateDistance({ x: 3, y: 4 })}`);

// 抽象类
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

// ============================================================
// 4. 泛型
// ============================================================

console.log("\n=== 4. 泛型 ===");

// 泛型函数
function identity<T>(arg: T): T {
    return arg;
}

console.log(`identity<string>("hello") = ${identity<string>("hello")}`);
console.log(`identity<number>(42) = ${identity<number>(42)}`);
console.log(`类型推断 identity("world") = ${identity("world")}`);

// 泛型接口
interface GenericIdentityFn<T> {
    (arg: T): T;
}

let myIdentity: GenericIdentityFn<number> = identity;
console.log(`泛型函数接口: ${myIdentity(100)}`);

// 泛型类
class GenericNumber<T> {
    zeroValue: T;
    add: (x: T, y: T) => T;

    constructor(zero: T, addFn: (x: T, y: T) => T) {
        this.zeroValue = zero;
        this.add = addFn;
    }
}

let myGenericNumber = new GenericNumber<number>(0, (x, y) => x + y);
console.log(`泛型类: 5 + 3 = ${myGenericNumber.add(5, 3)}`);

// 泛型约束
interface Lengthwise {
    length: number;
}

function loggingIdentity<T extends Lengthwise>(arg: T): T {
    console.log(`长度: ${arg.length}`);
    return arg;
}

loggingIdentity("hello");
loggingIdentity([1, 2, 3]);

console.log("\n=== 运行完成 ===");