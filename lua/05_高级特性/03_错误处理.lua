-- ============================================================
-- Lua 高级特性 - 错误处理
-- 运行：lua 03_错误处理.lua
-- ============================================================

print("=== 错误处理 ===")

-- error 函数抛出错误
function divide(a, b)
    if b == 0 then
        error("除数不能为零")
    end
    return a / b
end

-- pcall 保护调用（捕获错误）
print("pcall 保护调用:")
ok, result = pcall(divide, 10, 0)
if ok then
    print("  成功: " .. result)
else
    print("  错误: " .. result)
end

ok, result = pcall(divide, 10, 2)
if ok then
    print("  成功: " .. result)
end

-- xpcall 带错误处理函数
print("\nxpcall 带错误处理:")
function errorHandler(err)
    return "捕获到错误: " .. err
end

ok, result = xpcall(function() return divide(5, 0) end, errorHandler)
print("  " .. result)

-- assert 断言
print("\nassert 断言:")
local n = 5
assert(n > 0, "n 必须大于 0")
print("  assert 通过: n = " .. n)

-- assert 在失败时会抛出错误
print("  assert 失败测试 (被 pcall 捕获):")
local ok2, err = pcall(function()
    assert(n > 10, "n 必须大于 10")
end)
if not ok2 then
    print("    " .. err)
end

print("\n=== 运行完成 ===")
