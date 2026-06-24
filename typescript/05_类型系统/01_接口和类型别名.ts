// ============================================================
// TypeScript 类型系统 - 接口和类型别名
// 运行：ts-node 01_接口和类型别名.ts
// ============================================================

console.log("=== 1. 接口 Interface ===");

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

// 可选属性
let user2: Person = { firstName: "John", lastName: "Doe", id: 2, age: 30 };
console.log(`${user2.firstName} ${user2.lastName}, 年龄: ${user2.age}`);

// 只读属性
// user.id = 5;  // 错误：只读属性不能修改
console.log(`只读 id: ${user.id}`);

// ============================================================
// 2. 函数类型接口
// ============================================================

console.log("\n=== 2. 函数类型接口 ===");

interface SearchFunc {
    (source: string, subString: string): boolean;
}

let mySearch: SearchFunc = function (source: string, subString: string): boolean {
    let result = source.search(subString);
    return result > -1;
};
console.log(`mySearch("hello world", "world") = ${mySearch("hello world", "world")}`);

// ============================================================
// 3. 可索引的类型
// ============================================================

console.log("\n=== 3. 可索引的类型 ===");

interface StringArray {
    [index: number]: string;
}

let myArray: StringArray = ["Bob", "Fred"];
console.log(`可索引类型: myArray[0] = ${myArray[0]}`);

// 字符串索引签名
interface StringDictionary {
    [key: string]: string;
}

let dict: StringDictionary = {
    "name": "张三",
    "city": "北京"
};
console.log(`字典: name=${dict["name"]}, city=${dict["city"]}`);

// ============================================================
// 4. 类类型接口
// ============================================================

console.log("\n=== 4. 类类型接口 ===");

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

// ============================================================
// 5. 继承接口
// ============================================================

console.log("\n=== 5. 继承接口 ===");

interface Shape {
    color: string;
}

interface Square extends Shape {
    sideLength: number;
}

let square: Square = { color: "blue", sideLength: 10 };
console.log(`正方形: color=${square.color}, sideLength=${square.sideLength}`);

// 多继承
interface PenStroke {
    penWidth: number;
}

interface Square2 extends Shape, PenStroke {
    sideLength: number;
}

let square2: Square2 = { color: "red", sideLength: 5, penWidth: 2 };
console.log(`正方形2: color=${square2.color}, sideLength=${square2.sideLength}, penWidth=${square2.penWidth}`);

// ============================================================
// 6. 类型别名
// ============================================================

console.log("\n=== 6. 类型别名 ===");

type Name = string;
type NameResolver = () => string;
type NameOrResolver = Name | NameResolver;

function getName(n: NameOrResolver): Name {
    if (typeof n === "string") {
        return n;
    } else {
        return n();
    }
}

console.log(`类型别名: ${getName("张三")}`);
console.log(`类型别名函数: ${getName(() => "李四")}`);

// 类型别名 vs 接口
// 类型别名可以表示基本类型、联合类型、交叉类型等
type StringOrNumber = string | number;
let val1: StringOrNumber = "hello";
let val2: StringOrNumber = 42;
console.log(`联合类型: val1=${val1}, val2=${val2}`);

// 交叉类型
type PersonInfo = {
    name: string;
    age: number;
};

type EmployeeInfo = {
    company: string;
    salary: number;
};

type PersonEmployee = PersonInfo & EmployeeInfo;

let pe: PersonEmployee = {
    name: "张三",
    age: 25,
    company: "Google",
    salary: 100000
};
console.log(`交叉类型: ${pe.name} @ ${pe.company}`);

// ============================================================
// 7. 接口 vs 类型别名
// ============================================================

console.log("\n=== 7. 接口 vs 类型别名 ===");

// 接口可以被 extends 和 implements
interface Animal {
    name: string;
}

interface Dog extends Animal {
    bark(): void;
}

class Labrador implements Dog {
    name: string = "Labrador";
    bark() {
        console.log("汪汪叫");
    }
}

let dog = new Labrador();
dog.bark();

// 类型别名可以使用联合类型、交叉类型、元组等
type Result<T> = 
    | { success: true; data: T }
    | { success: false; error: string };

function fetchData(): Result<string> {
    return { success: true, data: "数据加载成功" };
}

let result = fetchData();
if (result.success) {
    console.log(`成功: ${result.data}`);
} else {
    console.log(`失败: ${result.error}`);
}

// 接口可以声明合并
interface User {
    name: string;
}

interface User {
    age: number;
}

let u: User = { name: "王五", age: 28 };
console.log(`声明合并: name=${u.name}, age=${u.age}`);

console.log("\n=== 运行完成 ===");
