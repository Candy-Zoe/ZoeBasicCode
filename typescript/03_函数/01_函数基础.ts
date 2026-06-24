// ============================================================
// TypeScript 函数 - 函数基础
// 运行：ts-node 01_函数基础.ts
// ============================================================

console.log("=== 1. 函数定义 ===");

// 函数声明
function add(x: number, y: number): number {
    return x + y;
}
console.log(`add(1, 2) = ${add(1, 2)}`);

// 函数表达式
let myAdd = function (x: number, y: number): number {
    return x + y;
};
console.log(`myAdd(3, 4) = ${myAdd(3, 4)}`);

// 函数类型
let myAdd2: (x: number, y: number) => number = function (x: number, y: number): number {
    return x + y;
};
console.log(`myAdd2(5, 6) = ${myAdd2(5, 6)}`);

// ============================================================
// 2. 可选参数和默认参数
// ============================================================

console.log("\n=== 2. 可选参数和默认参数 ===");

// 可选参数
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
console.log(`buildName2("Bob", "Jones") = ${buildName2("Bob", "Jones")}`);

// ============================================================
// 3. 剩余参数
// ============================================================

console.log("\n=== 3. 剩余参数 ===");

function buildFullName(firstName: string, ...restOfName: string[]): string {
    return firstName + " " + restOfName.join(" ");
}
console.log(`全名: ${buildFullName("Joseph", "Samuel", "Lucas", "MacKinzie")}`);

// 求和函数
function sum(...numbers: number[]): number {
    return numbers.reduce((acc, val) => acc + val, 0);
}
console.log(`sum(1, 2, 3, 4, 5) = ${sum(1, 2, 3, 4, 5)}`);

// ============================================================
// 4. 函数重载
// ============================================================

console.log("\n=== 4. 函数重载 ===");

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
// 5. this 指向
// ============================================================

console.log("\n=== 5. this 指向 ===");

let deck = {
    suits: ["红桃", "方块", "梅花", "黑桃"],
    cards: Array(52),
    createCardPicker: function () {
        return () => {
            let pickedCard = Math.floor(Math.random() * 52);
            let pickedSuit = Math.floor(pickedCard / 13);
            return { suit: this.suits[pickedSuit], card: pickedCard % 13 + 1 };
        };
    }
};

let cardPicker = deck.createCardPicker();
let pickedCard = cardPicker();
console.log(`抽到的牌: ${pickedCard.suit} ${pickedCard.card}`);

// ============================================================
// 6. 回调函数和高阶函数
// ============================================================

console.log("\n=== 6. 回调函数和高阶函数 ===");

// 高阶函数：接受函数作为参数
function applyOperation(a: number, b: number, op: (x: number, y: number) => number): number {
    return op(a, b);
}

let result1 = applyOperation(10, 5, (x, y) => x + y);
let result2 = applyOperation(10, 5, (x, y) => x * y);
console.log(`10 + 5 = ${result1}`);
console.log(`10 × 5 = ${result2}`);

// 返回函数的函数
function multiplyBy(factor: number): (x: number) => number {
    return function (x: number): number {
        return x * factor;
    };
}

let double = multiplyBy(2);
let triple = multiplyBy(3);
console.log(`double(5) = ${double(5)}`);
console.log(`triple(5) = ${triple(5)}`);

console.log("\n=== 运行完成 ===");
