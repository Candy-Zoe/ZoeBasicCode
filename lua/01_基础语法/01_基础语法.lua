-- ============================================================
-- Lua 基础语法
-- 运行：lua 01_基础语法.lua
-- ============================================================

print("=== 1. 变量和数据类型 ===")

-- 变量
name = "张三"
age = 25
print("姓名: " .. name .. ", 年龄: " .. age)

-- 局部变量
local x = 10
local y = 20
print("局部变量: x=" .. x .. ", y=" .. y)

-- 数据类型
print(nil)        -- nil 空
print(type("Hello"))     -- string
print(type(123))         -- number
print(type(true))        -- boolean
print(type({}))          -- table
print(type(function() end))  -- function

-- 字符串
str = "Hello World"
print("字符串长度: " .. #str)
print("大写: " .. string.upper(str))
print("小写: " .. string.lower(str))
print("子串: " .. string.sub(str, 1, 5))

-- 字符串格式化
print(string.format("姓名: %s, 年龄: %d", name, age))

-- 多行字符串
multi = [[
第一行
第二行
第三行
]]
print("多行字符串:")
print(multi)

-- ============================================================
-- 2. 运算符
-- ============================================================

print("\n=== 2. 运算符 ===")

a = 10
b = 3

print("a + b = " .. (a + b))
print("a - b = " .. (a - b))
print("a * b = " .. (a * b))
print("a / b = " .. (a / b))
print("a % b = " .. (a % b))
print("a ^ b = " .. (a ^ b))  -- 幂运算

-- 关系运算符
print("a > b: " .. tostring(a > b))
print("a == b: " .. tostring(a == b))
print("a ~= b: " .. tostring(a ~= b))

-- 逻辑运算符
print("true and false: " .. tostring(true and false))
print("true or false: " .. tostring(true or false))
print("not true: " .. tostring(not true))

-- 长度运算符
arr = {1, 2, 3, 4, 5}
print("数组长度: " .. #arr)

-- ============================================================
-- 3. 表 (Table)
-- ============================================================

print("\n=== 3. 表 Table ===")

-- 数组（数字索引）
fruits = {"苹果", "香蕉", "橙子"}
print("第一个水果: " .. fruits[1])  -- Lua 索引从 1 开始！
print("数组长度: " .. #fruits)

-- 遍历数组
print("遍历数组:")
for i, v in ipairs(fruits) do
    print("  " .. i .. ". " .. v)
end

-- 字典（字符串索引）
person = {
    name = "张三",
    age = 25,
    city = "北京"
}
print("姓名: " .. person.name)
print("年龄: " .. person["age"])

-- 遍历字典
print("遍历字典:")
for k, v in pairs(person) do
    print("  " .. k .. ": " .. tostring(v))
end

-- 混合表
mixed = {
    10,
    20,
    name = "test",
    value = 100
}
print("混合表 [1]: " .. mixed[1])
print("混合表 name: " .. mixed.name)

-- 表的操作
table.insert(fruits, "葡萄")   -- 添加元素
table.remove(fruits, 1)        -- 删除元素
table.sort(fruits)             -- 排序
print("操作后: " .. table.concat(fruits, ", "))

-- 多维表
matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
}
print("矩阵 [2][3]: " .. matrix[2][3])

-- ============================================================
-- 4. 函数
-- ============================================================

print("\n=== 4. 函数 ===")

-- 基本函数
function add(a, b)
    return a + b
end
print("add(5, 3) = " .. add(5, 3))

-- 多返回值
function divide(a, b)
    return a / b, a % b
end
q, r = divide(10, 3)
print("10 / 3 = " .. q .. " 余 " .. r)

-- 可变参数
function sum(...)
    local total = 0
    local args = {...}
    for i = 1, #args do
        total = total + args[i]
    end
    return total
end
print("sum(1, 2, 3, 4, 5) = " .. sum(1, 2, 3, 4, 5))

-- 匿名函数
local double = function(x)
    return x * 2
end
print("double(5) = " .. double(5))

-- 高阶函数 - 函数作为参数
function apply(arr, func)
    local result = {}
    for i = 1, #arr do
        result[i] = func(arr[i])
    end
    return result
end

nums = {1, 2, 3}
doubled = apply(nums, function(x) return x * 2 end)
print("映射后: " .. table.concat(doubled, ", "))

-- 闭包
function counter()
    local count = 0
    return function()
        count = count + 1
        return count
    end
end

c = counter()
print("计数器 1: " .. c())
print("计数器 2: " .. c())
print("计数器 3: " .. c())

-- ============================================================
-- 5. 控制结构
-- ============================================================

print("\n=== 5. 控制结构 ===")

-- if 语句
score = 85
if score >= 90 then
    print("优秀")
elseif score >= 80 then
    print("良好")
elseif score >= 60 then
    print("及格")
else
    print("不及格")
end

-- 嵌套 if
age = 20
if age >= 18 then
    if age >= 60 then
        print("老年")
    else
        print("成年")
    end
else
    print("未成年")
end

-- while 循环
print("while 循环:")
i = 1
while i <= 3 do
    print("  " .. i)
    i = i + 1
end

-- repeat ... until 循环（类似 do-while）
print("repeat 循环:")
i = 1
repeat
    print("  " .. i)
    i = i + 1
until i > 3

-- for 数值循环
print("for 数值循环:")
for i = 1, 3 do
    print("  " .. i)
end

print("for 步长为 2:")
for i = 1, 5, 2 do
    print("  " .. i)
end

-- for 泛型循环
print("ipairs 遍历数组:")
for i, v in ipairs({10, 20, 30}) do
    print("  " .. i .. ": " .. v)
end

print("pairs 遍历表:")
for k, v in pairs({a=1, b=2, c=3}) do
    print("  " .. k .. " = " .. v)
end

-- break
print("break 示例:")
for i = 1, 10 do
    if i > 3 then
        break
    end
    print("  " .. i)
end

-- ============================================================
-- 6. 模块和包
-- ============================================================

print("\n=== 6. 模块和包 ===")

-- 创建模块
mymodule = {}
mymodule.version = "1.0"
function mymodule.hello()
    print("Hello from mymodule!")
end

mymodule.hello()
print("版本: " .. mymodule.version)

-- 标准库
print("math 库:")
print("  math.pi = " .. math.pi)
print("  math.abs(-5) = " .. math.abs(-5))
print("  math.sqrt(9) = " .. math.sqrt(9))
print("  math.floor(3.7) = " .. math.floor(3.7))
print("  math.ceil(3.1) = " .. math.ceil(3.1))
print("  math.random(1, 10) = " .. math.random(1, 10))

print("table 库:")
t = {3, 1, 2}
table.sort(t)
print("  排序: " .. table.concat(t, ", "))
table.insert(t, 4)
print("  插入后: " .. table.concat(t, ", "))

print("string 库:")
s = "Hello, World!"
print("  长度: " .. #s)
print("  大写: " .. string.upper(s))
print("  查找: " .. string.find(s, "World"))

-- os 库
print("os 库:")
print("  当前时间戳: " .. os.time())
print("  日期: " .. os.date("%Y-%m-%d %H:%M:%S"))

-- io 库
print("io 库:")
-- io.write("请输入: ")
-- local input = io.read()
-- print("你输入了: " .. input)

-- ============================================================
-- 7. 元表和面向对象
-- ============================================================

print("\n=== 7. 元表和面向对象 ===")

-- 元表示例
t1 = {1, 2, 3}
t2 = {4, 5, 6}

mt = {
    __add = function(a, b)
        local result = {}
        for i = 1, #a do
            result[i] = a[i] + b[i]
        end
        return result
    end
}

setmetatable(t1, mt)
setmetatable(t2, mt)

t3 = t1 + t2
print("表相加: " .. table.concat(t3, ", "))

-- 面向对象 - 原型模式
Account = { balance = 0 }

function Account:new(o)
    o = o or {}
    setmetatable(o, self)
    self.__index = self
    return o
end

function Account:deposit(v)
    self.balance = self.balance + v
end

function Account:withdraw(v)
    if v <= self.balance then
        self.balance = self.balance - v
    end
end

a = Account:new{balance = 100}
a:deposit(50)
print("存款后余额: " .. a.balance)
a:withdraw(30)
print("取款后余额: " .. a.balance)

-- 继承
SpecialAccount = Account:new()
function SpecialAccount:withdraw(v)
    if v - self.balance >= 0 then
        self.balance = self.balance - v
    end
end

s = SpecialAccount:new{balance=100}
s:withdraw(200)  -- 可以透支
print("特殊账户余额: " .. s.balance)

-- ============================================================
-- 8. 错误处理
-- ============================================================

print("\n=== 8. 错误处理 ===")

-- error 函数
function divide(a, b)
    if b == 0 then
        error("除数不能为零")
    end
    return a / b
end

-- pcall 保护调用
ok, result = pcall(divide, 10, 0)
if ok then
    print("成功: " .. result)
else
    print("错误: " .. result)
end

ok, result = pcall(divide, 10, 2)
if ok then
    print("成功: " .. result)
end

-- xpcall 带错误处理
function errorHandler(err)
    return "捕获到错误: " .. err
end

ok, result = xpcall(function() return divide(5, 0) end, errorHandler)
print(result)

-- assert
print("assert 测试:")
local n = 5
assert(n > 0, "n 必须大于 0")
print("assert 通过")

print("\n=== 运行完成 ===")