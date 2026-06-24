// ============================================================
// TypeScript 基础语法 - 变量、类型、运算符
// 运行：ts-node 01_变量和数据类型.ts
// 或编译：tsc 01_变量和数据类型.ts && node 01_变量和数据类型.js
// ============================================================

console.log("=== 1. 基本类型 ===");

// 1. 布尔值
let isDone: boolean = false;
console.log(`布尔值: isDone = ${isDone}`);

// 2. 数字
let decimal: number = 6;
let hex: number = 0xf00d;
let binary: number = 0b1010;
let octal: number = 0o744;
console.log(`数字: decimal=${decimal}, hex=${hex}, binary=${binary}`);

// 3. 字符串
let color: string = "blue";
let fullName: string = `Bob Bobbington`;
let age: number = 37;
let sentence: string = `Hello, my name is ${fullName}. I'll be ${age + 1} years old.`;
console.log(`字符串: ${sentence}`);

// 4. 数组
let list1: number[] = [1, 2, 3];
let list2: Array<number> = [1, 2, 3];
console.log(`数组: [${list1.join(', ')}]`);

// 5. 元组 Tuple
let x: [string, number];
x = ["hello", 10];
console.log(`元组: [${x[0]}, ${x[1]}]`);

// 6. 枚举
enum Color {Red = 1, Green = 2, Blue = 4}
let c: Color = Color.Green;
console.log(`枚举: c = ${c}, Color[Green] = ${Color[2]}`);

// 7. Any 类型
let notSure: any = 4;
notSure = "maybe a string instead";
notSure = false;
console.log(`any: 可以随意改变类型`);

// 8. Void 类型
function warnUser(): void {
    console.log("这是一个没有返回值的函数");
}
warnUser();

// 9. Null 和 Undefined
let u: undefined = undefined;
let n: null = null;
console.log(`undefined = ${u}, null = ${n}`);

// 10. Never 类型
function error(message: string): never {
    throw new Error(message);
}
try {
    error("测试 Never 类型");
} catch (e: any) {
    console.log(`Never 类型: 抛出异常 - ${e.message}`);
}

// 11. Object 类型
declare function create(o: object | null): void;
console.log("Object 类型: 可以传入对象");

// ============================================================
// 类型断言
// ============================================================

console.log("\n=== 2. 类型断言 ===");

let someValue: any = "this is a string";

// 方式一：尖括号语法
let strLength1: number = (<string>someValue).length;

// 方式二：as 语法（JSX 中只能用这个）
let strLength2: number = (someValue as string).length;

console.log(`字符串长度: ${strLength1}`);

// ============================================================
// 变量声明
// ============================================================

console.log("\n=== 3. 变量声明 (let/const/var) ===");

var a = 10;          // var: 函数作用域，可重复声明
let b = 20;          // let: 块级作用域，不可重复声明
const PI = 3.14159;  // const: 常量，必须初始化
console.log(`var a=${a}, let b=${b}, const PI=${PI}`);

// const 对象的属性可以修改
const person = { name: "张三", age: 25 };
person.age = 26;
console.log(`const 对象属性可修改: age=${person.age}`);

// ============================================================
// 解构赋值
// ============================================================

console.log("\n=== 4. 解构赋值 ===");

// 数组解构
let [first, second] = [1, 2];
console.log(`数组解构: first=${first}, second=${second}`);

// 对象解构
let { name, age: personAge } = { name: "李四", age: 30 };
console.log(`对象解构: name=${name}, age=${personAge}`);

// 展开运算符
let arr1 = [1, 2, 3];
let arr2 = [...arr1, 4, 5, 6];
console.log(`展开运算符: [${arr2.join(', ')}]`);

let obj1 = { a: 1, b: 2 };
let obj2 = { ...obj1, c: 3 };
console.log(`对象展开: a=${obj2.a}, b=${obj2.b}, c=${obj2.c}`);

// ============================================================
// 类型别名
// ============================================================

console.log("\n=== 5. 类型别名 ===");

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

// ============================================================
// 字符串字面量类型
// ============================================================

console.log("\n=== 6. 字面量类型 ===");

type Direction = "north" | "south" | "east" | "west";

function move(direction: Direction): string {
    return `向 ${direction} 移动`;
}

console.log(move("north"));
console.log(move("west"));

// 数字字面量
type DiceValue = 1 | 2 | 3 | 4 | 5 | 6;
let roll: DiceValue = 4;
console.log(`骰子点数: ${roll}`);

console.log("\n=== 运行完成 ===");