// ============================================================
// TypeScript 高级特性 - 枚举、命名空间、模块、装饰器
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
// 2. 命名空间
// ============================================================

console.log("\n=== 2. 命名空间 ===");

namespace Validation {
    export interface StringValidator {
        isAcceptable(s: string): boolean;
    }

    const lettersRegexp = /^[A-Za-z]+$/;
    const numberRegexp = /^[0-9]+$/;

    export class LettersOnlyValidator implements StringValidator {
        isAcceptable(s: string): boolean {
            return lettersRegexp.test(s);
        }
    }

    export class ZipCodeValidator implements StringValidator {
        isAcceptable(s: string): boolean {
            return s.length === 5 && numberRegexp.test(s);
        }
    }
}

let strings = ["Hello", "98052", "101"];
let validators: { [s: string]: Validation.StringValidator } = {};
validators["ZIP code"] = new Validation.ZipCodeValidator();
validators["Letters only"] = new Validation.LettersOnlyValidator();

for (let s of strings) {
    for (let name in validators) {
        let isMatch = validators[name].isAcceptable(s);
        console.log(`"${s}" ${isMatch ? "匹配" : "不匹配"} ${name}`);
    }
}

// 命名空间别名
namespace Shapes {
    export namespace Polygons {
        export class Triangle { }
        export class Square { }
    }
}

import polygons = Shapes.Polygons;
console.log("\n命名空间别名: 可以用 polygons 访问 Shapes.Polygons");

// ============================================================
// 3. 模块导入导出
// ============================================================

console.log("\n=== 3. 模块 ===");

// 导出接口和类（此处为内联示例，实际项目中用独立文件）
// export interface StringValidator { ... }
// export class ZipCodeValidator implements StringValidator { ... }

// 导入
// import { ZipCodeValidator } from "./ZipCodeValidator";

// 默认导出
// export default class ...
// import MyClass from "./MyClass";

// 重命名导出
// export { ZipCodeValidator as ZCV };

// 命名空间导入
// import * as validator from "./ZipCodeValidator";

console.log("模块使用: export 导出, import 导入");
console.log("默认导出: export default");
console.log("命名空间导入: import * as name from 'module'");

// ============================================================
// 4. 装饰器
// ============================================================

console.log("\n=== 4. 装饰器 ===");

// 类装饰器
function sealed(constructor: Function) {
    Object.seal(constructor);
    Object.seal(constructor.prototype);
    console.log("类被密封了");
}

@sealed
class Greeter {
    greeting: string;
    constructor(message: string) {
        this.greeting = message;
    }
    greet() {
        return "Hello, " + this.greeting;
    }
}

let greeter = new Greeter("world");
console.log(greeter.greet());

// 方法装饰器
function enumerable(value: boolean) {
    return function (target: any, propertyKey: string, descriptor: PropertyDescriptor) {
        descriptor.enumerable = value;
    };
}

class MyClass {
    private _value: number = 0;

    @enumerable(false)
    getValue(): number {
        return this._value;
    }

    setValue(v: number): void {
        this._value = v;
    }
}

let obj = new MyClass();
console.log("方法装饰器: @enumerable 设置属性的可枚举性");

// 属性装饰器
function format(formatString: string) {
    return function (target: any, propertyKey: string): void {
        console.log(`属性 ${propertyKey} 使用了格式: ${formatString}`);
    };
}

class User {
    @format("YYYY-MM-DD")
    birthday: string = "2000-01-01";
}

console.log("属性装饰器: @format 标记属性");

// 参数装饰器
function required(target: Object, propertyKey: string | symbol, parameterIndex: number) {
    console.log(`参数 ${parameterIndex} 是必填的`);
}

class SomeClass {
    someMethod(@required param1: string, param2: number) {
    }
}

// ============================================================
// 5. 高级类型
// ============================================================

console.log("\n=== 5. 高级类型 ===");

// 交叉类型
interface PersonInfo {
    name: string;
    age: number;
}

interface EmployeeInfo {
    company: string;
    salary: number;
}

type PersonEmployee = PersonInfo & EmployeeInfo;

let pe: PersonEmployee = {
    name: "张三",
    age: 25,
    company: "Google",
    salary: 100000
};
console.log(`交叉类型: ${pe.name} @ ${pe.company}`);

// 联合类型
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

// 类型守卫
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

// 可辨识联合
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

// 索引类型
function pluck<T, K extends keyof T>(o: T, names: K[]): T[K][] {
    return names.map(n => o[n]);
}

let person2 = { name: "张三", age: 25, city: "北京" };
let result = pluck(person2, ["name", "city"]);
console.log(`索引类型: [${result.join(', ')}]`);

// 映射类型
interface Person3 {
    name: string;
    age: number;
}

type ReadonlyPerson = Readonly<Person3>;
type PartialPerson = Partial<Person3>;

let rp: ReadonlyPerson = { name: "李四", age: 30 };
console.log(`Readonly 类型: ${rp.name}, ${rp.age} (不可修改)`);

// 条件类型
type IsString<T> = T extends string ? "yes" : "no";
type A = IsString<string>;   // "yes"
type B = IsString<number>;   // "no"
console.log(`条件类型: IsString<string> = "yes", IsString<number> = "no"`);

console.log("\n=== 运行完成 ===");