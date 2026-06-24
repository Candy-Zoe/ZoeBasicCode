-- ============================================================
-- Lua 基础语法 - 运算符
-- 运行：lua 02_运算符.lua
-- ============================================================

print("=== 运算符 ===")

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

print("\n=== 运行完成 ===")
