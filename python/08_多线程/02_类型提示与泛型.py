# ============================================================
# Python 类型提示与泛型 (PEP 484, 585)
# ============================================================

from typing import (
    TypeVar, Generic, List, Dict, Tuple, Set, Optional, Union,
    Callable, Iterator, Iterable, Any, Generic, Protocol,
    NewType, Final, Literal
)
import sys
from abc import ABC, abstractmethod

print("=== 1. 基本类型提示 ===")


def greet(name: str) -> str:
    return f"你好, {name}"


def add(a: int, b: int) -> int:
    return a + b


def process(data: list, max_size: int = 100) -> dict:
    return {"data": data, "size": len(data)}


print(greet("张三"))
print(f"3 + 5 = {add(3, 5)}")
print(process([1, 2, 3]))


print("\n=== 2. Optional 和 Union ===")


def find_user(user_id: int) -> Optional[str]:
    """可能返回 None"""
    if user_id > 0:
        return f"用户{user_id}"
    return None


def parse_value(value: Union[int, str]) -> int:
    """参数可以是 int 或 str"""
    if isinstance(value, int):
        return value
    return int(value)


print(find_user(1))
print(find_user(-1))
print(parse_value(42))
print(parse_value("42"))


print("\n=== 3. List, Dict 等容器 ===")


def process_items(items: List[int]) -> Dict[str, int]:
    result = {}
    for item in items:
        result[f"item_{item}"] = item * 2
    return result


def get_info() -> Tuple[str, int, bool]:
    return "张三", 25, True


def get_friends() -> Set[str]:
    return {"Alice", "Bob", "Charlie"}


print(process_items([1, 2, 3]))
name, age, active = get_info()
print(f"姓名: {name}, 年龄: {age}, 激活: {active}")
print(f"朋友: {get_friends()}")


print("\n=== 4. Callable 函数类型 ===")


def apply_func(x: int, func: Callable[[int], int]) -> int:
    return func(x)


def double(x: int) -> int:
    return x * 2


print(apply_func(5, double))
print(apply_func(5, lambda x: x * x))


print("\n=== 5. TypeVar 泛型变量 ===")

T = TypeVar('T')  # 通用类型变量
K = TypeVar('K')
V = TypeVar('V')


def first(items: List[T]) -> Optional[T]:
    """返回列表的第一个元素"""
    return items[0] if items else None


def get_value(d: Dict[K, V], key: K) -> Optional[V]:
    return d.get(key)


print(f"first([1,2,3]) = {first([1, 2, 3])}")
print(f"first(['a','b']) = {first(['a', 'b'])}")
print(f"first([]) = {first([])}")

scores = {"张三": 95, "李四": 88}
print(f"李四的分数: {get_value(scores, '李四')}")


print("\n=== 6. Generic 泛型类 ===")


class Stack(Generic[T]):
    """泛型栈"""
    def __init__(self):
        self._items: List[T] = []

    def push(self, item: T) -> None:
        self._items.append(item)

    def pop(self) -> T:
        return self._items.pop()

    def is_empty(self) -> bool:
        return len(self._items) == 0

    def size(self) -> int:
        return len(self._items)


# 整数栈
int_stack: Stack[int] = Stack()
int_stack.push(1)
int_stack.push(2)
int_stack.push(3)
print(f"整数栈大小: {int_stack.size()}")
print(f"弹出: {int_stack.pop()}")

# 字符串栈
str_stack: Stack[str] = Stack()
str_stack.push("A")
str_stack.push("B")
print(f"字符串栈大小: {str_stack.size()}")


class Pair(Generic[K, V]):
    """键值对"""
    def __init__(self, key: K, value: V):
        self.key = key
        self.value = value

    def get_key(self) -> K:
        return self.key

    def get_value(self) -> V:
        return self.value

    def __repr__(self) -> str:
        return f"Pair({self.key}, {self.value})"


p1: Pair[str, int] = Pair("age", 25)
p2: Pair[int, str] = Pair(1, "Hello")
print(f"p1: {p1}")
print(f"p2: {p2}")


print("\n=== 7. 泛型约束 ===")

# 必须是数字类型
Number = TypeVar('Number', int, float, complex)


def add_numbers(a: Number, b: Number) -> Number:
    return a + b


print(f"add(3, 5) = {add_numbers(3, 5)}")
print(f"add(1.5, 2.5) = {add_numbers(1.5, 2.5)}")


# 约束必须可比较
Comparable = TypeVar('Comparable', bound='Comparable')


def maximum(a: Comparable, b: Comparable) -> Comparable:
    return a if a > b else b


