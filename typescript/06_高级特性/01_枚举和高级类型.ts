// ============================================================
// TypeScript 高级特性 - 枚举和高级类型
// 运行：ts-node 01_枚举和高级类型.ts
// ============================================================

console.log("=== 1. 枚举 ===");

// 数字枚举
enum Direction {
    Up = 1,
    Down,      // 2
    Left,      // 3
    Right      // 4
}

console.log(`Direction.Up = ${Direction.Up}`);
console.log(`Direction.Down = ${Direction.Down}`);
console.log(`Direction[3] = ${Direction[3]}`);

// 字符串枚举
enum Message {
    Success = "操作成功",
    Error = "操作失败",
    Warning = "警告信息"
}

console.log(`Message.Success = ${Message.Success}`);
console.log(`Message.Error = ${Message.Error}`);

// 异构枚举
enum BooleanLikeHeterogeneousEnum {
    No = 0,
    Yes = "YES",
}

console.log(`BooleanLikeHeterogeneousEnum.No = ${BooleanLikeHeterogeneousEnum.No}`);
console.log(`BooleanLikeHeterogeneousEnum.Yes = ${BooleanLikeHeterogeneousEnum.Yes}`);

// 计算枚举成员
enum FileAccess {
    None,
    Read    = 1 << 1,    // 2
    Write   = 1 << 2,    // 4
    ReadWrite  = Read | Write,  // 6
}

console.log(`FileAccess.Read = ${FileAccess.Read}`);
console.log(`FileAccess.Write = ${FileAccess.Write}`);
console.log(`FileAccess.ReadWrite = ${FileAccess.ReadWrite}`);

// 常量枚举
const enum Directions {
    Up,
    Down,
    Left,
    Right
}

let directions = [Directions.Up, Directions.Down, Directions.Left, Directions.Right];
console.log(`Directions 数组: [${directions.join(', ')}]`);

// ============================================================
// 2. 联合类型
// ============================================================

console.log("\n=== 2. 联合类型 ===");

function padLeft(value: string, padding: string | number): string {
    if (typeof padding === "number") {
        return Array(padding + 1).join(" ") + value;
    }
    if (typeof padding === "string") {
        return padding + value;
    }
    return value;
}

console.log(`padLeft("hello", 4) = "${padLeft("hello", 4)}"`);
console.log(`padLeft("hello", ">> ") = "${padLeft("hello", ">> ")}"`);

// ============================================================
// 3. 类型守卫
// ============================================================

console.log("\n=== 3. 类型守卫 ===");

// 自定义类型守卫
function isNumber(x: any): x is number {
    return typeof x === "number";
}

function isString(x: any): x is string {
    return typeof x === "string";
}

let val: string | number = "hello";
if (isString(val)) {
    console.log(`类型守卫: 字符串长度=${val.length}`);
}

// typeof 类型守卫
function formatValue(value: string | number): string {
    if (typeof value === "number") {
        return value.toFixed(2);
    }
    return value.toUpperCase();
}
console.log(`格式化: ${formatValue(3.14159)}`);
console.log(`格式化: ${formatValue("hello")}`);

// instanceof 类型守卫
class Bird {
    fly() { console.log("鸟在飞"); }
}

class Fish {
    swim() { console.log("鱼在游"); }
}

function getPet(): Bird | Fish {
    return Math.random() > 0.5 ? new Bird() : new Fish();
}

let pet = getPet();
if (pet instanceof Bird) {
    pet.fly();
} else {
    pet.swim();
}

// ============================================================
// 4. 可辨识联合
// ============================================================

console.log("\n=== 4. 可辨识联合 ===");

interface Square {
    kind: "square";
    size: number;
}

interface Rectangle {
    kind: "rectangle";
    width: number;
    height: number;
}

interface Circle {
    kind: "circle";
    radius: number;
}

type Shape = Square | Rectangle | Circle;

function area(s: Shape): number {
    switch (s.kind) {
        case "square": return s.size * s.size;
        case "rectangle": return s.width * s.height;
        case "circle": return Math.PI * s.radius * s.radius;
    }
}

let circle: Circle = { kind: "circle", radius: 5 };
console.log(`圆形面积: ${area(circle).toFixed(2)}`);

let square: Square = { kind: "square", size: 4 };
console.log(`正方形面积: ${area(square)}`);

// ============================================================
// 5. 索引类型
// ============================================================

console.log("\n=== 5. 索引类型 ===");

function pluck<T, K extends keyof T>(o: T, names: K[]): T[K][] {
    return names.map(n => o[n]);
}

let person = { name: "张三", age: 25, city: "北京" };
let result = pluck(person, ["name", "city"]);
console.log(`索引类型: [${result.join(', ')}]`);

// keyof 操作符
type PersonKeys = keyof typeof person;
console.log(`Person 的键类型: "name" | "age" | "city"`);

// ============================================================
// 6. 映射类型
// ============================================================

console.log("\n=== 6. 映射类型 ===");

interface Person3 {
    name: string;
    age: number;
}

type ReadonlyPerson = Readonly<Person3>;
type PartialPerson = Partial<Person3>;

let rp: ReadonlyPerson = { name: "李四", age: 30 };
console.log(`Readonly 类型: ${rp.name}, ${rp.age} (不可修改)`);

let pp: PartialPerson = { name: "王五" };
console.log(`Partial 类型: ${pp.name} (age 可选)`);

// 自定义映射类型
type MyReadonly<T> = {
    readonly [P in keyof T]: T[P];
};

type MyPartial<T> = {
    [P in keyof T]?: T[P];
};

// ============================================================
// 7. 条件类型
// ============================================================

console.log("\n=== 7. 条件类型 ===");

type IsStringType<T> = T extends string ? "yes" : "no";
type A = IsStringType<string>;   // "yes"
type B = IsStringType<number>;   // "no"
console.log(`条件类型: IsStringType<string> = "yes", IsStringType<number> = "no"`);

// 分布式条件类型
type NonNullable<T> = T extends null | undefined ? never : T;
type StringOrNumber = NonNullable<string | number | null | undefined>;
let son: StringOrNumber = "hello";
console.log(`NonNullable: string | number`);

// 类型推断 infer
type ReturnType<T> = T extends (...args: any[]) => infer R ? R : any;
type FuncReturnType = ReturnType<() => string>;
let frt: FuncReturnType = "test";
console.log(`ReturnType: ${frt}`);

console.log("\n=== 运行完成 ===");
