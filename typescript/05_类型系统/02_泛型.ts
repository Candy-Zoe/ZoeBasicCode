// ============================================================
// TypeScript 类型系统 - 泛型
// 运行：ts-node 02_泛型.ts
// ============================================================

console.log("=== 1. 泛型函数 ===");

// 基本泛型函数
function identity<T>(arg: T): T {
    return arg;
}

console.log(`identity<string>("hello") = ${identity<string>("hello")}`);
console.log(`identity<number>(42) = ${identity<number>(42)}`);
console.log(`类型推断 identity("world") = ${identity("world")}`);

// 多个泛型参数
function pair<T, U>(first: T, second: U): [T, U] {
    return [first, second];
}

let p1 = pair<string, number>("answer", 42);
console.log(`pair: [${p1[0]}, ${p1[1]}]`);

let p2 = pair("hello", true);
console.log(`类型推断 pair: [${p2[0]}, ${p2[1]}]`);

// ============================================================
// 2. 泛型接口
// ============================================================

console.log("\n=== 2. 泛型接口 ===");

interface GenericIdentityFn<T> {
    (arg: T): T;
}

let myIdentity: GenericIdentityFn<number> = identity;
console.log(`泛型函数接口: ${myIdentity(100)}`);

// 泛型接口 - 集合
interface Collection<T> {
    add(item: T): void;
    remove(item: T): boolean;
    size(): number;
    contains(item: T): boolean;
}

class SimpleCollection<T> implements Collection<T> {
    private items: T[] = [];

    add(item: T): void {
        this.items.push(item);
    }

    remove(item: T): boolean {
        const index = this.items.indexOf(item);
        if (index > -1) {
            this.items.splice(index, 1);
            return true;
        }
        return false;
    }

    size(): number {
        return this.items.length;
    }

    contains(item: T): boolean {
        return this.items.includes(item);
    }
}

let coll = new SimpleCollection<string>();
coll.add("apple");
coll.add("banana");
console.log(`集合大小: ${coll.size()}`);
console.log(`包含 apple: ${coll.contains("apple")}`);

// ============================================================
// 3. 泛型类
// ============================================================

console.log("\n=== 3. 泛型类 ===");

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

// 泛型栈
class Stack<T> {
    private items: T[] = [];

    push(item: T): void {
        this.items.push(item);
    }

    pop(): T | undefined {
        return this.items.pop();
    }

    peek(): T | undefined {
        return this.items[this.items.length - 1];
    }

    isEmpty(): boolean {
        return this.items.length === 0;
    }

    size(): number {
        return this.items.length;
    }
}

let stack = new Stack<number>();
stack.push(1);
stack.push(2);
stack.push(3);
console.log(`栈大小: ${stack.size()}`);
console.log(`栈顶: ${stack.peek()}`);
console.log(`弹出: ${stack.pop()}`);
console.log(`弹出后大小: ${stack.size()}`);

// ============================================================
// 4. 泛型约束
// ============================================================

console.log("\n=== 4. 泛型约束 ===");

interface Lengthwise {
    length: number;
}

function loggingIdentity<T extends Lengthwise>(arg: T): T {
    console.log(`长度: ${arg.length}`);
    return arg;
}

loggingIdentity("hello");
loggingIdentity([1, 2, 3]);
// loggingIdentity(123);  // 错误：number 没有 length 属性

// 多个泛型约束
interface Serializable {
    serialize(): string;
}

interface Deserializable {
    deserialize(data: string): void;
}

// 使用交叉类型
function process<T extends Serializable & Deserializable>(obj: T): T {
    let data = obj.serialize();
    console.log(`序列化: ${data}`);
    obj.deserialize(data);
    return obj;
}

// 在类型参数中使用泛型约束
function getProperty<T, K extends keyof T>(obj: T, key: K): T[K] {
    return obj[key];
}

let person = { name: "张三", age: 25, city: "北京" };
let nameVal = getProperty(person, "name");
let ageVal = getProperty(person, "age");
console.log(`name: ${nameVal}, age: ${ageVal}`);

// ============================================================
// 5. 泛型工具类型
// ============================================================

console.log("\n=== 5. 泛型工具类型 ===");

interface User {
    name: string;
    age: number;
    email: string;
}

// Partial：所有属性变成可选
type PartialUser = Partial<User>;
let partialUser: PartialUser = { name: "李四" };
console.log(`Partial: ${partialUser.name}`);

// Required：所有属性变成必需
type RequiredUser = Required<PartialUser>;
let requiredUser: RequiredUser = { name: "王五", age: 30, email: "wang@example.com" };
console.log(`Required: ${requiredUser.name}`);

// Readonly：所有属性变成只读
type ReadonlyUser = Readonly<User>;
let readonlyUser: ReadonlyUser = { name: "赵六", age: 28, email: "zhao@example.com" };
console.log(`Readonly: ${readonlyUser.name}`);

// Pick：选择部分属性
type UserName = Pick<User, "name" | "email">;
let userName: UserName = { name: "孙七", email: "sun@example.com" };
console.log(`Pick: ${userName.name}, ${userName.email}`);

// Omit：排除部分属性
type UserWithoutEmail = Omit<User, "email">;
let userWithoutEmail: UserWithoutEmail = { name: "周八", age: 22 };
console.log(`Omit: ${userWithoutEmail.name}, ${userWithoutEmail.age}`);

// Record：构造键值对类型
type ScoreMap = Record<string, number>;
let scores: ScoreMap = { "数学": 95, "语文": 88, "英语": 92 };
console.log(`Record: 数学=${scores["数学"]}`);

// Exclude：排除联合类型中的某些类型
type AllTypes = string | number | boolean;
type WithoutBoolean = Exclude<AllTypes, boolean>;
let wb: WithoutBoolean = "hello";
console.log(`Exclude: wb = ${wb}`);

// Extract：提取联合类型中的某些类型
type Extracted = Extract<AllTypes, string | number>;
let ext: Extracted = 42;
console.log(`Extract: ext = ${ext}`);

// ReturnType：获取函数返回类型
function getUser() {
    return { name: "吴九", age: 35 };
}
type UserType = ReturnType<typeof getUser>;
let ut: UserType = { name: "吴九", age: 35 };
console.log(`ReturnType: ${ut.name}`);

// ============================================================
// 6. 条件类型
// ============================================================

console.log("\n=== 6. 条件类型 ===");

type IsString<T> = T extends string ? "yes" : "no";
type A = IsString<string>;   // "yes"
type B = IsString<number>;   // "no"
console.log(`条件类型: IsString<string> = "yes", IsString<number> = "no"`);

// 分布式条件类型
type ToArray<T> = T extends any ? T[] : never;
type StrArrOrNumArr = ToArray<string | number>;
let sa: string[] = ["a", "b"];
let na: number[] = [1, 2, 3];
console.log(`分布式条件类型: string[] | number[]`);

// 推断类型 infer
type ReturnType2<T> = T extends (...args: any[]) => infer R ? R : any;
type FuncReturn = ReturnType2<() => number>;
let fr: FuncReturn = 123;
console.log(`infer 推断返回类型: ${fr}`);

console.log("\n=== 运行完成 ===");