print(f"max(10, 20) = {maximum(10, 20)}")
print(f"max('a', 'z') = {maximum('a', 'z')}")


print("\n=== 8. Protocol 协议 ===")


class Drawable(Protocol):
    """任何实现了 draw 方法的类都满足该协议"""
    def draw(self) -> None: ...


class Circle:
    def draw(self) -> None:
        print("绘制圆形")


class Square:
    def draw(self) -> None:
        print("绘制方形")


def render(shape: Drawable) -> None:
    shape.draw()


# 不需要显式继承 Protocol，只要有 draw 方法即可
render(Circle())
render(Square())


print("\n=== 9. 抽象泛型类 ===")


class Repository(Generic[T], ABC):
    """抽象仓库"""
    @abstractmethod
    def add(self, item: T) -> None:
        pass

    @abstractmethod
    def get(self, id: int) -> Optional[T]:
        pass

    @abstractmethod
    def all(self) -> List[T]:
        pass


class UserRepository(Repository):
    def __init__(self):
        self.users: Dict[int, str] = {}

    def add(self, item: str) -> None:
        self.users[len(self.users) + 1] = item

    def get(self, id: int) -> Optional[str]:
        return self.users.get(id)

    def all(self) -> List[str]:
        return list(self.users.values())


repo: Repository[str] = UserRepository()
repo.add("张三")
repo.add("李四")
print(f"用户列表: {repo.all()}")


print("\n=== 10. 高级类型 ===")


# Final - 常量
MAX_SIZE: Final = 100


# Literal - 字面量类型
def set_mode(mode: Literal["read", "write", "append"]) -> None:
    print(f"模式: {mode}")


set_mode("read")


# NewType - 创建新类型
UserId = NewType('UserId', int)
UserName = NewType('UserName', str)


def get_user(user_id: UserId) -> UserName:
    return UserName(f"用户{user_id}")


uid = UserId(42)
name = get_user(uid)
print(f"ID: {uid}, 名称: {name}")


print("\n=== 11. 类型别名 ===")


# Python 3.12+ 用 type 关键字创建类型别名
# Python 3.10+ 用 | 替代 Union
# Python 3.9+ 内置集合支持泛型

# Python 3.10+
def process_value(value: int | str | None) -> str:
    if value is None:
        return "空"
    return str(value)


print(process_value(42))
print(process_value("hello"))
print(process_value(None))


print("\n=== 12. 泛型数据结构 ===")


class Queue(Generic[T]):
    """泛型队列"""
    def __init__(self):
        self._items: List[T] = []

    def enqueue(self, item: T) -> None:
        self._items.append(item)

    def dequeue(self) -> Optional[T]:
        if not self._items:
            return None
        return self._items.pop(0)

    def peek(self) -> Optional[T]:
        return self._items[0] if self._items else None

    def is_empty(self) -> bool:
        return len(self._items) == 0

    def size(self) -> int:
        return len(self._items)


q: Queue[str] = Queue()
q.enqueue("第一")
q.enqueue("第二")
q.enqueue("第三")
print(f"队首: {q.peek()}")
print(f"出队: {q.dequeue()}")
print(f"剩余: {q.size()}")


class LinkedList(Generic[T]):
    """泛型链表"""
    class Node:
        def __init__(self, data: T):
            self.data = data
            self.next: Optional['LinkedList.Node'] = None

    def __init__(self):
        self.head: Optional[LinkedList.Node] = None
        self.size_count = 0

    def append(self, data: T) -> None:
        new_node = LinkedList.Node(data)
        if self.head is None:
            self.head = new_node
        else:
            current = self.head
            while current.next:
                current = current.next
            current.next = new_node
        self.size_count += 1

    def __iter__(self) -> Iterator[T]:
        current = self.head
        while current:
            yield current.data
            current = current.next

    def __len__(self) -> int:
        return self.size_count


ll: LinkedList[int] = LinkedList()
for i in [10, 20, 30, 40]:
    ll.append(i)

print(f"链表长度: {len(ll)}")
print(f"链表元素: {list(ll)}")


print("\n=== 13. TypeGuard 类型守卫 ===")

from typing import TypeGuard


def is_string_list(val: List[Any]) -> TypeGuard[List[str]]:
    return all(isinstance(x, str) for x in val)


def process(items: List[Any]) -> None:
    if is_string_list(items):
        # items 类型收窄为 List[str]
        for s in items:
            print(f"  字符串: {s.upper()}")


process(["hello", "world"])
process([1, 2, 3])


print("\n=== 运行完成 ===")