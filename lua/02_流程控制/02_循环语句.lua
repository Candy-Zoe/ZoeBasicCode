-- ============================================================
-- Lua 流程控制 - 循环语句 (while, repeat, for)
-- 运行：lua 02_循环语句.lua
-- ============================================================

print("=== 循环语句 ===")

-- while 循环
print("while 循环:")
i = 1
while i <= 3 do
    print("  " .. i)
    i = i + 1
end

-- repeat ... until 循环（类似 do-while，至少执行一次）
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

-- for 泛型循环 - ipairs 遍历数组
print("ipairs 遍历数组:")
for i, v in ipairs({10, 20, 30}) do
    print("  " .. i .. ": " .. v)
end

-- for 泛型循环 - pairs 遍历表
print("pairs 遍历表:")
for k, v in pairs({a=1, b=2, c=3}) do
    print("  " .. k .. " = " .. v)
end

-- break 跳出循环
print("break 示例:")
for i = 1, 10 do
    if i > 3 then
        break
    end
    print("  " .. i)
end

print("\n=== 运行完成 ===")
