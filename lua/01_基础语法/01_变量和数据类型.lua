-- ============================================================
-- Lua 基础语法 - 变量和数据类型
-- 运行：lua 01_变量和数据类型.lua
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

print("\n=== 运行完成 ===")
