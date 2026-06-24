-- ============================================================
-- Lua 流程控制 - 条件语句 (if, elseif)
-- 运行：lua 01_条件语句.lua
-- ============================================================

print("=== 条件语句 ===")

-- if-elseif-else 语句
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

-- 逻辑运算符与条件结合
local is_student = true
local has_id = false
if is_student and has_id then
    print("可以进入图书馆")
elseif is_student or has_id then
    print("需要登记后进入")
else
    print("不能进入")
end

print("\n=== 运行完成 ===")
